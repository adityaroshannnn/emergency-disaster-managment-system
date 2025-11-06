package com.edms.servlet;


import com.edms.dao.PostDAO;
import com.edms.model.Post;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/post")
public class PostServlet extends HttpServlet {
    private PostDAO postDAO = new PostDAO();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "list";
        }
        
        switch (action) {
            case "list":
                listPosts(request, response);
                break;
            case "my-posts":
                myPosts(request, response);
                break;
            case "delete":
                deletePost(request, response);
                break;
            default:
                listPosts(request, response);
                break;
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String postType = request.getParameter("postType");
        String location = request.getParameter("location");
        String priority = request.getParameter("priority");
        
        Post post = new Post(userId, title, content, postType);
        post.setLocation(location);
        post.setPriority(priority);
        
        boolean isCreated = postDAO.createPost(post);
        
        if (isCreated) {
            request.setAttribute("success", "Post created successfully!");
            response.sendRedirect("social.jsp");
        } else {
            request.setAttribute("error", "Failed to create post");
            request.getRequestDispatcher("add-post.jsp").forward(request, response);
        }
    }
    
    private void listPosts(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Post> posts = postDAO.getAllPosts();
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("social.jsp").forward(request, response);
    }
    
    private void myPosts(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId != null) {
            List<Post> posts = postDAO.getPostsByUserId(userId);
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("my-posts.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
    
    private void deletePost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId != null) {
            int postId = Integer.parseInt(request.getParameter("id"));
            postDAO.deletePost(postId, userId);
        }
        
        response.sendRedirect("post?action=my-posts");
    }
}

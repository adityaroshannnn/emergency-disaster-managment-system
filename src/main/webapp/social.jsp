<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.edms.model.*"%>
<%@ page import="com.edms.dao.*"%>
<%
// Session guard
if (session.getAttribute("userId") == null) {
response.sendRedirect("login.jsp");
return;
}
// Data load
PostDAO postDAO = new PostDAO();
List<Post> posts = postDAO.getAllPosts();
%>
<div class="container">
    <div class="social-page">
        <div class="page-header">
            <h1>📢 Community Updates</h1>
            <a href="add-post.jsp" class="btn-primary">+ New Post</a>
        </div>

        <div class="posts-feed">
            <%
                if (posts != null && !posts.isEmpty()) {
                    for (Post post : posts) {
            %>
                <div class="post-card priority-<%= post.getPriority() %>">
                    <div class="post-header">
                        <div class="post-author">
                            <h4><%= post.getUserFullName() != null ? post.getUserFullName() : "User" %></h4>
                            <p class="post-meta">
                                @<%= post.getUsername() != null ? post.getUsername() : "-" %>
                                -  <%= post.getCreatedAt() %>
                            </p>
                        </div>
                        <div class="post-badges">
                            <span class="badge type-<%= post.getPostType() %>"><%= post.getPostType() %></span>
                            <span class="badge priority-<%= post.getPriority() %>"><%= post.getPriority() %></span>
                        </div>
                    </div>

                    <div class="post-content">
                        <h3><%= post.getTitle() %></h3>
                        <p><%= post.getContent() %></p>
                        <%
                            String pLoc = post.getLocation();
                            if (pLoc != null && !pLoc.isEmpty()) {
                        %>
                            <p class="post-location">📍 <%= pLoc %></p>
                        <%
                            }
                        %>
                    </div>
                    <div class="post-footer">
                        <span class="post-status"><%= post.getStatus() %></span>
                    </div>
                </div>
            <%
                    } // end for
                } else {
            %>
                <p class="no-posts">No posts yet. Be the first to post an update!</p>
            <%
                } // end if
            %>
        </div>
    </div>
</div>
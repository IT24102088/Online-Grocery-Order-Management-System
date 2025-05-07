<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp"%>
<%@ page import="com.example.onlinegroceryordermanagementsystem.users" %>
<html>
<head>
    <title>User Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .profile-card {
            max-width: 600px;
            margin: 30px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .role-badge {
            font-size: 0.8rem;
            padding: 5px 10px;
            border-radius: 20px;
        }
        .owner-badge {
            background-color: #ffc107;
            color: #000;
        }
        .admin-badge {
            background-color: #0d6efd;
            color: #fff;
        }
        .user-badge {
            background-color: #198754;
            color: #fff;
        }
    </style>
</head>
<body>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setHeader("Expires","0");

    if(session.getAttribute("username") == null){
        response.sendRedirect("index.jsp");
    }

    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");
    String fullName = (String) session.getAttribute("fullName");

    // Determine role display
    String roleDisplay = "Normal User";
    String roleClass = "user-badge";

    if("owner".equalsIgnoreCase(role)) {
        roleDisplay = "Owner (Root Account)";
        roleClass = "owner-badge";
    } else if("admin".equalsIgnoreCase(role)) {
        roleDisplay = "Administrator";
        roleClass = "admin-badge";
    }
%>
<jsp:include page="navbar.jsp" />

<div class="container mt-5">
    <div class="profile-card bg-white">
        <div class="text-center mb-4">
            <h2>User Profile</h2>
            <span class="role-badge <%= roleClass %>"><%= roleDisplay %></span>
        </div>

        <div class="mb-4">
            <h4>Account Information</h4>
            <hr>
            <div class="row mb-3">
                <div class="col-md-4 fw-bold">Username:</div>
                <div class="col-md-8"><%= username %></div>
            </div>
            <div class="row mb-3">
                <div class="col-md-4 fw-bold">Full Name:</div>
                <div class="col-md-8">
                    <%= fullName != null ? fullName : "Not specified" %>
                    <button class="btn btn-sm btn-outline-primary ms-2" data-bs-toggle="modal" data-bs-target="#nameModal">
                        Change
                    </button>
                </div>
            </div>
        </div>

        <div class="mb-4">
            <h4>Security</h4>
            <hr>
            <div class="d-flex justify-content-between align-items-center">
                <span class="fw-bold">Password</span>
                <button class="btn btn-outline-warning" data-bs-toggle="modal" data-bs-target="#passwordModal">
                    Change Password
                </button>
            </div>
        </div>

        <div class="text-center mt-4">
            <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">
                Delete Account
            </button>
        </div>
    </div>
</div>

<!-- Change Name Modal -->
<div class="modal fade" id="nameModal" tabindex="-1" aria-labelledby="nameModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="UpdateProfileServlet" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="nameModalLabel">Change Full Name</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="newFullName" class="form-label">New Full Name</label>
                        <input type="text" class="form-control" id="newFullName" name="newFullName"
                               value="<%= fullName != null ? fullName : "" %>" required>
                    </div>
                    <input type="hidden" name="action" value="updateName">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Change Password Modal -->
<div class="modal fade" id="passwordModal" tabindex="-1" aria-labelledby="passwordModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="UpdateProfileServlet" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="passwordModalLabel">Change Password</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="currentPassword" class="form-label">Current Password</label>
                        <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                    </div>
                    <div class="mb-3">
                        <label for="newPassword" class="form-label">New Password</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">Confirm New Password</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    </div>
                    <input type="hidden" name="action" value="updatePassword">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-primary">Change Password</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Delete Account Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="UpdateProfileServlet" method="post">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteModalLabel">Delete Account</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to delete your account? This action cannot be undone.</p>
                    <div class="mb-3">
                        <label for="deletePassword" class="form-label">Enter your password to confirm</label>
                        <input type="password" class="form-control" id="deletePassword" name="deletePassword" required>
                    </div>
                    <input type="hidden" name="action" value="deleteAccount">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger">Delete Account</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

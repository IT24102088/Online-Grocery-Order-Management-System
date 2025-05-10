<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.onlinegroceryordermanagementsystem.users" %>
<%@ page import="com.example.onlinegroceryordermanagementsystem.Product" %>
<html>
<head>
    <title>Admin Panel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <style>
        .admin-card {
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            margin-bottom: 20px;
        }
        .nav-pills .nav-link.active {
            background-color: #198754;
        }
        .badge-banned {
            background-color: #dc3545;
        }
        .badge-active {
            background-color: #198754;
        }
        .product-img {
            max-height: 80px;
        }
        .search-box {
            max-width: 300px;
        }
    </style>
</head>
<body>
<%-- Security Check --%>
<%
    //String role = (String) session.getAttribute("role");
    //if(!"owner".equals(role) && !"admin".equals(role)) {
    //    response.sendRedirect("index.jsp");
   // }

    List<users> allUsers = users.getUserList();
    List<Product> allProducts = Product.getProductsList();
%>

<jsp:include page="navbar.jsp"/>

<div class="container-fluid mt-4">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-3">
            <div class="card admin-card">
                <div class="card-header bg-primary text-white">
                    <h5><i class="bi bi-shield-lock"></i> Admin Panel</h5>
                </div>
                <div class="card-body">
                    <ul class="nav nav-pills flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="#users" data-bs-toggle="tab">
                                <i class="bi bi-people-fill"></i> User Management
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#products" data-bs-toggle="tab">
                                <i class="bi bi-cart-fill"></i> Product Management
                            </a>
                        </li>
                        <% if("owner".equals("owner")) { %>
                        <li class="nav-item">
                            <a class="nav-link" href="#backup" data-bs-toggle="tab">
                                <i class="bi bi-database"></i> Data Backup
                            </a>
                        </li>
                        <% } %>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Main Content -->
        <div class="col-md-9">
            <div class="tab-content">
                <!-- User Management Tab -->
                <div class="tab-pane fade show active" id="users">
                    <div class="card admin-card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <h5><i class="bi bi-people"></i> User Management</h5>
                            <div class="search-box">
                                <input type="text" class="form-control" placeholder="Search users..." id="userSearch">
                            </div>
                        </div>
                        <div class="card-body">
                            <table class="table table-hover">
                                <thead class="table-light">
                                <tr>
                                    <th>Username</th>
                                    <th>Status</th>
                                    <th>Role</th>
                                    <th>Ban/Unban</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% for(users user : allUsers) { %>
                                <tr class="user-row">
                                    <td><%= user.getUserName() %></td>
                                    <td>
                                            <span class="badge rounded-pill <%= user.isBanned() ? "bg-danger" : "bg-success" %>">
                                                <%= user.isBanned() ? "Banned" : "Active" %>
                                            </span>
                                    </td>
                                    <td><%= user.getRole() %></td>
                                    <td>

                                        <form action="banUsers" method="post" class="d-inline">
                                            <input type="hidden" name="action" value="toggleBan">
                                            <input type="hidden" name="userId" value="<%= user.getUserName() %>">
                                            <button type="submit" class="btn btn-sm <%= user.isBanned() ? "btn-success" : "btn-warning" %>">
                                                <%= user.isBanned() ? "Unban" : "Ban" %>
                                            </button>
                                        </form>

                                        <!-- Role Management (Only for owner) -->
                                        <% if("owner".equals("owner") && !user.getUserName().equals(session.getAttribute("username"))) { %>
                                        <% if(!"owner".equals(user.getRole())) { %>
                                        <form action="AdminServlet" method="post" class="d-inline">
                                            <input type="hidden" name="action" value="changeRole">
                                            <input type="hidden" name="userId" value="<%= user.getUserName() %>">
                                            <input type="hidden" name="newRole" value="<%= "admin".equals(user.getRole()) ? "user" : "admin" %>">
                                            <button type="submit" class="btn btn-sm btn-info">
                                                <%= "admin".equals(user.getRole()) ? "Demote" : "Promote" %>
                                            </button>
                                        </form>
                                        <% } %>
                                        <% } %>
                                    </td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- Product Management Tab -->
                <div class="tab-pane fade" id="products">
                    <div class="card admin-card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                            <h5><i class="bi bi-cart"></i> Product Management</h5>
                            <div>
                                <a href="addProducts.jsp" class="btn btn-success">
                                    <i class="bi bi-plus-lg"></i> Add Product
                                </a>
                            </div>
                        </div>
                        <div class="card-body">
                            <table class="table table-hover">
                                <thead class="table-light">
                                <tr>
                                    <th>Image</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Edit/Delete</th>

                                </tr>
                                </thead>
                                <tbody>
                                <% for(Product product : allProducts) { %>
                                <tr>
                                    <td>
                                        <img src="Images/<%= product.getImageName() %>"
                                             class="product-img"
                                             alt="<%= product.getpName() %>"
                                             onerror="this.src='Images/default-product.png'">
                                    </td>
                                    <td><%= product.getpName() %></td>
                                    <td>$<%= String.format("%.2f", product.getpPrice()) %></td>
                                    <!--<td>< // product.getStock() %></td>
                                    <td>< product.getCategory() %></td>-->

                                    <td>
                                        <a href="editProduct.jsp?id=<%= product.getId() %>"
                                           class="btn btn-sm btn-primary">
                                            <i class="bi bi-pencil"></i> Edit
                                        </a>
                                        <form action="AdminServlet" method="post" class="d-inline">
                                            <input type="hidden" name="action" value="deleteProduct">
                                            <input type="hidden" name="productId" value="<%= product.getId() %>">
                                            <button type="submit" class="btn btn-sm btn-danger">
                                                <i class="bi bi-trash"></i> Delete
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                                <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <!-- Backup Tab (Owner Only) -->
                <% if("owner".equals("owner")) { %>
                <div class="tab-pane fade" id="backup">
                    <div class="card admin-card">
                        <div class="card-header">
                            <h5><i class="bi bi-database"></i> Data Backup</h5>
                        </div>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="card mb-3">
                                        <div class="card-body text-center">
                                            <i class="bi bi-database-check fs-1 text-primary"></i>
                                            <h5 class="mt-3">Create System Backup</h5>
                                            <p>Backup all product and user data</p>
                                            <form action="AdminServlet" method="post">
                                                <input type="hidden" name="action" value="backup">
                                                <button type="submit" class="btn btn-primary">
                                                    <i class="bi bi-download"></i> Create Backup
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="card">
                                        <div class="card-body text-center">
                                            <i class="bi bi-clock-history fs-1 text-info"></i>
                                            <h5 class="mt-3">Restore Backup</h5>
                                            <p>Restore from previous backup</p>
                                            <button class="btn btn-info" >
                                                <i class="bi bi-upload"></i> Restore
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // User search functionality
    document.getElementById('userSearch').addEventListener('input', function() {
        const searchTerm = this.value.toLowerCase();
        document.querySelectorAll('#users tbody tr.user-row').forEach(row => {
            const username = row.cells[0].textContent.toLowerCase();
            const email = row.cells[1].textContent.toLowerCase();
            row.style.display = (username.includes(searchTerm) || email.includes(searchTerm)) ? '' : 'none';
        });
    });

    // Initialize tab if coming from another page with hash
    window.addEventListener('DOMContentLoaded', () => {
        if(window.location.hash) {
            const tabTrigger = new bootstrap.Tab(document.querySelector(`a[href="${window.location.hash}"]`));
            tabTrigger.show();
        }
    });
</script>
</body>
</html>

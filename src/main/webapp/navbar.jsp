<nav class="navbar navbar-expand-lg navbar-dark bg-success">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Online Grocery</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link <%= request.getRequestURI().endsWith("shop.jsp") ? "active" : "" %>"
                       href="shop.jsp">Shop</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= request.getRequestURI().endsWith("cart.jsp") ? "active" : "" %>"
                       href="cart.jsp">Cart</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= request.getRequestURI().endsWith("orders.jsp") ? "active" : "" %>"
                       href="orders.jsp">Orders</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= request.getRequestURI().endsWith("profile.jsp") ? "active" : "" %>"
                       href="profile.jsp">Profile</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <%= request.getRequestURI().endsWith("adminPanel.jsp") ? "active" : "" %>"
                       href="adminPanel.jsp">Admin</a>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
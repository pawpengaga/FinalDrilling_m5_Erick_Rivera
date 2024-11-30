<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<header>
  <nav class="navbar navbar-expand-lg bg-body-tertiary px-4">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Horoscopo Chino</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav w-100">          

          <c:if test="${not empty sessionScope.current_user}">
             <li class="nav-item">
              <span class="nav-link">Bievendid@: ${current_user.nombre}</span>
            </li>
          </c:if>
          
          <div class="ms-auto">
            <c:if test="${empty sessionScope.current_user}">
              <li class="nav-item ms-auto">
                <a class="nav-link" href="login.jsp">Login</a>
              </li>
            </c:if>
            <c:if test="${not empty sessionScope.current_user}">
              <li class="nav-item ms-auto">
                <a class="nav-link" href="/FinalDrilling_m5_Erick_Rivera/logout">
                  <span>Cerrar sesión </span>
                  <i class="fa-solid fa-arrow-right-from-bracket"></i>
                </a>
              </li>
            </c:if>
          </div>
        </ul>
      </div>
    </div>
  </nav>
</header>
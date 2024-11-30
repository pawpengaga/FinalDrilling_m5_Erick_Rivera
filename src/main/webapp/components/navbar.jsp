<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<header>
  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Horoscopo Chino</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="index.jsp">Inicio</a>
          </li>

          <c:if test="${empty sessionScope.usuario}">
            <li class="nav-item">
              <a class="nav-link" href="login.jsp">Login</a>
            </li>
          </c:if>
          <c:if test="${not empty sessionScope.usuario}">
            <li class="nav-item">
              <a class="nav-link" href="/FinalDrilling_m5_Erick_Rivera/logout">Logout</a>
            </li>
          </c:if>
        </ul>
      </div>
    </div>
  </nav>
</header>
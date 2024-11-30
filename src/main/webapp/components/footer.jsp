<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!-- El footer y los scripts finales de bootstrap -->
<footer class="d-flex justify-content-center w-100 p-3 mt-auto bg-body-tertiary" style="position: absolute; bottom: 0;">
  <p class="mb-0">Erick Rivera - 2024</p>
</footer>
<script src="	https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- Altera general -->
<c:if test="${not empty message}">
  <script>
      alert("${message}")
  </script>
</c:if>

<script src="assets/js/script.js"></script>
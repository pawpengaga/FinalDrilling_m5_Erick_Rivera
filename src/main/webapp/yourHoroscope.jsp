<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="components/head.jsp" %>
<body>
    <%@ include file="components/navbar.jsp" %>
    
    <div class="container mt-5">
        <div>
            <h1 class="display-3">Tu horóscopo</h1>
        </div>
        <c:if test="${not empty animal}">
    
            <div class="d-flex flex-row mt-5">
                <div class="col-6 d-flex flex-column">
                  <p><em>Tu animal del horóscopo Chino es...</em></p>
                  <div class="d-flex flex-row gap-3 align-items-center">
                    <img src="assets/img/${animal}-char.svg" alt="${animal}" style="width: 40px;">
                    <h1 class="mb-0">${animalReal}</h1>
                  </div>
                  <p class="mt-4">
                    Sint exercitation irure magna esse qui consectetur exercitation cillum. Ea tempor ut commodo anim eu id occaecat consectetur nostrud pariatur duis officia. Qui occaecat dolor sunt culpa laboris irure do do id id exercitation mollit occaecat. Do labore non enim incididunt nisi. Ut dolore nostrud sint aliquip magna minim non dolor nostrud dolore ut laboris mollit eiusmod.
                  </p>
                  <a href="index.jsp" class="mylink">Volver al inicio</a>
                </div>
                <div class="col-6 d-flex justify-content-center">
                    <img src="assets/img/${animal}.svg" alt="${animal}" style="width: 300px;">
                </div>
              </div>
        </c:if>
    </div>
    <%@ include file="components/footer.jsp" %>
</body>
</html>
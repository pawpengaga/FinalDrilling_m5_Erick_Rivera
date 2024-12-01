<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<%@ include file="components/head.jsp" %>
<body>
    <%@ include file="components/navbar.jsp" %>
    <div class="container mt-5 h-auto">
        <h1 class="display-3">Horóscopo Chino</h1>  
        <section class="h-auto">
            <c:if test="${empty sessionScope.current_user}">
                <div class="mt-5">
                    <p>Para ver el contenido, <a class="mylink" href="login.jsp">inicia sesión</a></p>
                    <p>¿No tienes una cuenta? <a class="mylink" href="signup.jsp">Regístrate</a></p>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.current_user}">
                <div class="mt-5 mb-5 col-12" id="index-menu">
                    <nav class="nav nav-pills nav-fill gap-3 w-100">
                        <a class="nav-link py-4 display-5 active" href="/FinalDrilling_m5_Erick_Rivera/horoscopo"><i class="fa-solid fa-yin-yang"></i> Ver tu animal</a>
                        <a class="nav-link py-4 display-5" href="searchUsers.jsp"><i class="fa-solid fa-magnifying-glass"></i> Buscar usuarios</a>
                        <a class="nav-link py-4 display-5" href="/FinalDrilling_m5_Erick_Rivera/users?accion=list"><i class="fa-solid fa-list"></i> Listar usuarios</a>
                        <a class="nav-link py-4 display-5" href="edituser.jsp"><i class="fa-solid fa-pen-to-square"></i> Modificar datos</a>
                        <a class="nav-link py-4 display-5" href="javascript: deleteAccount()"><i class="fa-solid fa-xmark"></i> Eliminar cuenta</a>
                    </nav>
                    <form name="deleteForm" action="/FinalDrilling_m5_Erick_Rivera/users?accion=delete" method="POST" style="display: none;">
                        <input type="hidden" name="userId" value="${current_user.id}" />
                    </form>
                </div>
            </c:if>
        </section>
        
    </div>
    <script>
        function deleteAccount(){
            const confirma = confirm("Está a punto de borrar su cuenta. ¿Está segur@?")
            if (confirma){
                document.deleteForm.submit()
            } else {
                alert("Sabia decisión...")
            }
        }
    </script>
    <%@ include file="components/footer.jsp" %>
</body>
</html>
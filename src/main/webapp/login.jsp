<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<%@ include file="components/head.jsp" %>
<body>
    <%@ include file="components/navbar.jsp" %>
    
    <div class="container mt-5">
        <h1 class="display-3">Iniciar sesión</h1>
        <form action="/FinalDrilling_m5_Erick_Rivera/users?accion=login" method="POST" class="mt-5 d-flex flex-column gap-4">
            <!-- COLUMNA 1 -->
            <div class="col-6 d-flex flex-column gap-4">
                <div class="w-100">
                    <label for="email" class="form-label fw-bold">Email</label>
                    <input type="email" name="email" id="email" placeholder="ejemplo@mail.com" class="form-control" required />
                </div>
                <div class="w-100">
                    <label for="password" class="form-label fw-bold">Contraseña</label>
                    <input type="password" name="password" id="password" class="form-control" required />
                </div>
                <div>
                    <button type="submit" class="btn btn-primary w-100 mt-3">Iniciar sesión</button>
                </div>
                <div>
                    <p>Si aún no tienes una cuenta haz clic <a class="mylink" href="signup.jsp">aquí</a></p>
                </div>
            </div>
        </form>
    </div>
    
    <%@ include file="components/footer.jsp" %>
</body>
</html>
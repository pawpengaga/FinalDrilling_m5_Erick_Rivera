<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="components/head.jsp" %>
<body>
    <%@ include file="components/navbar.jsp" %>
    
    <div class="container mt-5">
        <h1 class="display-3">Registrarse</h1>
        <form action="/FinalDrilling_m5_Erick_Rivera/users?accion=register" method="POST" class="mt-5 d-flex flex-column gap-4">
            <!-- COLUMNA 1 -->
            <div class="col-12 mx-auto d-flex flex-row gap-4">
                <div class="w-100">
                    <label for="nombre" class="form-label fw-bold">Nombre</label>
                    <input type="text" name="nombre" id="nombre" placeholder="Nombre completo" class="form-control" required />
                </div>
                <div class="w-100">
                    <label for="username" class="form-label fw-bold">Nombre de usuario</label>
                    <label for="username" class="form-label input-group">
                        <span class="input-group-text">@</span>
                        <input type="text" name="username" id="username" class="form-control" required />
                    </label>
                </div>
                <div class="w-100">
                    <label for="email" class="form-label fw-bold">Email</label>
                    <input type="email" name="email" id="email" placeholder="ejemplo@mail.com" class="form-control" required />
                </div>
            </div>
            <!-- COLUMNA 2 -->
            <div class="col-12 mx-auto d-flex flex-row gap-4">
                <div class="w-100">
                    <label for="fecha_nacimiento" class="form-label fw-bold">Fecha de nacimiento</label>
                    <input type="date" name="fecha_nacimiento" id="fecha_nacimiento" class="form-control" required />
                </div>
                <div class="w-100">
                    <label for="password" class="form-label fw-bold">Contraseña</label>
                    <input type="password" name="password" id="password" class="form-control" required />
                </div>
            </div>
            <div>
                <button type="submit" class="btn btn-primary">Registrarse</button>
            </div>
            <div>
                <p>¿Ya tienes una cuenta? Haz clic <a href="login.jsp" class="mylink">aquí</a></p>
            </div>
        </form>
    </div>
    
    <%@ include file="components/footer.jsp" %>
</body>
</html>
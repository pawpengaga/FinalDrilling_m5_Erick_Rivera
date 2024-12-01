<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="components/head.jsp" %>
<body>
    <%@ include file="components/navbar.jsp" %>
    
    <div class="container mt-5">
        <h1 class="display-3">Editar usuario</h1>
        <c:if test="${not empty current_user}">
            <form action="/FinalDrilling_m5_Erick_Rivera/users?accion=edit" method="POST" class="d-flex flex-column gap-5">
                <!-- COLUMNA 0 -->
                <input type="hidden" name="userId" id="userId" value="${current_user.id}" />
                <div class="mt-5 d-flex flex-row gap-4">
                    <!-- COLUMNA 1 -->
                    <div class="col-8 mx-auto d-flex flex-column gap-4">
                        <div class="w-100">
                            <label for="nombre" class="form-label fw-bold">Nombre</label>
                            <input type="text" name="nombre" id="nombre" placeholder="Nombre completo" class="form-control" value="${current_user.nombre}" required />
                        </div>
                        <div class="w-100">
                            <label for="username" class="form-label fw-bold">Nombre de usuario</label>
                            <label for="username" class="form-label input-group">
                                <span class="input-group-text">@</span>
                                <input type="text" name="username" id="username" class="form-control" value="${current_user.username}" required />
                            </label>
                        </div>
                        <div class="w-100">
                            <label for="email" class="form-label fw-bold">Email</label>
                            <input type="email" name="email" id="email" placeholder="ejemplo@mail.com" class="form-control" value="${current_user.email}" required />
                        </div>
                        <div class="w-100">
                            <label for="fecha_nacimiento" class="form-label fw-bold">Fecha de nacimiento</label>
                            <input type="date" name="fecha_nacimiento" id="fecha_nacimiento" class="form-control" value="${current_user.fecha_nacimiento}" required />
                        </div>
                    </div>
                    <!-- COLUMNA 2 -->
                    <div class="w-100 mx-auto d-flex flex-column gap-4">
                        <div class="w-100">
                            <label for="password" class="form-label fw-bold">Nueva contraseña</label>
                            <input type="password" name="password" id="password" class="form-control" placeholder="(Opcional)" />
                        </div>
                        <div class="w-100">
                            <label for="password" class="form-label fw-bold">Contraseña actual</label>
                            <input type="password" name="currentPassword" id="currentPassword" class="form-control" placeholder="Su contraseña actual" required />
                        </div>
                    </div>
                </div>
                <div class="d-flex flex-column gap-4">
                    <button type="submit" class="btn btn-primary w-fit">Editar registro</button>
                    <a href="index.jsp" class="mylink">Volver al inicio</a>
                </div>
            </form>
        </c:if>
    </div>
    
    <%@ include file="components/footer.jsp" %>
</body>
</html>
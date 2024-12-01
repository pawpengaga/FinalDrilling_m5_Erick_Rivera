<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<%@ include file="components/head.jsp" %>
<body>
    <%@ include file="components/navbar.jsp" %>
    
    <div class="container mt-5">
        <h1 class="display-3">Buscar usuarios</h1>
        <div class="mt-5 mb-5 col-12 d-flex flex-column gap-3">
            <div class="col-12">
                <form id="searchForm" name="searchForm" action="/FinalDrilling_m5_Erick_Rivera/users" method="GET">
                    <input type="hidden" name="accion" id="accion" value="search" />
                    <input type="text" name="myquery" id="myquery" class="form-control" placeholder="Buscar usuarios por nombre..." oninput="realTimeSearch()" />
                </form>
            </div>
            <div id="rts-results" class="col-12 mt-4">
                <c:if test="${not empty usuarios}">
                    <table class="table striped-table w-100">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Email</th>
                                <th>Fecha de nacimiento</th>
                                <th>Animal</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="user" items="${usuarios}">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.nombre}</td>
                                    <td>${user.email}</td>
                                    <td>${user.fecha_nacimiento}</td>
									<c:set var="animalNames" value="${fn:replace(user.animal, 'ó', 'o')}"/>
									<c:set var="animalsFinal" value="${fn:toLowerCase(animalNames)}" />
                                    <td>
                                        <div class="d-flex flex-column gap-2">
                                            <div>
                                                <img src="assets/img/${animalsFinal}-char.svg" alt="${animalsFinal}" style="width: 40px;">
                                            </div>
                                            <div class="d-flex align-items-center">
                                                <p class="fw-bold m-0">${user.animal}</p>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </div>

    <script>
        // Metodo overkill para busquedas en tiempo real...
        let myTimeout

        function realTimeSearch() {

            clearTimeout(myTimeout)
            myTimeout = setTimeout(() => {
                document.searchForm.submit()
            }, 1000)
        }
    </script>
    
    <%@ include file="components/footer.jsp" %>
</body>
</html>
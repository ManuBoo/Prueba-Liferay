<%@ page import="UserListPortlet.portlet.User" %>

<%@ page import="java.util.List" %>
<%@ page import="javax.portlet.*" %>

<%@ taglib uri="http://liferay.com/tld/portlet" prefix="portlet" %>

<portlet:actionURL var="searchUrl" name="searchUsers"/>

<form action="<%= searchUrl %>" method="post">
    Nombre: <input type="text" name="searchName" value="<%= request.getAttribute("searchName") %>"/>
    Apellidos: <input type="text" name="searchSurname" value="<%= request.getAttribute("searchSurname") %>"/>
    Correo: <input type="text" name="searchEmail" value="<%= request.getAttribute("searchEmail") %>"/>
    <input type="hidden" name="page" value="1"/>
    <input type="submit" value="Buscar" />
</form>

<table border="1">
    <thead>
        <tr>
            <th>Nombre de Usuario</th>
            <th>Nombre</th>
            <th>Apellido 1</th>
            <th>Apellido 2</th>
            <th>Correo Electrónico</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null && !users.isEmpty()) {
                for (User user : users) {
        %>
            <tr>
                <td><%= user.getName() %></td>
                <td><%= user.getSurname1() %></td>
                <td><%= user.getSurname2() %></td>
                <td><%= user.getEmail() %></td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="5">No se encontraron usuarios.</td>
            </tr>
        <%
            }
        %>
    </tbody>
</table>

<%-- Paginación --%>
<%
    int currentPage = (int) request.getAttribute("page"); // Cambiamos el nombre a currentPage para evitar conflictos
    int totalUsers = 20; // Cambia esto según el número total de usuarios simulados
    int pageSize = (int) request.getAttribute("pageSize");
    int totalPages = (int) Math.ceil((double) totalUsers / pageSize);
%>
<div>
    <% if (currentPage > 1) { %>
        <a href="<%= searchUrl + "&page=" + (currentPage - 1) %>">Anterior</a>
    <% } %>
    <span>Página <%= currentPage %> de <%= totalPages %></span>
    <% if (currentPage < totalPages) { %>
        <a href="<%= searchUrl + "&page=" + (currentPage + 1) %>">Siguiente</a>
    <% } %>
</div>


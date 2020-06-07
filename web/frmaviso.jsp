<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${aviso.id == 0}">Nuevo</c:if>
            <c:if test="${aviso.id !=0}">Editar</c:if>
                registro
            </h1>
            <form action="Inicio" method="POST">
                <input type="hidden" name="id" value="${aviso.id}" />
            
            <label>Descripcion</label>
            <textarea name="descripcion">${aviso.descripcion} </textarea>
            <br>
            <label>Stock:</label>
            <input type="text" name="stock" value="${aviso.stock}" />
            <br>
            <input type="submit" value="Enviar" />
        </form>

    </body>
</html>

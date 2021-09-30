<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Afficher tous les joueurs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
    <div class="container">
        <div class="row">
            <h1 class="text-center">Liste de tous les joueurs</h1><br>
            <c:forEach items="${joueurs}" var="joueur">
                <div class="col-lg-4 col-xs-12 text-center">
                    <div class="box box-archipel">
                        <img src="/icons/user_icon_2.png" alt="user-icon" style="width: 15%">
                        <div class="box-title">
                            <h3><c:out value="${joueur.getPrenomJoueur()}"/> <c:out value="${joueur.getNomJoueur()}"/></h3>
                        </div>
                        <div class="box-text">
                            <span>ID : <c:out value="${joueur.getIdJoueur()}"/></span><br>
                            <span><c:out value="${joueur.getMailJoueur()}"/></span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>

</html>
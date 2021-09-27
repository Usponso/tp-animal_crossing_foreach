<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Profil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="/img/animal_crossing_logo.png" width="100">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <form action="/accueil" method="post" id="form-accueil">
                    <input type="hidden" name="id" value="<c:out value="${joueur.getIdJoueur()}"/>">
                    <input type="hidden" name="nom" value="<c:out value="${joueur.getNomJoueur()}"/>">
                    <input type="hidden" name="prenom" value="<c:out value="${joueur.getPrenomJoueur()}"/>">
                    <input type="hidden" name="mail" value="<c:out value="${joueur.getMailJoueur()}"/>">
                    <a href="#" onclick="document.getElementById('form-accueil').submit();" class="nav-link">Accueil</a>
                </form>
                <a class="nav-link active" aria-current="page" href="#">Profil</a>
                <a class="nav-link" href="/allJoueurs">Liste des joueurs</a>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <h1 class="text-center">Profil</h1><br>

        <div class="col-3"></div>

        <div class="col-6">
            <c:if test="${not empty erreur}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${erreur}"/>
                </div>
            </c:if>

            <c:if test="${not empty success}">
                <div class="alert alert-success" role="alert">
                    <c:out value="${success}"/>
                </div>
            </c:if>

            <form action="/updateJoueur" method="post">
                <div class="input-group">
                    <input type="hidden" name="id" value="<c:out value="${joueur.getIdJoueur()}"/>">

                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Nom</span>
                        <input type="text" class="form-control" name="nom" aria-label="nom" aria-describedby="basic-addon1" value="<c:out value="${joueur.getNomJoueur()}"/>" required>
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon2">Prénom</span>
                        <input type="text" class="form-control" name="prenom" aria-label="prenom" aria-describedby="basic-addon2" value="<c:out value="${joueur.getPrenomJoueur()}"/>" required>
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon3">Mail</span>
                        <input type="text" class="form-control" name="mail" aria-label="mail" aria-describedby="basic-addon3" value="<c:out value="${joueur.getMailJoueur()}"/>" required>
                    </div>

                    <input type="submit" class="btn btn-success" value="Modifier">
                </form>
            </div>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>

</body>

</html>
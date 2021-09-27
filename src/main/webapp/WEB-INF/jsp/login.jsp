<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Connexion</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
    <div class="container">
        <div class="row">
            <h1 class="text-center">Connexion</h1><br>

            <div class="col-3"></div>

            <div class="col-6">
                <form action="/traitementLogin" method="post">

                    <c:if test="${not empty erreur}">
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${erreur}"/>
                        </div>
                    </c:if>

                    <div class="input-group">
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Nom</span>
                            <input type="text" class="form-control" name="nom" placeholder="Nom" aria-label="nom" aria-describedby="basic-addon1" required>
                        </div>

                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon2">Prénom</span>
                            <input type="text" class="form-control" name="prenom" placeholder="Prénom" aria-label="prenom" aria-describedby="basic-addon2" required>
                        </div>

                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon3">Mail</span>
                            <input type="text" class="form-control" name="mail" placeholder="Mail" aria-label="mail" aria-describedby="basic-addon3" required>
                        </div>

                        <input type="submit" class="btn btn-success" value="Connexion">
                    </div>
                </form>

                <div class="text-end">
                    <a href="/inscription" class="link-primary">Pas encore inscrit ? N'attendez plus, inscrivez-vous !</a>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>${ile.getNomIle()}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light">
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
                <a class="nav-link" aria-current="page" href="/profil/<c:out value="${joueur.getIdJoueur()}"/>">Profil</a>
                <a class="nav-link" href="/allJoueurs">Liste des joueurs</a>
            </div>
        </div>
        <div class="d-flex">
            <a class="btn btn-danger" href="http://localhost:8080/logout">
                <i class="fa fa-power-off"></i>
            </a>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <h1 class="text-center" style="margin-top:30px; margin-bottom: 20px">${ile.getNomIle()}</h1>

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

        <!-- Tableau des cinemas -->
        <div class="col-lg-3 col-xs-12">
            <div class="row">
                <div class="col-lg-6">Cinemas</div>
                <div class="col-lg-6 text-end">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#ajouterCinemaModal">
                        +
                    </button>
                </div>
            </div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Nom</th>
                    <th scope="col">Séances</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cinemas}" var="cinema">
                    <tr>
                        <td style="cursor: pointer" >${cinema.getNomCinema()}</td>
                        <td>
                            <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#afficherSeancesModal${cinema.getIdCinema()}">
                                <i class="fa fa-ticket"></i> Voir
                            </button>
                        </td>
                        <td class="action">
                            <a href="http://localhost:8080/deleteCinema/${joueur.getIdJoueur()}/${ile.getIdIle()}/${cinema.getIdCinema()}" class="btn btn-danger">
                                <i class="fa fa-trash-o"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <hr>
            <div class="text-center">
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#ajouterFilmModal">
                    <i class="fa fa-film"></i> Ajouter un film
                </button>
            </div>
        </div>

        <!-- Tableau des batiments -->
        <div class="col-lg-3 col-xs-12">
            <div class="row">
                <div class="col-lg-6">Batiments</div>
                <div class="col-lg-6 text-end">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#ajouterBatimentModal">
                        +
                    </button>
                </div>
            </div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Nom</th>
                    <th scope="col">Type</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${batiments}" var="batiment">
                    <tr>
                        <td>${batiment.getNomBatiment()}</td>
                        <td>
                            <c:forEach items="${typesBatiments}" var="type">
                                <c:if test="${type.getIdTypeBatiment() == batiment.getIdTypeBatiment()}">
                                    ${type.getNomTypeBatiment()}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td class="action">
                            <a href="http://localhost:8080/deleteBatiment/${joueur.getIdJoueur()}/${ile.getIdIle()}/${batiment.getIdBatiment()}" class="btn btn-danger">
                                <i class="fa fa-trash-o"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Tableau des forets -->
        <div class="col-lg-3 col-xs-12">
            <div class="row">
                <div class="col-lg-6">Forets</div>
                <div class="col-lg-6 text-end">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#ajouterForetModal">
                        +
                    </button>
                </div>
            </div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Nom</th>
                    <th scope="col">Superficie</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${forets}" var="foret">
                    <tr>
                        <td>${foret.getNomForet()}</td>
                        <td>${foret.getSuperficieForet()}</td>
                        <td class="action">
                            <a href="http://localhost:8080/deleteForet/${joueur.getIdJoueur()}/${ile.getIdIle()}/${foret.getIdForet()}" class="btn btn-danger">
                                <i class="fa fa-trash-o"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Tableau des espaces -->
        <div class="col-lg-3 col-xs-12">
            <div class="row">
                <div class="col-lg-6">Espaces naturels</div>
                <div class="col-lg-6 text-end">
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#ajouterEspaceModal">
                        +
                    </button>
                </div>
            </div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Nom</th>
                    <th scope="col">Type</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${espaces}" var="espace">
                    <tr>
                        <td>${espace.getNomEspace()}</td>
                        <td>
                            <c:forEach items="${typesEspaces}" var="type">
                                <c:if test="${type.getIdTypeEspace() == espace.getIdTypeEspace()}">
                                    ${type.getNomTypeEspace()}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td class="action">
                            <a href="http://localhost:8080/deleteEspace/${joueur.getIdJoueur()}/${ile.getIdIle()}/${espace.getIdEspace()}" class="btn btn-danger">
                                <i class="fa fa-trash-o"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</div>

<!-- Modal creation cinema -->
<div class="modal fade" id="ajouterCinemaModal" tabindex="-1" aria-labelledby="cinemaModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/creerCinema" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="cinemaModalLabel">Ajouter un cinéma</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="modal-body">
                        <input type="hidden" name="idJoueur" value="<c:out value="${joueur.getIdJoueur()}"/>">
                        <input type="hidden" name="idArchipel" value="${archipel.getIdArchipel()}">
                        <input type="hidden" name="idIle" value="${ile.getIdIle()}">
                        <label for="nomCinemaInput" class="form-label">Nom du cinéma</label>
                        <input type="text" class="form-control" id="nomCinemaInput" name="nomCinema" placeholder="Ex: Les Etoiles" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                    <button type="submit" class="btn btn-success">Ajouter</button>
                </div>
            </div>
        </form>
    </div>
</div>

<c:forEach items="${cinemas}" var="cinema">
    <!-- Modal seances -->
    <div class="modal fade" id="afficherSeancesModal${cinema.getIdCinema()}" tabindex="-1" aria-labelledby="seanceModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="seanceModalLabel">${cinema.getNomCinema()} : Séances</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th scope="col">Film</th>
                            <th scope="col">Places restantes</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${cinema.getFilms()}" var="film">
                                <tr>
                                    <td>${film.getNomFilm()}</td>
                                    <td>${film.getNbPlaces()}</td>
                                    <td>
                                        <form action="/reserverSeance" method="post">
                                            <input type="hidden" name="idJoueur" value="${joueur.getIdJoueur()}">
                                            <input type="hidden" name="idArchipel" value="${archipel.getIdArchipel()}">
                                            <input type="hidden" name="idIle" value="${ile.getIdIle()}">
                                            <input type="hidden" name="idCinema" value="${cinema.getIdCinema()}">
                                            <input type="hidden" name="idFilm" value="${film.getIdFilm()}">
                                            <input type="hidden" name="nbPlaces" value="${film.getNbPlaces()}">

                                            <input type="submit" class="btn btn-success" value="Réserver" <c:if test="${film.getNbPlaces()<=0}">disabled</c:if> >
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>
</c:forEach>


<!-- Modal creation film -->
<div class="modal fade" id="ajouterFilmModal" tabindex="-1" aria-labelledby="filmModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/ajouterFilm" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="filmModalLabel">Ajouter un film</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="modal-body">
                        <input type="hidden" name="idJoueur" value="<c:out value="${joueur.getIdJoueur()}"/>">
                        <input type="hidden" name="idArchipel" value="${archipel.getIdArchipel()}">
                        <input type="hidden" name="idIle" value="${ile.getIdIle()}">
                        <label for="nomFilmInput" class="form-label">Nom du film</label>
                        <input type="text" class="form-control" id="nomFilmInput" name="nomFilm" placeholder="Ex: Kaamelott : Premier Volet" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                    <button type="submit" class="btn btn-success">Ajouter</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- Modal creation batiment -->
<div class="modal fade" id="ajouterBatimentModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/creerBatiment" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="batimentModalLabel">Ajouter un batiment</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="modal-body">
                        <input type="hidden" name="idJoueur" value="<c:out value="${joueur.getIdJoueur()}"/>">
                        <input type="hidden" name="idArchipel" value="${archipel.getIdArchipel()}">
                        <input type="hidden" name="idIle" value="${ile.getIdIle()}">
                        <label for="nomBatimentInput" class="form-label">Nom du batiment</label>
                        <input type="text" class="form-control" id="nomBatimentInput" name="nomBatiment" placeholder="Ex: Ecole Jules Ferry" required>
                        <br>
                        <select class="form-select" aria-label="Select types batiment" name="idTypeBatiment" required>
                            <option selected>Sélectionner un type</option>
                            <c:forEach items="${typesBatiments}" var="type">
                                <option value="${type.getIdTypeBatiment()}">${type.getNomTypeBatiment()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                    <button type="submit" class="btn btn-success">Ajouter</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- Modal creation foret -->
<div class="modal fade" id="ajouterForetModal" tabindex="-1" aria-labelledby="foretModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/creerForet" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="foretModalLabel">Ajouter une foret</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="modal-body">
                        <input type="hidden" name="idJoueur" value="<c:out value="${joueur.getIdJoueur()}"/>">
                        <input type="hidden" name="idArchipel" value="${archipel.getIdArchipel()}">
                        <input type="hidden" name="idIle" value="${ile.getIdIle()}">
                        <label for="nomForetInput" class="form-label">Nom de la foret</label>
                        <input type="text" class="form-control" id="nomForetInput" name="nomForet" placeholder="Ex: La forêt domaniale de Grande Chartreuse" required>
                        <br>
                        <label for="superficieForetInput" class="form-label">Superficie</label>
                        <input type="number" class="form-control" id="superficieForetInput" name="superficieForet" placeholder="Ex: 3000 (hectares)" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                    <button type="submit" class="btn btn-success">Ajouter</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- Modal creation espace naturel -->
<div class="modal fade" id="ajouterEspaceModal" tabindex="-1" aria-labelledby="espaceModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/creerEspace" method="post">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="espaceModalLabel">Ajouter un espace naturel</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="modal-body">
                        <input type="hidden" name="idJoueur" value="<c:out value="${joueur.getIdJoueur()}"/>">
                        <input type="hidden" name="idArchipel" value="${archipel.getIdArchipel()}">
                        <input type="hidden" name="idIle" value="${ile.getIdIle()}">
                        <label for="nomEspaceInput" class="form-label">Nom de la foret</label>
                        <input type="text" class="form-control" id="nomEspaceInput" name="nomEspace" placeholder="Ex: Bora Bora" required>
                        <br>
                        <select class="form-select" aria-label="Select types espace" name="idTypeEspace" required>
                            <option selected>Sélectionner un type</option>
                            <c:forEach items="${typesEspaces}" var="type">
                                <option value="${type.getIdTypeEspace()}">${type.getNomTypeEspace()}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                    <button type="submit" class="btn btn-success">Ajouter</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>

</body>

</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Accueil</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
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
                <a class="nav-link active" aria-current="page" href="#">Accueil</a>
                <a class="nav-link" aria-current="page" href="/profil/<c:out value="${joueur.getIdJoueur()}"/>">Profil</a>
                <a class="nav-link" href="/allJoueurs">Liste des joueurs</a>
            </div>
        </div>
    </div>
</nav>

<div class="container">
    <div class="row">
        <h1 class="text-center text-uppercase" style="margin-top:40px">Accueil</h1><br>

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

        <div class="col-lg-4 col-xs-12 text-center">
            <div class="box box-user">
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

        <div class="col-lg-4 col-xs-12 text-center">
            <div class="box box-archipel">
                <img src="/icons/archipel_icon.png" alt="archipel-icon" style="width: 15%">
                <c:if test="${not empty archipel}">
                    <div class="box-title">
                        <h3><c:out value="${archipel.getNomArchipel()}"/> </h3>
                    </div>
                    <div class="box-text">
                        <span>Localisation : <c:out value="${archipel.getLocalisationArchipel()}"/></span><br>
                        <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modifierArchipelModal"><i class="fa fa-edit"></i> Modifier</button>
                        <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#supprimerArchipelModal"><i class="fa fa-trash"></i> Supprimer</button>
                    </div>
                </c:if>

                <c:if test="${empty archipel}">
                    <div class="box-title">
                        <h3>Aucun archipel</h3><br>
                    </div>
                    <div class="box-text">
                        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#archipelModal">
                            Creer un archipel
                        </button>
                    </div>
                </c:if>
            </div>
        </div>


        <div class="col-lg-4 col-xs-12 text-center">
            <div class="box box-ile">
                <img src="/icons/island_icon_2.png" alt="ile_icon" style="width: 15%">
                <div class="box-title">
                    <h3>Ajouter une ile</h3>
                </div>
                <div class="box-text">
                    <c:if test="${not empty archipel}">
                        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#ileModal">
                            Creer une ile
                        </button>
                    </c:if>

                    <c:if test="${empty archipel}">
                        Vous n'avez pas encore d'archipel.
                    </c:if>
                </div>
            </div>
        </div>

        <c:forEach items="${iles}" var="ile">
            <div class="col-lg-8"></div>

            <div class="col-lg-4 col-xs-12 text-center">
                <div class="box box-ile">
                    <img src="/icons/island_icon_2.png" alt="ile_icon" style="width: 15%">
                    <div class="box-title title-ile" onclick="window.location='http://localhost:8080/ile/${joueur.getIdJoueur()}/${archipel.getIdArchipel()}/${ile.getIdIle()}';">
                        <h3><c:out value="${ile.getNomIle()}"/></h3>
                    </div>
                    <div class="box-text">
                        <span>Localisation : <c:out value="${ile.getLocalisationIle()}"/></span><br>
                        <button class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#modifierIleModal<c:out value="${ile.getIdIle()}"/>"><i class="fa fa-edit"></i> Modifier</button>
                        <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#supprimerIleModal<c:out value="${ile.getIdIle()}"/>"><i class="fa fa-trash"></i> Supprimer</button>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>

    <c:if test="${empty archipel}">
    <!-- Modal -->
    <div class="modal fade" id="archipelModal" tabindex="-1" aria-labelledby="archipelModalLabel" aria-hidden="true">
        <div class="modal-lg modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="archipelModalLabel">Creation d'un archipel</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="archipel" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="idJoueur" value="<c:out value="${joueur.getIdJoueur()}"/>">
                        <label for="nomArchipelInput" class="form-label">Nom de l'archipel</label>
                        <input type="text" class="form-control" id="nomArchipelInput" name="nomArchipel" placeholder="Ex: Aeternum" required>
                        <label for="localisationArchipelInput" class="form-label">Localisation de l'archipel</label>
                        <input type="text" class="form-control" id="localisationArchipelInput" name="localisationArchipel" placeholder="Localisation" required>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                        <button type="submit" class="btn btn-success">Creer</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </c:if>

    <c:if test="${not empty archipel}">
        <!-- Modal modification de l'archipel-->
        <div class="modal fade" id="modifierArchipelModal" tabindex="-1" aria-labelledby="modifierArchipelModalLabel" aria-hidden="true">
            <div class="modal-lg modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modifierArchipelModalLabel">Modification d'un archipel</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="updateArchipel" method="post">
                        <div class="modal-body">
                            <input type="hidden" name="idJoueur" value="<c:out value="${joueur.getIdJoueur()}"/>">
                            <input type="hidden" name="idArchipel" value="<c:out value="${archipel.getIdArchipel()}"/>">
                            <label for="nomArchipel2Input" class="form-label">Nom de l'archipel</label>
                            <input type="text" class="form-control" id="nomArchipel2Input" name="nomArchipel" placeholder="Ex: Aeternum" value="<c:out value="${archipel.getNomArchipel()}"/>" required>
                            <label for="localisationArchipel2Input" class="form-label">Localisation de l'archipel</label>
                            <input type="text" class="form-control" id="localisationArchipel2Input" name="localisationArchipel" placeholder="Localisation" value="<c:out value="${archipel.getLocalisationArchipel()}"/>" required>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                            <button type="submit" class="btn btn-success">Modifier</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Modal suppression de l'archipel -->
        <div class="modal fade" id="supprimerArchipelModal" tabindex="-1" aria-labelledby="supprimerArchipelModalLabel" aria-hidden="true">
            <div class="modal-lg modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="supprimerArchipelModalLabel"><i class="fa fa-exclamation-triangle"></i> Suppression d'un archipel</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="deleteArchipel" method="post">
                        <div class="modal-body">
                            <input type="hidden" name="idJoueur" value="<c:out value="${joueur.getIdJoueur()}"/>">
                            <input type="hidden" name="idArchipel" value="<c:out value="${archipel.getIdArchipel()}"/>">
                            <div class="alert alert-danger" role="alert">
                                Souhaitez-vous supprimer votre archipel ?
                                Cela entrainera <u>la suppression de toutes les iles ainsi que tous leurs elements.</u>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                            <button type="submit" class="btn btn-danger">Supprimer</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Modal création d'une ile -->
        <div class="modal fade" id="ileModal" tabindex="-1" aria-labelledby="ileModalLabel" aria-hidden="true">
            <div class="modal-lg modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="ileModalLabel">Creation d'une ile</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="creerIle" method="post">
                        <div class="modal-body">
                            <input type="hidden" name="idJoueur" value="<c:out value="${joueur.getIdJoueur()}"/>">
                            <input type="hidden" name="idArchipel" value="<c:out value="${archipel.getIdArchipel()}"/>">
                            <label for="nomIleInput" class="form-label">Nom de l'ile</label>
                            <input type="text" class="form-control" id="nomIleInput" name="nomIle" placeholder="Ex: Aeternum" required>
                            <label for="localisationIleInput" class="form-label">Localisation de l'ile</label>
                            <input type="text" class="form-control" id="localisationIleInput" name="localisationIle" placeholder="Localisation" required>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                            <button type="submit" class="btn btn-success">Creer</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <c:forEach items="${iles}" var="ile">
            <!-- Modal modification d'une ile -->
            <div class="modal fade" id="modifierIleModal<c:out value="${ile.getIdIle()}"/>" tabindex="-1" aria-labelledby="modifierIleModalLabel<c:out value="${ile.getIdIle()}"/>" aria-hidden="true">
                <div class="modal-lg modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="modifierIleModalLabel">Modification d'une ile</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="/updateIle" method="post">
                            <div class="modal-body">
                                <input type="hidden" name="idJoueur" value="<c:out value="${joueur.getIdJoueur()}"/>">
                                <input type="hidden" name="idArchipel" value="<c:out value="${archipel.getIdArchipel()}"/>">
                                <input type="hidden" name="idIle" value="<c:out value="${ile.getIdIle()}"/>">
                                <label for="modifierNomIleInput" class="form-label">Nom de l'ile</label>
                                <input type="text" class="form-control" id="modifierNomIleInput" name="nomIle" placeholder="Ex: MyIsland" value="<c:out value="${ile.getNomIle()}"/>" required>
                                <label for="modifierLocalisationIleInput" class="form-label">Localisation de l'ile</label>
                                <input type="text" class="form-control" id="modifierLocalisationIleInput" name="localisationIle" placeholder="Localisation" value="<c:out value="${ile.getLocalisationIle()}"/>" required>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Annuler</button>
                                <button type="submit" class="btn btn-success">Modifier</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <!-- Modal suppression d'une ile -->
            <div class="modal fade" id="supprimerIleModal<c:out value="${ile.getIdIle()}"/>" tabindex="-1" aria-labelledby="supprimerIleModalLabel<c:out value="${ile.getIdIle()}"/>" aria-hidden="true">
                <div class="modal-lg modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="supprimerIleModalLabel"><i class="fa fa-exclamation-triangle"></i> Suppression d'une ile</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <form action="deleteIle" method="post">
                            <div class="modal-body">
                                <input type="hidden" name="idJoueur" value="<c:out value="${joueur.getIdJoueur()}"/>">
                                <input type="hidden" name="idArchipel" value="<c:out value="${archipel.getIdArchipel()}"/>">
                                <input type="hidden" name="idIle" value="<c:out value="${ile.getIdIle()}"/>">
                                <div class="alert alert-danger" role="alert">
                                    Souhaitez-vous supprimer votre ile <b><c:out value="${ile.getNomIle()}"/></b> ?
                                    Cela entrainera <u>la suppression de tous les elements presents sur cette ile.</u>
                                </div>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                                <button type="submit" class="btn btn-danger">Supprimer</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>

    </c:if>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
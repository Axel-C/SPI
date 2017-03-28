/* Sauvegarde tampon du user*/
var login, mdp, telephone, alias, numeroSiret, idMec, role;

$(document).ready(function(){
    initialisation();
    
    $('#ajoutPorte').click(function () {
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: "v1/maintenance/",
            data: JSON.stringify({
                "date": "Pas encore eu de maintenance",
                "idUser": idMec,
                "rapport": "",
                "type": $('#description').val(),
                "numPorte": $('#numPorte').val()
            }),
            success: function (data, textStatus, jqXHR) {
                alert("Ajout de porte reussit");
                window.location.replace("index.html");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("Pas reussit");
            }
        });
    });
    
    $('#btnConnection').click(function () {
        afficherContenu('#ContenuLogin');
    });
    $('#nousContacter').click(function () {
        afficherContenu('#contenuContact');
    });
     $('.ajouterArticle').click(function () {
        afficherContenu('#contenuAjout');
    });
    $('.creerCompte').click(function () {
        afficherContenu('#creerCompte');
    });
    
    $('#validation').click(function () {
        $.ajax({
            type: "GET",
            url: "v1/secure/who",
            dataType: 'json',
            beforeSend: function (req) {
                const s = btoa($("#login").val() + ":" + $("#password").val());
                req.setRequestHeader("Authorization", "Basic " + s);
            },
            success: function (data) {
                login = $("#login").val();
                mdp = $("#password").val();
                afficherContenu('#contenu');
                membreConnecte($("#login").val(), $("#password").val(), data.telephone, data.alias, data.numSiret, data.id, data.role);
                window.location.replace("index.html");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#ContenuLogin .panel-warning').show();
                $('#ContenuLogin .error').empty();
                $('#ContenuLogin .error').append("<h3 class='panel-title'>Mauvais login ou mot de passe</h3>");
            }
        });
    });
    
    $('#modifCompte').click(function () {
        if ($('#contenuCompte input[name=password]').val() !== $('#contenuCompte input[name=confirmation]').val()) {
            alert('Le mots de passe et la confirmation ne sont pas identique');

        } else if ($('#contenuCompte input[name=email]').val() === "") {
            alert('Adresse email vide');

        } else {

            $.ajax({
                url: "v1/user/" + idMec,
                type: "PUT",
                // dataType: "json",
                beforeSend: function (req) {
                    const s = btoa(login + ":" + $('#contenuCompte input[name=oldPassword]').val());
                    req.setRequestHeader("Authorization", "Basic " + s);
                },
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({
                    "email": $('#contenuCompte input[name=email]').val(),
                    "password": $('#contenuCompte input[name=password]').val(),
                    "alias": alias,
                    "telephone": telephone

                }),

                success: function () {
                    alert("votre compte à bien été modifié, vous allez être déconnécté")
                    window.location.replace("index.html");

                },
                error: function (xhr, status, errorThrown) {
                    alert("Sorry, there was a problem!");
                    console.log("Error: " + errorThrown);
                    console.log("Status: " + status);
                    console.dir(xhr);
                }
            });
        }
    });
    
    $('#validCompte').click(function () {

        $.ajax({
            url: "v1/user",
            contentType: 'application/json',
            type: "POST",
            dataType: "json",
            beforeSend: function (req) {
                const s = btoa(login + ":" + mdp);
                req.setRequestHeader("Authorization", "Basic " + s);
            },
            /*headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
},*/
            data: JSON.stringify({
                "email": $('#creerCompte input[name=email]').val(),
                "alias": $('#creerCompte input[name=nom]').val(),
                "name": $('#creerCompte input[name=nom]').val(),
                "numSiret": $('#creerCompte input[name=siret]').val(),
                "telephone": $('#creerCompte input[name=telephone]').val(),
                "password": $('#creerCompte input[name=password]').val(),
                "role": "user"
            }),

            success: function (json) {


            },
            error: function (xhr, status, errorThrown) {
                alert("Sorry, there was a problem!");
                console.log("Error: " + errorThrown);
                console.log("Status: " + status);
                console.dir(xhr);
            }
        });


    });
    
     $('#btnNouvelArticle').click(function () {
        if ($('#contenuAjout input[name=nom]').val() == "") {
            alert('Aucun nom pour l\' article ');
        } else {
            var prix = parseFloat($('#contenuAjout input[name=prix]').val());
            $.ajax({
                url: "v1/produits",
                type: "POST",
                dataType: "json",
                beforeSend: function (req) {
                    const s = btoa(login + ":" + mdp);
                    req.setRequestHeader("Authorization", "Basic " + s);
                },
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify({
                    "categorie": $('#contenuAjout input[name=categorie]').val(),
                    "description": $('#contenuAjout input[name=description]').val(),
                    "libelle": $('#contenuAjout input[name=nom]').val(),
                    "prix": prix,
                    "reference": $('#contenuAjout input[name=reference]').val(),
                    "urlImage": $('#contenuAjout input[name=image]').val()
                }),

                success: function (json) {


                },
                /*error: function( xhr, status, errorThrown ) {
                    alert( "Sorry, there was a problem!" );
                    console.log( "Error: " + errorThrown );
                    console.log( "Status: " + status );
                    console.dir(xhr);
                } */
            });
            window.location.replace("index.html");

        }
    });
}
                  
function initialisation(){
    login = localStorage.getItem('login');
    mdp = localStorage.getItem('mdp');
    telephone = localStorage.getItem('telephone');
    alias = localStorage.getItem('alias');
    numeroSiret = localStorage.getItem('numeroSiret');
    idMec = localStorage.getItem('idMec');
    role = localStorage.getItem('role');
    
    /* Regarder si quelqu'un est connecté pour savoir quoi afficher */
    $.ajax({
        url: "v1/secure/who",
        type: "GET",
        dataType: "json",
        beforeSend: function (req) {
            const s = btoa(login + ":" + mdp);
            req.setRequestHeader("Authorization", "Basic " + s);
        },
        success: function (json) {
            afficherContenu('#contenu');
            membreConnecte(login, mdp, telephone, alias, numeroSiret, idMec, role);
            $('#contenuCompte input[name=email]').val(login);
            var user = JSON.parse(JSON.stringify(json));
            if (user.role == "admin") {
                $('.ajouterArticle').css('display', 'block');
                $('.creerCompte').css('display', 'block');
            } 
            /* AFFICHER SES PORTES */
            $.ajax({
                    url: "v1/maintenance/bu/" + idMec,
                    type: "GET",
                    dataType: "json",
                    beforeSend: function (req) {
                        const s = btoa(login + ":" + mdp);
                        req.setRequestHeader("Authorization", "Basic " + s);
                    },
                    success: function (json) {
                        var portes = JSON.parse(JSON.stringify(json));
                        
                        afficherPortes(portes);
                        
                        $('.ajoutMaintenance').click(function () {
                            $('#porteMaintenance').val($(this).attr('id'));
                            $('#descritpion').show();
                        });
                    },
                    error: function (xhr, status, errorThrown) {
                        console.info("Erreur pour filtrer vos portes");
                    }
            });
            
            
        },
        error: function (xhr, status, errorThrown) {
            console.info("Vous n'etes pas encore connectée");
        }
    });
    
    /* Afficher les articles pour la premiere fois */
    $.ajax({
        url: "v1/produits",
        type: "GET",
        dataType: "json",
        beforeSend: function (req) {
            const s = btoa(login + ":" + mdp);
            req.setRequestHeader("Authorization", "Basic " + s);
        },
        success: function (json) {
            var articles = JSON.parse(JSON.stringify(json));
            initCatalogue("Les indispensables", articles);
            filtreCategorie(articles);
            
            $('.nav li').on('click', afficheArticleCategorie);
            
             $('.enSavoirPlus').on('click', btnEnSavoirPlus(this));
            
        },
        error: function( xhr, status, errorThrown ) {
            console.log( "Error: " + errorThrown );
            console.log( "Status: " + status );
            console.dir(xhr);
        } 
    });
    
    
    setTimeout(ajouterEvent, 500);
}

function membreConnecte(login, mdp, telephone, alias, numeroSiret, idM, role){
    $('#navProfil').empty();
        $('#navProfil').append('<a id="btnProfil" href="#">' + login + "</a><button id='deconnection' type='button'>" +
            "<span class='glyphicon glyphicon-log-out' aria-hidden='true'></span></button>");
        localStorage.setItem('login', login);
        localStorage.setItem('mdp', mdp);
        localStorage.setItem('telephone', telephone);
        localStorage.setItem('alias', alias);
        localStorage.setItem('numeroSiret', numeroSiret);
        localStorage.setItem('idMec', idM);
        localStorage.setItem('role', role);
        $('#btnProfil').click(function () {
            afficherContenu('#contenuCompte');
        });

        $('#deconnection').click(function () {
            localStorage.setItem('login', "");
            localStorage.setItem('mdp', "");
            localStorage.setItem('telephone', "");
            localStorage.setItem('alias', "");
            localStorage.setItem('numeroSiret', "");
            localStorage.setItem('idMec', "");
            localStorage.setItem('role', "");
            window.location.replace('index.html', "");
        });
}

function afficherContenu(div) {
    $('section.col-md-9:visible').hide(300);
    $(div).show(300);
}

function creerArticle(id, name, img, prix, description) {
    var retour = "";
    if(description != undefined) retour += "<article class='thumbnail col-md-5 >";
    else retour += "<article class='thumbnail col-md-4' >";
    retour += "<img src='" + img + "' class='img-thumbnail img-responsive' alt='" + name + "'>";
    retour += "<div class='caption'>";
    retour += "<h3>" + name + "</h3>";
    if(prix != -1) retour += "<h3>" + prix + "€ </h3>";
    if(description != undefined) retour += "<p>" + description + "</p>";
    retour += "<a href='#' class='btn btn-info enSavoirPlus' id='id" + id + "'>En savoir plus</a>";
    if(role == admin) retour += "<a href='#' class='btn btn-danger supprimer' id='id" + id + "'>Supprimer</a>";
    retour += "</div></article>";
    return $(retour);
}

function afficherArticles(articles){
    var catalogue = $('#catalogue');
    var row = $('<div class="row">');
    for (var i = 1; i <= articles.length; i++) {
        row.append(creerArticle(articles[i-1].idp, articles[i-1].libelle, articles[i-1].description, articles[i-1].urlImage, articles[i-1].prix));
        if (i%3 == 0) {
            catalogue.append(row);
            var row = $('<div class="row">');
        }
    }
    catalogue.append(row);
}

function afficherArticleDescription(article) {
    var catalogue = $('#catalogue');
    var row = $('<div class="row">');
    row.append(creerArticle(articles.idp, articles.libelle, articles.urlImage, articles.prix, articles.description));
    catalogue.append(row);
}

function creerPortes(numPorte, type, date, rapport, idM){
    var retour = "";
    retour += '<tr><td>' + numPortes + '</td>';
    retour += '<td>' + type + '</td>';
    retour += '<td>' + date + '</td>';
    retour += '<td>' + rapport + '</td>';
    retour += '<td><button type="button" class="ajoutMaintenance" id="' +idM + '"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></button></td><tr>');
    return $(retour);
}

function afficherPortes(portes){
    for (var i = 1; i <= articles.length; i++) {
        $('#sesPortes').append(creerPortes(portes[i-1].numPorte, portes[i-1].type, portes[i-1].date, portes[i-1].rapport, portes[i-1].idM));
    }
    $('#sesPortes').append('<td><input class="input-group" placeholder="numero porte" type="text" id="numPorte" ></td><td><input class="input-group"  placeholder="Description " type="text" id="description" ></td><td><input class="input-group"  type="text" name="" disabled></td><td><input class="input-group"  type="text" name="" disabled></td></tr>');
    
}
function filtreCategorie(articles){
    var categorie = [];
    for (var i = 1; i <= articles.length; i++) {
        if (categorie.indexOf(articles[i-1].categorie) == -1) {
            categorie.push(articles[i-1].categorie);
        }
    }
    for (var i = 0; i < categorie.length; i++) {
        $('.nav-pills').append('<li role="presentation"  ><a href="#" class="categorie" id="' + categorie[i] + '">' + categorie[i] + '</a></li>');
    }
}

function initCatalogue(titre, articles){
    $('#catalogue').empty();
    $('#catalogue').append($('<h1>' + titre + '</h1>'));
    afficherArticles(articles);
}

function afficheArticleCategorie() {
    $('.nav .active').removeClass('active');
    $(this).addClass('active');
    if ($('section.col-md-9:visible').attr('id') != "contenu") {
        afficherContenu('#contenu');
    } else {
        $('#catalogue').addClass('fadeInDownBig');
        setTimeout(function () {
            $('#catalogue').removeClass('fadeInDownBig');
        }, 1000);
    }
    var categorie = $('.nav .active ').text();

    $.ajax({
        url: "v1/produits/categorie/" + categorie,
        type: "GET",
        dataType: "json",
        beforeSend: function (req) {
            const s = btoa(login + ":" + mdp);
            req.setRequestHeader("Authorization", "Basic " + s);
        },
        success: function (json) {
            var articles = JSON.parse(JSON.stringify(json));
            initCatalogue(categorie, articles);

            $('.enSavoirPlus').click(btnEnSavoirPlus(this));
        },
        error: function (xhr, status, errorThrown) {

            console.log("Error: " + errorThrown);
            console.log("Status: " + status);
            console.dir(xhr);
        }


    });
    ajouterEvent();
};


/* EVENT */
function btnEnSavoirPlus(btn) {
    var num = $(btn).attr('id').substring(2);
    $.ajax({
        url: "v1/produits/" + num,
        type: "GET",
        dataType: "json",
        beforeSend: function (req) {
            const s = btoa(login + ":" + mdp);
            req.setRequestHeader("Authorization", "Basic " + s);
        },
        success: function (json) {
            var articles = JSON.parse(JSON.stringify(json));
            initCatalogue(articles.libelle, articles);
            $('nav li .active').removeClass('active');
            $('nav li #' + articles.categorie).addClass('active');
        },
        error: function (xhr, status, errorThrown) {
            console.log("Error: " + errorThrown);
            console.log("Status: " + status);
            console.dir(xhr);
        }
    });
}


function ajouterEvent() {
    $('.supprimer').click(function () {
        var id = $(this).attr('id');
        if (confirm('Voulez vous supprimer cet élément ?')) {
            $.ajax({
                url: "v1/produits/" + id.substring(2),
                contentType: 'application/json',
                type: "DELETE",
                dataType: "json",
                beforeSend: function (req) {
                    const s = btoa(login + ":" + mdp);
                    req.setRequestHeader("Authorization", "Basic " + s);
                },
                /*headers: {
'Accept': 'application/json',
'Content-Type': 'application/json'
},*/
                success: function (json) {


                },
                error: function (xhr, status, errorThrown) {
                    alert("Sorry, there was a problem!");
                    console.log("Error: " + errorThrown);
                    console.log("Status: " + status);
                    console.dir(xhr);
                }
            });
            window.location.replace('index.html');
        }
    });


}



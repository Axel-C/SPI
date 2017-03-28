$(document).ready(function () {
    var login = localStorage.getItem('login');
    var mdp = localStorage.getItem('mdp');
    var telephone = localStorage.getItem('telephone');
    var alias = localStorage.getItem('alias');
    var numeroSiret = localStorage.getItem('numeroSiret');
    var idMec = localStorage.getItem('idMec');
    var role = localStorage.getItem('role');


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
    $('.ajoutMaintenance').click(function () {

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
                mettreContenueLogin($("#login").val(), $("#password").val(), data.telephone, data.alias, data.numSiret, data.id, data.role);
                window.location.replace("index.html");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#ContenuLogin .panel-warning').show();
                $('#ContenuLogin .error').empty();
                $('#ContenuLogin .error').append("<h3 class='panel-title'>Mauvais login ou mot de passe</h3>");
            }
        });
    });
    var mettreContenueLogin = function (login, mdp, telephone, alias, numeroSiret, idM, role) {
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
    var afficherContenu = function (div) {
        $('section.col-md-9:visible').hide(300);
        $(div).show(300);
    }
    var creerArticle = function (id, name, description, img, prix) {

        var retour = " <article class='thumbnail col-md-4' >" +
            "<img src='" + img + "' class='img-thumbnail img-responsive' alt='" + name + "'>" +
            "<div class='caption' >" +
            "<h3>" + name + "</h3>";
        if (prix != -1) {
            retour += "<h3>" + prix + "€ </h3>";
        }
        retour += "<a href='#' class='btn btn-info enSavoirPlus' id='id" + id + "'>En savoir plus</a>";
        if (role == "admin") {
            retour += "<a href='#' class='btn btn-danger supprimer' id='id" + id + "'>Supprimer</a>";

        }
        retour += "</div> </article>";

        return $(retour);

    }
    var creerArticleDescription = function (id, name, description, img, prix) {
        if (prix == undefined ||  prix == -1) {
            return $(" <article class='thumbnail col-md-5' id='descriptionArticle' >" +
                "<img src='" + img + "' class='img-thumbnail img-responsive' alt='" + name + "'>" +
                "<div class='caption' >" +
                "<h3>" + name + "</h3>" +
                "<p>" + description + "</p>" +
                "</div>" +
                " </article>");
        }

        return $(" <article class='thumbnail col-md-5' id='descriptionArticle' >" +
            "<img src='" + img + "' class='img-thumbnail img-responsive' alt='" + name + "'>" +
            "<div class='caption' >" +
            "<h3>" + name + "</h3>" +
            "<p>" + prix + "€ </p>" +
            "<p>" + description + "</p>" +
            "</div>" +
            " </article>");
    }
    var afficherArticles = function (articles) {

        var catalogue = $('#catalogue');
        var row = $('<div class="row">');
        for (var i = 1; i <= articles.length; i++) {
            row.append(creerArticle(articles[i - 1].idp, articles[i - 1].libelle, articles[i - 1].description, articles[i - 1].urlImage, articles[i - 1].prix));
            if (i % 3 == 0) {
                catalogue.append(row);
                var row = $('<div class="row">');
            }
        }

        catalogue.append(row);


    }
    var afficherArticle = function (articles) {
        var catalogue = $('#catalogue');
        var row = $('<div class="row">');
        row.append(creerArticleDescription(articles.idp, articles.libelle, articles.description, articles.urlImage, articles.prix));
        catalogue.append(row);
    }
    var afficherArticlesDepart = function (articles) {
        var categorie = [];
        var catalogue = $('#catalogue');
        var row = $('<div class="row">');
        for (var i = 1; i <= articles.length; i++) {
            row.append(creerArticle(articles[i - 1].idp, articles[i - 1].libelle, articles[i - 1].description, articles[i - 1].urlImage, articles[i - 1].prix));
            if (categorie.indexOf(articles[i - 1].categorie) == -1) {
                categorie.push(articles[i - 1].categorie);
            }
            if (i % 3 == 0) {
                catalogue.append(row);
                var row = $('<div class="row">');
            }
        }
        catalogue.append(row);

        for (var i = 0; i < categorie.length; i++) {
            $('.nav-pills').append('<li role="presentation"  ><a href="#" class="categorie" id="' + categorie[i] + '">' + categorie[i] + '</a></li>');
        }
    }
    var ajouterArticle = function (regex) {
        $('#catalogue .row').append(regex);
    }

    var trierCategorie = function () {
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
                $('#catalogue').empty();
                $('#catalogue').append($('<h1>' + categorie + '</h1>'));
                var articles = JSON.parse(JSON.stringify(json));
                afficherArticles(articles);
                $('nav li .active').removeClass('active');
                $(this).addClass('active');

                $('.enSavoirPlus').click(function () {
                    var num = $(this).attr('id').charAt('2');
                    $.ajax({
                        url: "v1/produits/" + num,
                        type: "GET",
                        dataType: "json",
                        beforeSend: function (req) {
                            const s = btoa(login + ":" + mdp);
                            req.setRequestHeader("Authorization", "Basic " + s);
                        },
                        success: function (json) {
                            $('#catalogue').empty();
                            var articles = JSON.parse(JSON.stringify(json));
                            $('#catalogue').append($('<h1>' + articles.libelle + '</h1>'));
                            afficherArticle(articles);
                            $('nav li .active').removeClass('active');
                            $('nav li #' + articles.categorie).addClass('active');
                        },
                        error: function (xhr, status, errorThrown) {
                            
                            console.log("Error: " + errorThrown);
                            console.log("Status: " + status);
                            console.dir(xhr);
                        }


                    })
                });
            },
            error: function (xhr, status, errorThrown) {
                
                console.log("Error: " + errorThrown);
                console.log("Status: " + status);
                console.dir(xhr);
            }


        })
        ajouterEvent();
    };

    $.ajax({
        url: "v1/produits",
        type: "GET",
        dataType: "json",
        beforeSend: function (req) {
            const s = btoa(login + ":" + mdp);
            req.setRequestHeader("Authorization", "Basic " + s);
        },
        success: function (json) {
            $('#catalogue').empty();
            $('#catalogue').append($('<h1>Les indispensables</h1>'));
            var articles = JSON.parse(JSON.stringify(json));
            console.log(articles);
            afficherArticlesDepart(articles);
            $('.nav li').on('click', trierCategorie);


            $('.enSavoirPlus').click(function () {
                var num = $(this).attr('id').charAt('2');
                $.ajax({
                    url: "v1/produits/" + num,
                    type: "GET",
                    dataType: "json",
                    beforeSend: function (req) {
                        const s = btoa(login + ":" + mdp);
                        req.setRequestHeader("Authorization", "Basic " + s);
                    },
                    success: function (json) {
                        $('#catalogue').empty();
                        var articles = JSON.parse(JSON.stringify(json));
                        $('#catalogue').append($('<h1>' + articles.libelle + '</h1>'));
                        afficherArticle(articles);
                        $('nav li .active').removeClass('active');
                        $('nav li #' + articles.categorie).addClass('active');
                    },
                    error: function (xhr, status, errorThrown) {
                        
                        console.log("Error: " + errorThrown);
                        console.log("Status: " + status);
                        console.dir(xhr);
                    }


                })
            });
        },
        error: function (xhr, status, errorThrown) {
            
            console.log("Error: " + errorThrown);
            console.log("Status: " + status);
            console.dir(xhr);
        }
    });

    //Savoir si le mec c'est un user ou autre

    $.ajax({
        url: "v1/secure/who",
        type: "GET",
        dataType: "json",
        beforeSend: function (req) {
            const s = btoa(login + ":" + mdp);
            req.setRequestHeader("Authorization", "Basic " + s);
        },
        success: function (json) {
            var user = JSON.parse(JSON.stringify(json));
            if (user.role == "admin") {
                $('.ajouterArticle').css('display', 'block');
                $('.creerCompte').css('display', 'block');
            } else {
                $.ajax({
                    url: "v1/maintenance/bu/" + idMec,
                    type: "GET",
                    dataType: "json",
                    beforeSend: function (req) {
                        const s = btoa(login + ":" + mdp);
                        req.setRequestHeader("Authorization", "Basic " + s);
                    },
                    success: function (json) {
                        var porte = JSON.parse(JSON.stringify(json));
                        if (porte.length > 1) {
                            for (var i = 0; i < porte.length; i++) {
                                $('#sesPortes').append('<tr><td>' + porte[i].numPorte + '</td>' +
                                    '<td>' + porte[i].type + '</td>' +
                                    '<td>' + porte[i].date + '</td>' +
                                    '<td>' + porte[i].rapport + '</td>' +
                                    '<td><button type="button" class="ajoutMaintenance" id="' + porte[i].idM + '"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></button></td><tr>');

                            }
                        } else {
                            $('#sesPortes').append('<tr><td>' + porte.numPorte + '</td>' +
                                '<td>' + porte.type + '</td>' +
                                '<td>' + porte.date + '</td>' +
                                '<td>' + porte.rapport + '</td>' +
                                '<td><button type="button" class="ajoutMaintenance" id="' + porte.idM + '"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span></button></td><tr>');
                        }
                        $('#sesPortes').append('<td><input class="input-group" placeholder="numero porte" type="text" id="numPorte" ></td>' +
                            '<td><input class="input-group"  placeholder="Description " type="text" id="description" ></td> ' +
                            '<td><input class="input-group"  type="text" name="" disabled></td> ' +
                            '<td><input class="input-group"  type="text" name="" disabled></td></tr>');

                        $('.ajoutMaintenance').click(function () {
                            $('#porteMaintenance').val($(this).attr('id'));
                            $('#descritpion').show();
                        });

                        $('#faireMaintenance').click(function () {
                            $.ajax({
                                url: "v1/maintenance/" + this.attributes.id.value,
                                type: "GET",
                                dataType: "json",
                                beforeSend: function (req) {
                                    const s = btoa(login + ":" + mdp);
                                    req.setRequestHeader("Authorization", "Basic " + s);
                                },
                                success: function (json) {

                                },
                                error: function (xhr, status, errorThrown) {

                                }


                            });
                        });


                    },
                    error: function (xhr, status, errorThrown) {
                        console.info("Vous n'etes pas encore connectée");
                    }
                });
            }

            afficherContenu('#contenu');
            mettreContenueLogin(login, mdp, telephone, alias, numeroSiret, idMec, role);
            $('#contenuCompte input[name=email]').val(login);
        },
        error: function (xhr, status, errorThrown) {
            console.info("Vous n'etes pas encore connectée");
        }


    })






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
    setTimeout(ajouterEvent, 500)
    

});

$(document).ready(function () {
    var login = localStorage.getItem('login');
    var mdp = localStorage.getItem('mdp');
    var telephone = localStorage.getItem('telephone');
    var alias = localStorage.getItem('alias');
    var numeroSiret = localStorage.getItem('numeroSiret');
    var idMec = localStorage.getItem('idMec');

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
                console.log(s + ", " + $("#login").val() + " : " + $("#password").val());
            },
            success: function (data) {
                login = $("#login").val();
                mdp = $("#password").val();
                afficherContenu('#contenu');
                mettreContenueLogin($("#login").val(), $("#password").val(), data.telephone, data.alias, data.numSiret, data.id);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $('#ContenuLogin .panel-warning').show();
                $('#ContenuLogin .error').empty();
                $('#ContenuLogin .error').append("<h3 class='panel-title'>Mauvais login ou mot de passe</h3>");
            }
        });
    });
    var mettreContenueLogin = function (login, mdp, telephone, alias, numeroSiret, idM) {
        $('#navProfil').empty();
        $('#navProfil').append('<a id="btnProfil" href="#">' + login + "</a><button id='deconnection' type='button'>" +
            "<span class='glyphicon glyphicon-log-out' aria-hidden='true'></span>");
        localStorage.setItem('login', login);
        localStorage.setItem('mdp', mdp);
        localStorage.setItem('telephone', telephone);
        localStorage.setItem('alias', alias);
        localStorage.setItem('numeroSiret', numeroSiret);
        localStorage.setItem('idMec', idM);
        $('#btnProfil').click(function () {
            afficherContenu('#contenuCompte');
        });

        $('#deconnection').click(function () {
            localStorage.setItem('login', "");
            localStorage.setItem('mdp', "");
            window.location.replace('index.html');
        });
    }
    var afficherContenu = function (div) {
        $('section.col-md-9:visible').hide(300);
        $(div).show(300);
    }
    var creerArticle = function (id, name, description, img, prix) {
        if (prix == undefined ||  prix == 0) {
            return $(" <article class='thumbnail col-md-4' >" +
                "<img src='" + img + "' class='img-thumbnail img-responsive' alt='" + name + "'>" +
                "<div class='caption' >" +
                "<h3>" + name + "</h3>" +
                "<a href='#' class='btn btn-info enSavoirPlus' id='id" + id + "'>En savoir plus</a>" +
                "</div>" +
                " </article>");
        }
        return $(" <article class='thumbnail col-md-4' >" +
            "<img src='" + img + "' class='img-thumbnail img-responsive' alt='" + name + "'>" +
            "<div class='caption' >" +
            "<h3>" + name + "</h3>" +
            "<h3>" + prix + "€ </h3>" +
            "<a href='#' class='btn btn-info enSavoirPlus' id='id" + id + "'>En savoir plus</a>" +
            "</div>" +
            " </article>");

    }
    var creerArticleDescription = function (id, name, description, img, prix) {
        if (prix == undefined ||  prix == 0) {
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
                            alert("Sorry, there was a problem!");
                            console.log("Error: " + errorThrown);
                            console.log("Status: " + status);
                            console.dir(xhr);
                        }


                    })
                });
            },
            error: function (xhr, status, errorThrown) {
                alert("Sorry, there was a problem!");
                console.log("Error: " + errorThrown);
                console.log("Status: " + status);
                console.dir(xhr);
            }


        })
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
                        alert("Sorry, there was a problem!");
                        console.log("Error: " + errorThrown);
                        console.log("Status: " + status);
                        console.dir(xhr);
                    }


                })
            });
        },
        error: function (xhr, status, errorThrown) {
            alert("Sorry, there was a problem!");
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
            } else{
                $.ajax({
                    url: "v1/maintenance/bu/"+idMec,
                    type : "GET" ,
                    dataType : "json" ,
                    beforeSend : function(req) {
                       const s =  btoa(login + ":" + mdp);
                       req.setRequestHeader("Authorization", "Basic " + s);
                    },
                    success : function(json){
                        var porte = JSON.parse(JSON.stringify(json));
                        if(porte.length > 1){
                            for(var i=0; i<porte.length; i++){
                                $('#sesPortes').append('<tr><td>'+porte[i].idM+'</td>'+
                                                   '<td>'+porte[i].type+'</td>'+
                                                   '<td>'+porte[i].date+'</td>'+
                                                   '<td>'+porte[i].rapport+'</td><tr>');

                            }
                        } else {
                            $('#sesPortes').append('<tr><td>'+porte.idM+'</td>'+
                                                   '<td>'+porte.type+'</td>'+
                                                   '<td>'+porte.date+'</td>'+
                                                   '<td>'+porte.rapport+'</td><tr>');
                        }
                        $('#sesPortes').append('<td><input class="input-group" placeholder="numero porte" type="text" name="numPorte" ></td>'+ 
                                            '<td><input class="input-group"  placeholder="Description " type="text" name="oldPassword" ></td> '+
                                            '<td><input class="input-group"  type="text" name="" disabled></td> '+
                                            '<td><input class="input-group"  type="text" name="" disabled></td> ');
                    } ,
                    error :  function( xhr, status, errorThrown){
                        console.info("Vous n'etes pas encore connectée");
                    }
                });   
            }
            console.log(user.role);
            afficherContenu('#contenu');
            mettreContenueLogin(login, mdp, telephone, alias, numeroSiret, idMec);
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
                    alert('ça marche');

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

                    alert('ça marche');

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


});

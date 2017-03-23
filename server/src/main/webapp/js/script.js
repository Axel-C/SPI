$(document).ready(function(){
    
    $('.nav li').click(function(){
        $('.nav .active').removeClass('active');
        $(this).addClass('active');
        afficherContenu('#contenu');
    });
   $('.categorie').click(function(event){
        var categorie = $(this).attr('id') ;
        var txt = $(this).text();
        $.ajax({
            url : "v1/produits/categorie/" + categorie ,
            type : "GET" ,
            dataType : "json" ,
            success : function(json){
                $('#catalogue').empty() ;
                console.log($(this).attr('id'));
                $('#catalogue').append($('<h1>'+ txt +'</h1>'));
                var articles = JSON.parse(JSON.stringify(json));
                afficherArticles(articles);
                 $('nav li .active').removeClass('active');
        $(this).addClass('active');
            } ,
            error :  function( xhr, status, errorThrown  ){
                alert( "Sorry, there was a problem!" );
        console.log( "Error: " + errorThrown );
        console.log( "Status: " + status );
        console.dir( xhr );
            }
            
            
        })
        
    })
        
    $('#btnConnection').click(function(){
        afficherContenu('#ContenuLogin');        
    });
    $('#nousContacter').click(function(){
        afficherContenu('#contenuContact');
    });
    
    var afficherContenu = function(div){
        $('section.col-md-9:visible').hide(300);
        $(div).show(300); 
    }
    var creerArticle = function( id , name , description  , img){
         return $(" <article class='thumbnail col-md-4'>"+
                  "<img src='"+ img + "' class='img-thumbnail img-responsive' alt='"+ name + "'>"+
                  "<div class='caption' >"+
                    "<h3>"+ name +"</h3>"+
                 "<a href='"+id+"' class='btn btn-info' >En savoir plus</a>"+
                "</div>"+
                   " </article>");
    }        
    var afficherArticles = function(articles){
        
        var catalogue = $('#catalogue');
        var row = $('<div class="row">');
        for(var i=1; i<=articles.length; i++){
            row.append(creerArticle(articles[i-1].id, articles[i-1].libelle, articles[i-1].description, articles[i-1].urlImage));
            if(i%3 == 0){
                catalogue.append(row);
                var row = $('<div class="row">');
            }
        }
        catalogue.append(row);
    }  
    var ajouterArticle = function(regex){
        $('#catalogue .row').append(regex);
    }

    var article = creerArticle(1 , 'Moteur Basse performance' , "" , "http://www.moteur-diesel-marin-midif.fr/Files/111759/Img/10/moteur-diesel-inboard-midif-MD1760-52-cv-zoom.png" );
    
    article.appendTo('#catalogue .row');
    
    
    $.ajax({
      url: "v1/produits",
      type: "GET",
      dataType : "json",
      success: function( json ) {
        $('#catalogue').empty();
        $('#catalogue').append($('<h1>Les indispensables</h1>'));
        var articles = JSON.parse(JSON.stringify(json));  
        console.log(articles);
        afficherArticles(articles);
      },
      error: function( xhr, status, errorThrown ) {
        alert( "Sorry, there was a problem!" );
        console.log( "Error: " + errorThrown );
        console.log( "Status: " + status );
        console.dir( xhr );
      }
    }); // end ajax
});



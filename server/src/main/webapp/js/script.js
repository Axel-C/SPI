$(document).ready(function(){
        
    var creerArticle = function( id , name , description  , img){
         return $(" <article class='thumbnail col-md-4'>"+
                  "<img src='"+ img + "' class='img-thumbnail img-responsive' alt='"+ name + "'>"+
                  "<div class='caption' >"+
                    "<h3>"+ name +"</h3>"+
                 "<a href='"+id+"' class='btn btn-info' >En savoir plus</a>"+
                "</div>"+
                   " </article>");
    }        
        
    var ajouterArticle = function(regex){
        $('#catalogue .row').append(regex);
    }

    var article = creerArticle(1 , 'Moteur Basse performance' , "" , "http://www.moteur-diesel-marin-midif.fr/Files/111759/Img/10/moteur-diesel-inboard-midif-MD1760-52-cv-zoom.png" );
    
    article.appendTo('#catalogue .row');
    
    $('.categorie1').click(function(){
        console.info('within list books !');
        $.ajax({
          url: "http://localhost:8080/v1/produits",
          type: "GET",
          dataType : "json",
          success: function( json ) {
              console.info('within list books !');
            $('#catalogue .row').empty();
            console.log(JSON.stringify(json));
            var articles = JSON.stringify(json);  
            ajouterArticle(creerArticle(articles.id, articles.libelle, articles.description, articles.urlImage));
          },
          error: function( xhr, status, errorThrown ) {
            alert( "Sorry, there was a problem!" );
            console.log( "Error: " + errorThrown );
            console.log( "Status: " + status );
            console.dir( xhr );
          },
          complete: function( xhr, status ) {
            console.log('ajax request completed !');
          }
        }); // end ajax
    });
});



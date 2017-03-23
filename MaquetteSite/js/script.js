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
    
    article.appendTo('#catalogue .row')



})


$(document).ready(function(){
    $('nav li').click(function(){
        $('nav li .active').removeClass('active');
        $(this).addClass('active');
    })
    
    
   /* $('.categorie').click(function(event){
        console.log($(this).attr('id'));
        $.ajax({
            url : "v1/produits/" + $(this).attr('id') ,
            type : "GET" ,
            dataType : "json" ,
            success : function(json){
                $('#catalogue').empty() ;
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
        
    })*/
        
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
        for(var i=1; i<articles.length; i++){
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
        var articles = JSON.parse(JSON.stringify(json));  
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



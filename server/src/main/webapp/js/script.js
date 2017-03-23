$(document).ready(function(){
    
   
 
        
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
    var afficherArticlesDepart = function(articles){
        var categorie = [];
        var catalogue = $('#catalogue');
        var row = $('<div class="row">');
        for(var i=1; i<=articles.length; i++){
            row.append(creerArticle(articles[i-1].id, articles[i-1].libelle, articles[i-1].description, articles[i-1].urlImage));
            if(categorie.indexOf(articles[i-1].categorie) == -1){
                categorie.push(articles[i-1].categorie);   
            }
            if(i%3 == 0){
                catalogue.append(row);
                var row = $('<div class="row">');
            }
        }
        catalogue.append(row);
        
        for(var i=0; i<categorie.length; i++){
            $('.nav-pills').append('<li role="presentation"  ><a href="#" class="categorie" id="'+categorie[i]+'">'+categorie[i]+'</a></li>');
        }
    }  
    var ajouterArticle = function(regex){
        $('#catalogue .row').append(regex);
    }
    
    var trierCategorie = function(){
        $('.nav .active').removeClass('active');
        $(this).addClass('active');
        if( $('section.col-md-9:visible').attr('id') != "contenu" ){
            afficherContenu('#contenu');
        }else{
            //$('#catalogue').hide();
            $('#catalogue').addClass('fadeInDownBig');
            setTimeout(function() { $('#catalogue').removeClass('fadeInDownBig'); } , 1000) ;
        }
        var categorie = $('.nav .active ').text() ;
        
        $.ajax({
            url : "v1/produits/categorie/" + categorie ,
            type : "GET" ,
            dataType : "json" ,
            success : function(json){
                $('#catalogue').empty() ;
                $('#catalogue').append($('<h1>'+ categorie +'</h1>'));
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
    };
    
    $.ajax({
      url: "v1/produits",
      type: "GET",
      dataType : "json",
      success: function( json ) {
        $('#catalogue').empty();
        $('#catalogue').append($('<h1>Les indispensables</h1>'));
        var articles = JSON.parse(JSON.stringify(json));  
        console.log(articles);
        afficherArticlesDepart(articles);
        $('.nav li').on('click' , trierCategorie) ;
      },
      error: function( xhr, status, errorThrown ) {
        alert( "Sorry, there was a problem!" );
        console.log( "Error: " + errorThrown );
        console.log( "Status: " + status );
        console.dir( xhr );
      }
    });
    
});



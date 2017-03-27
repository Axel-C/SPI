$(document).ready(function(){
    $('#modifCompte').click(function(){
        if($('#contenuCompte input[name=password]').val() !== $('#contenuCompte input[name=password]').val()){
            alert('Le mots de passe et la confirmation ne sont pas identique');
            
        }else if($('#contenuCompte input[name=email]').val() === "" ){
               alert('Adresse email vide');
            
        }else{
        
        $.ajax({
                url : "v1/produits",
                type : "PUT" ,
               dataType : "json" ,
               beforeSend : function(req) {
                   const s =  btoa(login + ":" + mdp);
                   req.setRequestHeader("Authorization", "Basic " + s);
               },
               headers: { 
                        'Accept': 'application/json',
                        'Content-Type': 'application/json' 
                    },
                data : JSON.stringify({
                          "email": $('#contenuCompte input[name=email]').val() ,
                        "password": $('#contenuCompte input[name=password]').val() ,
                        "numSiret" : numeroSiret ,
                        "alias" : alias ,
                        "telephone" : telephone    
                       
                }),
                 
                success : function(json){
                    alert('ça marche');
                    
                } ,
               error: function( xhr, status, errorThrown ) {
                   alert( "Sorry, there was a problem!" );
                   console.log( "Error: " + errorThrown );
                   console.log( "Status: " + status );
                   console.dir(xhr);
               } 
            });
        }
        
        
    })
    
    
    
});
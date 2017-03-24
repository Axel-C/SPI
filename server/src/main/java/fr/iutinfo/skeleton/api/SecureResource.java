package fr.iutinfo.skeleton.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


@Path("/secure")
public class SecureResource {
    final static Logger logger = LoggerFactory.getLogger(SecureResource.class);

    @GET
    @Path("/who")
    public User secureWhoAmI(@Context SecurityContext context) {
        if(context.getUserPrincipal().equals(User.anonymous))
    	return (User) context.getUserPrincipal();
        else{
        	throw new NotAuthorizedException(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    @GET
    @Path("/byannotation")
    @RolesAllowed({"user"})
    public User secureByAnnotation(@Context SecurityContext context) {
        return (User) context.getUserPrincipal();
    }

}

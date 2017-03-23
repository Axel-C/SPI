package fr.iutinfo.skeleton.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UtilisateursRessource {
	private static int cpt = 0;

	@Context
	public UriInfo uriInfo;

	public UtilisateursRessource() {
	}

	// Hashmap pour stocker les différents utilisateurs
	private static Map<Integer, Utilisateurs> users = new HashMap<>();

	/**
	 * Méthode gérant les requètes POST/
	 * 
	 * @param user
	 * @return 201 si OK
	 */
	@POST
	public Response createUtilisateurs(Utilisateurs user) {
		user.setId(getCpt());
		if (users.containsKey(user.getId())) {
			return Response.status(Response.Status.CONFLICT).build();
		} else {
			users.put(user.getId(), user);
			URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + user.getId()).build();
			return Response.created(instanceURI).build();
		}

	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createUtilisateur(@FormParam("nom") String nom, @FormParam("prenom") String prenom,
			@FormParam("mdp") String mdp, @FormParam("email") String email, @FormParam("adresse") String adresse,
			@FormParam("validation") boolean validation, @FormParam("role") String role,
			@FormParam("numSiret") String numSiret, @FormParam("telephone") String telephone) {
		Utilisateurs util = new Utilisateurs(getCpt(), nom, prenom, mdp, email, adresse, validation, role, numSiret, telephone);
		users.put(util.getId(), util);
		URI instanceURI = uriInfo.getAbsolutePathBuilder().path(""+util.getId()).build();
		return Response.created(instanceURI).build();
	}
	
	@GET
	public List<Utilisateurs> getUsers(){
		return new ArrayList<Utilisateurs>(users.values());
	}
	
	@GET
	@Path("/{id}")
	@Produces({"application/json","application/xml"})
	public Utilisateurs getUser(@PathParam("id") Integer id){
		if(!users.containsKey(id)){
			throw new NotFoundException();
		}else{
			return users.get(id);
		}
	}
	
	@PUT
	@Path("/{id}")
	public Response modifyUtilisateurs(@PathParam("id") Integer id, Utilisateurs prod){
		if(!users.containsKey(id)){
			throw new NotFoundException();
		}else{
			users.put(id, prod);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteUtilisateurs(@PathParam("id") Integer id){
		if(!users.containsKey(id)){
			throw new NotFoundException();
		}else{
			users.remove(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		}			
	}
	
	private int getCpt() {
		return cpt++;
	}
}

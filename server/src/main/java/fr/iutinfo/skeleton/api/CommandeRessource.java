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

@Path("/commandes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommandeRessource {

	private static int cpt = 0;

	@Context
	public UriInfo uriInfo;

	// Hashmap pour stocker les différentes Commandes
	private static Map<Integer, Commandes> command = new HashMap<>();

	/**
	 * Une ressource doit avoir un constructeur vide (sans argument... du coup)
	 */
	public CommandeRessource() {
	}

	/**
	 * Méthode de création d'une commande. Prend en charge les requètes HTTP POST.
	 * 
	 * @param instance
	 *            de la commande à créer
	 * @return Response, code de retour 201 si création est faite et 409 si il
	 *         existe déjà
	 */
	@POST
	public Response createCommandes(Commandes Commandes) {
		Commandes.setIdc(getCpt());
		if (command.containsKey(Commandes.getIdc())) {
			return Response.status(Response.Status.CONFLICT).build();
		} else {
			command.put(Commandes.getIdc(), Commandes);
			URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + Commandes.getIdc()).build();
			return Response.created(instanceURI).build();
		}

	}

	/**
	 * Méthode de création d'une commande. Prend en charge les requètes HTTP POST
	 * 
	 * @param id (id de l'utilisateur)
	 * @param idp (id du produit)
	 * @param prixTotal
	 * @return Response le corps de réponse est vide, le code de retour HTTP est
	 *         fixé à 201 si la création est faite. URI de la ressource est renvoyé en cas de succès.
	 */
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createTask(@FormParam("id") int id, @FormParam("idp") int idp,
			@FormParam("prixTotal") float prixTotal) {
		if(!UtilisateursRessource.users.containsKey(id) && !ProduitsRessource.products.containsKey(idp))
			throw new NotFoundException();
		
		Commandes com = new Commandes(getCpt(),id,idp,prixTotal);
		command.put(com.getIdc(), com);
		URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + com.getIdc()).build();
		return Response.created(instanceURI).build();
	}

	/**
	 * Récupération de tous les Commandes créés.
	 * 
	 * @return une ArrayList contenant tous les Commandes
	 */
	@GET
	public List<Commandes> getCommandes() {
		return new ArrayList<Commandes>(command.values());
	}

	/**
	 * Méthode qui prend en charge les requètes HTTP GET sur /Commandes/{idc}
	 * 
	 * @param idc
	 * @return le produit demandé
	 * 
	 */
	@GET
	@Path("/{idc}")
	@Produces({ "application/json", "application/xml" })
	public Commandes getProduit(@PathParam("idc") Integer idc) {
		if (!command.containsKey(idc)) {
			throw new NotFoundException();
		} else {
			return command.get(idc);
		}
	}

	private int getCpt() {
		return cpt++;
	}
	
	/**
	 * 
	 * Méthode prenant en charge les requètes HTTP PUT sur /Commandes/{idc}
	 * 
	 * @param id
	 * @param prod
	 * @return un code de retour HTTP, pas de contenu cependant. Si l'id n'existe pas on renvoie 404
	 */
	@PUT
	@Path("/{idc}")
	public Response modifyCommandes(@PathParam("idc") Integer idc, Commandes com){
		if(!command.containsKey(idc)){
			throw new NotFoundException();
		}else{
			command.put(idc, com);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}
	
	/**
	 * 
	 * Méthode prenant en charge les requètes HTTP DELETE sur /Commandes/{idc}
	 * 
	 * @param idc
	 * @return 
	 */
	@DELETE
	@Path("/{idc}")
	public Response deleteCommandes(@PathParam("idc") Integer idc){
		if(!command.containsKey(idc)){
			throw new NotFoundException();
		}else{
			command.remove(idc);
			return Response.status(Response.Status.NO_CONTENT).build();
		}			
	}
	
}

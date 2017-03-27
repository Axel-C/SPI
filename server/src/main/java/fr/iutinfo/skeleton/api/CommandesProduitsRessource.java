package fr.iutinfo.skeleton.api;

import java.net.URI;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/commandesProduits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommandesProduitsRessource {



	@Context
	public UriInfo uriInfo;

	private static CommandeProduitsDao dao = BDDFactory.getDbi().open(CommandeProduitsDao.class);
	
	/**
	 * Une ressource doit avoir un constructeur vide (sans argument... du coup)
	 */
	public CommandesProduitsRessource() throws SQLException{
		if(!BDDFactory.tableExist("commandesProduit")){
			dao.createCommandeProduitsTable();
		}
	}

	/**
	 * Méthode de création d'une commandeProduits. Prend en charge les requètes HTTP POST.
	 * 
	 * @param instance
	 *            de la commande à créer
	 * @return Response, code de retour 201 si création est faite et 409 si il
	 *         existe déjà
	 */
	@POST
	public Response createCommandes(CommandeProduits commandesP) throws SQLIntegrityConstraintViolationException {
		if (dao.all().contains(commandesP)){
		dao.insert(commandesP);
		return Response.status(Response.Status.CONFLICT).build();
		}else {
			URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + commandesP.getIdc()).build();
			return Response.created(instanceURI).build();
		}
		
	}

	/**
	 * Méthode de création d'une commandeProduits. Prend en charge les requètes HTTP POST
	 * 
	 * @param idc (id de la commande)
	 * @param idp (id du produit)
	 * @param prixTotal
	 * @return Response le corps de réponse est vide, le code de retour HTTP est
	 *         fixé à 201 si la création est faite. URI de la ressource est renvoyé en cas de succès.
	 */
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createTask(@FormParam("idc") int idc, @FormParam("idp") int idp,
			@FormParam("nombre") int nombre) {
//		if(!UtilisateursRessource.users.containsKey(id) && !ProduitsRessource.products.containsKey(idp))
//			throw new NotFoundException();
		
		CommandeProduits com = new CommandeProduits(idc,idp,nombre);
		//command.put(com.getIdc(), com);
		URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + com.getIdc()).build();
		return Response.created(instanceURI).build();
	}

	/**
	 * Récupération de tous les CommandesProduits créés.
	 * 
	 * @return une ArrayList contenant toutes les CommandesProduits
	 */
	@GET
	public List<CommandeProduits> getCommandes() {
		return new ArrayList<CommandeProduits>(dao.all());
	}

	/**
	 * Méthode qui prend en charge les requètes HTTP GET sur /commandesProduits/{idc}
	 * 
	 * @param idc
	 * @return la commandeProduits demandé
	 * 
	 */
	@GET
	@Path("/{idc}")
	@Produces({ "application/json", "application/xml" })
	public CommandeProduits getCommandeProduitbyIdc(@PathParam("idc") Integer idc) {
		
		if (dao.findByIdc(idc)==null) {
			throw new NotFoundException();
		} else {
			return dao.findByIdc(idc);
		}
	}

	@GET
	@Path("/{idp}")
	@Produces({ "application/json", "application/xml" })
	public CommandeProduits getCommandeProduitbyIdp(@PathParam("idp") Integer idp) {
		
		if (dao.findByIdp(idp)==null) {
			throw new NotFoundException();
		} else {
			return dao.findByIdc(idp);
		}
	}

	
	/**
	 * 
	 * Méthode prenant en charge les requètes HTTP PUT sur /commandesProduits/{idc}
	 * 
	 * @param id
	 * @param prod
	 * @return un code de retour HTTP, pas de contenu cependant. Si l'id n'existe pas on renvoie 404
	 */
/*	@PUT
	@Path("/{idc}")
	public Response modifyCommandes(@PathParam("idc") Integer idc, Commandes com){
		
		if(!command.containsKey(idc)){
			throw new NotFoundException();
		}else{
			command.put(idc, com);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}
*/	
	/**
	 * 
	 * Méthode prenant en charge les requètes HTTP DELETE sur /commandes/{idc}
	 * 
	 * @param idc
	 * @return 
	 * @return 
	 */
	@DELETE
	@Path("/{idc}")
	public Response deleteCommandes(@PathParam("idc") Integer idc){
		if(dao.findByIdc(idc)==null){
			throw new NotFoundException();
		}
		dao.delete(idc);	
		return Response.status(Response.Status.NO_CONTENT).build();
		
		
	}
	
}



package fr.iutinfo.skeleton.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
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

@Path("/maintenance")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MaintenanceRessource {
	private static int cpt = 0;

	@Context
	public UriInfo uriInfo;

	public MaintenanceRessource() {
	}

	// Hashmap pour stocker les différents utilisateurs
	protected static Map<Integer, Maintenance> maintenances = new HashMap<>();

	/**
	 * Méthode gérant les requètes POST/
	 * 
	 * @param user
	 * @return 201 si OK
	 */
	@POST
	public Response createMaintenance(Maintenance mnt) {
		mnt.setIdM(getCpt());
		if (maintenances.containsKey(mnt.getIdM())) {
			return Response.status(Response.Status.CONFLICT).build();
		} else {
			maintenances.put(mnt.getIdM(), mnt);
			URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + mnt.getIdM()).build();
			return Response.created(instanceURI).build();
		}
	}

	/**
	 * 
	 * Méthode prend en charge les méthodes HTTP POST.
	 * 
	 * @param type
	 *            de maintenance
	 * @param idM
	 *            id de la maintenance à effectuer
	 * @param idPro
	 *            id du produit utilisé pour la maintenance
	 * @param idUser
	 *            id du client
	 * @param rapport
	 *            de mission
	 * @param date
	 *            de la maintenance
	 * @return une Response contenant les infos sur le succès ou l'erreur
	 */
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public Response createMaintenance(@FormParam("type") String type, @FormParam("idPro") int idPro,
			@FormParam("idUser") int idUser, @FormParam("rapport") String rapport, @FormParam("date") String date) {

		// On vérifie que l'utilisateur et le produit existe bien.
//		if (!UtilisateursRessource.users.containsKey(idUser))
//			throw new NotFoundException();
//		if (!ProduitsRessource.products.containsKey(idPro))
//			throw new NotFoundException();

		Maintenance mnt = new Maintenance(type, getCpt(), idPro, idUser, rapport, date);
		maintenances.put(mnt.getIdM(), mnt);
		URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + mnt.getIdM()).build();
		return Response.created(instanceURI).build();
	}

	/**
	 * Prend en charge HTTP GET sur /maintenance/{idM}
	 * 
	 * @return la liste des utilisateurs
	 */
	@GET
	public List<Maintenance> getMaintenances() {
		return new ArrayList<Maintenance>(maintenances.values());
	}

	@GET
	@Path("/{idM}")
	@Produces({ "application/json", "application/xml" })
	public Maintenance getMaintenance(@PathParam("idM") Integer id) {
		if (!maintenances.containsKey(id)) {
			throw new NotFoundException();
		} else {
			return maintenances.get(id);
		}
	}

	@PUT
	@Path("/{idM}")
	public Response modifyMaintenance(@PathParam("idM") Integer id, Maintenance prod) {
		if (!maintenances.containsKey(id)) {
			throw new NotFoundException();
		} else {
			maintenances.put(id, prod);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}

	@DELETE
	@Path("/{idM}")
	public Response deleteMaintenance(@FormParam("idM") Integer id) {
		if (!maintenances.containsKey(id)) {
			throw new NotFoundException();
		} else {
			maintenances.remove(id);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}

	private int getCpt() {
		return cpt++;
	}
}

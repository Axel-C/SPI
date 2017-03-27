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
	private MaintenanceDao dao = BDDFactory.getDbi().open(MaintenanceDao.class);	
	
	/**
	 * Méthode gérant les requètes POST/
	 * 
	 * @param mnt
	 * @return 201 si OK
	 */
	@POST
	public Response createMaintenance(Maintenance mnt) {
		if (dao.all().contains(mnt))
			return Response.status(Response.Status.CONFLICT).build();
		else {
			int id = dao.insert(mnt);
			mnt.setIdM(id);
			URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + mnt.getIdM()).build();
			return Response.created(instanceURI).build();
		}
	}

	/**
	 * Prend en charge HTTP GET sur /maintenance
	 * 
	 * @return la liste des utilisateurs
	 */
	@GET
	public List<Maintenance> getMaintenances() {
		return new ArrayList<Maintenance>(dao.all());
	}

	
	/**
	 * Prend en charge les requètes HTTP GET sur /maintenance/{idM}
	 * 
	 * @param id
	 * @return un utilisateur 
	 */
	@GET
	@Path("/{idM}")
	@Produces({ "application/json", "application/xml" })
	public Maintenance getMaintenance(@PathParam("idM") Integer id) {
		if (dao.all.) {
			throw new NotFoundException();
		} else {
			return maintenances.get(id);
		}
	}

/*	@PUT
	@Path("/{idM}")
	public Response modifyMaintenance(@PathParam("idM") Integer id, Maintenance prod) {
		if (!maintenances.containsKey(id)) {
			throw new NotFoundException();
		} else {
			maintenances.put(id, prod);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}
*/
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

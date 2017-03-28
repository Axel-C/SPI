package fr.iutinfo.skeleton.api;

import java.net.URI;
import java.sql.SQLException;
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

@Path("/porte")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PorteResource {
	private PorteDao dao = BDDFactory.getDbi().open(PorteDao.class);	
	
	@Context
	public UriInfo uriInfo;

	public PorteResource() throws SQLException{
		if (!BDDFactory.tableExist("porte")) {
			dao.createPorteTable();
			//dao.insert(new Porte("t", 1, 1, 1, "test", "08/01/2015"));
		}
	}

	
	
	
	/**
	 * Méthode gérant les requètes POST/
	 * 
	 * @param porte
	 * @return 201 si OK
	 */
	@POST
	public Response createPorte(Porte porte) {
		if (dao.all().contains(porte))
			return Response.status(Response.Status.CONFLICT).build();
		else {
			int id = dao.insert(porte);
			porte.setId(id);
			URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + porte.getId()).build();
			return Response.created(instanceURI).build();
		}
	}
	
	

	/**
	 * Prend en charge HTTP GET sur /porte
	 * 
	 * @return la liste des utilisateurs
	 */
	@GET
	public List<Porte> getPortes() {
		return new ArrayList<Porte>(dao.all());
	}

	
	/**
	 * Prend en charge les requètes HTTP GET sur /porte/{idM}
	 * 
	 * @param id
	 * @return un utilisateur 
	 */
	@GET
	@Path("/{id}")
	@Produces({ "application/json", "application/xml" })
	public Porte getPorte(@PathParam("id") Integer id) {
		if (dao.findByid(id) == null) {
			throw new NotFoundException();
		} else {
			return dao.findByid(id);
		}
	}
	@GET
	@Path("/byuser/{idUser}")
	@Produces({ "application/json", "application/xml" })
	public List<Porte> getPortebyUser(@PathParam("idUser") Integer idUser) {
		if (dao.findByidUser(idUser) == null) {
			throw new NotFoundException();
		} else {
			return dao.findByidUser(idUser);
		}
	}
	
/*	@PUT
	@Path("/{idM}")
	public Response modifyPorte(@PathParam("idM") Integer id, Porte prod) {
		if (!portes.containsKey(id)) {
			throw new NotFoundException();
		} else {
			portes.put(id, prod);
			return Response.status(Response.Status.NO_CONTENT).build();
		}
	}
*/
	@DELETE
	@Path("/{id}")
	public Response deletePorte(@FormParam("id") Integer id) {
		dao.delete(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}

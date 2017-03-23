package fr.iutinfo.skeleton.api;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	@Context
	public Context context;

	private static UtilisateurDao dao = BDDFactory.getDbi().open(UtilisateurDao.class);

	public UtilisateursRessource() throws SQLException {
		if (!BDDFactory.tableExist("utilisateurs")) {
			dao.createUtilisateursTable();
		}
	}

	/**
	 * Méthode gérant les requètes POST/
	 * 
	 * @param user
	 * @return 201 si OK
	 */
	@POST
	public Response createUtilisateurs(Utilisateurs user) {
		user.setId(getCpt());
		if (dao.all().contains(user)) {
			return Response.status(Response.Status.CONFLICT).build();
		} else {
			dao.insert(user);
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
		Utilisateurs util = new Utilisateurs(getCpt(), nom, prenom, mdp, email, adresse, validation, role, numSiret,
				telephone);
		dao.insert(util);
		URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + util.getId()).build();
		return Response.created(instanceURI).build();
	}

	@GET
	public List<Utilisateurs> getUsers() {
		
		return new ArrayList<Utilisateurs>(dao.all());
	}

	@GET
	@Path("/{id}")
	@Produces({ "application/json", "application/xml" })
	public Utilisateurs getUser(@PathParam("id") Integer id) {
		// dao.all().stream().filter((u->{return u.getId() ==
		// id;})).collect(Collectors.toList());
		if (dao.findById(id) != null)
			return dao.findById(id);
		else
			throw new NotFoundException();

	}
	
	@PUT
	@Path("/{id}")
	public Response modifyUtilisateurs(@PathParam("id") Integer id, Utilisateurs prod) {
		if (dao.findById(id) == null) {
			dao.updateUtilisateur(id);
			return Response.noContent().build();
		}else{
			throw new NotFoundException();
		}
	}

	@DELETE
	@Path("/{id}")
	public Response deleteUtilisateurs(@PathParam("id") Integer id) {
		if (dao.findById(id) != null) {
			dao.delete(id);
			return Response.noContent().build();
		} else {
			throw new NotFoundException();
		}
	}

	private int getCpt() {
		return cpt++;
	}
}

package fr.iutinfo.skeleton.api;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/produits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProduitsRessource {
	final static Logger logger = LoggerFactory.getLogger(ProduitsRessource.class);
	@Context
	public UriInfo uriInfo;

	// Hashmap pour stocker les différents produits
	private static ProduitsDao dao = BDDFactory.getDbi().open(ProduitsDao.class);

	/**
	 * Une ressource doit avoir un constructeur vide (sans argument... du coup)
	 */
	public ProduitsRessource() throws SQLException {
		// dao.dropProduitsTable();
		logger.debug("TABLE produits PAS DROPPED");
		if (!BDDFactory.tableExist("produits")) {
			dao.createProduitsTable();
		}
	}

	/**
	 * Méthode de création d'un produit. Prend en charge les requètes HTTP POST.
	 * 
	 * @param instance
	 *            du produit à créer
	 * @return Response, code de retour 201 si création est faite et 409 si il
	 *         existe déjà
	 */
	@POST
	public Response createProduits(Produits produits, @Context SecurityContext context) {
		User u = (User) context.getUserPrincipal();
		if (u.getRole().equals("admin")) {
			if (dao.all().contains(produits))
				return Response.status(Response.Status.CONFLICT).build();
			else {
				int id = dao.insert(produits);
				produits.setIdp(id);
				URI instanceURI = uriInfo.getAbsolutePathBuilder().path("" + produits.getIdp()).build();
				return Response.created(instanceURI).build();
			}
		} else {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	/**
	 * Récupération de tous les produits créés.
	 * 
	 * @return une ArrayList contenant tous les produits
	 */
	@GET
	public List<Produits> getProduits(@Context SecurityContext context) {
		User u = (User) context.getUserPrincipal();

		List<Produits> jack = new ArrayList<Produits>(dao.all());
		if (u.isAnonymous()) {
			jack = jack.stream().map(p -> {
				p.setPrix(-1f);
				logger.debug("LIST PRODUIT: " + p.toString());
				return p;
			}).collect(Collectors.toList());
		}

		return jack;
	}

	/**
	 * Méthode qui prend en charge les requètes HTTP GET sur /produits/{id}
	 * 
	 * @param id
	 * @return le produit demandé
	 * 
	 */
	@GET
	@Path("/{id}")
	@Produces({ "application/json", "application/xml" })
	public Produits getProduit(@PathParam("id") Integer id, @Context SecurityContext context) {
		if (!((User) context.getUserPrincipal()).isAnonymous()) {
			if (dao.findByIdp(id) == null) {
				throw new NotFoundException();
			} else {
				return dao.findByIdp(id);
			}
		} else {
			Produits p = dao.findByIdp(id);
			if (p == null)
				throw new NotFoundException();
			else {
				p.setPrix(0f);
				return p;
			}

		}
	}

	@GET
	@Path("/categorie/{categorie}")
	@Produces({ "application/json", "application/xml" })
	public List<Produits> getProduitByCategorie(@PathParam("categorie") String categorie,
			@Context SecurityContext context) {
		List<Produits> jack = dao.findByCategorie(categorie);
		User u = (User) context.getUserPrincipal();
		if (u.isAnonymous()) {
			jack = jack.stream().map(p -> {
				p.setPrix(-1f);
				logger.debug("LIST PRODUIT: " + p.toString());
				return p;
			}).collect(Collectors.toList());
		}

		return jack;
	}

	/**
	 * 
	 * Méthode prenant en charge les requètes HTTP PUT sur /produits/{id}
	 * 
	 * @param id
	 * @param prod
	 * @return un code de retour HTTP, pas de contenu cependant. Si l'id
	 *         n'existe pas on renvoie 404
	 */
	/*
	 * @PUT
	 * 
	 * @Path("/{id}") public Response modifyProduits(@PathParam("id") Integer
	 * id, Produits prod) { if () { throw new NotFoundException(); } else {
	 * products.put(id, prod); return
	 * Response.status(Response.Status.NO_CONTENT).build(); } }
	 */

	/**
	 * 
	 * Méthode prenant en charge les requètes HTTP DELETE sur /produits/{id}
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("/{id}")
	public Response deleteProduits(@PathParam("id") Integer id, @Context SecurityContext context) {
		User u = (User) context.getUserPrincipal();
		if (u.getRole().equals("admin")) {
			if (dao.findByIdp(id) == null) {
				throw new NotFoundException();
			} else {
				dao.delete(id);
				return Response.status(Response.Status.NO_CONTENT).build();
			}
		}else{
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
}

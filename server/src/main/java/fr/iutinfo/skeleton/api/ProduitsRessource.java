package fr.iutinfo.skeleton.api;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

public class ProduitsRessource {

	@Context
	public UriInfo uriInfo;
	
	//Hashmap pour stocker les différents produits
	private static Map<Integer, Produits> products = new HashMap<>();
	
	/**
	 * Une ressource doit avoir un constructeur vide (sans argument... du coup)
	 */
	public ProduitsRessource() {
	}
	
	

}

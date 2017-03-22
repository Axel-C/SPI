package fr.iutinfo.skeleton.api;

import java.lang.reflect.Field;
import java.util.HashMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.eclipse.persistence.oxm.MediaType;
import org.junit.Before;
import org.junit.Test;

public class ProduitsRessourceTest {

	private WebTarget target;

	@Before
	public void setup() throws Exception {

		Client c = ClientBuilder.newClient();

		target = c.target("http://localhost:8080/");

		Field field = ProduitsRessource.class.getDeclaredField("products");
		field.setAccessible(true);
		field.set(null, new HashMap<>());

	}

	/**
	 * Test de création d'un produit (retour HTTP et envoie de l'URI de la
	 * nouvelle instance)
	 */
	@Test
	public void testCreateProduits() {
		Produits prod = new Produits("Ecrou",0,"E54POI", 2.53f, "Ecrou d'une certaine taille et d'un certain poids", "Ecrou");
		// Conversion en JSON		
		//Entity<Produits> prodEntity = Entity.entity(prod, MediaType.APPLICATION_JSON);
		
	}
}

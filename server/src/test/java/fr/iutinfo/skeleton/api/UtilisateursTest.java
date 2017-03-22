package fr.iutinfo.skeleton.api;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilisateursTest {

	@Test
	public void test() {
		Utilisateurs u = new Utilisateurs(1,"oulianov","vladimir","cccp","soviet@urss.ru","kremlin"
				,true,"siperadmain","123456789","0835656565");
		Utilisateurs u2 = new Utilisateurs(1,"djougashvili","josef","cccp","soviet@urss.ru","kremlin"
				,true,"users","123456789","0835656565");
		Utilisateurs u3 = new Utilisateurs(1,"oulianov","vladimir","cccp","soviet@urss.ru","kremlin"
				,true,"siperadmain","123456789","0835656565");
		assertFalse(u.equals(u2));
		assertTrue(u.equals(u3));
	}

}

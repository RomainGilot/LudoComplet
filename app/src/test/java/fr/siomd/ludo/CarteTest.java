package fr.siomd.ludo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import junit.framework.TestCase;

import fr.siomd.ludo.entity.Carte;

public class CarteTest extends TestCase {

    private Carte uneCarte;

    @Before
    public void setUp() throws Exception {
        uneCarte = new Carte("Carreau", "Roi");
    }

    @After
    public void tearDown() throws Exception {
        uneCarte = null;
    }

    @Test
    public void testGetCouleur() {
        String result = uneCarte.getCouleur();
        String expResult = "Carreau";
        assertEquals(expResult, result);

    }
    @Test
    public void testGetFigure() {
        String result = uneCarte.getFigure();
        String expResult = "Roi";
        assertEquals(expResult, result);
    }
    @Test
    public void testGetValeur() {
        int result = uneCarte.getValeur();
        int expResult = 13;
        assertEquals(expResult, result);
    }
    @Test
    public void testGetNom() {
        String result = uneCarte.getNom();
        String expResult = "Roi de Carreau";
        assertEquals(expResult, result);
    }
    @Test
    public void testGetNomImg() {
        String result = uneCarte.getNomImg();
        String expResult = "ca13";
        assertEquals(expResult, result);
    }
    @Test
    public void testIsAtout() {
        Boolean result = uneCarte.isAtout("Carreau");
        Boolean expResult = true;
        assertEquals(expResult, result);
    }
}
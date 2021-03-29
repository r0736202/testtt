package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Speler;

public class SpelerTest {
	
	private String naam;
	private String anderenaam;
	private int positiveScore;
	private int negativeScore;
	private Speler speler;

	@Before
	public void setUp() throws Exception {
		naam = "Lars";
		anderenaam = "Lies";
		positiveScore = 5;
		negativeScore = -5;
		speler = new Speler(naam);
	}

	@Test
	public void test_Speler_Met_geldige_naam_Maakt_Speler_object_met_correcte_naam_en_score0() {
		speler = new Speler(naam);
		assertEquals(naam, speler.getNaam());
		assertEquals(0, speler.getScore());
	}
	
	@Test (expected = DomainException.class)
	public void test_Speler_Met_naam_null_Gooit_exception() {
		speler = new Speler(null);
	}
	
	@Test (expected = DomainException.class)
	public void test_Speler_Met_naam_enkel_bestaande_spaties_Gooit_exception() {
		speler = new Speler("   ");
	}
	
	@Test
	public void test_Equals_Als_naam_en_score_gelijk_zijn_Geeft_true(){
		speler.addToScore(positiveScore);
		Speler andereSpeler = new Speler(naam);
		andereSpeler.addToScore(positiveScore);
		
		assertTrue(speler.equals(andereSpeler));
	}
	
	@Test
	public void test_Equals_Als_parameter_null_Geeft_false(){
		assertFalse(speler.equals(null));
	}
	
	@Test
	public void test_Equals_Als_speler_een_andere_naam_heeft_Geeft_false(){
		Speler andereSpeler = new Speler(anderenaam);
		assertFalse(speler.equals(andereSpeler));
	}
	
	@Test
	public void test_Equals_Als_speler_aan_andere_score_heeft_Geeft_false(){
		Speler andereSpeler = new Speler(naam);
		andereSpeler.addToScore(positiveScore);
		assertFalse(speler.equals(andereSpeler));
	}
	
	@Test
	public void test_AddToScore_Met_positieve_score_Voegt_gegeven_score_toe_aan_bestaande_score(){
		speler.addToScore(positiveScore);
		assertEquals(positiveScore, speler.getScore());
	}
	
	@Test
	public void test_AddtoScore_Met_negatieve_score_Voegt_negatieve_score_toe_als_de_resulterende_score_niet_negatief_wordt(){
		speler.addToScore(positiveScore);
		speler.addToScore(positiveScore);
		speler.addToScore(negativeScore);
		assertEquals(positiveScore, speler.getScore());
	}
	
	@Test (expected = DomainException.class)
	public void test_AddToScore_Als_resulterende_score_negatief_wordt_Gooit_exception(){
		speler.addToScore(negativeScore);
	}
}

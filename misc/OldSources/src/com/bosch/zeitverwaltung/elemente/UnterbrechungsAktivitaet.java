package com.bosch.zeitverwaltung.elemente;

/**
 * <p>
 * Sonderkativit�t, die eine Unterbrechung darstellt. Diese ist durch ihre
 * Eigenschaften festgelegt.
 * </p>
 * 
 * @author Lars Geyer
 */
public final class UnterbrechungsAktivitaet extends Aktivitaet {
	/**
	 * <p>
	 * Konstruktor, er erzeugt die spezielle Unterbrechungs-Aktivit�t
	 * </p>
	 */
	public UnterbrechungsAktivitaet() {
		super("Unterbrechung", "Unterbrechung", "Unterbrechung", false);
	}

	/**
	 * <p>
	 * Methode wird leer �berschrieben, dadurch kann dieses Property nicht mehr
	 * gesetzt werden.
	 * </p>
	 */
	@Override
	public void setEuro(double euro) {

	}

	/**
	 * <p>
	 * Methode wird leer �berschrieben, dadurch kann dieses Property nicht mehr
	 * gesetzt werden.
	 * </p>
	 */
	@Override
	public void setKm(int km) {

	}

	/**
	 * <p>
	 * Methode wird leer �berschrieben, dadurch kann dieses Property nicht mehr
	 * gesetzt werden.
	 * </p>
	 */
	@Override
	public void setReisezeit(boolean aktiv) {

	}
}

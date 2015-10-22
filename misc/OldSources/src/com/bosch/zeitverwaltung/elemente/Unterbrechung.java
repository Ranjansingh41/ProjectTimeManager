/**
 * 
 */
package com.bosch.zeitverwaltung.elemente;

/**
 * <p>
 * Buchung, die eine Unterbrechung repr�sentiert.
 * </p>
 * 
 * <p>
 * Die Klasse definiert die spezifische Funktionalit�t einer Unterbrechung.
 * </p>
 * 
 * @author Lars Geyer
 */
public final class Unterbrechung extends Buchung {

	/**
	 * <p>
	 * Konstruktor, er erh�lt die Startzeit der Buchung und definiert die
	 * Unterbrechungs-Aktivit�t als Buchungsaktivit�t.
	 * </p>
	 * 
	 * @param startZeit
	 *            Startzeit der Unterbrechung
	 */
	public Unterbrechung(Uhrzeit startZeit) {
		super(startZeit, new UnterbrechungsAktivitaet());
	}

	/**
	 * <p>
	 * Die Aktivit�t einer Unterbrechung ist unver�nderbar.
	 * </p>
	 * 
	 * @param aktivitaet
	 *            Nicht benutzt
	 */
	@Override
	public void setAktivitaet(Aktivitaet aktivitaet) {

	}

	/**
	 * <p>
	 * Berechnet die Anwesenheitsminuten, da eine Unterbrechung einer
	 * Nichtanwesenheit entspricht, gibt die Methode 0 zur�ck.
	 * </p>
	 * 
	 * @return 0
	 */
	@Override
	public int berechneBuchungsdauer() {
		return 0;
	}

}

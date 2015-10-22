package com.bosch.zeitverwaltung.auswertung.framework.modell;

/**
 * <p>
 * Datensatz mit den Daten einer Zusammenfassung f�r einen Berechnungszeitraum
 * </p>
 * 
 * @author Lars Geyer
 * @see BasisAuswertung
 * @see BuchungsBerechnung
 */
public class Zusammenfassung extends BasisAuswertung {
	private int arbeitszeit;
	private int projektzeit;

	/**
	 * <p>
	 * Erzeugt ein Zusammenfassungsobjekt, dass nicht mehr ver�nderbar ist.
	 * </p>
	 * 
	 * @param arbeitszeit
	 *            Gesamte Arbeitszeit im Berechnungszeitraum im Minuten
	 * @param projektzeit
	 *            Gesamte Projekt-Arbeitszeit im Berechnungszeitraum in Minuten
	 */
	public Zusammenfassung(int arbeitszeit, int projektzeit) {
		this.arbeitszeit = arbeitszeit;
		this.projektzeit = projektzeit;
	}

	/**
	 * <p>
	 * Gibt die gesamte Arbeitszeit im Berechnungszeitraum in Minuten zur�ck
	 * </p>
	 * 
	 * @return Arbeitszeit in Minuten
	 */
	public int getArbeitszeitInMinuten() {
		return arbeitszeit;
	}

	/**
	 * <p>
	 * Gibt die gesamte Projekt-Arbeitszeit im Berechungszeitraum in Minuten
	 * zur�ck
	 * </p>
	 * 
	 * @return Arbeitszeit in Minuten
	 */
	public int getProjektzeitInMinuten() {
		return projektzeit;
	}

	/**
	 * <p>
	 * Gibt die gesamte Arbeitszeit f�r Linient�tigkeiten im Berechungszeitraum
	 * in Minuten zur�ck
	 * </p>
	 * 
	 * @return Arbeitszeit in Minuten
	 */
	public int getLinienzeitInMinuten() {
		return arbeitszeit - projektzeit;
	}

	/**
	 * <p>
	 * Gibt die gesamte Arbeitszeit im Berechnungszeitraum als Stundenstring
	 * zur�ck
	 * </p>
	 * 
	 * @return Arbeitszeit als Stundenstring
	 */
	public String getArbeitszeit() {
		return erzeugeStundenString(arbeitszeit);
	}

	/**
	 * <p>
	 * Gibt die gesamte Projekt-Arbeitszeit im Berechnungszeitraum als
	 * Stundenstring zur�ck
	 * </p>
	 * 
	 * @return Arbeitszeit als Stundenstring
	 */
	public String getProjektzeit() {
		return erzeugeStundenString(projektzeit);
	}

	/**
	 * <p>
	 * Gibt die gesamte Arbeitszeit f�r Linient�tigkeiten im Berechnungszeitraum
	 * als Stundenstring zur�ck
	 * </p>
	 * 
	 * @return Arbeitszeit als Stundenstring
	 */
	public String getLinienzeit() {
		return erzeugeStundenString(arbeitszeit - projektzeit);
	}

	/**
	 * <p>
	 * Gibt den Anteil Projekt-Arbeitszeit an Gesamtarbeitszeit im
	 * Berechnungszeitraum als Prozentstring zur�ck
	 * </p>
	 * 
	 * @return Anteil als Prozentstring
	 */
	public String getProjektanteil() {
		if (arbeitszeit > 0) {
			return erzeugeProzentString((projektzeit * 1000) / arbeitszeit);
		} else {
			return erzeugeProzentString(0);
		}
	}
}

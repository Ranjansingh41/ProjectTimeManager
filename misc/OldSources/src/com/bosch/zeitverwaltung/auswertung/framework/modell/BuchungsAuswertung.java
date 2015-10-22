package com.bosch.zeitverwaltung.auswertung.framework.modell;

import java.util.Comparator;

/**
 * <p>
 * Enth�lt einen Datensatz der Buchungs-Auswertung, d.h. Name, Buchungsnummer,
 * Minuten und Anteile an der Gesamtarbeitszeit.
 * </p>
 * 
 * @author Lars Geyer
 * @see BasisAuswertung
 * @see BuchungsBerechnung
 */
public class BuchungsAuswertung extends BasisAuswertung {
	private String auswertungsName;
	private String buchungsNummer;
	private int minuten;
	private int projektPromille;
	private int gesamtPromille;

	/**
	 * <p>
	 * Gibt einen <em>Comparator</em> zur�ck, der es erlaubt, Objekte des Typs
	 * in einer sortierten Liste zu verwalten.
	 * </p>
	 * 
	 * @return <em>java.util.Comparator</em>-Objekt f�r <em>SortedSet</em>-Interface
	 */
	public static Comparator<BuchungsAuswertung> getComparator() {
		return new Comparator<BuchungsAuswertung>() {
			public int compare(BuchungsAuswertung elem1,
					BuchungsAuswertung elem2) {
				int back = 0;
				String vgl1 = elem1.getName();
				String vgl2 = elem2.getName();

				back = vgl1.compareTo(vgl2);
				if (back == 0) {
					vgl1 = elem1.getBuchungsNummer();
					vgl2 = elem2.getBuchungsNummer();

					back = vgl1.compareTo(vgl2);
				}
				return back;
			}
		};
	}

	/**
	 * <p>
	 * Konstruktor. Er erzeugt ein Objekt aus den Parametern. Das Objekt ist
	 * danach nicht mehr ver�nderbar. Der Konstruktor berechnet die Verh�ltnisse
	 * aus den �bergebenen Parametern.
	 * </p>
	 * 
	 * @param auswertungsName
	 *            Name der Aktivit�t
	 * @param buchungsNummer
	 *            Buchungsnummer der Aktitv�t
	 * @param minuten
	 *            Dauer der Aktivit�t im Betrachtungszeitraum
	 * @param arbeitszeit
	 *            Dauer aller Aktivit�ten im Betrachtungszeitraum
	 * @param projektzeit
	 *            Dauer aller Projektaktivit�ten im Betrachtungszeitraum oder -1
	 *            falls Aktivit�t keine Projektaktivit�t ist
	 */
	public BuchungsAuswertung(String auswertungsName, String buchungsNummer,
			int minuten, int arbeitszeit, int projektzeit) {
		this.auswertungsName = auswertungsName;
		this.buchungsNummer = buchungsNummer;
		this.minuten = minuten;
		int temp = 1000 * minuten;
		if (projektzeit == -1) {
			projektPromille = 0;
		} else {
			if (projektzeit > 0) {
				projektPromille = temp / projektzeit;
			} else {
				projektPromille = 0;
			}
		}
		if (arbeitszeit > 0) {
			gesamtPromille = temp / arbeitszeit;
		} else {
			gesamtPromille = 0;
		}
	}

	/**
	 * <p>
	 * Gibt den Namen der Aktivit�t zur�ck
	 * </p>
	 * 
	 * @return Name der Aktivit�t
	 */
	public String getName() {
		return auswertungsName;
	}

	/**
	 * <p>
	 * Gibt die Buchungsnummer der Aktivit�t zur�ck
	 * </p>
	 * 
	 * @return Buchungsnummer der Aktivit�t
	 */
	public String getBuchungsNummer() {
		return buchungsNummer;
	}

	/**
	 * <p>
	 * Gibt die Arbeitszeit in Minuten zur�ck, die f�r die Aktivit�t aufgewendet
	 * wurden
	 * </p>
	 * 
	 * @return Dauer der Aktivit�t im Berechnungszeitraum in Minuten
	 */
	public int getDauerInMinuten() {
		return minuten;
	}

	/**
	 * <p>
	 * Gibt die Arbeitszeit als Stundenstring zur�ck, die f�r die Aktivit�t
	 * aufgewedent wurden
	 * </p>
	 * 
	 * @return Dauer der Aktivit�t im Berechnungszeitraum als Stundenstring
	 */
	public String getDauer() {
		return erzeugeStundenString(minuten);
	}

	/**
	 * <p>
	 * Gibt den Anteil der Aktivit�t an der gesamten Arbeitszeit im
	 * Berechnungszeitraum als Prozentstring zur�ck
	 * </p>
	 * 
	 * @return Prozentstring
	 */
	public String getAnteil() {
		return erzeugeProzentString(gesamtPromille);
	}

	/**
	 * <p>
	 * Gibt den Anteil der Aktivit�t an der gesamten Projekt-Arbeitszeit im
	 * Berechungszeitraum als Prozentstring zur�ck
	 * </p>
	 * 
	 * @return Prozentstring
	 */
	public String getProjektAnteil() {
		return erzeugeProzentString(projektPromille);
	}
}

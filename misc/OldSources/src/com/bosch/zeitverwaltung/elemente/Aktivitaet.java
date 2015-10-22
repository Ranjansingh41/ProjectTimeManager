package com.bosch.zeitverwaltung.elemente;

/**
 * <p>
 * Datenstruktur zur Speicherung einer Aktivit�t
 * </p>
 * 
 * @author Lars Geyer
 * @see TagesEreignis
 */
public class Aktivitaet {
	/**
	 * <p>
	 * Die Aktivit�t beinhaltet Reiset�tigkeiten mit dem Privatfahrzeug, d.h.
	 * Abrechnung in Kilometer
	 * </p>
	 */
	private static final int AbrechnungKm = 1;
	/**
	 * <p>
	 * Die Aktivit�t beinhaltet Reiset�tigkeiten mit �ffentlichen
	 * Verkehrsmitteln, d.h. Abrechnung in Euro
	 * </p>
	 */
	private static final int AbrechnungEuro = 2;
	/**
	 * <p>
	 * Die Aktivit�t beinhaltet keine abrechnungsrelevanten Reiset�tigkeiten
	 * </p>
	 */
	private static final int KeineAbrechnung = 3;

	/**
	 * <p>
	 * Keine Reiset�tigkeit
	 * </p>
	 */
	private static final int KeineReisezeit = 1;
	/**
	 * <p>
	 * Aktive Reisezeit
	 * </p>
	 */
	private static final int AktiveReisezeit = 2;
	/**
	 * <p>
	 * Passive Reisezeit
	 * </p>
	 */
	private static final int PassiveReisezeit = 3;

	private final String aktivitaet;
	private final String kategorie;
	private final String buchungsNummer;
	private final boolean projektAktivitaet;
	private int abrechnungsTyp;
	private int abrechnungsInfo;
	private int reisezeit;

	/**
	 * <p>
	 * Konstruktor, er initialisiert die Datenstruktur zu einer
	 * Nicht-Reiseaktivit�t. Reisedaten k�nnen danach �ber Set-Methoden
	 * �bergeben werden.
	 * </p>
	 * 
	 * @param aktivitaet
	 *            Name der Aktivit�t
	 * @param kategorie
	 *            Kategorie der Aktivit�t, muss f�r die gleiche Buchungsnummer
	 *            gleich sein
	 * @param buchungsNummer
	 *            Buchungsnummer der Aktivit�t, f�r jede Kategorie gibt es eine
	 *            Buchungsnummer
	 * @param projektAktivitaet
	 *            Ist die Aktivit�t eine Projektaktivit�t (<em>true</em>) oder
	 *            eine Linienaktivit�t
	 */
	public Aktivitaet(final String aktivitaet, final String kategorie,
			final String buchungsNummer, final boolean projektAktivitaet) {
		this.aktivitaet = (aktivitaet != null) ? aktivitaet : kategorie;
		this.kategorie = kategorie;
		this.buchungsNummer = buchungsNummer;
		this.projektAktivitaet = projektAktivitaet;
		this.abrechnungsTyp = KeineAbrechnung;
		this.abrechnungsInfo = 0;
		this.reisezeit = KeineReisezeit;
	}

	/**
	 * <p>
	 * Mache die Aktivit�t zu einer passiven Reiseaktivit�t und tr�gt die
	 * Abrechnungsinformationen ein.
	 * </p>
	 * 
	 * @param euro
	 *            Betrag, der abzurechnen ist (auf ein 100stel Cent genau wg.
	 *            VVS)
	 */
	public void setEuro(final double euro) {
		this.abrechnungsTyp = AbrechnungEuro;
		this.abrechnungsInfo = (int) (euro * 10000.0);
		this.setReisezeit(false);
	}

	/**
	 * <p>
	 * Mache die Aktivit�t zu einer aktiven Reiseaktivit�t und tr�gt die
	 * Abrechnungsinformationen ein.
	 * </p>
	 * 
	 * @param km
	 *            Kilometer, die durch die Reiset�tigkeit abzurechnen sind
	 */
	public void setKm(final int km) {
		this.abrechnungsTyp = AbrechnungKm;
		this.abrechnungsInfo = km;
		this.setReisezeit(true);
	}

	/**
	 * <p>
	 * Mache die Aktivit�t zu einer Reiseaktivit�t ohne Abrechnung
	 * </p>
	 * 
	 * @param aktiv
	 *            true hei�t aktive Reisezeit, false passive Reisezeit
	 */
	public void setReisezeit(final boolean aktiv) {
		this.reisezeit = (aktiv) ? AktiveReisezeit : PassiveReisezeit;
	}

	/**
	 * <p>
	 * Gibt Namen der Aktivit�t zur�ck
	 * </p>
	 * 
	 * @return Name der Aktivit�t
	 */
	public String getAktivitaet() {
		return this.aktivitaet;
	}

	/**
	 * <p>
	 * Gibt Kategorie der Aktivit�t zur�ck
	 * </p>
	 * 
	 * @return Kategorie der Aktivit�t
	 */
	public String getKategorie() {
		return this.kategorie;
	}

	/**
	 * <p>
	 * Gibt Buchungsnummer der Aktivit�t zur�ck
	 * </p>
	 * 
	 * @return Buchungsnummer der Aktivit�t
	 */
	public String getBuchungsNummer() {
		return this.buchungsNummer;
	}

	/**
	 * <p>
	 * Abfrage, ob Aktivit�t Projekt- (true) oder Linient�tigkeit (false) ist.
	 * </p>
	 * 
	 * @return Ist Aktivit�t Projektt�tigkeit
	 */
	public boolean istProjektAktivitaet() {
		return this.projektAktivitaet;
	}

	/**
	 * <p>
	 * Ist die Aktivit�t eine Reiseaktivit�t?
	 * </p>
	 * 
	 * @return Reiseaktivit�t?
	 */
	public boolean istReisezeit() {
		return (this.reisezeit != KeineReisezeit);
	}

	/**
	 * <p>
	 * Ist die Aktivit�t eine aktive Reiseaktivit�t?
	 * </p>
	 * 
	 * @return Aktive Reiseaktivit�t?
	 */
	public boolean istAktiveReisezeit() {
		return (this.reisezeit == AktiveReisezeit);
	}

	/**
	 * <p>
	 * Ist die Aktivit�t eine passive Reiseaktivit�t?
	 * </p>
	 * 
	 * @return Passive Reiseaktivit�t?
	 */
	public boolean istPassiveReisezeit() {
		return (this.reisezeit == PassiveReisezeit);
	}

	/**
	 * <p>
	 * Ist Aktivit�t f�r Reiseabrechnungen relevant
	 * </p>
	 * 
	 * @return Abrechnungsrelevanz
	 */
	public boolean abrechungsRelevant() {
		return (this.abrechnungsTyp != KeineAbrechnung);
	}

	/**
	 * <p>
	 * Werden Reiset�tigkeiten in Kilometern abgerechnet
	 * </p>
	 * 
	 * @return Abrechnung in Kilometer
	 */
	public boolean abrechnungInKm() {
		return (this.abrechnungsTyp == AbrechnungKm);
	}

	/**
	 * <p>
	 * Werden Reiset�tigkeiten in Euro abgerechnet
	 * </p>
	 * 
	 * @return Abrechnung in Euro
	 */
	public boolean abrechnungInEuro() {
		return (this.abrechnungsTyp == AbrechnungEuro);
	}

	/**
	 * <p>
	 * Ausgabe der Abrechnungsinformationen als String, z.B. um sie in einem
	 * Dialog darzustellen
	 * </p>
	 * 
	 * @return Abrechungsinfo als String
	 */
	public String getAbrechnungsInfo() {
		String back = "";
		if (this.abrechnungsTyp == AbrechnungEuro) {
			back = Integer.toString(this.abrechnungsInfo / 10000) + ",";
			if (((this.abrechnungsInfo % 10000) % 10) != 0) {
				back = back + Integer.toString(this.abrechnungsInfo % 10000)
						+ " �";
			} else if (((this.abrechnungsInfo % 10000) % 100) != 0) {
				back = back
						+ Integer.toString(this.abrechnungsInfo % 10000 / 10)
						+ " �";
			} else {
				back = back
						+ Integer
								.toString((this.abrechnungsInfo % 10000) / 100)
						+ " �";
			}
		} else if (this.abrechnungsTyp == AbrechnungKm) {
			back = Integer.toString(this.abrechnungsInfo) + " km";
		}
		return back;
	}

	/**
	 * <p>
	 * Ausgabe des Abrechnungsbetrages, bei Abrechnung in Euro der Betrag, bei
	 * Abrechnung in Kilometer Kilometer multipliziert mit dem Kilometer-Betrag.
	 * Dieser ist hartkodiert 0,30�.
	 * </p>
	 * 
	 * @return Abrechnungsbetrag
	 */
	public double getAbrechnungsBetrag() {
		double back = 0.0;
		if (this.abrechnungsTyp == AbrechnungEuro) {
			back = this.abrechnungsInfo / 10000.0;
		} else if (this.abrechnungsTyp == AbrechnungKm) {
			back = this.abrechnungsInfo * 0.3;
		}
		return back;
	}

	/**
	 * <p>
	 * Ausgabe des Kilometerwertes bei einer Abrechnung in Kilometer-Aktivit�t.
	 * </p>
	 * 
	 * @return Kilometerwert
	 */
	public int getAbrechnungsKm() {
		int back = 0;
		if (this.abrechnungsTyp == AbrechnungKm) {
			back = this.abrechnungsInfo;
		}
		return back;
	}

	/**
	 * <p>
	 * Stringausgabe der Aktivit�t, besteht aus dem Aktivit�ts-Namen
	 * </p>
	 * 
	 * @return Stringrepr�sentation der Aktivit�t
	 */
	@Override
	public String toString() {
		return this.aktivitaet;
	}

	/**
	 * <p>
	 * Vergleicht zwei Aktivit�ten bzgl. Gleichheit
	 * </p>
	 * 
	 * @param obj
	 *            Vergleichsobjekt
	 * @return Gleichheit der beiden Objekte
	 */
	@Override
	public boolean equals(final Object obj) {
		boolean back = false;
		if (obj instanceof Aktivitaet) {
			final Aktivitaet vgl = (Aktivitaet) obj;
			back = true;
			if (!this.aktivitaet.equals(vgl.getAktivitaet())) {
				back = false;
			} else if (!this.kategorie.equals(vgl.getKategorie())) {
				back = false;
			} else if (!this.buchungsNummer.equals(vgl.getBuchungsNummer())) {
				back = false;
			}
		}
		return back;
	}
}

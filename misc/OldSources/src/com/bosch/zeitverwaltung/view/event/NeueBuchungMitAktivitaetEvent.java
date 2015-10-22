package com.bosch.zeitverwaltung.view.event;

import com.bosch.zeitverwaltung.elemente.Aktivitaet;

/**
 * <p>
 * Signalisiert einen neuen Buchungswunsch mit einer bereits ausgew�hlten
 * Aktivit�t.
 * </p>
 * 
 * @author Lars Geyer
 */
public class NeueBuchungMitAktivitaetEvent extends BuchungsEvent {
	private Aktivitaet aktivitaet;

	/**
	 * <p>
	 * Erzeugt den Event
	 * </p>
	 * 
	 * @param aktivitaet
	 *            Ausgew�hlte Aktivit�t
	 */
	NeueBuchungMitAktivitaetEvent(Aktivitaet aktivitaet) {
		this.aktivitaet = aktivitaet;
	}

	/**
	 * <p>
	 * Gibt ausgew�hlte Aktivit�t zur�ck
	 * </p>
	 * 
	 * @return Aktivit�t
	 */
	public Aktivitaet getAktivitaet() {
		return aktivitaet;
	}
}

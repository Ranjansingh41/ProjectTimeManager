package com.bosch.zeitverwaltung.view.event;

import com.bosch.zeitverwaltung.elemente.Buchung;

/**
 * <p>
 * Signalisiert den Wunsch eine Buchung zu l�schen
 * </p>
 * 
 * @author Lars Geyer
 */
public final class BuchungLoeschenEvent extends BuchungsEvent {
	private Buchung buchung;

	/**
	 * <p>
	 * Erzeugt den Event
	 * </p>
	 * 
	 * @param buchung
	 *            Zu l�schende Buchung
	 */
	BuchungLoeschenEvent(final Buchung buchung) {
		this.buchung = buchung;
	}

	/**
	 * <p>
	 * Gibt zu l�schende Buchung zur�ck
	 * </p>
	 * 
	 * @return Buchung
	 */
	public Buchung getBuchung() {
		return buchung;
	}
}

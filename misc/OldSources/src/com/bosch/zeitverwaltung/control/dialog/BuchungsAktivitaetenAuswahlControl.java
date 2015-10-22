package com.bosch.zeitverwaltung.control.dialog;

import com.bosch.zeitverwaltung.elemente.Aktivitaet;
import com.bosch.zeitverwaltung.modell.ModellFactory;

/**
 * <p>
 * Wird bei der Buchung benutzt, um eine Aktivit�t auszuw�hlen, sofern die
 * Buchung mittels der Men�option erfolgt.
 * </p>
 * 
 * @author Lars Geyer
 * 
 */
public class BuchungsAktivitaetenAuswahlControl extends
		AktivitaetenAuswahlControl {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void auswahl(Aktivitaet aktivitaet) {
		ModellFactory.getFactory().getTagesVerwaltung().getAktuellesModell()
		.addBuchung(aktivitaet);
	}
}

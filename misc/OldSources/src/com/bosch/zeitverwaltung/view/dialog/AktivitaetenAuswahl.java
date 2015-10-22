package com.bosch.zeitverwaltung.view.dialog;

import com.bosch.zeitverwaltung.elemente.Aktivitaet;

/**
 * <p>
 * Interface zu einer GUI-Komponente, die die Auswahl einer Aktivit�t
 * erm�glicht.
 * </p>
 * 
 * @author Lars Geyer
 * @see com.bosch.zeitverwaltung.control.dialog.AktivitaetenAuswahlControl
 * @see com.bosch.zeitverwaltung.control.dialog.BuchungsAktivitaetenAuswahlControl
 */
public interface AktivitaetenAuswahl extends BasisDialog {
	/**
	 * <p>
	 * Bestimmt die Aktivit�t, die zum Anfang der Darstellung markiert sein
	 * soll. Die Methode wird nach <em>setModell</em>, aber vor
	 * <em>showDialog</em> aufgerufen.
	 * </p>
	 * 
	 * @param selektiert
	 *            Zu selektierende Aktivit�t
	 */
	public void setSelected(Aktivitaet selektiert);
}

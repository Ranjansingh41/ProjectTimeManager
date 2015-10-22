package com.bosch.zeitverwaltung.view.dialog;

import com.bosch.zeitverwaltung.elemente.Tag;

/**
 * <p>
 * Interface zum Dialog, der es erlaubt einen Tag auszuw�hlen, dessen Buchungen
 * dann im Hauptfenster dargestellt werden sollen. Auf den <em>BasisDialog</em>
 * wird zur�ckgegriffen, um die Dialog- Schlie�en Funktionalit�t zu verwenden.
 * </p>
 * 
 * @author Lars Geyer
 * @see com.bosch.zeitverwaltung.control.dialog.TagOeffnenControl
 * @see BasisDialog
 */
public interface TagOeffnenAuswahl extends BasisDialog {
	/**
	 * <p>
	 * Diese Methode wird vor <em>showDialog</em> aufgerufen und wei�t den
	 * Dialog an, den �bergebenen Tag initial zu selektieren.
	 * </p>
	 * 
	 * @param startTag
	 *            Initial zu selektierender Tag
	 */
	public void setStartTag(Tag startTag);
}

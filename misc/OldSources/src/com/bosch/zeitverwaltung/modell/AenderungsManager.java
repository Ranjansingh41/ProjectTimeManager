package com.bosch.zeitverwaltung.modell;

/**
 * <p>
 * Interface zur Verwaltung von �nderungen an einem Managementobjekt. Das
 * Managementobjekt muss diese Schnittstelle implementieren. Danach kann eine
 * Speicherfunktion eine Speicherungen nur dann veranlassen, wenn sich etwas an
 * den Daten ver�ndert hat.
 * </p>
 * 
 * @author Lars Geyer
 */
public interface AenderungsManager {

	/**
	 * <p>
	 * Wurde das Modell ver�ndert?
	 * </p>
	 * 
	 * @return Das Modell wurde ver�ndert
	 */
	public boolean veraendert();

	/**
	 * <p>
	 * Eine Sicherung der Daten wurde vorgenommen, erst nach einer �nderung ist
	 * eine Sicherung wieder notwendig.
	 * </p>
	 */
	public void aenderungenGesichert();
}
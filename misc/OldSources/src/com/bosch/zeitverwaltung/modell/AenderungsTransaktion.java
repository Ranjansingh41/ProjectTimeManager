package com.bosch.zeitverwaltung.modell;

/**
 * <p>
 * Dieses Interface erlaubt die Durchf�hrung von �nderungen an einer Liste von
 * Elementen in einem Transkationsmodus, d.h. die �nderungen sind zur�cknehmbar.
 * Dies kann z.B. von einem GUI verwendet werden, um einen �nderungsdialog zu
 * haben, der einen Abbruch-Button zur Verf�gung stellt.
 * </p>
 * 
 * @author Lars Geyer
 */
public interface AenderungsTransaktion {
	/**
	 * <p>
	 * Starten der Transaktion, das Modell muss den aktuellen Zustand
	 * sicherstellen, um ihn wiederherstellen zu k�nnen.
	 * </p>
	 */
	public void starteAenderung();

	/**
	 * <p>
	 * �nderungen wurden abgeschlossen und sollen �bernommen werden.
	 * </p>
	 */
	public void commitAenderung();

	/**
	 * <p>
	 * Der �nderungsvorgang wurde abgebrochen, der alte Zustand soll
	 * wiederhergestellt werden.
	 * </p>
	 */
	public void restoreAenderung();
}

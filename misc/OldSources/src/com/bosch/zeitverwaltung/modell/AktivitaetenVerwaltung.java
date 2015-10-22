package com.bosch.zeitverwaltung.modell;

import com.bosch.zeitverwaltung.elemente.Aktivitaet;
import com.bosch.zeitverwaltung.elemente.AktivitaetsSortierung;
import com.bosch.zeitverwaltung.modell.listener.AktivitaetenChangedListener;
import com.bosch.zeitverwaltung.modell.listener.SortierungChangedListener;

/**
 * <p>
 * Schnittstelle zur Aktivit�ten-Verwaltung. In ihr werden die aktuell
 * ausw�hlbaren Aktivit�ten verwaltet. Historische Aktivit�ten sind nur in den
 * Buchungen der entsprechenden Buchungstage gespeichert und k�nnen nicht mehr
 * in die aktuelle Liste aufgenommen werden. Von Hand kann nat�rlich die
 * Aktivit�t wieder in die Konfigurationsdatei hineingezogen werden.
 * </p>
 * 
 * @author Lars Geyer
 * @see BasisModell
 */
public interface AktivitaetenVerwaltung extends BasisModell<Aktivitaet> {
	/**
	 * <p>
	 * Methode zum Setzen der Aktivitaets-Sortierung.
	 * </p>
	 * 
	 * @param sortierung
	 *            Die gew�hlte Aktivit�tssortierung
	 */
	public void setSortierung(AktivitaetsSortierung sortierung);

	/**
	 * <p>
	 * Gibt die aktuelle Aktivit�tssortierung zur�ck
	 * </p>
	 * 
	 * @return Aktuelle Sortierung
	 */
	public AktivitaetsSortierung sortierung();

	/**
	 * <p>
	 * Anmeldung eines Listeners, der informiert werden m�chte, wenn sich an der
	 * Sortierung der Aktivit�ten etwas ver�ndert.
	 * </p>
	 * 
	 * @param listener
	 *            M�chte �ber �nderungen der Aktivit�tensortierung informiert
	 *            werden
	 */
	public void addSortierungChangedListener(SortierungChangedListener listener);

	/**
	 * <p>
	 * Abmeldung eines Listeners, der informiert werden m�chte, wenn sich an der
	 * Sortierung der Aktivit�ten etwas ver�ndert.
	 * </p>
	 * 
	 * @param listener
	 *            Zu entfernender Listener
	 */
	public void removeSortierungChangedListener(
			SortierungChangedListener listener);

	/**
	 * <p>
	 * Anmeldung eines Listeners, der informiert werden m�chte, wenn sich an der
	 * Liste der Aktivit�ten etwas ver�ndert.
	 * </p>
	 * 
	 * @param listener
	 *            M�chte �ber �nderungen der Aktivit�ten informiert werden
	 */
	public void addAktivitaetenChangedListener(
			AktivitaetenChangedListener listener);

	/**
	 * <p>
	 * Abmeldung eines Listeners, der informiert werden m�chte, wenn sich an der
	 * Liste der Aktivit�ten etwas ver�ndert.
	 * </p>
	 * 
	 * @param listener
	 *            Zu entfernender Listener
	 */
	public void removeAktivitaetenChangedListener(
			AktivitaetenChangedListener listener);
}
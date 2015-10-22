package com.bosch.zeitverwaltung.modell.event;

import com.bosch.zeitverwaltung.elemente.AktivitaetsSortierung;
import com.bosch.zeitverwaltung.event.BasisEvent;

/**
 * <p>
 * Event, der eine �nderung der Altivit�tensortierung anzeigt.
 * </p>
 * 
 * @author Lars Geyer
 * @see AktivitaetsSortierung
 * @see com.bosch.zeitverwaltung.modell.listener.SortierungChangedListener
 * @see com.bosch.zeitverwaltung.modell.AktivitaetenVerwaltung
 */
public final class SortierungChangedEvent extends BasisEvent {
	private AktivitaetsSortierung sortierung;

	/**
	 * <p>
	 * Konstruktor, er wird nur von der <em>SortierungChangedEventFactory</em>
	 * aufgerufen.
	 * </p>
	 * 
	 * @param sortierung
	 *            Die neue Sortierung
	 */
	SortierungChangedEvent(AktivitaetsSortierung sortierung) {
		this.sortierung = sortierung;
	}

	/**
	 * <p>
	 * Die neue Sortierung
	 * </p>
	 * 
	 * @return Sortierung
	 */
	public AktivitaetsSortierung sortierung() {
		return sortierung;
	}

	/**
	 * <p>
	 * Sortierung ist nach Buchungsnummern
	 * </p>
	 * 
	 * @return Sortierung nach Buchungsnummern
	 */
	public boolean sortierungNachBuchungsnummern() {
		return (sortierung == AktivitaetsSortierung.AktivitaetsSortierungNachBuchungsnummer);
	}

	/**
	 * <p>
	 * Sortierung ist nach Aktivit�tsname
	 * </p>
	 * 
	 * @return Sortierung nach Aktivit�tsname
	 */
	public boolean sortierungNachAktivit�tsname() {
		return (sortierung == AktivitaetsSortierung.AktivitaetsSortierungNachName);
	}

	/**
	 * <p>
	 * Sortierung ist nach Aktivit�tskategorie
	 * </p>
	 * 
	 * @return Sortierung nach Aktivit�tskategorie
	 */
	public boolean sortierungNachAktivit�tskategorie() {
		return (sortierung == AktivitaetsSortierung.AktivitaetsSortierungNachKategorie);
	}
}

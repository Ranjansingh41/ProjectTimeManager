package com.bosch.zeitverwaltung.modell.event;

import java.util.Collection;

import com.bosch.zeitverwaltung.elemente.Aktivitaet;

/**
 * <p>
 * Factory f�r <em>AktivitaetenChangedEvent</em>-Objekte
 * </p>
 * 
 * @author Lars Geyer
 * @see AktivitaetenChangedEvent
 */
public final class AktivitaetenChangedEventFactory {
	/**
	 * <p>
	 * Erzeugt einen <em>AktivitaetenChangedEvent</em>
	 * </p>
	 * 
	 * @param aktivitaetenListe
	 *            Aktuelle Liste der Aktivit�ten
	 * @return Event
	 */
	public AktivitaetenChangedEvent aktivitaetenChangedEvent(
			Collection<Aktivitaet> aktivitaetenListe) {
		return new AktivitaetenChangedEvent(aktivitaetenListe);
	}
}

package com.bosch.zeitverwaltung.modell.event;

import java.util.Collection;

import com.bosch.zeitverwaltung.elemente.Aktivitaet;
import com.bosch.zeitverwaltung.event.BasisEvent;

/**
 * <p>
 * Event signalisiert eine Ver�nderung in der Liste der aktuell verf�gbaren
 * Aktivit�ten.
 * </p>
 * 
 * @author Lars Geyer
 */
public final class AktivitaetenChangedEvent extends BasisEvent {
	private Collection<Aktivitaet> neueListe;

	/**
	 * <p>
	 * Erzeugt einen Event. Diese Methode wird durch die Event-Factory
	 * aufgerufen.
	 * </p>
	 * 
	 * @param neueListe
	 *            Liste der Aktivit�ten nach der �nderung
	 */
	AktivitaetenChangedEvent(Collection<Aktivitaet> neueListe) {
		this.neueListe = neueListe;
	}

	/**
	 * <p>
	 * Gibt die Liste der aktuell g�ltigen Aktivit�ten zur�ck
	 * </p>
	 * 
	 * @return Liste mit Aktivit�ten
	 */
	public Collection<Aktivitaet> getAktivitaetenListe() {
		return neueListe;
	}
}

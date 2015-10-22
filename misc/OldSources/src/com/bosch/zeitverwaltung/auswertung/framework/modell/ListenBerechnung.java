package com.bosch.zeitverwaltung.auswertung.framework.modell;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.bosch.zeitverwaltung.elemente.Buchung;
import com.bosch.zeitverwaltung.modell.TagesBuchungen;

/**
 * <p>
 * Erzeugt eine Liste von Buchungen, die dann mit Hilfe der
 * <em>BuchungsBerechnung</em> bearbeitet werden k�nnen. Dies ist eine
 * Basisklasse f�r Auswertungsmodelle.
 * </p>
 * 
 * @author Lars Geyer
 * @see BuchungsBerechnung
 * @see TagesBuchungen
 */
public class ListenBerechnung extends BuchungsBerechnung {
	/**
	 * <p>
	 * Abgeleitete Modelle rufen diese Methode auf, um die
	 * <em>TagesBuchungen</em> zu setzen, f�r die Berechnungen durchgef�hrt
	 * werden sollen
	 * </p>
	 * 
	 * @param buchungen
	 *            Liste mit <em>TagesBuchungen</em>-Objekten
	 * @param filterKlasse
	 *            Name der Filterklasse, um nicht anrechenbare Zeiten
	 *            herauszufiltern
	 * 
	 * @throws ClassNotFoundException
	 *             Falls Filter nicht gefunden wurde
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	protected void setBuchungen(List<TagesBuchungen> buchungen,
			String filterKlasse) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		BuchungsFilter filter = new FilterLader().erzeugeFilter(filterKlasse);
		Iterator<TagesBuchungen> iter = buchungen.iterator();
		Collection<Buchung> buchungsListe = new LinkedList<Buchung>();
		while (iter.hasNext()) {
			buchungsListe.addAll(filter.berechneArbeitszeiten(iter.next()));
		}
		setBuchungen(buchungsListe);
	}
}

package com.bosch.zeitverwaltung.speicher;

import java.io.IOException;

import com.bosch.zeitverwaltung.elemente.Tag;
import com.bosch.zeitverwaltung.modell.TagesBuchungen;

/**
 * <p>
 * Schnittstelle zum Einladen und Verwalten von Buchungen eines Tages. �ber
 * diese Schnittstelle l�dt die Zeitvewaltung Buchungen ein.
 * </p>
 * 
 * @author Lars Geyer
 * @see BuchungenSpeichern
 */
public interface BuchungenLaden {
	/**
	 * <p>
	 * Methode �berpr�ft, ob f�r den �bergebenen Tag bereits Buchungen
	 * gespeichert wurden.
	 * </p>
	 * 
	 * @param tag
	 *            Tag, f�r den erfragt wird, ob Buchungen gespeichert sind
	 * @return true, falls Buchungen f�r den �bergebenen Tag gespeichert sind
	 */
	public boolean existierenBuchungen(Tag tag);

	/**
	 * <p>
	 * Methode zum Einlesen der Buchungen eines Tages.
	 * </p>
	 * 
	 * @param tag
	 *            Tag, f�r den die Buchungen eingelesen werden sollen
	 * @return Ein TagesBuchungen-Modell mit den Buchungen des Tages
	 * @throws IOException
	 *             Falls Datei nicht existiert oder aus anderen Gr�nden nicht
	 *             darauf zugegriffen werden kann.
	 */
	public TagesBuchungen ladeBuchungen(Tag tag) throws IOException;
}

package com.bosch.zeitverwaltung.modell;

import com.bosch.zeitverwaltung.elemente.Tag;

/**
 * <p>
 * Das ModellFactory-Singleton dient dazu, als abstrakte Schnittstelle die
 * Erzeugung aller relevanter Modelle zu erm�glichen. Eine konkrete
 * Modell-Factory muss diese Schnittstelle implementieren und so Links auf die
 * Modelle zur�ckgeben.
 * </p>
 * 
 * <p>
 * Die abstrakte Schnittstelle ist nach dem Muster f�r abstrakte Factories als
 * Singletons aufgebaut. Eine statische Variable erm�glicht mittels eines
 * Getters den Zugriff auf die Factory. Die abgeleitete Factory setzt die
 * Factory-Variable durch einen Aufruf von <em>setFactory</em>. Die
 * bereitstellenden Funktionen sind als abstrakte Methoden in dieser Klasse
 * definiert und werden von der abgeleiteten Factory realisiert.
 * </p>
 * 
 * @author Lars Geyer
 * @see com.bosch.zeitverwaltung.factory.ZeitverwaltungFactory
 */
public abstract class ModellFactory {
	private static ModellFactory modellFactory = null;

	/**
	 * <p>
	 * Setzen der Factory-Variable. Wird von einer abgeleiteten Factory-Klasse
	 * aufgerufen, um das konkrete Factory-Objekt bekannt zu machen.
	 * </p>
	 * 
	 * @param factory
	 *            Die abgeleitete Modell-Factory
	 */
	protected static void setFactory(ModellFactory factory) {
		if (modellFactory == null) {
			modellFactory = factory;
		} else {
			throw new IllegalArgumentException(
					"Singleton zum zweitenmal erzeugt");
		}
	}

	/**
	 * <p>
	 * Zugriff auf die Modell-Factory, um Modelle zu erzeugen.
	 * </p>
	 * 
	 * @return Link auf die Modell-Factory
	 */
	public static ModellFactory getFactory() {
		return modellFactory;
	}

	/**
	 * <p>
	 * Erzeugung eines leeren Buchungsmodells f�r einen Tag
	 * </p>
	 * 
	 * @param buchungsTag
	 *            Tag, f�r den die Buchungen gelten
	 * @return Buchungsmodell f�r den Tag ohne Buchungen
	 */
	public abstract TagesBuchungen erzeugeTagesBuchungen(Tag buchungsTag);

	/**
	 * <p>
	 * Referenz auf die Tages-Verwaltung, mit der die Buchungsmodelle verwaltet
	 * werden.
	 * </p>
	 * 
	 * @return Referenz auf die Tages-Verwaltung
	 */
	public abstract TagesVerwaltung getTagesVerwaltung();

	/**
	 * <p>
	 * Referenz auf die Aktivit�ten-Verwaltung, mit der die aktuellen
	 * Aktivit�ten verwaltet werden.
	 * </p>
	 * 
	 * @return Referenz auf die Aktivit�ten-Verwaltung
	 */
	public abstract AktivitaetenVerwaltung getAktivitaetenVerwaltung();

	/**
	 * <p>
	 * Referenz auf die Minuten-Delta-Verwaltung, mit der die Minuten-Deltas
	 * verwaltet werden.
	 * </p>
	 * 
	 * @return Referenz auf die Minuten-Delta-Verwaltung
	 */
	public abstract MinutenDeltaManager getMinutenDelta();
}

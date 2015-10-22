package com.bosch.zeitverwaltung.modell_impl;

import com.bosch.zeitverwaltung.elemente.Tag;
import com.bosch.zeitverwaltung.modell.AktivitaetenVerwaltung;
import com.bosch.zeitverwaltung.modell.MinutenDeltaManager;
import com.bosch.zeitverwaltung.modell.ModellFactory;
import com.bosch.zeitverwaltung.modell.TagesBuchungen;
import com.bosch.zeitverwaltung.modell.TagesVerwaltung;

/**
 * <p>
 * Factory-Implementierung f�r die Modelle
 * </p>
 * 
 * @author Lars Geyer
 * @see ModellFactory
 * @see com.bosch.zeitverwaltung.factory.ZeitverwaltungFactory
 */
public final class StandardModellFactory extends ModellFactory {
	private AktivitaetenVerwaltung aktivitaetenVerwaltung = new AktivitaetenListe();

	private TagesVerwaltung tagesVerwaltung = new TagesVerwaltungImpl();

	private MinutenDeltaManager minutenDeltaManager = new MinutenDeltaImpl();

	/**
	 * <p>
	 * Konstruktor, er registriert das Objekt als offizielle Modell-Factory
	 * </p>
	 */
	public StandardModellFactory() {
		setFactory(this);
	}

	/**
	 * <p>
	 * Referenz auf die Tages-Verwaltung, mit der die Buchungsmodelle verwaltet
	 * werden.
	 * </p>
	 * 
	 * @return Referenz auf die Tages-Verwaltung
	 */
	public TagesVerwaltung getTagesVerwaltung() {
		return tagesVerwaltung;
	}

	/**
	 * <p>
	 * Referenz auf die Aktivit�ten-Verwaltung, mit der die aktuellen
	 * Aktivit�ten verwaltet werden.
	 * </p>
	 * 
	 * @return Referenz auf die Aktivit�ten-Verwaltung
	 */
	public AktivitaetenVerwaltung getAktivitaetenVerwaltung() {
		return aktivitaetenVerwaltung;
	}

	/**
	 * <p>
	 * Referenz auf die Minuten-Delta-Verwaltung, mit der die Minuten-Deltas
	 * verwaltet werden.
	 * </p>
	 * 
	 * @return Referenz auf die Minuten-Delta-Verwaltung
	 */
	public MinutenDeltaManager getMinutenDelta() {
		return minutenDeltaManager;
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
	public TagesBuchungen erzeugeTagesBuchungen(Tag buchungsTag) {
		ZeitTabellenModell back = new ZeitTabellenModell();
		back.setBuchungsTag(buchungsTag);
		return back;
	}
}

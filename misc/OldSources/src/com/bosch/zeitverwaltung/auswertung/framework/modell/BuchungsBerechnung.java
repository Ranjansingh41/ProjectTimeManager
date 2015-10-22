package com.bosch.zeitverwaltung.auswertung.framework.modell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.bosch.zeitverwaltung.elemente.Aktivitaet;
import com.bosch.zeitverwaltung.elemente.Buchung;

/**
 * <p>
 * Klasse f�hrt die Auswertung der Buchungen eines Tages durch.
 * </p>
 * 
 * @author Lars Geyer
 * @see BuchungsAuswertung
 * @see Zusammenfassung
 */
public class BuchungsBerechnung {
	private Collection<Buchung> buchungsListe = null;

	/**
	 * <p>
	 * Setzt die Liste mit den Buchungen f�r die Auswertung
	 * </p>
	 * 
	 * @param buchungen
	 *            Die Liste mit den auszuwertenden Buchungen
	 */
	protected void setBuchungen(Collection<Buchung> buchungen) {
		buchungsListe = buchungen;
	}

	/**
	 * <p>
	 * Sortiert die mittels buchungen �bergebenen Buchungen nach Aktivit�ten und
	 * summiert die f�r die Aktivit�t jeweils gebuchten Minuten.
	 * </p>
	 * 
	 * @return Liste mit <em>BuchungsAuswertung</em>-Objekten f�r jede
	 *         Aktivit�t eines
	 */
	public List<BuchungsAuswertung> getAbbildungAktivitaetStunden() {
		AktivitaetsZugriff meinZugriff = new AktivitaetsZugriff() {
			public String getReferenz(Aktivitaet aktv) {
				return aktv.getAktivitaet();
			}
		};
		return berechneAbbildung(buchungsListe, meinZugriff);
	}

	/**
	 * <p>
	 * Sortiert die mittels buchungen �bergebenen Buchungen nach
	 * Aktivit�tskategorien und summiert die f�r die Kategorie jeweils gebuchten
	 * Minuten.
	 * </p>
	 * 
	 * @return Liste mit <em>BuchungsAuswertung</em>-Objekten f�r jede
	 *         Kategorie eines
	 */
	public List<BuchungsAuswertung> getAbbildungKategorieStunden() {
		AktivitaetsZugriff meinZugriff = new AktivitaetsZugriff() {
			public String getReferenz(Aktivitaet aktv) {
				return aktv.getKategorie();
			}
		};
		return berechneAbbildung(buchungsListe, meinZugriff);
	}

	/**
	 * <p>
	 * Gibt die Zusammenfassung der �bergebenen Buchungen zur�ck
	 * </p>
	 * 
	 * @return <em>Zusammenfassung</em>-Objekt mit der Zusammenfassung der
	 *         Buchungen
	 */
	public Zusammenfassung getZusammenfassung() {
		return new Zusammenfassung(getArbeitszeit(buchungsListe),
				getProjektzeit(buchungsListe));
	}

	/**
	 * <p>
	 * Eigentliche Ermittlung von Aktivit�ten/kategorien und Aufsummierung. Wird
	 * von den beiden Methoden <em>getAbbildungKategorieStunden</em> und
	 * <em>getAbbildungAktivitaetStunden</em> benutzt. Der
	 * <em>AktivitaetsZugriff</em> ist die M�glichkeit, zwischen Aktivitaet
	 * und Kategorie zu unterscheiden, ansonsten ist der Algorithmus gleich.
	 * </p>
	 * 
	 * @param buchungen
	 *            Buchungen
	 * @param meinZugriff
	 *            Zugriff auf die Aktivit�t wird gekapselt, um verschiedene
	 *            Zugriffe und somit Sortierungskriterien zu erm�glichen
	 * @return Liste mit <em>BuchungsAuswertung</em>-Objekten f�r jede
	 *         Aktitaet/Kategorie eines
	 */
	private List<BuchungsAuswertung> berechneAbbildung(
			Collection<Buchung> buchungen, AktivitaetsZugriff meinZugriff) {
		SortedSet<BuchungsAuswertung> back = new TreeSet<BuchungsAuswertung>(
				BuchungsAuswertung.getComparator());

		List<Aktivitaet> aktivitaeten = extrahiereElemente(buchungen,
				meinZugriff);

		int[] minuten = berechneMinuten(aktivitaeten, buchungen, meinZugriff);
		int arbeitszeit = getArbeitszeit(buchungen);
		int projektzeit = getProjektzeit(buchungen);
		Aktivitaet aktv;

		for (int i = 0; i < minuten.length; i++) {
			aktv = aktivitaeten.get(i);
			back.add(new BuchungsAuswertung(meinZugriff.getReferenz(aktv), aktv
					.getBuchungsNummer(), minuten[i], arbeitszeit, aktv
					.istProjektAktivitaet() ? projektzeit : -1));
		}

		return new LinkedList<BuchungsAuswertung>(back);
	}

	/**
	 * <p>
	 * Summiert Arbeitszeit, die in den �bergebenen Buchungen gespeichert sind
	 * zur Gesamtarbeitszeit
	 * </p>
	 * 
	 * @param buchungen
	 *            Buchungen
	 * @return Summe der gespeicherten Arbeitszeiten in Minuten
	 */
	private int getArbeitszeit(Collection<Buchung> buchungen) {
		int arbeitsminuten = 0;
		Iterator<Buchung> iter = buchungen.iterator();
		while (iter.hasNext()) {
			Buchung buchung = iter.next();
			if (buchung.getEndeZeit() != null) {
				arbeitsminuten += buchung.berechneBuchungsdauer();
			}
		}
		return arbeitsminuten;
	}

	/**
	 * <p>
	 * Summiert Arbeitszeit, die in den �bergebenen Buchungen gespeichert sind,
	 * sofern die Aktivit�t eine Projektaktivit�t ist sind.
	 * </p>
	 * 
	 * @param buchungen
	 *            Buchungen
	 * @return Summe der gespeicherten Projekt-Arbeitszeiten in Minuten
	 */
	private int getProjektzeit(Collection<Buchung> buchungen) {
		int arbeitsminuten = 0;
		Iterator<Buchung> iter = buchungen.iterator();
		while (iter.hasNext()) {
			Buchung buchung = iter.next();
			if (buchung.getAktivitaet().istProjektAktivitaet()) {
				if (buchung.getEndeZeit() != null) {
					arbeitsminuten += buchung.berechneBuchungsdauer();
				}
			}
		}
		return arbeitsminuten;
	}

	/**
	 * <p>
	 * Extrahiert aus einer Liste von Buchungen die Aktivit�ten oder Kategorien,
	 * je nach <em>AktivitaetsZugriff</em>-Objekt.
	 * </p>
	 * 
	 * @param buchungen
	 *            Buchungen
	 * @param referenz
	 *            Zugriff auf Aktivit�t (Aktivit�t/Kategorie)
	 * @return Liste mit Aktivit�ten/Kategorien, die in den Buchungen verwendet
	 *         werden
	 */
	private List<Aktivitaet> extrahiereElemente(Collection<Buchung> buchungen,
			AktivitaetsZugriff referenz) {
		List<Aktivitaet> back = new ArrayList<Aktivitaet>();

		Iterator<Buchung> iter = buchungen.iterator();
		while (iter.hasNext()) {
			Buchung buchung = iter.next();
			if (buchung.getEndeZeit() != null) {
				Aktivitaet aktAktivitaet = buchung.getAktivitaet();
				if (!enthaelt(back, aktAktivitaet, referenz)) {
					back.add(aktAktivitaet);
				}
			}
		}
		return back;
	}

	/**
	 * <p>
	 * Pr�ft, ob die Aktivit�t/Kategorie bereits in der Liste enthalten ist.
	 * </p>
	 * 
	 * @param liste
	 *            Liste mit Aktivit�ten/Kategorien
	 * @param aktv
	 *            Zu pr�fende Aktivit�t
	 * @param referenz
	 *            Zugriff auf Aktivit�t (Aktivit�t/Kategorie)
	 * @return true, falls die Aktivit�t bereits in der Liste enthalten ist
	 */
	private boolean enthaelt(List<Aktivitaet> liste, Aktivitaet aktv,
			AktivitaetsZugriff referenz) {
		return (sucheIndex(liste, aktv, referenz) > 0);
	}

	/**
	 * <p>
	 * Geht durch die Buchungen und summiert f�r jede Aktivit�t/Kategorie die
	 * daf�r verwendeten Minuten auf.
	 * </p>
	 * 
	 * @param aktivitaeten
	 *            Liste mit Aktivit�ten
	 * @param buchungen
	 *            Buchungen
	 * @param referenz
	 *            Zugriff auf Aktivit�t (Aktivit�t/Kategorie)
	 * @return Array mit Minuten, f�r jede Aktivit�t/Kategorie die aufsummierte
	 *         Anzahl
	 */
	private int[] berechneMinuten(List<Aktivitaet> aktivitaeten,
			Collection<Buchung> buchungen, AktivitaetsZugriff referenz) {
		int[] minuten = new int[aktivitaeten.size()];
		for (int i = 0; i < minuten.length; i++) {
			minuten[i] = 0;
		}

		Iterator<Buchung> iter = buchungen.iterator();
		while (iter.hasNext()) {
			Buchung buchung = iter.next();
			if (buchung.getEndeZeit() != null) {
				int index = sucheIndex(aktivitaeten, buchung.getAktivitaet(),
						referenz);
				minuten[index] += buchung.berechneBuchungsdauer();
			}
		}
		return minuten;
	}

	/**
	 * <p>
	 * Sucht in der Liste der Aktivit�ten/Kategorien nach dem Index der
	 * aktuellen Aktivit�t
	 * </p>
	 * 
	 * @param liste
	 *            Liste der Aktivit�ten/Kategorien
	 * @param aktv
	 *            Aktuelle Aktivit�t
	 * @param referenz
	 *            Zugriff auf die Aktivit�t (Aktivit�t/Kategorie)
	 * @return Index der Aktivit�t in der Liste
	 */
	private int sucheIndex(List<Aktivitaet> liste, Aktivitaet aktv,
			AktivitaetsZugriff referenz) {
		int back = -1;

		Iterator<Aktivitaet> iter = liste.iterator();
		String suche = referenz.getReferenz(aktv);
		Aktivitaet aktuell;
		int index = 0;
		while (iter.hasNext()) {
			aktuell = iter.next();
			if (suche.equals(referenz.getReferenz(aktuell))) {
				back = index;
				break;
			}
			index++;
		}
		return back;
	}

	/**
	 * <p>
	 * Mit Hilfe dieses Interfaces wird gew�hrleistet, dass die Funktionen zur
	 * Berechnung der Tages-Buchungen f�r Aktivit�ten und Aktivit�tskategorien
	 * gleich ist. Eine Implementierung des Interface stellt sicher, dass in der
	 * Berechnung auf den richtigen Wert zugegriffen wird.
	 * </p>
	 */
	private interface AktivitaetsZugriff {
		String getReferenz(Aktivitaet aktv);
	}
}

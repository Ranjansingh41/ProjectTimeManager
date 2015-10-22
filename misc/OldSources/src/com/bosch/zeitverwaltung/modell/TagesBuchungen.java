package com.bosch.zeitverwaltung.modell;

import com.bosch.zeitverwaltung.elemente.Aktivitaet;
import com.bosch.zeitverwaltung.elemente.Buchung;
import com.bosch.zeitverwaltung.elemente.Tag;
import com.bosch.zeitverwaltung.elemente.Uhrzeit;
import com.bosch.zeitverwaltung.modell.listener.BuchungenChangedListener;

/**
 * <p>
 * Die Klasse ist f�r die Verwaltung der Buchungen eines Tages verantwortlich.
 * </p>
 * 
 * @author Lars Geyer
 */
public interface TagesBuchungen extends AenderungsManager {
	/**
	 * <p>
	 * Gibt den Tag zur�ck, f�r den das Objekt Buchungen speichert.
	 * </p>
	 * 
	 * @return Buchungstag
	 */
	public Tag getBuchungsTag();

	/**
	 * <p>
	 * F�gt eine neue Buchung hinzu. Die Aktivit�t der Buchung wird �bergeben.
	 * Als Startzeit der Buchung wird entweder der aktuelle Zeitpunkt genommen,
	 * wenn die letzte Buchung keinen Endezeitpunkt besitzt. Falls die letzte
	 * Buchung bereits beendet wurde, wird deren Endezeitpunkt als
	 * Anfangszeitpunkt der Buchung verwendet.
	 * </p>
	 * 
	 * @param aktivitaet
	 *            Aktivit�t der neuen Buchung
	 *            
	 * @return Index der Buchung in der Liste 
	 */
	public int addBuchung(Aktivitaet aktivitaet);

	/**
	 * <p>
	 * F�gt eine neue Unterbrechung hinzu. Als Startzeit der Unterbrechung wird
	 * entweder der aktuelle Zeitpunkt genommen, wenn die letzte Buchung keinen
	 * Endezeitpunkt besitzt. Falls die letzte Buchung bereits beendet wurde,
	 * wird deren Endezeitpunkt als Anfangszeitpunkt der Unterbrechung
	 * verwendet.
	 * </p>
	 * 
	 * @return Index der Buchung in der Liste
	 */
	public int addUnterbrechung();

	/**
	 * <p>
	 * Beendet die aktuelle Buchung, d.h. deren Endezeitpunkt wird auf die
	 * aktuelle Uhrzeit gesetzt.
	 * </p>
	 */
	public void beendeBuchung();

	/**
	 * <p>
	 * Ver�ndert die Startzeit einer Buchung. Diese Methode ist auf die
	 * Darstellung der Buchungen in einer Liste ausgelegt, da sie die Buchung an
	 * Hand ihrer Position in der aktuellen Buchungsreihenfolge identifiziert.
	 * </p>
	 * 
	 * <p>
	 * Die Methode macht Konsistenzchecks und �ndert die Buchung nur, wenn sie
	 * im Kontext der anderen Buchungen Sinn macht, d.h. Startzeit ist vor dem
	 * Endezeitpunkt, die Startzeit ist nicht fr�her als die Startzeiten der
	 * vorhergehenden Buchung, etc.
	 * </p>
	 * 
	 * @param index
	 *            Buchung in Form der Position in der Buchungsreihenfolge
	 * @param neueStartzeit
	 *            Neue Startzeit der Buchung
	 */
	public void aendereStartzeit(int index, Uhrzeit neueStartzeit);

	/**
	 * <p>
	 * Ver�ndert die Endezeit einer Buchung. Diese Methode ist auf die
	 * Darstellung der Buchungen in einer Liste ausgelegt, da sie die Buchung an
	 * Hand ihrer Position in der aktuellen Buchungsreihenfolge identifiziert.
	 * </p>
	 * 
	 * <p>
	 * Die Methode macht Konsistenzchecks und �ndert die Buchung nur, wenn sie
	 * im Kontext der anderen Buchungen Sinn macht, d.h. Endezeit ist nach dem
	 * Startzeitpunkt, die Endezeit ist nicht sp�ter als die Endezeiten der
	 * nachfolgenden Buchung, etc.
	 * </p>
	 * 
	 * @param index
	 *            Buchung in Form der Position in der Buchungsreihenfolge
	 * @param neueEndezeit
	 *            Neue Endezeit der Buchung
	 */
	public void aendereEndezeit(int index, Uhrzeit neueEndezeit);

	/**
	 * <p>
	 * Ver�ndert die Aktivit�t einer Buchung. Diese Methode ist auf die
	 * Darstellung der Buchungen in einer Liste ausgelegt, da sie die Buchung an
	 * Hand ihrer Position in der aktuellen Buchungsreihenfolge identifiziert.
	 * </p>
	 * 
	 * @param index
	 *            Buchung in Form der Position in der Buchungsreihenfolge
	 * @param neueAktivitaet
	 *            Neue Aktivitaet der Buchung
	 */
	public void aendereAktivitaet(int index, Aktivitaet neueAktivitaet);

	/**
	 * <p>
	 * Ver�ndert den Kommentar einer Buchung. Diese Methode ist auf die
	 * Darstellung der Buchungen in einer Liste ausgelegt, da sie die Buchung an
	 * Hand ihrer Position in der aktuellen Buchungsreihenfolge identifiziert.
	 * </p>
	 * 
	 * @param index
	 *            Buchung in Form der Position in der Buchungsreihenfolge
	 * @param neuerKommentar
	 *            Neuer Kommentar der Buchung
	 */
	public void aendereKommentar(int index, String neuerKommentar);

	/**
	 * <p>
	 * Methode l�scht die �bergebene Buchung aus der Liste der Buchungen. Die
	 * Methode setzt die Startzeit der nachfolgenden Buchung auf die Startzeit
	 * der zu l�schenden Buchung. Bei der letzten Buchung wird die Endezeit auf
	 * die Endezeit der vorhergehenden Buchung gesetzt.
	 * </p>
	 * 
	 * @param buchung
	 *            Zu l�schende Buchung
	 */
	public void loescheBuchung(Buchung buchung);

	/**
	 * <p>
	 * Gibt die Anzahl der Buchungen f�r diesen Tag aus.
	 * </p>
	 * 
	 * @return Anzahl Buchungen
	 */
	public int getAnzahlBuchungen();

	/**
	 * <p>
	 * Gibt die Buchung an der �bergebenen Position in der Reihenfolge der
	 * Buchungen f�r diesen Tag zur�ck.
	 * </p>
	 * 
	 * @param index
	 *            Position der abgefragten Buchung
	 * @return Buchung
	 */
	public Buchung getBuchung(int index);

	/**
	 * <p>
	 * Ein Listener m�chte Buchungsver�nderungen signalisiert bekommen.
	 * </p>
	 * 
	 * @param listener
	 *            Listener, der an Buchungs�nderungen interessiert ist
	 */
	public void addBuchungenChangedListener(BuchungenChangedListener listener);

	/**
	 * <p>
	 * Entfernt einen Listener, der Buchungs�nderungen signalisiert bekommen
	 * m�chte.
	 * </p>
	 * 
	 * @param listener
	 *            Zu entfernender Listener
	 */
	public void removeBuchungenChangedListener(BuchungenChangedListener listener);
}
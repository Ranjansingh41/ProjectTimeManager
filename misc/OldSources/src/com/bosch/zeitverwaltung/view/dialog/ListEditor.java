package com.bosch.zeitverwaltung.view.dialog;

import com.bosch.zeitverwaltung.modell.BasisModell;
import com.bosch.zeitverwaltung.view.listener.DialogAktionListener;

/**
 * <p>
 * Schnittstelle eines Listen-Editors, d.h. der Dialog hat eine Liste von
 * Elementen, in die neue hinzugef�gt, alte ver�ndert oder gel�scht werden
 * k�nnen. Der Dialog besteht aus einem Listenfeld, in dem alle verf�gbaren
 * Objekte aufgelistet sind und ein Editierfeld, in dem die Eigenschaften des
 * aktuell ausgew�hlten Objekts editiert werden k�nnen.
 * </p>
 * 
 * @author Lars Geyer
 * @see BasisDialog
 * @see com.bosch.zeitverwaltung.control.dialog.ListEditorControl
 * @see BasisModell
 * 
 * @param <Element>
 *            Der Objekttyp, der in der Liste dargestellt werden soll.
 */
public interface ListEditor<Element> extends BasisDialog {
	/**
	 * <p>
	 * Diese Methode wird aufgerufen, wenn sich das Modell der zu editierenden
	 * Objekte ver�ndert hat. Mittels des Index wird dem Objekt die neue
	 * Position des aktuell ausgew�hlten Objekts mitgeteilt. Falls keines
	 * selektiert werden soll, wird -1 �bergeben.
	 * </p>
	 * 
	 * @param veraenderterIndex
	 *            Neuer Index des aktuell selektierten Objekts.
	 */
	public void listenModellVeraendert(int veraenderterIndex);

	/**
	 * <p>
	 * �bergibt einen Listener, der informiert werden m�chte, wenn ein neues
	 * Objekt in die Liste eingef�gt oder ein altes ver�ndert bzw. gel�scht
	 * werden soll. Der Listener ist dann f�r die Durchf�hrung der gew�nschten
	 * Aktion verantwortlich.
	 * </p>
	 * 
	 * @param listener
	 *            Listener, der �nderungen an der Objektliste durchf�hrt.
	 */
	public void addDialogAktionListener(DialogAktionListener listener);

	/**
	 * <p>
	 * Entfernt einen Listener, der informiert werden m�chte, wenn ein neues
	 * Objekt in die Liste eingef�gt oder ein altes ver�ndert bzw. gel�scht
	 * werden soll.
	 * </p>
	 * 
	 * @param listener
	 *            Listener
	 */
	public void removeDialogAktionListener(DialogAktionListener listener);
}

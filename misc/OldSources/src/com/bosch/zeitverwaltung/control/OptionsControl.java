package com.bosch.zeitverwaltung.control;

import com.bosch.zeitverwaltung.control.dialog.AktivitaetenEditorControl;
import com.bosch.zeitverwaltung.control.dialog.MinutenDeltaControl;
import com.bosch.zeitverwaltung.control.dialog.SortierungAendernControl;
import com.bosch.zeitverwaltung.modell.listener.AktivitaetenChangedListener;
import com.bosch.zeitverwaltung.view.UIFactory;
import com.bosch.zeitverwaltung.view.event.AktivitaetenEditorEvent;
import com.bosch.zeitverwaltung.view.event.AktivitaetenSortierungsEditorEvent;
import com.bosch.zeitverwaltung.view.event.MinutenDeltaEditorEvent;
import com.bosch.zeitverwaltung.view.event.OptionenEvent;
import com.bosch.zeitverwaltung.view.listener.OptionenListener;

/**
 * <p>
 * Dieses Control verwaltet alle Anforderungen an Options-�nderungen. Dazu
 * implementiert das Control die <em>OptionenListener</em>-Schnittstelle, mit
 * der die View-Komponente Options-�nderungen anfordert. Manche �nderungen
 * k�nnen direkt durchgef�hrt werden, bei anderen muss ein Editor-Dialog
 * aufgerufen werden, der die Options�nderungen durchf�hrt.
 * </p>
 * 
 * <p>
 * Die Schnittstelle <em>AktivitaetenChangedListener</em> implementiert die
 * Klasse, um �nderungen an den existierenden Aktivit�ten mitgeteilt zu
 * bekommen. Das Control ist dann f�r die Information der View-Komponente bzgl.
 * dieser �nderung zust�ndig.
 * </p>
 * 
 * @author Lars Geyer
 * @see OptionenListener
 * @see AktivitaetenChangedListener
 * @see InitSchliessenControl
 */
public class OptionsControl implements OptionenListener {
	/**
	 * <p>
	 * Konstruktor, er teilt dem Hauptfenster die aktuellen Aktivit�ten als Teil
	 * der Initialisierung mit.
	 * </p>
	 */
	public OptionsControl() {
		UIFactory.getFactory().erzeugeMainWindow().addOptionenListener(this);
	}

	/**
	 * <p>
	 * Implementiert <em>OptionenListener</em>-Interface. Der Benutzer hat
	 * den Wunsch nach einer Options�nderung.
	 * </p>
	 * 
	 * @param evt
	 *            <em>OptionenEvent</em>, der gew�nschte Optionen�nderung
	 *            n�her beschreibt
	 */
	public void event(OptionenEvent evt) {
		if (evt instanceof AktivitaetenEditorEvent) {
			AktivitaetenEditorControl dialog = new AktivitaetenEditorControl();
			dialog.showDialog();
		} else if (evt instanceof AktivitaetenSortierungsEditorEvent) {
			SortierungAendernControl dialog = new SortierungAendernControl();
			dialog.showDialog();
		} else if (evt instanceof MinutenDeltaEditorEvent) {
			MinutenDeltaControl dialog = new MinutenDeltaControl();
			dialog.showDialog();
		}
	}
}

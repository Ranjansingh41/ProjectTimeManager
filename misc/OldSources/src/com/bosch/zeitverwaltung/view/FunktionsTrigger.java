package com.bosch.zeitverwaltung.view;

import com.bosch.zeitverwaltung.view.listener.FunktionsListener;

/**
 * <p>
 * Schnittstelle zur Anmeldung von Auswertungsfunktionen. Die Funktionen m�ssen
 * in der Reihenfolge, in der sie dargestellt werden sollen angemeldet werden.
 * Ein FunktionsListener wird aufgerufen, sobald eine Funktion ausgew�hlt wurde.
 * Als Parameter wird der Name der Funktion �bergeben.
 * 
 * @author Lars Geyer
 * @see MainWindow
 * @see com.bosch.zeitverwaltung.funktion.FunktionsControl
 */
public interface FunktionsTrigger {
	/**
	 * <p>
	 * Funktion hinzuf�gen, die Reihenfolge der Aufrufe dieser Methode wird in
	 * der Darstellung ber�cksichtigt.
	 * </p>
	 * 
	 * @param name
	 *            Name der Funktion
	 * @param kurzwahl
	 *            Ein Buchstabe f�r die Kurzwahl der Funktion in einer Konstante
	 *            der Klasse KeyEvent
	 */
	public void addFunktion(String name, int kurzwahl);

	/**
	 * <p>
	 * Hinzuf�gen eines FunktionsListeners, der auf Funktionsauswahlen reagiert.
	 * Dem Listener wird der Name der Funktion mitgeteilt.
	 * </p>
	 * 
	 * @param listener
	 *            Listener, der auf Funktionsaufrufe reagieren soll
	 */
	public void addFunktionsListener(FunktionsListener listener);

	/**
	 * <p>
	 * Entfernen eines FunktionsListeners, der auf Funktionsauswahlen reagiert.
	 * </p>
	 * 
	 * @param listener
	 *            Listener, der nicht mehr auf Funktionsaufrufe reagieren soll
	 */
	public void removeFunktionsListener(FunktionsListener listener);
}

package com.bosch.zeitverwaltung.view.event;


/**
 * <p>
 * Factory f�r <em>FunktionsEvent</em>-Objekte
 * </p>
 * 
 * @author Lars Geyer
 * @see FunktionsEvent
 */
public final class FunktionsEventFactory {
	/**
	 * <p>
	 * Erzeugt einen <em>FunktionsEvent</em>
	 * </p>
	 * 
	 * @param funktionsName
	 *            Name der Auswertungsfunktion
	 * 
	 * @return Event
	 */
	public FunktionsEvent funktionsEvent(String funktionsName) {
		return new FunktionsEvent(funktionsName);
	}
}

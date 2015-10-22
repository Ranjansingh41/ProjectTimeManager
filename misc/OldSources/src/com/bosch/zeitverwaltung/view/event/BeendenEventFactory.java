package com.bosch.zeitverwaltung.view.event;

/**
 * <p>
 * Factory f�r <em>BeendenEvent</em>-Objekte
 * </p>
 * 
 * @author Lars Geyer
 * @see BeendenEvent
 */
public final class BeendenEventFactory {
	/**
	 * <p>
	 * Erzeugt einen <em>BeendenEvent</em>
	 * </p>
	 * 
	 * @return Event
	 */
	public BeendenEvent beendenEvent() {
		return new BeendenEvent();
	}
}

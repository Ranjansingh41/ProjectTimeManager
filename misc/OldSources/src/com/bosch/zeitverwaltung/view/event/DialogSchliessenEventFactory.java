package com.bosch.zeitverwaltung.view.event;

/**
 * <p>
 * Factory f�r <em>DialogSchliessenEvent</em>-Objekte
 * </p>
 * 
 * @author Lars Geyer
 * @see DialogSchliessenEvent
 */
public class DialogSchliessenEventFactory {
	/**
	 * <p>
	 * Erzeugt einen Commit-Event
	 * </p>
	 * 
	 * @return Schlie�en-Event
	 */
	public DialogSchliessenEvent commitEvent() {
		return new DialogCommitEvent<Object>();
	}

	/**
	 * <p>
	 * Erzeugt einen Commit-Event mit Botschaft
	 * </p>
	 * 
	 * @return Schlie�en-Event
	 */
	public <Element> DialogSchliessenEvent commitEvent(Element botschaft) {
		return new DialogCommitEvent<Element>(botschaft);
	}

	/**
	 * <p>
	 * Erzeugt einen Abbruch-Event
	 * </p>
	 * 
	 * @return Schlie�en-Event
	 */
	public DialogSchliessenEvent abbruchEvent() {
		return new DialogAbbruchEvent();
	}

	/**
	 * <p>
	 * Erzeugt einen Verneinungs-Event
	 * </p>
	 * 
	 * @return Schlie�en-Event
	 */
	public DialogSchliessenEvent neinEvent() {
		return new DialogVerneinungEvent();
	}
}

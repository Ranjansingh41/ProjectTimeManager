package com.bosch.zeitverwaltung.view.event;

/**
 * <p>
 * Factory f�r <em>DialogAktionEvent</em>-Objekte
 * </p>
 * 
 * @author Lars Geyer
 * @see DialogAktionEvent
 */
public final class DialogAktionEventFactory {
	/**
	 * <p>
	 * Erzeugt einen Hinzuf�gen-Event
	 * </p>
	 * 
	 * @return DialogAktionEvent
	 */
	public <Element> DialogAktionEvent hinzufuegenEvent(Element eingabe) {
		return new DialogHinzufuegenEvent<Element>(eingabe);
	}

	/**
	 * <p>
	 * Erzeugt einen Ver�ndern-Event
	 * </p>
	 * 
	 * @return DialogAktionEvent
	 */
	public <Element> DialogAktionEvent veraendernEvent(Element auswahl, Element eingabe) {
		return new DialogVeraendernEvent<Element>(auswahl, eingabe);
	}

	/**
	 * <p>
	 * Erzeugt einen L�schen-Event
	 * </p>
	 * 
	 * @return DialogAktionEvent
	 */
	public <Element> DialogAktionEvent loeschenEvent(Element auswahl) {
		return new DialogLoeschenEvent<Element>(auswahl);
	}
}

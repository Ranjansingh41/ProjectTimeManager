package com.bosch.zeitverwaltung.view.event;

/**
 * <p>
 * Ein Element soll wie �bergeben ver�ndert werden.
 * </p>
 * 
 * @author Lars Geyer
 *
 */
public final class DialogVeraendernEvent<Element> extends DialogAktionEvent {
	private Element selektion;
	private Element eingabe;
	
	/**
	 * <p>
	 * Erzeugt einen Element ver�ndern Event
	 * </p>
	 * 
	 * @param selektion Das zu ver�ndernde Element
	 */
	DialogVeraendernEvent(Element selektion, Element eingabe) {
		this.selektion = selektion;
		this.eingabe = eingabe;
	}

	/**
	 * <p>
	 * Gibt zu loeschendes Element zur�ck
	 * </p>
	 * 
	 * @return Zu loeschendes Element
	 */
	public Element getElement() {
		return selektion;
	}

	/**
	 * <p>
	 * Gibt die �nderungen zur�ck
	 * </p>
	 * 
	 * @return Die durchzuf�hrenden �nderungen
	 */
	public Element getEingabe() {
		return eingabe;
	}
}

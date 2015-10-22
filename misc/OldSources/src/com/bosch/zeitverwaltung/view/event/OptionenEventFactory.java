package com.bosch.zeitverwaltung.view.event;

/**
 * <p>
 * Factory f�r <em>OptionenEvent</em>-Objekte
 * </p>
 * 
 * @author Lars Geyer
 * @see OptionenEvent
 */
public class OptionenEventFactory {
	/**
	 * <p>
	 * Erzeugt einen Aktivit�teneditor-Event
	 * </p>
	 * 
	 * @return Aktivit�teneditor-Event
	 */
	public OptionenEvent aktivitaetenEditorEvent() {
		return new AktivitaetenEditorEvent();
	}

	/**
	 * <p>
	 * Erzeugt einen Sortierungs-Event
	 * </p>
	 * 
	 * @return Sortierungs-Event
	 */
	public OptionenEvent sortierungsEvent() {
		return new AktivitaetenSortierungsEditorEvent();
	}

	/**
	 * <p>
	 * Erzeugt einen Minuten-Delta-Event
	 * </p>
	 * 
	 * @return Minuten-Delta-Event
	 */
	public OptionenEvent minutenDeltaEvent() {
		return new MinutenDeltaEditorEvent();
	}
}

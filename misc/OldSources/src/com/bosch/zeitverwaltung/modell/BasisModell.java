package com.bosch.zeitverwaltung.modell;

import java.util.Collection;

/**
 * <p>
 * Basis-Modell. Es hat einige grundlegende Funktionalit�ten, die in mehreren
 * Modellen zum Einsatz kommen.
 * </p>
 * 
 * @author Lars Geyer
 * @see AktivitaetenVerwaltung
 * @see BuchungsFilter
 * 
 * @param <Element>
 *            Objekttyp, der mittels des Interface verwaltet wird
 */
public interface BasisModell<Element> extends AenderungsTransaktion {
	/**
	 * <p>
	 * Methode erlaubt den Zugriff auf die verwalteten Elemente
	 * </p>
	 * 
	 * @return Liste der verwaltenten Elemente
	 */
	public Collection<Element> elemente();

	/**
	 * <p>
	 * Hinzuf�gen eines neuen Elements in die Liste
	 * </p>
	 * 
	 * @param neuesElement
	 *            Einzuf�gendes Element
	 * @return Index des Elements in der Liste
	 * @throws Exception
	 *             Falls die �berpr�fung des neuen Elements fehl schl�gt
	 */
	public int elementHinzufuegen(Element neuesElement) throws Exception;

	/**
	 * <p>
	 * L�schen des �bergebenen Elements aus der Liste
	 * </p>
	 * 
	 * @param zuLoeschendes
	 *            Zu l�schendes Element
	 */
	public void elementLoeschen(Element zuLoeschendes);
}

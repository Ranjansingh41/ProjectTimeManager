package com.bosch.zeitverwaltung.view.dialog;

public abstract class UIDialogFactory {
	/**
	 * <p>
	 * Neuer Dialog zur Auswahl eines Tages.
	 * </p>
	 * 
	 * @return Tages-Auswahl-Dialog
	 */
	public abstract TagOeffnenAuswahl erzeugeTagOeffnenAuswahlDialog();

	/**
	 * <p>
	 * Neuer Dialog zur Auswahl einer Aktivit�t.
	 * </p>
	 * 
	 * @return Aktivit�ten-Auswahl-Dialog
	 */
	public abstract AktivitaetenAuswahl erzeugeAktivitaetenAuswahlDialog();

	/**
	 * <p>
	 * Neuer Editor f�r die Eingabe/Ver�nderung der Aktivit�ten-Liste.
	 * </p>
	 * 
	 * @return Aktivit�ten-Editor
	 */
	public abstract AktivitaetenEditor erzeugeAktivitaetenEditor();

	/**
	 * <p>
	 * Neuer Minuten-Delta-Auswahldialog
	 * </p>
	 * 
	 * @return Minuten-Delta-Auswahldialog
	 */
	public abstract MinutenDeltaAuswahl erzeugeMinutenDeltaAuswahl();

	/**
	 * <p>
	 * Neuer Aktivit�tssortierung-Auswahldialog
	 * </p>
	 * 
	 * @return Aktivit�tssortierung-Auswahldialog
	 */
	public abstract AktivitaetsSortierungsAuswahl erzeugeAktivitaetsSortierungsAuswahl();

	/**
	 * <p>
	 * Neuer About Anwendungs-Dialog.
	 * </p>
	 * 
	 * @return About-Dialog
	 */
	public abstract AboutInformation erzeugeAboutInformationDialog();

	/**
	 * <p>
	 * Neuer Nachfragen-Dialog, um dem Benutzer direkte Fragen
	 * (Ja/Nein[/Abbruch])zu stellen
	 * </p>
	 * 
	 * @return Nachfragen-Dialog
	 */
	public abstract NachfragenBox erzeugeNachfragenDialog();
}

package com.bosch.zeitverwaltung.control;

import java.io.IOException;
import com.bosch.zeitverwaltung.view.MainWindow;
import com.bosch.zeitverwaltung.view.UIFactory;
import com.bosch.zeitverwaltung.view.dialog.AboutInformation;
import com.bosch.zeitverwaltung.view.dialog.UIDialogFactory;
import com.bosch.zeitverwaltung.view.event.AboutEvent;
import com.bosch.zeitverwaltung.view.event.HilfeEvent;
import com.bosch.zeitverwaltung.view.listener.HilfeListener;

/**
 * <p>
 * Hauptcontrol der Anwendung. Es initialisiert die Anwendung und startet die
 * Darstellung des Hauptfensters. Danach legt sich der Hauptthread schlafen, bis
 * der Anwendung-Schlie�en-Event den Thread wieder aufweckt.
 * </p>
 * 
 * <p>
 * �ber das Applikations-Control werden einzelne Controls gestartet, die sich
 * mit den unterschiedlichen Event-Typen besch�ftigen. Einen Teil dieser
 * Funktionalit�t �bernimmt das Applikations-Control selbst. Es ist f�r die
 * Verarbeitung von Hilfe-Events zust�ndig. F�r die anderen Typen von Events
 * gibt es jeweils ein Control:
 * </p>
 * 
 * <p>
 * <ul>
 * <li><em>BuchungsControl</em> f�r Buchungs-Events</li>
 * <li><em>OptionsControl</em> f�r Options-�nderungs-Events</li>
 * <li><em>SpeicherControl</em> f�r Datei-Events</li>
 * <li><em>FunktionsControl</em> f�r Auswertungs-Events</li>
 * <li><em>InitSchliessenControl</em> f�r Anwendung-Schlie�en-Events</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Die Controls werden in der Initialisierung durch das
 * <em>InitSchliessenControl</em> erzeugt. Sie laufen autark und reagieren auf
 * Events, durch die sie auch Einfluss auf den Kontrollfluss bekommen.
 * </p>
 * 
 * @author Lars Geyer
 * @see com.bosch.zeitverwaltung.Zeitverwaltung
 * @see BuchungsControl
 * @see OptionsControl
 * @see SpeicherControl
 * @see com.bosch.zeitverwaltung.funktion.FunktionsControl
 * @see InitSchliessenControl
 * @see HilfeListener
 */
public class ApplikationsControl implements HilfeListener {
	/**
	 * <p>
	 * Initialisierung und Darstellung der Anwendung. Danach wird der
	 * Hauptthread schlafen gelegt.
	 * </p>
	 * 
	 * @throws IOException
	 *             Bei Problemen mit Dateien (IO-, XML-Fehler, etc.)
	 * @throws IllegalArgumentException
	 *             Falls Anwendung bereits l�uft (nur eine Instanz erlaubt)
	 */
	public void run() throws IOException, IllegalArgumentException {
		new InitSchliessenControl(this);
		MainWindow hauptFenster = UIFactory.getFactory().erzeugeMainWindow();
		hauptFenster.addHilfeListener(this);
		hauptFenster.starteDarstellung();
		synchronisiere();
	}

	/**
	 * {@inheritDoc}
	 */
	public void event(HilfeEvent evt) {
		if (evt instanceof AboutEvent) {
			UIDialogFactory uiFactory = UIFactory.getFactory().getDialogFactory();
			AboutInformation dialog = uiFactory.erzeugeAboutInformationDialog();
			dialog.showDialog();
		}
	}

	/**
	 * <p>
	 * Warte auf das Ende der Anwendung
	 * </p>
	 */
	private synchronized void synchronisiere() {
		boolean beenden = false;
		while (!beenden) {
			try {
				wait();
				beenden = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * <p>
	 * Wird von der Anwendung-Beenden-Behandlung aufgerufen, um Hauptthread
	 * aufzuwecken.
	 * </p>
	 */
	public synchronized void rendezvous() {
		notifyAll();
	}
}

package com.bosch.zeitverwaltung.swing_view.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;

import com.bosch.zeitverwaltung.event.EventVerteiler;
import com.bosch.zeitverwaltung.view.dialog.BasisDialog;
import com.bosch.zeitverwaltung.view.event.DialogSchliessenEvent;
import com.bosch.zeitverwaltung.view.event.DialogSchliessenEventFactory;
import com.bosch.zeitverwaltung.view.listener.DialogSchliessenListener;

/**
 * <p>
 * Basis-Funktionali�t eines Dialogs. Dies betrifft Initialisierung, sowie
 * Starten und Beenden der Darstellung
 * </p>
 * 
 * @author Lars Geyer
 * @see BasisDialog
 */
public abstract class BasisDialogImpl extends JDialog implements BasisDialog {
	private static final long serialVersionUID = 1L;

	private DialogSchliessenPanel schlie�enPanel = null;

	private EventVerteiler<DialogSchliessenEvent, DialogSchliessenListener> listenerMgmt = 
		new EventVerteiler<DialogSchliessenEvent, DialogSchliessenListener>();

	/**
	 * <p>
	 * Konstruktor, er initialisiert den Dialog. Danach kann er dargestellt
	 * werden
	 * </p>
	 * 
	 * @param meinFrame
	 *            Hauptfenster, in dessen Kontext Dialog dargestellt werden soll
	 */
	protected BasisDialogImpl(Frame meinFrame) {
		super(meinFrame);
	}

	/**
	 * <p>
	 * F�ge einen Listener hinzu, der Dialog-Schlie�en-Events mitgeteilt
	 * bekommen m�chte. nach dem Schlie�en kann die Verarbeitung der �nderung
	 * durch den Listener erfolgen.
	 * </p>
	 * 
	 * @param listener
	 *            Listener f�r Dialog-Schlie�en-Events
	 */
	public void addDialogSchliessenListener(DialogSchliessenListener listener) {
		listenerMgmt.addEventListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeDialogSchliessenListener(DialogSchliessenListener listener) {
		listenerMgmt.delEventListener(listener);
	}

	/**
	 * <p>
	 * Mittels dieser Methode wird der Dialog auf den Bildschirm gebracht. Der
	 * Dialog ist modal und die Methode kehrt erst zur�ck, wenn die Auswahl
	 * erfolgt ist. Danach ist der Dialog wieder vom Bildschirm entfernt und
	 * kann entsorgt werden.
	 * </p>
	 */
	public void showDialog() {
		setVisible(true);
	}

	/**
	 * <p>
	 * Initialisiert das Schlie�en-Panel
	 * </p>
	 * 
	 * @return Schlie�en-Panel
	 */
	protected DialogSchliessenPanel getSchlie�enPanel() {
		if (schlie�enPanel == null) {
			schlie�enPanel = new DialogSchliessenPanel(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					setVisible(false);
					String kommando = evt.getActionCommand();
					DialogSchliessenEventFactory eventFactory = new DialogSchliessenEventFactory();
					if (kommando.equals(DialogSchliessenPanel.commitKommando)) {
						listenerMgmt.event(eventFactory
								.commitEvent(getBotschaft()));
					} else if (kommando
							.equals(DialogSchliessenPanel.abbruchKommando)) {
						listenerMgmt.event(eventFactory.abbruchEvent());
					}
				}
			});
		}
		return schlie�enPanel;
	}

	/**
	 * <p>
	 * Ermittelt die Botschaft f�r den Dialog-Schlie�en-Event bei einem Commit.
	 * Die hier realisierte Version der Funktion gibt <em>null</em> zur�ck,
	 * f�r alle Dialoge, die beim Commit keine Botschaft senden m�chten.
	 * Dialoge, die eine Botschaft haben, m�ssen die Methode �berschreiben.
	 * </p>
	 * 
	 * @return Botschaft f�r den Dialog-Schlie�en-Event bei einem Commit
	 */
	protected Object getBotschaft() {
		return null;
	}

	/**
	 * <p>
	 * Basis-Initialisierung, wird durch �berschreibende Methoden erg�nzt. Diese
	 * m�ssen allerdings immer diese Methode aufrufen.
	 * </p>
	 */
	protected void initialize() {
		this.setTitle(getTitel());
		this.setContentPane(getJContentPane());
		this.pack();
		this.setResizable(false);
		this.setModal(true);
		this.setLocationRelativeTo(this.getParent());
		this.addWindowListener(new WindowAdapter() {
			/**
			 * <p>
			 * Beim Schlie�en des Dialogs in der Titelzeile wird der Dialog
			 * abgebrochen.
			 * </p>
			 * 
			 * @param evt
			 *            Ohne Bedeutung
			 */
			public void windowClosing(WindowEvent evt) {
				setVisible(false);
				listenerMgmt.event(new DialogSchliessenEventFactory()
						.abbruchEvent());
			}
		});
	}

	/**
	 * <p>
	 * Aufbau der Dialogbestandteile. Dies wird von einer abgeleiteten Klasse
	 * definiert, da diese wei�, wie der Dialog aussehen soll.
	 * </p>
	 * 
	 * @return Panel mit dem Inhalt des Dialogs
	 */
	protected abstract JPanel getJContentPane();

	/**
	 * <p>
	 * Der Titel des Dialogs ist anwendungsabh�ngig und wird von einer
	 * abgeleiteten Klasse definiert. �ber diese Methode kann der Titel hier in
	 * der Initialisierung gesetzt werden.
	 * </p>
	 * 
	 * @return Titel des Dialogs
	 */
	protected abstract String getTitel();
}
package com.bosch.zeitverwaltung.swing_view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.bosch.zeitverwaltung.elemente.Aktivitaet;
import com.bosch.zeitverwaltung.elemente.Buchung;
import com.bosch.zeitverwaltung.event.EventVerteiler;
import com.bosch.zeitverwaltung.view.event.BuchungsEvent;
import com.bosch.zeitverwaltung.view.event.BuchungsEventFactory;
import com.bosch.zeitverwaltung.view.listener.BuchungsListener;

/**
 * <p>
 * Klasse definiert das Buchungsmen� der Zeitverwaltung
 * </p>
 * 
 * @author Lars Geyer
 */
class BuchungsMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	private MainWindowImpl meinFenster = null;

	private EventVerteiler<BuchungsEvent, BuchungsListener> listenerMgmt = 
		new EventVerteiler<BuchungsEvent, BuchungsListener>();

	private JMenuItem buchungDurchfuehren = null;
	private JMenuItem unterbrechungDurchfuehren = null;
	private JMenuItem buchungLoeschen = null;
	private JMenuItem buchungBeenden = null;

	/**
	 * <p>
	 * Der Konstruktor erzeugt die Swing-Objektstruktur des Men�s.
	 * </p>
	 */
	BuchungsMenu(MainWindowImpl fenster) {
		meinFenster = fenster;
		initialisiere();
	}

	/**
	 * <p>
	 * F�gt Listener f�r Men�events hinzu
	 * </p>
	 * 
	 * @param listener
	 *            Listener
	 */
	void addBuchungsListener(BuchungsListener listener) {
		listenerMgmt.addEventListener(listener);
	}

	/**
	 * <p>
	 * Entfernt Listener f�r Men�events
	 * </p>
	 * 
	 * @param listener
	 *            Listener
	 */
	void removeBuchungsListener(BuchungsListener listener) {
		listenerMgmt.delEventListener(listener);
	}

	/**
	 * <p>
	 * Methode wird vom Hauptfenster aufgerufen, sobald �ber das Fenster eine
	 * Buchung durchgef�hrt wurde.
	 * </p>
	 * 
	 * @param aktivitaet
	 *            Aktivit�t der Buchung
	 */
	void neueBuchung(Aktivitaet aktivitaet) {
		listenerMgmt.event(new BuchungsEventFactory()
				.neueBuchungEvent(aktivitaet));
	}

	/**
	 * <p>
	 * Initialisierung des Men�s
	 * </p>
	 */
	private void initialisiere() {
		setText("Buchungen");
		setMnemonic(KeyEvent.VK_B);
		add(getBuchungDurchfuehren());
		add(getUnterbrechungDurchfuehren());
		addSeparator();
		add(getBuchungBeenden());
		add(getBuchungLoeschen());
	}

	/**
	 * <p>
	 * Zugriff und Initialisierung des GUI-Objekts
	 * </p>
	 * 
	 * @return Neue Buchung Men�punkt
	 */
	private JMenuItem getBuchungDurchfuehren() {
		if (buchungDurchfuehren == null) {
			buchungDurchfuehren = new JMenuItem();
			buchungDurchfuehren.setText("Buchung");
			buchungDurchfuehren.setMnemonic(KeyEvent.VK_B);
			buchungDurchfuehren.setAccelerator(KeyStroke.getKeyStroke(
					KeyEvent.VK_B, InputEvent.CTRL_MASK));
			buchungDurchfuehren.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listenerMgmt.event(new BuchungsEventFactory()
							.neueBuchungEvent());
				}
			});
		}
		return buchungDurchfuehren;
	}

	/**
	 * <p>
	 * Zugriff und Initialisierung des GUI-Objekts
	 * </p>
	 * 
	 * @return Neue Unterbrechung Men�punkt
	 */
	private JMenuItem getUnterbrechungDurchfuehren() {
		if (unterbrechungDurchfuehren == null) {
			unterbrechungDurchfuehren = new JMenuItem();
			unterbrechungDurchfuehren.setText("Unterbrechung");
			unterbrechungDurchfuehren.setMnemonic(KeyEvent.VK_U);
			unterbrechungDurchfuehren.setAccelerator(KeyStroke.getKeyStroke(
					KeyEvent.VK_U, InputEvent.CTRL_MASK));
			unterbrechungDurchfuehren.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listenerMgmt.event(new BuchungsEventFactory()
							.neueUnterbrechungEvent());
				}
			});
		}
		return unterbrechungDurchfuehren;
	}

	/**
	 * <p>
	 * Zugriff und Initialisierung des GUI-Objekts
	 * </p>
	 * 
	 * @return Buchung l�schen Men�punkt
	 */
	private JMenuItem getBuchungLoeschen() {
		if (buchungLoeschen == null) {
			buchungLoeschen = new JMenuItem();
			buchungLoeschen.setText("Buchung l�schen");
			buchungLoeschen.setMnemonic(KeyEvent.VK_L);
			buchungLoeschen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Buchung buchung = meinFenster.getSelektion();
					if (buchung != null) {
						listenerMgmt.event(new BuchungsEventFactory()
								.loeschenEvent(buchung));
					}
				}
			});
		}
		return buchungLoeschen;
	}

	/**
	 * <p>
	 * Zugriff und Initialisierung des GUI-Objekts
	 * </p>
	 * 
	 * @return Buchung beenden Men�punkt
	 */
	private JMenuItem getBuchungBeenden() {
		if (buchungBeenden == null) {
			buchungBeenden = new JMenuItem();
			buchungBeenden.setText("Buchung beenden");
			buchungBeenden.setMnemonic(KeyEvent.VK_N);
			buchungBeenden.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
					InputEvent.CTRL_MASK));
			buchungBeenden.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listenerMgmt.event(new BuchungsEventFactory()
							.beendenEvent());
				}
			});
		}
		return buchungBeenden;
	}
}

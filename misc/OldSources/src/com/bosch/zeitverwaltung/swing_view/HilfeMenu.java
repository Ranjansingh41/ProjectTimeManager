package com.bosch.zeitverwaltung.swing_view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.bosch.zeitverwaltung.event.EventVerteiler;
import com.bosch.zeitverwaltung.view.event.HilfeEvent;
import com.bosch.zeitverwaltung.view.event.HilfeEventFactory;
import com.bosch.zeitverwaltung.view.listener.HilfeListener;

/**
 * <p>
 * Klasse definiert das Hilfemen� der Anwendung
 * </p>
 * 
 * @author Lars Geyer
 * 
 */
class HilfeMenu extends JMenu {
	private static final long serialVersionUID = 1L;

	private EventVerteiler<HilfeEvent, HilfeListener> hilfeManager = 
		new EventVerteiler<HilfeEvent, HilfeListener>();

	private JMenuItem aboutBox = null;

	/**
	 * <p>
	 * Der Konstruktor erzeugt die Swing-Objektstruktur des Men�s.
	 * </p>
	 */
	HilfeMenu() {
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
	void addHilfeListener(HilfeListener listener) {
		hilfeManager.addEventListener(listener);
	}

	/**
	 * <p>
	 * Entfernt Listener f�r Men�events
	 * </p>
	 * 
	 * @param listener
	 *            Listener
	 */
	void removeHilfeListener(HilfeListener listener) {
		hilfeManager.delEventListener(listener);
	}

	/**
	 * <p>
	 * Initialisierung des Men�s
	 * </p>
	 */
	private void initialisiere() {
		setText("Hilfe");
		setMnemonic(KeyEvent.VK_H);
		add(getAboutBox());
	}

	/**
	 * <p>
	 * Zugriff und Initialisierung des GUI-Objekts
	 * </p>
	 * 
	 * @return Aboutbox-Men�punkt
	 */
	private JMenuItem getAboutBox() {
		if (aboutBox == null) {
			aboutBox = new JMenuItem();
			aboutBox.setText("�ber ...");
			aboutBox.setMnemonic(KeyEvent.VK_A);
			aboutBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					hilfeManager.event(new HilfeEventFactory().aboutEvent());
				}
			});
		}
		return aboutBox;
	}
}

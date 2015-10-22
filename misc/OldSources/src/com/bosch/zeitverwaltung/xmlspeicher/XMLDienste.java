package com.bosch.zeitverwaltung.xmlspeicher;

import java.io.File;
import java.io.IOException;

import com.bosch.zeitverwaltung.elemente.Tag;
import com.bosch.zeitverwaltung.services.ApplikationsDatenVerzeichnis;

/**
 * Einige Basisdienste f�r die Speicherung von Dateien
 * 
 * @author Lars Geyer
 * @see XMLBuchungsReader
 * @see XMLBuchungsWriter
 */
class XMLDienste {
	// Unterverzeichnis in der die Buchungsdateien gespeichert werden
	// Basis ist das Anwendungsdaten-Verzeichnis von Windows
	static final String datenVerzeichnis = "Buchungen";
	// Name der Lock-Datei, die mehrere Instanzen der Zeitverwaltung verhindern
	// soll
	static final String lockDatei = "locked.txt";
	// Flag, dass die Pr�fung der Lockdatei protokolliert. Dies verhindert, dass
	// die
	// Pr�fung mehrfach durchgef�hrt wird, was in einer Exception resultieren
	// w�rde.
	private static boolean pruefungDurchgefuehrt = false;

	// Tags, die in Buchungsdateien zur Anwendung kommen
	static final String tagWurzel = "buchungen";
	static final String tagBuchung = "buchung";
	static final String tagKommentar = "kommentar";

	/**
	 * <p>
	 * Das Paket speichert f�r jeden Tag die Buchungen in einer XML-Datei mit
	 * einem bestimmten Namen. Die Methode erzeugt den passenden Namen dieser
	 * Datei abh�ngig vom Buchungs-Tag.
	 * </p>
	 * 
	 * @param buchungsTag
	 *            Buchungstag, f�r den Daten in eine Datei geschrieben werden
	 *            sollen
	 * @return Name der XML-Datei in der Buchungen eines Tages gespeichert
	 *         werden
	 */
	String erzeugeDateiname(Tag buchungsTag) {
		StringBuffer dateiname = new StringBuffer();
		dateiname.append("Buchungen_");
		dateiname.append(buchungsTag.getJahr());
		if (buchungsTag.getMonat() < 10) {
			dateiname.append('0');
		}
		dateiname.append(buchungsTag.getMonat());
		if (buchungsTag.getTag() < 10) {
			dateiname.append('0');
		}
		dateiname.append(buchungsTag.getTag());
		dateiname.append(".xml");
		return dateiname.toString();
	}

	/**
	 * <p>
	 * Erzeugt ein Handle auf das Speicherverzeichnis der Buchungsdaten. Dies
	 * ist ein Unterverzeichnis des Anwendungsdaten-Ordners von Windows.
	 * </p>
	 * 
	 * @return <em>File</em>-Handle auf das Speicherverzeichnis von
	 *         Buchungsdaten
	 */
	File getDatenVerzeichnis() {
		File back = null;

		File appdata = new ApplikationsDatenVerzeichnis()
				.getAppdataVerzeichnis();
		back = new File(appdata, datenVerzeichnis);

		if (!back.exists()) {
			back.mkdir();
		}

		return back;
	}

	/**
	 * <p>
	 * Die Zeitverwaltung darf nur einmal aufgerufen werden. Der Grund daf�r
	 * ist, dass ansonsten die Datenintegrit�t der Buchungsdaten nicht
	 * sichergestellt werden kann. Aus diesem Grund ist dies eine Funktionalit�t
	 * der Speicherungsschnittstelle, da andere Implementierungen diese H�rde
	 * nicht besitzen.
	 * </p>
	 * 
	 * <p>
	 * Die Sicherstellung erfolgt mittels einer Lockdatei, die in das
	 * Buchungsverzeichnis geschrieben und beim Beenden der Anwendung wieder
	 * gel�scht wird.
	 * </p>
	 */
	void pruefeLockDatei() {
		if (!pruefungDurchgefuehrt) {
			File lock = new File(getDatenVerzeichnis(), lockDatei);
			try {
				if (!lock.createNewFile()) {
					throw new IllegalArgumentException(
							"Keine zweite Instanz von Zeitverwaltung m�glich");
				}
				lock.deleteOnExit();
			} catch (IOException e) {
				e.printStackTrace();
				throw new IllegalArgumentException(e.getLocalizedMessage());
			}
			pruefungDurchgefuehrt = true;
		}
	}
}

package com.bosch.zeitverwaltung.view.dialog;

import com.bosch.zeitverwaltung.view.listener.DialogSchliessenListener;

/**
 * <p>
 * Grundlegende Editoren-Schnittstelle. Sie beherbergt die Funktionslit�t zur
 * Darstellung und Beendigung des Dialogs
 * </p>
 * 
 * @author Lars Geyer
 * @see com.bosch.zeitverwaltung.control.dialog.BasisEditorControl
 */
public interface BasisDialog {
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
	public void addDialogSchliessenListener(DialogSchliessenListener listener);

	/**
	 * <p>
	 * Entfernt einen Listener, der Dialog-Schlie�en-Events mitgeteilt bekommen
	 * m�chte.
	 * </p>
	 * 
	 * @param listener
	 *            Listener f�r Dialog-Schlie�en-Events
	 */
	public void removeDialogSchliessenListener(DialogSchliessenListener listener);

	/**
	 * <p>
	 * Mittels dieser Methode wird der Dialog auf den Bildschirm gebracht. Der
	 * Dialog ist modal und die Methode kehrt erst zur�ck, wenn die Auswahl
	 * erfolgt ist. Danach ist der Dialog wieder vom Bildschirm entfernt und
	 * kann entsorgt werden.
	 * </p>
	 */
	public void showDialog();
}

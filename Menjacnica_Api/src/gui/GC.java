package gui;

import java.awt.EventQueue;

import javax.swing.JOptionPane;

import logickakontrola.Kontroler;


public class GC {

	public static Kontroler kontrola = new Kontroler();
	public static Prozor prozor;
	
	public static void exit() {
		int opcija = JOptionPane.showConfirmDialog(null, "Izlaz?", "Napustanje programa",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prozor frame = new Prozor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

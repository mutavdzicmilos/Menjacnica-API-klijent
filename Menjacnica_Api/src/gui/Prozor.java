package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logickakontrola.Drzava;
import logickakontrola.Menjacnica;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.CaretEvent;

public class Prozor extends JFrame {

	private JPanel contentPane;
	private JLabel lblIzZemlje;
	private JComboBox comboBoxFrom;
	private JLabel lblIznos;
	private JTextField textFieldFrom;
	private JLabel lblZemlje;
	private JComboBox comboBoxTo;
	private JLabel lblIznos1;
	private JTextField textFieldTo;
	private ArrayList<Drzava> drzavalista;
	private JButton btnKonvertuj;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { MainWindow frame = new MainWindow();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */
	/**
	 * Create the frame.
	 */
	public Prozor() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				GuiKontroler.exit();
			}
		});
		drzavalista = GuiKontroler.kontrola.getCountries();
		setTitle("Menjacnica");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblIzZemlje());
		contentPane.add(getComboBoxFrom());
		contentPane.add(getLblIznos());
		contentPane.add(getTextFieldFrom());
		contentPane.add(getLblZemlje());
		contentPane.add(getComboBoxTo());
		contentPane.add(getLblIznos1());
		contentPane.add(getTextFieldTo());
		contentPane.add(getBtnKonvertuj());
	}

	private JLabel getLblIzZemlje() {
		if (lblIzZemlje == null) {
			lblIzZemlje = new JLabel("Iz valute zemlje");
			lblIzZemlje.setBounds(32, 30, 113, 14);
		}
		return lblIzZemlje;
	}

	private JComboBox getComboBoxFrom() {
		if (comboBoxFrom == null) {
			comboBoxFrom = new JComboBox(drzavalista.toArray());
			comboBoxFrom.setBounds(32, 74, 113, 20);
		}
		return comboBoxFrom;
	}

	private JLabel getLblIznos() {
		if (lblIznos == null) {
			lblIznos = new JLabel("Iznos");
			lblIznos.setBounds(32, 124, 113, 14);
		}
		return lblIznos;
	}

	private JTextField getTextFieldFrom() {
		if (textFieldFrom == null) {
			textFieldFrom = new JTextField();
			textFieldFrom.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {

				}

				public void removeUpdate(DocumentEvent e) {
					if (textFieldFrom.getText().isEmpty())
						btnKonvertuj.setEnabled(false);
				}

				public void insertUpdate(DocumentEvent e) {
					btnKonvertuj.setEnabled(true);
				}
			});
			textFieldFrom.setBounds(32, 168, 113, 20);
			textFieldFrom.setColumns(10);
		}
		return textFieldFrom;
	}

	private JLabel getLblZemlje() {
		if (lblZemlje == null) {
			lblZemlje = new JLabel("U valutu zemlje");
			lblZemlje.setBounds(298, 30, 113, 14);
		}
		return lblZemlje;
	}

	private JComboBox getComboBoxTo() {
		if (comboBoxTo == null) {
			comboBoxTo = new JComboBox(drzavalista.toArray());
			comboBoxTo.setBounds(298, 74, 113, 20);
		}
		return comboBoxTo;
	}

	private JLabel getLblIznos1() {
		if (lblIznos1 == null) {
			lblIznos1 = new JLabel("Iznos");
			lblIznos1.setBounds(298, 124, 113, 14);
		}
		return lblIznos1;
	}

	private JTextField getTextFieldTo() {
		if (textFieldTo == null) {
			textFieldTo = new JTextField();
			textFieldTo.setEditable(false);
			textFieldTo.setColumns(10);
			textFieldTo.setBounds(298, 168, 113, 20);
		}
		return textFieldTo;
	}

	private JButton getBtnKonvertuj() {
		if (btnKonvertuj == null) {
			btnKonvertuj = new JButton("Konvertuj");
			btnKonvertuj.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int fromIndex = comboBoxFrom.getSelectedIndex();
					int toIndex = comboBoxTo.getSelectedIndex();
					String from = drzavalista.get(fromIndex).getCurrencyId();
					String to = drzavalista.get(toIndex).getCurrencyId();
					konvertuj(from, to);
					}
				}
			);
			btnKonvertuj.setEnabled(false);
			btnKonvertuj.setBounds(178, 218, 89, 23);
		}
		return btnKonvertuj;
	}

	private void konvertuj(String from, String to) {
		double odnos = 0;
		try {
			odnos = GuiKontroler.kontrola.returnExchangeRate(from, to);
			double amount = Double.parseDouble(textFieldFrom.getText());

			textFieldTo.setText("" + odnos * amount);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Konverzija nije uspela!", "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
		try {
			
			GuiKontroler.kontrola.saveExchange(from, to, odnos);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Sacuvavanje nije uspesno", "Greska",
					JOptionPane.ERROR_MESSAGE);
			
		}
	}
}

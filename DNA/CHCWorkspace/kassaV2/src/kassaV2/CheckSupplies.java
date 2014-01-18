package kassaV2;

/**
 *@author SirNoolas - David Vonk
 */

import javax.swing.*;
import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CheckSupplies extends JFrame {
	
	// initialise variables
	JComboBox listDrinks, listItems, listEntry;
	JButton exit;
	String infoOnComponent = "";
	JLabel label1, label2;

	public static void main(String[] args) {
		
		new CheckSupplies();

	}
	
	public CheckSupplies(){
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("CHCSystems - Vooraden");
		
		JPanel thePanel = new JPanel();
		
		
		// panel1
		JPanel panel1 = new JPanel();
		
		label1 = new JLabel("Producten:");
		panel1.add(label1);
		
		// Combo box 1
		String[] drinks = {"Koffie", "Thee", "Chocolademelk", "Cola", "Sinas"};
		listDrinks = new JComboBox(drinks);
		
		panel1.add(listDrinks);
		
		thePanel.add(panel1);		
		
		this.add(thePanel);
		this.setVisible(true);
	}

}




















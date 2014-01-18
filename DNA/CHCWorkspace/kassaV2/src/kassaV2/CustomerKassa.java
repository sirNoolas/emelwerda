package kassaV2;

/**
 *@author SirNoolas - David Vonk
 */

import javax.swing.*;

import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.io.*;

public class CustomerKassa extends JFrame{
	
	// initialise variables for filesystems
	static String filePath, parentDirectory;
	static File parentDir, historyFile;

	// initialise variables for the window and calculations
	JButton sendButton, cancelButton;
	JLabel label1, label2, label3, label4, copyrightNotice;
	JSlider howManyChildren, howManyAdults, howManyMJK;
	JCheckBox adultMJKOn2;
	JRadioButton adultMJKOn1, adultSponsor, regAdult; // PAY ATENTION!!! SPONSOR == DONATOR...

	double adults, children, amountOfMJK, totalCalc;
	double ADULTPRICE = 4.00; // Price in euro's
	double CHILDPRICE = 2.00; // Price in euro's
	double MJKPRICE = 3.00; // price in euro's

	public static void main(String[] args) {
		
		PrintWriter historyOutput = initFile("C:/CHCSystems", "C:/CHCSystems/history.txt");
		
		if(supplyFile.exists()){
			new CustomerKassa();
		}else{
			System.err.println("Error while creating files (can not find specified file) : Check source code");
		}
		
	} // end of main

	public CustomerKassa(){
		int maxSliderSize = 10;
		
		this.setSize(375, 280);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("CHCSystems - Klanten Registratie");

		JPanel thePanel = new JPanel();

		
		// initialising the number of customers panels
		// panel1
		JPanel panel1 = new JPanel();

		label1 = new JLabel("Aantal volwassenen: 0");
		panel1.add(label1);

		// initialising slider
		howManyAdults = new JSlider(0, maxSliderSize, 0);
		howManyAdults.setMinorTickSpacing(1);
		howManyAdults.setMajorTickSpacing(5);
		howManyAdults.setPaintTicks(true);
		howManyAdults.setPaintLabels(true);

		ListenForSlider lForSliderAdults = new ListenForSlider();
		howManyAdults.addChangeListener(lForSliderAdults);

		panel1.add(howManyAdults);

		thePanel.add(panel1);

		// radiobuttons for sponsor, MJK, etc.
		JPanel panel4 = new JPanel();
		
		label4 = new JLabel("Specificaties:");
		panel4.add(label4);
		
		adultMJKOn1 = new JRadioButton("MJK");
		adultSponsor = new JRadioButton("sponsor");
		regAdult = new JRadioButton("normaal");

		ButtonGroup adultSpec = new ButtonGroup();

		adultSpec.add(adultMJKOn1);
		adultSpec.add(adultSponsor);
		adultSpec.add(regAdult);

		panel4.add(regAdult);
		panel4.add(adultMJKOn1);
		panel4.add(adultSponsor);

		regAdult.setSelected(true);
		
		thePanel.add(panel4);

		
		// panel2
		JPanel panel2 = new JPanel();

		label2 = new JLabel("Aantal kinderen: 0");
		panel2.add(label2);
		
		// initialising slider
		howManyChildren = new JSlider(0, maxSliderSize, 0);
		howManyChildren.setMinorTickSpacing(1);
		howManyChildren.setMajorTickSpacing(5);
		howManyChildren.setPaintTicks(true);
		howManyChildren.setPaintLabels(true);

		ListenForSlider lForSliderChildren = new ListenForSlider();
		howManyChildren.addChangeListener(lForSliderChildren);

		panel2.add(howManyChildren);

		thePanel.add(panel2);
		
		
		// initialise panel3
		// initialise amount of MJK
		JPanel panel3 = new JPanel();
		
		label3 = new JLabel("aantal MJK's: 0");
		panel3.add(label3);
		
		// initialise checkbox
		adultMJKOn2 = new JCheckBox("MJK");
		panel3.add(adultMJKOn2);
		
		// initialising slider
		howManyMJK = new JSlider(0, maxSliderSize, 0);
		howManyMJK.setMinorTickSpacing(1);
		howManyMJK.setMajorTickSpacing(5);
		howManyMJK.setPaintTicks(true);
		howManyMJK.setPaintLabels(true);
		
		ListenForSlider lForSliderSponsors = new ListenForSlider();
		howManyMJK.addChangeListener(lForSliderSponsors);
		
		panel3.add(howManyMJK);
		
		thePanel.add(panel3);
		
		// Initialise Button
		sendButton = new JButton("Opslaan");
		ListenForButton lForButton1 = new ListenForButton();
		sendButton.addActionListener(lForButton1);
		
		thePanel.add(sendButton);

		// Initialise Button
		cancelButton = new JButton("Anuleren");
		ListenForButton lForButton2 = new ListenForButton();
		cancelButton.addActionListener(lForButton2);

		thePanel.add(cancelButton);
		
		//make everything visible
		this.add(thePanel);
		this.setVisible(true);
	} // end of CustomerKassa

	private class ListenForButton implements ActionListener{

		public void actionPerformed(ActionEvent e){

			if(e.getSource() == sendButton){
				adults = howManyAdults.getValue();
				children = howManyChildren.getValue();

				if(adultMJKOn1.isSelected() && adultMJKOn2.isSelected()){ 
					amountOfMJK = howManyMJK.getValue();
					totalCalc = getPrice(true);
				}else if(adultSponsor.isSelected()){ 
					ADULTPRICE = 0;	CHILDPRICE = 0;
					totalCalc = 0;
				}else{
					totalCalc = getPrice(false);
				}

				
				JOptionPane.showMessageDialog(CustomerKassa.this, "De klant moet �" + totalCalc + " betalen.");
				System.exit(0);
			}else if(e.getSource() == cancelButton){
				System.exit(0);
			}
			
		} // end of actionPerformed

	} // end of ListenForButton
	
	private class ListenForSlider implements ChangeListener{
		
		public void stateChanged(ChangeEvent e){
			
			if(e.getSource() == howManyAdults){
				label1.setText("Aantal volwassenen: " + howManyAdults.getValue());				
			}else if(e.getSource() == howManyChildren){
				label2.setText("Aantal kinderen: " + howManyChildren.getValue());	
			}else if(e.getSource() == howManyMJK){
				label3.setText("Aantal MJK's: " + howManyMJK.getValue());	
			}
			
		} // end of actionPerformed
		
	} // end of ListenForSlider
	
	public double getPrice(boolean hasMJK){
		double num = 0;
		
		if(hasMJK){
			num = (amountOfMJK * MJKPRICE) + ((adults - amountOfMJK) * ADULTPRICE) + (children * CHILDPRICE);
		}else{
			num = adults * ADULTPRICE + children * CHILDPRICE;
		}
		
		return num;
		
	} // end of getPrice

	private static PrintWriter createFile(String dirName, String fileName){

		try{
			
			parentDir = new File(dirName);
			
			if(!parentDir.exists()){ 
				parentDir.mkdir();

				historyFile = new File(fileName);
			}
			
			PrintWriter infoToWrite = new PrintWriter(
					new BufferedWriter(
							new FileWriter(historyFile)));
			
		}catch(IOException e){
			System.out.println("An I/O Error Occured");
			System.exit(0);
		}
		return null;

	}
} // end of CustomerKassa












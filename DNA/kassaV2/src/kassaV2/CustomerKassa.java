package kassaV2;

/**
 *@author SirNoolas - David Vonk
 */

import javax.swing.*;

import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.io.*;

import java.util.Date;


public class CustomerKassa extends JFrame{
	// initialise variables for the window and calculations
	JButton sendButton, cancelButton;
	JLabel label1, label2, label3, label4, copyrightNotice;
	JSlider howManyChildren, howManyAdults, howManyMJK;
	JCheckBox adultMJKOn2;
	JRadioButton adultMJKOn1, adultSponsor, regAdult; // PAY ATENTION!!! SPONSOR == DONATOR...

	static int adults;
	static int children;
	static int amountOfMJK;
	static double totalCalc;
	double ADULTPRICE = 4.00; // Price in euro's
	double CHILDPRICE = 2.00; // Price in euro's
	double MJKPRICE = 3.00; // price in euro's

	public static void main(String[] args) {

			new CustomerKassa();
		
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
				
				if(totalCalc >= 0 && adults > 0 && adults >= amountOfMJK){
					writeStart();
					JOptionPane.showMessageDialog(CustomerKassa.this, "De klant moet �" + totalCalc + " betalen.");
					System.exit(0);
				}else{
					JOptionPane.showMessageDialog(CustomerKassa.this, "ERROR: Foutieve invoer");
				}
				
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
	
	
	
	public void writeStart(){
		Customer customerInfo = getCustomer();	
		
		PrintWriter custOutput = createFile("C:/Users/David/Desktop/CHCSystems", "C:/Users/David/Desktop/CHCSystems/customerHistory.txt");
		
		createCustomer(customerInfo, custOutput);
	
	} // end of writeStart
	
	private static class Customer{
		
		public double entryPrice;
		public int amountOfAdults, amountOfChildren, amountOfMJK;
		
		public Customer(double entryPrice, int amountOfAdults, int amountOfChildren, int amountOfMJK){
			this.entryPrice = entryPrice;
			this.amountOfAdults = amountOfAdults;
			this.amountOfChildren = amountOfChildren;
			this.amountOfMJK = amountOfMJK;	
		
		} // end of Customer
		
	} // end of Customer
	
	private static Customer getCustomer(){
		
		Customer customerInfo = new Customer(totalCalc, adults, children, amountOfMJK);
		
		return customerInfo;
		
	}
	
	private static PrintWriter createFile(String dirName, String fileName){
		
		/*File customerHistoryDir = new File(dirName);
		customerHistoryDir.mkdir();
		
		File customerHistory = new File(fileName);
		*/
		try{

			PrintWriter infoToWrite = new PrintWriter(
					new BufferedWriter(
							new FileWriter(fileName, true)));
			return infoToWrite;
			
		}catch(IOException e){
			System.out.println("An I/O Error Ocurred");
			System.exit(0);
			
		} // end of try/catch
		
		return null;
				
	} // end of PrintWriter
	
	private static void createCustomer(Customer customerInfo, PrintWriter custOutput){
		
		String stringCustomerInfo = Double.toString(customerInfo.entryPrice) + " " + Integer.toString(customerInfo.amountOfAdults) + " " + Integer.toString(customerInfo.amountOfChildren) + " " +  Integer.toString(customerInfo.amountOfMJK);
		
		Date date = new Date();
		
		custOutput.println(date + " - " + stringCustomerInfo);
		custOutput.close();
		
	}

} // end of CustomerKassa












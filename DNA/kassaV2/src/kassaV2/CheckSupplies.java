package kassaV2;

/**
 *@author SirNoolas - David Vonk
 */

import javax.swing.*;

import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.io.*;
import java.util.List;

public class CheckSupplies extends JFrame {

	public static void main(String[] args) {
		
		// read from the file		
		Item[] sellableItems = getItemInfo();
		
		// write to the file
		PrintWriter itemOutput = createFile("C:/Users/David/Desktop/CHCSystems/CATALOGUS.CHCFile");
		for(Item thing : sellableItems){
			createItems(thing, itemOutput);
		}
		itemOutput.close();
		
		//new CheckSupplies();
		// TODO: watch java video tutorial 25

	} // end of main
	
	
	public CheckSupplies(){
		this.setSize(700, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("CHCSystems - Vooraden");
		
		JPanel thePanel = new JPanel();		
		
		this.add(thePanel);
		this.setVisible(true);
	}
	
	
	// File writing/reading code:
	private static class Item{
		
		public String itemName, itemDiscription;
		public double itemPrice;
		public int availableAmount;
		
		public Item(String itemName, String itemDiscription, double itemPrice, int availableAmount){
			
			this.itemName = itemName;
			this.itemDiscription = itemDiscription;
			this.itemPrice = itemPrice;
			this.availableAmount = availableAmount;
					
		} // end of Item
		
	} // end of Item

	private static PrintWriter createFile(String fileName){
		File catalog = new File(fileName);
		try{
			PrintWriter infoToWrite = new PrintWriter(
					new BufferedWriter(
							new FileWriter(catalog, false)));
			
			return infoToWrite;
			
		}catch(IOException e){
			System.out.println("An I/O Error Occured");
			System.exit(0);
		} // end of try/catch
		
		return null;
		
	} // end of PrintWriter 
	
	private static void createItems(Item thing, PrintWriter itemOutput){

		String itemInfo = thing.itemName + " - " + thing.itemDiscription + " - " + Double.toString(thing.itemPrice) + " - " + Integer.toString(thing.availableAmount);
		
		itemOutput.println(itemInfo);
		
	}
	
	private static Item[] getItemInfo(){
		
		File catalog = new File("C:/Users/David/Desktop/CHCSystems/CATALOGUS.CHCFile");
		
		try{
			BufferedReader getInfo = new BufferedReader(
					new FileReader(catalog));
			
			int expectedLines = 10;
			
			Item[] tempSellableItems = new Item[expectedLines];
			Item[] sellableItems = null;
			
			String[] itemInfo = getInfo.readLine().split(" - ");
			String tempItemInfo = "";
			
			for(int i = 0; i < expectedLines && itemInfo != null; i++){

				tempSellableItems[i] = new Item(itemInfo[0], itemInfo[1], Double.parseDouble(itemInfo[2]), Integer.parseInt(itemInfo[3]));
				tempItemInfo = getInfo.readLine();
				if(tempItemInfo != null){
						itemInfo = tempItemInfo.split(" - ");
				}else if(tempSellableItems[i + 1] == null){
					sellableItems = new Item[i+1];
					for(int x = 0; x <= i; x++){
						sellableItems[x] = tempSellableItems[x];
					}
					break;
				}
			}
			
			getInfo.close();
			
			return sellableItems;
			
		}catch(FileNotFoundException e){
			System.out.println("Couldn't Find File");
			System.exit(0);
			
		}catch(IOException e){
			System.out.println("An I/O Error Occured");
			System.exit(0);
		} // end of try/catch
		
		return null;
	}
	
} // end of checkSupplies




















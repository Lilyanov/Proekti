/* 
 * All copyright reserved by Skrill MB Bulgaria
 * @author Yavor Lilyanov
 * @version 1.0
*/

package CVPack;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import au.com.bytecode.opencsv.CSVWriter;


public class Interface {
	
	private static void menu() {
		System.out.println("1.Enter new CV.");
		System.out.println("2.View the list of all CVs");
		System.out.println("3.Export the list of all CVs in a file");
		System.out.println("4.Exit");
	}
	
	private static void consoleView(ArrayList<CV> list) {
		for(int i = 0; i < list.size(); i++) {
			list.get(i).showCV();
			System.out.println();
		}
	}
	

	private static void saveInSCVFile(ArrayList<CV> list) {
		
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter("result.scv") , '\t');
			String [] titles= {
					"First Name", "Last Name", "Date of Birth", "Email", "Phone", "Address", "Nationality", "Gender",
					"School", "School Start Date", "School End Date", "School qualification",
					"University", "University Start Date", "University End Date", "University qualification"
					};

			writer.writeNext(titles);

			for(int i = 0; i < list.size(); i++) {
				list.get(i).exportInFile(writer);
			}
			
		} catch(IOException ex) {
			System.out.println("Cannot write in file");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner scan = new Scanner(System.in);
		boolean flag = true;
		int option = 0;
		ArrayList<CV> csv = new ArrayList<CV>();
		
		do
		{
			menu();
			try
			{
				System.out.print("Choose option: ");
				option = scan.nextInt();
			}
			catch(InputMismatchException ex)
			{
				System.out.println("Insert a number[1-4");
			}
			switch(option)
			{
			case 1:
				{
					CV newCv = new CV();
					newCv.setCV();
					csv.add(newCv);
					break;
				}
			case 2:
				{
					consoleView(csv);
					break;
				}
			case 3:
				{
					saveInSCVFile(csv);
					break;
				}
			case 4: 
				{
					flag = false;
					System.out.println("Progam ends!");
					break;
				}
			}
			
		}while(flag);
	}

}
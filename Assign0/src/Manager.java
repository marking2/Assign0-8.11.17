import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.time.*;
//changes
public class Manager {

	public static String fileName;
	public static File folderName; // added by mark 7-11 18:15 
	public Wifi newWifi = new Wifi();
	public String formatLine = "TIME,ID,LAT,LON,ALT,Number Of Networks";
	public String formatLineWifi = "SSID,MAC,Frequency,Signal";
	public Wifi[] wifiArr = new Wifi[12];
	public String ID = "";
	public ArrayList<String>  ArrCSVPaths  = new ArrayList<>();
	public LinkedList<Wifi> WAL = new LinkedList<>();

	/*/
	 * 	public Manager(String FN){
		this.fileName = FN;
		readByFolder2();
	}
	 */
	public Manager(String FolderPath){
		//		File folder = new File("/Users/gal/Desktop/TestMark")
		File folder = new File(FolderPath);
		readByFolder2(folder);
	}


	public Manager(String SearchName, File Folder){ // added by mark 7-11 18:15 for folder 
		this.fileName = SearchName;
		this.folderName = Folder;
	}

	public static void main(String [] args){
		/**
		 * Mark's test code to read Full folder and find some specific file.txt (Wifi Scan)
		 */
		//		File f = new File("//Users/gal/Desktop/TestMark");
		//		Manager folderReader =new Manager(".csv",f);
		//		folderReader.readByFolder2();
		Manager m = new Manager ("/Users/gal/Desktop/TestMark");
		
		/*
		 * first step - txt to csv
		 */
		//Manager m = new Manager("Assign0//WigleWifi_20171027162929.csv");
		//m.readFile(fileName);
		//		m.filter();
		//	m.writeFile("/Users/marki/Desktop/WigleWifi_20171027162929_2.csv");
		/*
		 * second step - csv to kml(filter)
		 */
		//		m = new Manager("/Users/marki/Desktop/gmon_wlan_export_2017_10_26.csv");
		//		m.filter();
		//		m.writeFile("/Users/marki/Desktop/gmon_wlan_export_2017_10_26.kml");
		//				File t = new File("/Users/gal/Desktop/TestMark/1.txt");
		//				t.writeFile("/Users/gal/Desktop/TestMark/1.csv");
	}

	/*
	 * non-static function to call a filter by condition
	 */
	private void filter() {
		FilterByID FID = new FilterByID();
		FID.filter(wifiArr);
	}

	/*
	 * // added by mark 7-11 18:15 
	 * Reading the folder
	 */

	public static void listFilesForFolder(final File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				if(fileEntry.getName().contains(".csv")){
					System.out.println(fileEntry.getName());
					String fName = fileEntry.getAbsolutePath();
					//call function readFile(fName);
				}
			}
		}
	}
	public void readByFolder(){
		File[] list = folderName.listFiles();
		//		File[] list = null ;
		FileReader fr;
		BufferedReader br;
		String[] compareIt = {"BSSID","LAT","LON","SSID"};

		try{
			for (int i = 0; i < list.length; i++) {

				if(list[i].getName().contains(fileName) && list[i].canRead()){
					boolean Besure = false; // we are not sure yet, that this is the our Wifi info' file.
					fr = new FileReader(list[i].getPath());
					br = new BufferedReader(fr);
					String str = br.readLine();
					String[] tmp = str.split(",");
					if(tmp[0]==compareIt[0] && tmp[1]==compareIt[1]&& tmp[2]==compareIt[2]&& tmp[3]==compareIt[3]) Besure = true;
					if(Besure == true){
						//						readFile(list[i].getPath()); //STOPED HERE. need to continue the Writing Step.

					}
				}
			}

		}catch(IOException e){
			System.out.println(e);
		}
		finally{
			System.out.println(Arrays.toString(list) + "\n end of the Reading the following folders were readed.");
		}
	}

	public void readByFolder2(File FolderName){
		File[] list = FolderName.listFiles();
		System.out.println(Arrays.toString(list));
		for (final File fileEntry : FolderName.listFiles()) {
			if (fileEntry.isDirectory()) {
				readByFolder2(fileEntry);
			} 
			else if(fileEntry.getName().contains(".csv")){
				ArrCSVPaths.add(fileEntry.getAbsolutePath());
//				System.out.println(fileEntry.getAbsolutePath());
				CheckCSVFiles();
			}
		}
	}
	
	public void CheckCSVFiles(){
	
		FileReader fr;
		BufferedReader br;
		String compareIt = "MAC,SSID,AuthMode,FirstSeen,Channel,RSSI,CurrentLatitude,CurrentLongitude,AltitudeMeters,AccuracyMeters,Type";
	
		try{
			for (int i = 0; i < ArrCSVPaths.size(); i++) {
					boolean Besure = true; // we are not sure yet, that this is the our Wifi info' file.
					fr = new FileReader(ArrCSVPaths.get(i));
					br = new BufferedReader(fr);//fix when the file is empty!!!
					String str = br.readLine();
					str = br.readLine();
					String[] tmp = str.split(",");
					if(!(str.equals(compareIt))) {
						Besure = false;
					}
					if(Besure == false){
						ArrCSVPaths.remove(i);//STOPED HERE. need to continue the Writing Step.
				}
			}

		}catch(IOException e){
			System.out.println(e);
		}
//		finally{
//			System.out.println((ArrCSVPaths) + "\n end of the Reading the following folders were readed.");
//		}
		System.out.println(ArrCSVPaths.size());
	}
	/*
	 * the function reads a file line by line, and constructs an LinkeList of Objects of type Wifi
	 * the methods that used: 
	 * 	1. Read the line in to String 
	 * 	2. repreduce that line as array of words (seperated by the ',' ) 
	 * 	3. Create new Wifi object (constracture that get an array of strings )
	 * 	4. add the new Wifi to the LinkedList<Wifi>
	 */

	public void readFile(String fileName)
	{
		try{
			FileReader fr=new FileReader(fileName);
			BufferedReader br=new BufferedReader(fr);
			String str = br.readLine(); // first line is #Format
			String [] s1 = str.split(",");
			this.ID = s1[2].substring(6); // The name of the Scanning Device. [Lenovo , Asus , Android etc..]
			str = br.readLine(); // first line is #Format
			s1 = str.split(","); // first place in the array is the format line - not interesting, throw
			while((str = br.readLine())!= null){
				if(str != null){
					String[] s2 = str.split(",");
					newWifi = new Wifi(s2);
					WAL.add(newWifi);
				}
			}	
		}
		catch(IOException ex){
			System.out.println("error reading file \n");
			System.exit(2);
		}
	}

	public void writeFile(String fileName)
	{
		String[] s1 = formatLine.split(",");
		int counter = 0; // counter of networks in each row
		try{
			FileWriter fw = new FileWriter(fileName);
			PrintWriter outs = new PrintWriter(fw);
			for (int i = 0; i < s1.length; i++) { // HEAD format line print
				outs.print(s1[i] + " ,");
			}
			s1 = formatLineWifi.split(",");
			for (int i = 0; i < 10; i++) { // Wifis format
				outs.print(s1[0] + (i+1) + " ," + s1[1] + (i+1) + " ," + s1[2] + (i+1) + " ," + s1[3] + (i+1) + " ,");
			}
			outs.println();
			outs.print(WAL.get(0).getTime() + " ,");
			outs.print(this.ID + " ,");
			outs.print(WAL.get(0).getLAT() + " ,");
			outs.print(WAL.get(0).getLON() + " ,");
			outs.print(WAL.get(0).getALT() + " ,");
			outs.print(" ,");
			for (int i = 0, j = 0; i < WAL.size() - 1; i = j) {
				if(WAL.get(i).getTime().compareTo(WAL.get(j).getTime()) == 0){
					counter = 1;
					while(WAL.get(i).getTime().compareTo(WAL.get(j).getTime()) == 0){
						outs.print(WAL.get(j).getSSID() + " ," + WAL.get(j).getMAC() + " ," + WAL.get(j).getChannel() + " ," + WAL.get(j).getRSSI() + " ,");
						counter++;
						j++;
					}
				}
				outs.println();
				if(WAL.get(i).getTime().compareTo(WAL.get(j).getTime()) != 0){
					outs.print(WAL.get(j).getTime() + " ,");
					outs.print(this.ID + " ,");
					outs.print(WAL.get(j).getLAT() + " ,");
					outs.print(WAL.get(j).getLON() + " ,");
					outs.print(WAL.get(j).getALT() + " ,");
					outs.print(" ,");
					outs.print(WAL.get(j).getSSID() + " ," + WAL.get(j).getMAC() + " ," + WAL.get(j).getChannel() + " ," + WAL.get(j).getRSSI() + " ,");
					counter = 1;
					j++;
				}
			}
			outs.println();
//			if(num == ArrCSVPaths.size()){
			outs.close();
			fw.close();
//			}
		}
		catch(IOException ex){
			System.out.println("error writing file\n" + ex);
		}
	}
}

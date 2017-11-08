import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.time.*;

public class Manager {
//change
	//other change of mark. mmm mm  ff
	public static String fileName;
	public Wifi newWifi = new Wifi();
	public String formatLine = "TIME,ID,LAT,LON,ALT,Number Of Networks";
	public String formatLineWifi = "SSID,MAC,Frequency,Signal";
	public Wifi[] wifiArr = new Wifi[12];
	public String ID = "";
	public LinkedList<Wifi> WAL = new LinkedList<>();

	public Manager(String FN){
		this.fileName = FN;
	}

	public static void main(String [] args){
		/*
		 * first step - txt to csv
		 */
		Manager m = new Manager("Assign0//WigleWifi_20171027162929.csv");
		m.readFile(fileName);
		//		m.filter();
		m.writeFile("/Users/marki/Desktop/WigleWifi_20171027162929_2.csv");
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
	 * the function reads a file line by line, and constructs an array of Objects of type Wifi
	 */
	public void readFile(String fileName)
	{
		try{
			FileReader fr=new FileReader(fileName);
			BufferedReader br=new BufferedReader(fr);
			String str = br.readLine(); // first line is #Format
			String [] s1 = str.split(",");
			this.ID = s1[2].substring(6); 
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
			outs.close();
			fw.close();
		}
		catch(IOException ex){
			System.out.println("error writing file\n" + ex);
		}
	}
}


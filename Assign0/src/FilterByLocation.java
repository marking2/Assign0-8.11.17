

public class FilterByLocation implements Filter {
	
	/**
	 * Using the primative sort To sort the location by the parameters of Longitude and Latitude , 
	 * if the sum of that two in Wifi[i] is bigger than in Wifi[j] (Wifi[i] is comming before Wifi[j] in the array)
	 * then we swap the places of the two.
	 * 	-complexity O(n^2)
	 */
	public void filter(Wifi[] wifiArr){
		Wifi temp = new Wifi();
		
		for (int i = 1; i < wifiArr.length - 1; i++) {
			for (int j = 1; j < wifiArr.length - 1; j++) {
				double x = Double.parseDouble(wifiArr[i].getLAT());
				double y = Double.parseDouble(wifiArr[i].getLON());
				double x2 = Double.parseDouble(wifiArr[j].getLAT());
				double y2 = Double.parseDouble(wifiArr[j].getLON());
				if(x+y >= x2+y2){
					temp = wifiArr[i];
					wifiArr[i] = wifiArr[j];
					wifiArr[j] = temp;
				}
			}
		}
	}
}

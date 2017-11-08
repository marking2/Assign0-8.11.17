
public class FilterByID  implements Filter {
	
	//implemtaion
	public void filter(Wifi[] wifiArr){ 
		Wifi temp = new Wifi();
		int x;
		for (int i = 1; i < wifiArr.length - 1; i++) {
			for (int j = 1; j < wifiArr.length - 1; j++) {
				x = wifiArr[i].getSSID().compareToIgnoreCase(wifiArr[j].getSSID());
				if(x <= 0){
					temp = wifiArr[i];
					wifiArr[i] = wifiArr[j];
					wifiArr[j] = temp;
				}
			}
		}
	}	
}


public class FilterByTime implements Filter  {
	
	/**
	 * Sorting by the Insertion Sort. O(n^2)
	 * @MarkGEule
	 */
	
	public void filter(Wifi[] wifiArr){
		Wifi temp = new Wifi();
		for (int i = 2; i < wifiArr.length-1; i++) {
			int j =i;
			while(j>1 && Integer.parseInt(wifiArr[j-1].getTime())  >  Integer.parseInt(wifiArr[j].getTime())){
				temp = wifiArr[j-1];
				wifiArr[j-1] = wifiArr[j];
				wifiArr[j] = temp;
				j--;
			}
		}
	}
	

}

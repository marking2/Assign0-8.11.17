

public class Wifi {

	String MAC, LAT, LON, SSID, ALT, Crypt;
	String Type; // Wifi or GSM
	String Channel, RSSI, Time;

	/*
	 * Constructors
	 */
	public Wifi(String MAC,String SSID, String Crypt, String Time, String Channel, String RSSI, String LAT,String LON,String ALT, String Type){
		this.MAC = MAC;
		this.SSID = SSID;
		this.Crypt = Crypt;
		this.Time = Time;
		this.Channel = Channel;
		this.RSSI = RSSI;
		this.LAT = LAT;
		this.LON = LON;
		this.ALT = ALT;
		this.Type = Type;
	}

	public Wifi(String[] arr){
		this.MAC = arr[0];
		this.SSID = arr[1];
		this.Crypt = arr[2];
		this.Time = arr[3];
		this.Channel = arr[4];
		this.RSSI = arr[5];
		this.LAT = arr[6];
		this.LON = arr[7];
		this.ALT = arr[8];
		this.Type = arr[9];
	}

	public Wifi(){
		this.MAC = "";
		this.SSID = "";
		this.Crypt = "";
		this.Time = "";
		this.Channel = "";
		this.RSSI = "";
		this.LAT = "";
		this.LON = "";
		this.ALT = "";
		this.Type = "";
	}

	public String[] getValues (){
		String[] values = new String[10];
		values[0] = this.MAC;
		values[1] = this.SSID;
		values[2] = this.Crypt;
		values[3] = this.Time;
		values[4] = this.Channel;
		values[5] = this.RSSI;
		values[6] = this.LAT;
		values[7] = this.LON;
		values[8] = this.ALT;
		values[9] = this.Type;
		return values;	
	}

	public Wifi get(){
		return this;
	}

	public String toString() {
		return "Wifi [MAC=" + MAC + ", SSID=" + SSID + ", Crypt=" + Crypt + ", Time=" + Time  
				+ ", Channel=" + Channel +  ", RSSI=" + RSSI + ", LAT=" + LAT + ", LON=" + LON +  
				", ALT=" + ALT + "]";
	}
	public String getMAC() {
		return MAC;
	}

	public void setMAC(String MAC) {
		this.MAC = MAC;
	}

	public String getLAT() {
		return LAT;
	}

	public void setLAT(String lAT) {
		this.LAT = lAT;
	}

	public String getLON() {
		return LON;
	}

	public void setLON(String lON) {
		this.LON = lON;
	}

	public String getALT() {
		return ALT;
	}

	public void setALT(String alt) {
		this.ALT = alt;
	}
	
	public String getSSID() {
		return SSID;
	}

	public void setSSID(String sSID) {
		this.SSID = sSID;
	}

	public String getCrypt() {
		return Crypt;
	}

	public void setCrypt(String crypt) {
		this.Crypt = crypt;
	}

	public String getType() {
		return Type;
	}

	public void setConnection_Mode(String type) {
		this.Type = type;
	}

	public String getChannel() {
		return Channel;
	}

	public void setChannel(String channel) {
		this.Channel = channel;
	}

	public String getRSSI() {
		return RSSI;
	}

	public void setRXL(String rssi) {
		this.RSSI = rssi;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		this.Time = time;
	}
//	
//	public String[] rearrangeForCsv(){
//		String[] arr = new String[10];
//		
//		arr[6] = this.MAC;
//		arr[5] = this.SSID;
//		arr[2] = this.Crypt;
//		arr[0] = this.Time;
//		arr[7] = this.Channel;
//		arr[8] = this.RSSI;
//		arr[2] = this.LAT;
//		arr[3] = this.LON;
//		arr[4] = this.ALT;
//		arr[5] = this.Type;
//		return arr;
//	}


}

// 170101003 -- Ahmet Kaan Cig

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

public class Client{
	
	public Client(String Saddress,int portNum, String offs) {
		Config confObj = new Config();
		confObj.setOffset(Integer.parseInt(offs));
		
		for (int i = 0; i < 30; i++) {
			try {
				Socket sockt1 = new Socket(InetAddress.getByName(Saddress), portNum);
				confObj.setT1(System.currentTimeMillis());				
				ObjectOutputStream oOs = new ObjectOutputStream(sockt1.getOutputStream());
				oOs.writeObject(confObj);
				ObjectInputStream oIs = new ObjectInputStream(sockt1.getInputStream());
				confObj= (Config) oIs.readObject();
				oOs.close();
				oIs.close();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			confObj.setT4((long)(System.currentTimeMillis()));
			confObj.getResultsOandD();			
			System.out.println("O ==> " + confObj.getO() + " ms,\tD ==> " + confObj.getD() + " ms");
		}
		
	}
	public static void main(String[] args) {
		String[] arr= {null,null};
		String address = "";
		String offset = arr[0];
		int port = 1000;
		int i =0;
    	for (String s: args) {
    		arr[i]=s;
    		i+=1;
    	}
    	if(arr[0]!= null && arr[1]!=null) {
    		try {
    			String[] arr2 = arr[1].split(":");
            	address = arr2[0];
            	port = Integer.parseInt(arr2[1]) ;
            	offset = arr[0];
    		}
    		catch (Exception e) {
    			return;
			}
    		
    	}
		new Client(address,port,offset);
	}
}
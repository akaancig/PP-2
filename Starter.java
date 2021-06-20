// 170101003 -- Ahmet Kaan Cig

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Starter{
    public static Config nObject;
	public static void main(String[] args) throws Exception {
		// Starter hem server hem clienti ayni anda baslatir(ayni port uzerinden)
		//Ornek girdi = > java Starter 110 localhost:4444 (110 offset, 4444 port olmak uzere)
		System.out.println("30 kez gonderme-alma islemi basladi");
		String[] arr= {null,null};
		int i =0;
    	for (String s: args) {
    		arr[i]=s;
    		i+=1;
    	}
    	if(arr[0]!= null && arr[1]!=null) {
    		try {
    			String[] arr2 = arr[1].split(":");
            	String address = arr2[0];
            	int port = Integer.parseInt(arr2[1]) ;
            	String offset = arr[0];
            	runProcess("javac *.java");
            	runProcess("java Server "+String.valueOf(port));
            	runProcess("java Client "+offset+" "+address+":"+port);
    		}
    		catch (Exception e) {
    			System.out.println("Example Input = > java Starter 110 localhost:4444 ");
			}
    	}
	}
	private static void printLines(String name, InputStream ins) throws Exception {
	    String line = null;
	    BufferedReader in = new BufferedReader(
	        new InputStreamReader(ins));
	    while ((line = in.readLine()) != null) {
	        System.out.println(name + " " + line);
	    }
	  }
	private static void runProcess(String command) throws Exception {
	    Process pro = Runtime.getRuntime().exec(command);
	    printLines(command + " stdout:", pro.getInputStream());
	    printLines(command + " stderr:", pro.getErrorStream());
	    pro.waitFor();
	    System.out.println(command + " exitValue() " + pro.exitValue());
	  }
}
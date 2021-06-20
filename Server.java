// 170101003 -- Ahmet Kaan Cig

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
	public Config confObj;
	public ServerSocket sockt1;
	
	public Server (int port) {
		try {
			sockt1 = new ServerSocket(port);
			System.out.println("Starting server port: "+port);
			
			while (true) {
				try {
					Socket newSocket = sockt1.accept();
					WorkerThread newThread = new WorkerThread(newSocket);
					new Thread(newThread).start();
				} catch (Exception e) {
				}
			}
		} catch (IOException e) {
			try {
				sockt1.close();
			} catch (IOException e1) {
			}
		}
	}
	public static void main(String[] args) {
		String[] arr= {null,null};
		int i =0;
    	for (String s: args) {
    		arr[i]=s;
    		i+=1;
    	}
		new Server(Integer.parseInt(arr[0]));
	}
	private class WorkerThread implements Runnable { 
		private Socket soc1;

		public WorkerThread(Socket newSocket) {
			soc1 = newSocket;
		}

		@Override
		public void run() {
			InputStream is;
			try {
				is = soc1.getInputStream();
				ObjectInputStream oIs = new ObjectInputStream(is);
				confObj = (Config) oIs.readObject();
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
			
			confObj.setDelay(confObj.getRandomDelay());
			confObj.sleepThread(confObj.getDelay());
			
			confObj.setT2(System.currentTimeMillis() + confObj.getOffset());
			
			confObj.setT3(System.currentTimeMillis() + confObj.getOffset());
			
			try {
				Thread.sleep(confObj.getDelay());
			}catch (Exception e) {
			}
			
			try {
				ObjectOutputStream oOs = new ObjectOutputStream(soc1.getOutputStream());
				oOs.flush();
				oOs.writeObject(confObj);
				oOs.close();
			} catch (IOException e) {
			}
			try {
				soc1.close();
			} catch (Exception e) {
			}
		}
	}
	
}
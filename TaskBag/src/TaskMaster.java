import java.net.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.io.*;

public class TaskMaster {	
	public static void main(String args[]) {
		
		PriorityQueue<Task> taskBag = new PriorityQueue<>();
		for(int i=0; i<10; i++) {
			taskBag.add(new Task());
		}
		
		try {
			ServerSocket servSock = new ServerSocket(10000);
			while(true) {
				Socket sock = servSock.accept();
				new TaskMasterThread(sock, taskBag).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

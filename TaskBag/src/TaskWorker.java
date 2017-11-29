import java.net.*;
import java.io.*;

public class TaskWorker {

	Socket sock;
	DataInputStream dis;
	DataOutputStream dos;
	ObjectInputStream ois;
	int insidePoints, outsidePoints;
	
	public TaskWorker() throws UnknownHostException, IOException {
		sock = new Socket("localhost", 10000);
		dis = new DataInputStream(sock.getInputStream());
		dos = new DataOutputStream(sock.getOutputStream());
		ois = new ObjectInputStream(dis);
	}
	
	public void run() {
		try {
			dos.writeBoolean(true);
			Task task = (Task)ois.readObject();
			task.isInsideCircle();
			dos.writeBoolean(task.isInside);
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

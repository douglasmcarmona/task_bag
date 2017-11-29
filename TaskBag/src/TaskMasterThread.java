import java.net.Socket;
import java.util.PriorityQueue;
import java.io.*;

public class TaskMasterThread extends Thread {

	DataInputStream dis;
	DataOutputStream dos;
	ObjectOutputStream oos;
	PriorityQueue<Task> taskBag;
	
	public TaskMasterThread(Socket sock, PriorityQueue<Task> taskBag) throws IOException {
		dis = new DataInputStream(sock.getInputStream());
		dos = new DataOutputStream(sock.getOutputStream());
		oos = new ObjectOutputStream(dos);
		this.taskBag = taskBag;
	}
	
	@Override
	public void run() {
		try {
			if(dis.readBoolean()) {
				oos.writeObject(taskBag.poll());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

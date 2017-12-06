import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

public class TaskMasterThread extends Thread {

	DataInputStream dis;
	DataOutputStream dos;
	ObjectOutputStream oos;
	ArrayList<Task> taskBag;
	ArrayList<Boolean> answers;
	
	public TaskMasterThread(Socket sock, ArrayList<Task> taskBag, ArrayList<Boolean> answers) throws IOException {
		// sock = socket q o processo principal criou
		dis = new DataInputStream(sock.getInputStream()); // le dados do cliente
		dos = new DataOutputStream(sock.getOutputStream()); // escreve dados pro cliente
		oos = new ObjectOutputStream(dos); // escreve objetos pro cliente
		this.taskBag = taskBag;
		this.answers = answers;
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Conexao aceita");
			while(!taskBag.isEmpty()) {
				dis.readBoolean(); // le booleano do cliente
				synchronized(this) {
					oos.writeObject(taskBag.remove(0)); // escreve tarefa pro cliente
				}
				answers.add(dis.readBoolean()); // coloca resposta do cliente na lista
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
import java.net.*;
import java.io.*;

public class TaskWorker {

	Socket sock;
	DataInputStream dis; // recebe dados do servidor
	DataOutputStream dos; // escreve dados pro servidor
	ObjectInputStream ois; // recebe objeto do servidor
	int insidePoints, outsidePoints;
	Task task;
	
	public TaskWorker() throws UnknownHostException, IOException, InterruptedException {
		System.out.println("Tentando conectar...");
		sock = new Socket("localhost", 10000);
		dis = new DataInputStream(sock.getInputStream());
		dos = new DataOutputStream(sock.getOutputStream());
		ois = new ObjectInputStream(dis);
		System.out.println("Sucesso");
		Thread.sleep(3000);
	}
	
	public void takeTask() {
		try {
			System.out.println("Obtendo tarefa...");
			dos.writeBoolean(true); // escreve booleano pro servidor
			task = (Task)ois.readObject(); // recebe tarefa do servidor
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() throws InterruptedException {
		takeTask();
		System.out.println("Executando...");
		Thread.sleep(3000);
		task.isInsideCircle(); // executa tarefa descrita
		try {
			dos.writeBoolean(task.isInside); // escreve booleano pro servidor
			System.out.println("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		TaskWorker tw;
		try {
			tw = new TaskWorker();
			Thread.sleep(3000);
			System.out.println("Sorteando pontos...");
			for(int i=0; i<30; i++) {
				tw.run();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
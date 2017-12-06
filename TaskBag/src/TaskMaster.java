import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class TaskMaster {

	static ArrayList<Task> taskBag = new ArrayList<>();
	static ArrayList<Boolean> answers = new ArrayList<>();
	static ArrayList<Thread> threads = new ArrayList<>();

	public static void run() throws InterruptedException, IOException {
		System.out.println("Criando tarefas...");
		for (int i = 0; i < 30; i++) {
			taskBag.add(new Task(i));
		}
		Thread.sleep(3000);
		System.out.println("Abrindo socket...");
		ServerSocket servSock = new ServerSocket(10000); // aceita conexoes
		// new TaskGenerator(taskBag).start();
		Thread.sleep(3000);
		System.out.println("Esperando conexoes...");
		for (int i = 0; i < 1; i++) {
			Socket sock = servSock.accept(); // comunicacao com o cliente
			Thread t = new TaskMasterThread(sock, taskBag, answers); // manda o socket pra thread
			threads.add(t);
			t.start();
		}
		for (Thread t : threads) {
			t.join();
		}
		double insidePoints = 0;
		for(boolean answer : answers) {
			insidePoints += answer ? 1 : 0;
		}
		Thread.sleep(3000);
		System.out.println("Pontos inscritos: " + insidePoints
				+ "\nTotal de pontos: " + answers.size());
		servSock.close();
		Thread.sleep(3000);
		System.out.println("calculando pi...");
		double pi = (double)(insidePoints/answers.size())*4;
		System.out.println("Pi = " + pi);
	}

	public static void main(String args[]) {
		try {
			run();
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
import java.util.ArrayList;

public class TaskGenerator extends Thread {

	static ArrayList<Task> taskBag;
	int taskCounter;
	
	public TaskGenerator(ArrayList<Task> tb) {
		taskBag = tb;
		taskCounter = 10;
	}
	
	public synchronized void generateTask() {
		try {
			Thread.sleep(10000);
			taskBag.add(new Task(taskCounter++));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
			generateTask();
		}
	}
}

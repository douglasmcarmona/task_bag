import java.io.Serializable;

// tarefa: sortear uma coordenada cartesiana e verificar se ela esta inscrita em uma circunferencia
// de raio 1 com centro na origem
// OBS: pi = (pontos dentro/total de pontos) * 4;

public class Task implements Serializable {
	private static final long serialVersionUID = 7294615574408072543L;
	double x, y;	
	boolean isInside;
	int id;
	
	public Task(int id) {
		isInside = false;
		this.id = id;
	}
	
	public void isInsideCircle() {
		x = Math.random();
		y = Math.random();
		double distance = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
		System.out.println("Coordenada sorteada: " + x + ", " + y
				+ "\nDistancia ate a origem: " + distance);
		
		if(distance <= 1) isInside = true;
		System.out.println("ponto sorteado " + (isInside ? "dentro" : "fora"));
	}
}
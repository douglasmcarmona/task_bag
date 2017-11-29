import java.io.Serializable;

// tarefa: sortear uma coordenada cartesiana e verificar se ela esta inscrita em uma circunferencia
// de raio 1 com centro na origem
// OBS: pi = (pontos dentro/total de pontos) * 4;

public class Task implements Serializable {
	private static final long serialVersionUID = 7294615574408072543L;
	double x1, x2, y1, y2;	
	boolean isInside;
	
	public Task() {
		isInside = false;
	}
	
	public void isInsideCircle() {
		x1 = Math.random();
		y1 = Math.random();
		x2 = Math.random();
		y2 = Math.random();
		double distance = Math.sqrt((Math.pow(((x2-x1)), 2)+Math.pow((y2-y1), 2)));
		if(distance <= 1) isInside = true;
	}
}
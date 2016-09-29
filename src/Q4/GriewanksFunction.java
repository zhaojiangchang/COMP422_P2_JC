package Q4;

import java.util.List;
import java.lang.Math;

public class GriewanksFunction  extends Problem {
	  public GriewanksFunction() {
	        setMinimization(true);
	        setMinDomain(-30);
	        setMaxDomain(30);
	        setMaxVelocity(10);
	    }

	    public double fitness(List<Double> position) {
	        double fitness = 0;
	        for (int i = 0; i < position.size(); ++i) {
	        	if (position.get(i) <= 30 && position.get(i) >= -30) {
	        		fitness += (Math.pow(position.get(i), 2)/4000);
	        	}
	        }
	        for (int i = 0; i < position.size(); i++) {
	        	fitness += Math.cos(position.get(i)/Math.sqrt(i));
			}
	        fitness++;
	        return fitness;
	    }
}

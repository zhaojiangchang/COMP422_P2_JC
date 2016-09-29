package Q4;

import java.util.List;
import java.lang.Math;

public class RosenbrockFunction  extends Problem {
	  public RosenbrockFunction() {
	        setMinimization(true);
	        setMinDomain(-30);
	        setMaxDomain(30);
	        setMaxVelocity(10);
	    }

	    public double fitness(List<Double> position) {
	        double fitness = 0;
	        for (int i = 0; i < position.size()-1; ++i) {
	        	if (position.get(i) <= 30 && position.get(i) >= -30) {
	        		fitness += 100 * Math.pow((Math.pow(position.get(i), 2)-(position.get(i+1))), 2) + Math.pow((position.get(i)-1), 2);
	        	}
	        }
	        return fitness;
	    }
}

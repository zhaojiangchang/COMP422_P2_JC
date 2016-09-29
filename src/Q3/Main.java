package Q3;

import Q1.SymbolicRegression;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;


public class Main {
	public static void main(String[] args) {
		try {
		
			// Solve symbolic regression problem using Genetic Programming
			GPConfiguration regressionConfig = new GPConfiguration();
			SymbolicRegression regressionProblem = new SymbolicRegression(regressionConfig);

			String regressionPath = "/Users/JackyChang/Documents/workspace/COMP422_P2_JC/res/Q3/symbolic_regression.conf";
			String[] mainArgs = {regressionPath};

			try {
				regressionProblem.main(mainArgs);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

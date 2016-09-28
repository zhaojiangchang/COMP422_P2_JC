package Q1;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;



public class Main {

    @SuppressWarnings("static-access")
	public Main() {
        try {
            GPConfiguration xorConfig = new GPConfiguration();
            SymbolicRegression xorProblem = new SymbolicRegression(xorConfig);
			String xorPath = "/Users/JackyChang/Documents/workspace/COMP422_P2_JC/src/res/xor.conf";
            String[] mainArgs = {xorPath};
            try {
                xorProblem.main(mainArgs);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (InvalidConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	public static void main( String[] args ) {
		new Main();
	}
}

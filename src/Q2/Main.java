package Q2;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
	private static final String[] tasks = { 
			"digits00-train.csv", "digits00-test.csv",
			"digits05-train.csv", "digits05-test.csv",
			"digits10-train.csv", "digits10-test.csv",
			"digits15-train.csv", "digits15-test.csv",
			"digits20-train.csv", "digits20-test.csv",
			"digits30-train.csv", "digits30-test.csv",
			"digits40-train.csv", "digits40-test.csv",
			"digits50-train.csv", "digits50-test.csv",
			"digits60-train.csv", "digits60-test.csv" 
	};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			knn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void knn() throws IOException {
		ArrayList<Double>results = new ArrayList<Double>();
		int t = 1;
		for(int i = 0; i<tasks.length; i+=2){
//		for(int i = 0; i<2; i+=2){

			try {
				KNN_Algorithm knn = new KNN_Algorithm(tasks[i], tasks[i+1]);
				knn.setOptions(i);
				double tenRuns = 0.0;
				for(int run = 0; run<10; run++){
					tenRuns +=knn.testClassifier();
				}
				double result =tenRuns/10.0; 
				results.add(result);
				System.out.println("Task"+ t+": "+ result);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				t++;
			
		}
	    System.out.println( "KNN Done" );
	}

	private static void recordResults( ArrayList<Double> results, String classifier, String params ) throws IOException
	{
		DateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
		Date date = new Date();
		System.out.println( dateFormat.format( date ) ); //2014/08/06 15:59:48
		FileWriter write = new FileWriter( "output.txt", true );
		write.append( "\n" + classifier + "\n" );
		write.append( "\nRun Date / Time\n" + dateFormat.format( date ) );
		write.append( "\nPercentage \t Accuracy\n" );
		write.append( "0% Noise \t\t" + results.get( 0 ).toString() + "\n" );
		write.append( "5% Noise \t\t" + results.get( 1 ).toString() + "\n" );
		write.append( "10% Noise \t\t" + results.get( 2 ).toString() + "\n" );
		write.append( "15% Noise \t\t" + results.get( 3 ).toString() + "\n" );
		write.append( "20% Noise \t\t" + results.get( 1 ).toString() + "\n" );
		write.append( "30% Noise \t\t" + results.get( 2 ).toString() + "\n" );
		write.append( "40% Noise \t\t" + results.get( 3 ).toString() + "\n" );
		write.append( "50% Noise \t\t" + results.get( 1 ).toString() + "\n" );
		write.append( "60% Noise \t\t" + results.get( 2 ).toString() + "\n" );
		write.append("Parameters :  \n");
		write.append( params );
		write.close();
	}
}

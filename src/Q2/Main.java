package Q2;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;


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
//		knn();
		mlp();
	}

	private static void mlp() {
		//		ArrayList<Double>results = new ArrayList<Double>();
		//		int t = 1;
		Map<String, Double>results = new HashMap<String,Double>();
		for(int i = 0; i<tasks.length; i+=2){
			//		for(int i = 0; i<2; i+=2){

			try {
				double result = 0.0;
				for(int j = 0; j<10; j++){
					Algorithm mlp = new Algorithm(tasks[i], tasks[i+1], "mlp");
					Instances train = mlp.getTrain();
					Instances test = mlp.getTest();
					MultilayerPerceptron perception = new MultilayerPerceptron();
					//Setting Parameters
					perception.setLearningRate(1.2);
					perception.setMomentum(0.2);
					perception.setTrainingTime(500);
					perception.setHiddenLayers("4");
					perception.buildClassifier(train);
					
					Evaluation eval = new Evaluation(test);
					eval.evaluateModel(perception, test);
					//				eval.crossValidateModel(perception, test, 9, new Random(1));


					//				System.out.println(eval.errorRate()); //Printing Training Mean root squared Error
					//				System.out.println(eval.toSummaryString()); //Summary of Training
					//				mlp.testClassifier();
					result+=eval.correlationCoefficient();
//					System.out.println(eval.toSummaryString("\nResults\n======\n", false));
				}
				results.put(tasks[i].substring(0, 8), result/10);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//				t++;

		}
		for(Map.Entry<String, Double> result: results.entrySet()){
			System.out.println(result.getKey()+"   "+result.getValue());
		}
		System.out.println( "Neural Network Classifier Done" );

	}

	private static void knn(){
		ArrayList<Double>results = new ArrayList<Double>();
		int t = 1;
		for(int i = 0; i<tasks.length; i+=2){
			//		for(int i = 0; i<2; i+=2){

			try {
				Algorithm knn = new Algorithm(tasks[i], tasks[i+1], "knn");
				knn.setKnnOptions(i);
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
}

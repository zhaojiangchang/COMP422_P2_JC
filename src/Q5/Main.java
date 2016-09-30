package Q5;

import java.io.File;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;

public class Main {
	final static String[] files = {"balance.data", "wine.data"};
	final static String path = "/Users/JackyChang/Documents/workspace/COMP422_P2_JC/res/Q5/";
	public void naiveBayesClassfier(Instances instances, String files2){
		try {

			NaiveBayes cls = new NaiveBayes();
			cls.buildClassifier(instances);
			Evaluation eval = new Evaluation(instances);
			eval.crossValidateModel(cls, instances, 10, new Random(1));
			System.out.println(eval.toSummaryString("\nSummary\n======\n", false));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void decisionTreeClassfier(Instances instances, String filename){
		try {

			J48 cls = new J48();
			cls.buildClassifier(instances);
			Evaluation eval = new Evaluation(instances);
			eval.crossValidateModel(cls, instances, 10, new Random(1));
			System.out.println(eval.toSummaryString("\nSummary\n======\n", false));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static Instances getInstances(String filename){
		DataSource source = null;
			try {
				source = new DataSource(path+filename);
				Instances instances = source.getDataSet();
				ArffSaver training_saver = new ArffSaver();
				training_saver.setInstances(instances);
				training_saver.setFile(new File(path+filename+".arff"));
				training_saver.writeBatch();
				source = new DataSource(path+filename+".arff");

				instances = source.getDataSet();
				instances.setClassIndex(instances.numAttributes()-1);          
				return instances;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		for(String filename: files){	
				Instances instances = getInstances(filename);
				System.out.println(filename);
				System.out.println("naiveBayesClassfier");
				m.naiveBayesClassfier(instances,filename);
				System.out.println();
				System.out.println();
				System.out.println("decisionTreeClassfier");
				m.decisionTreeClassfier(instances,filename);			
		}


	}

}

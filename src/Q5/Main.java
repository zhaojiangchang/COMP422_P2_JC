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
	final String path = "/Users/JackyChang/Documents/workspace/COMP422_P2_JC/res/Q5/";
	public void naiveBayesClassfier(String filename){
		DataSource source = null;
		try {
			source = new DataSource(path+filename);
			Instances instances = source.getDataSet();
			//	         trainingInst.setClassIndex(trainingInst.numAttributes()-1);
			ArffSaver training_saver = new ArffSaver();
			training_saver.setInstances(instances);
			training_saver.setFile(new File(path+filename+".arff"));
			training_saver.writeBatch();
			source = new DataSource(path+filename+".arff");

			instances = source.getDataSet();
			instances.setClassIndex(instances.numAttributes()-1);
			NaiveBayes cls = new NaiveBayes();
            cls.buildClassifier(instances);
            Evaluation eval = new Evaluation(instances);
			//				eval.crossValidateModel(perception, test, 9, new Random(1));
            eval.crossValidateModel(cls, instances, 10, new Random(1));
            System.out.println(filename);
            System.out.println("naiveBayesClassfier");

            System.out.println(eval.toSummaryString("\nSummary\n======\n", false));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void decisionTreeClassfier(String filename){
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
            J48 cls = new J48();
            cls.buildClassifier(instances);
            Evaluation eval = new Evaluation(instances);
            eval.crossValidateModel(cls, instances, 10, new Random(1));
            System.out.println(filename);
            System.out.println("decisionTreeClassfier");
            System.out.println(eval.toSummaryString("\nSummary\n======\n", false));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
		m.naiveBayesClassfier(files[0]);
		System.out.println();
		System.out.println();
		m.decisionTreeClassfier(files[0]);
		System.out.println();
		System.out.println();

		m.naiveBayesClassfier(files[1]);
		System.out.println();
		System.out.println();

		m.decisionTreeClassfier(files[1]);
	}

}

package Q2;


import com.sun.deploy.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;

public class KNN_Algorithm {
	private static final String path = "/Users/JackyChang/Documents/workspace/COMP422_P2_JC/src/res/";
	private Instances trainingInst = null;
	private Instances testInst = null;
    private static Classifier ibk = null;

	public KNN_Algorithm(String trainFile, String testFile) throws Exception{
		BufferedReader trainBR= readDataFile(path+trainFile);
		BufferedReader testBR = readDataFile(path+testFile);
		 DataSource trainSource = new DataSource(path+trainFile);
         trainingInst = trainSource.getDataSet();

         ArffSaver training_saver = new ArffSaver();
         training_saver.setInstances(trainingInst);
         training_saver.setFile(new File(path+trainFile+".arff"));
         training_saver.writeBatch();

         trainSource = new DataSource(path+trainFile+".arff");
         trainingInst = trainSource.getDataSet();
         trainingInst.setClassIndex(trainingInst.numAttributes()-1);
         
         DataSource testSource = new DataSource(path+testFile);
         testInst = testSource.getDataSet();
         ArffSaver test_saver = new ArffSaver();
         test_saver.setInstances(testInst);
         test_saver.setFile(new File(path+testFile+".arff"));
         test_saver.writeBatch();
         testSource = new DataSource(path+testFile+".arff");
         testInst = testSource.getDataSet();
         testInst.setClassIndex(testInst.numAttributes()-1);
//		trainIns = new Instances(trainBR);
//		testIns = new Instances(testBR);
//		trainIns.setClassIndex(trainIns.numAttributes() - 1);
//		testIns.setClassIndex( trainIns.numAttributes() - 1 );
		ibk = new IBk();		
		try {
			ibk.buildClassifier(trainingInst);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BufferedReader readDataFile(String filename) throws IOException {
//	    List<String> list;
//	    List<String> newList = new ArrayList<String>();
//		
//		try {
//			list = Files.readAllLines(Paths.get(filename));
//			for (String line : list) {
//				   List<String>str = Arrays.asList(line.trim().split("\\s+"));
//				   line = (StringUtils.join(str,","));
//				   newList.add(line);
//		    }
//			 FileWriter fw = new FileWriter(filename);
//			for (String line : newList) {
//				   fw.write(line+"\n");				
//		    }
//			fw.close();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    
		BufferedReader inputReader = null;
 
		try {
			inputReader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException ex) {
			System.err.println("File not found: " + filename);
		}
 
		return inputReader;
	}
	public double testClassifier()
    {
        double total = 500;
        double correct = 0;
        try
        {
        	int numInstances = testInst.numInstances();
        	 for (int instIdx = 0; instIdx < numInstances; instIdx++) { 
        		Instance ins = testInst.instance(instIdx);
        		  double pred = ibk.classifyInstance( ins );
                  if ( pred == ins.classValue() )
                  {
                      correct++;
                  }
        	 }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return ( correct / total );
    }
	public static void setOptions( int set )
    {
        int k = 1;
        switch ( set )
        {
            case 0 :
            	k = 2;
                break;
            case 2 :
            	k = 2;
            	break;
            	
            case 4 :
                k = 2;
                break;
            case 6 :
            	k = 2;
            	break;
            	
            case 8 :
                k = 2;
                break;
            case 10 :
            	k = 2;
            	break;
            case 12 :
            	k = 2;
            	break;
            	
            case 14 :
                k = 2;
                break;
            case 16 :
                k = 2;
                break;
        }
                ((IBk) ibk).setKNN( k );
    }

}
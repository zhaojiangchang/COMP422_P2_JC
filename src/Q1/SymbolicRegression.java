//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Q1;

import examples.gp.symbolicRegression.AndD;
import examples.gp.symbolicRegression.Gamma;
import examples.gp.symbolicRegression.Gaussian;
import examples.gp.symbolicRegression.Hill;
import examples.gp.symbolicRegression.Logistic;
import examples.gp.symbolicRegression.ModuloD;
import examples.gp.symbolicRegression.NotD;
import examples.gp.symbolicRegression.OrD;
import examples.gp.symbolicRegression.Sigmoid;
import examples.gp.symbolicRegression.Sign;
import examples.gp.symbolicRegression.Sqrt;
import examples.gp.symbolicRegression.Step;
import examples.gp.symbolicRegression.XorD;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.GPProblem;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.function.ADF;
import org.jgap.gp.function.Abs;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.Add3;
import org.jgap.gp.function.Add4;
import org.jgap.gp.function.And;
import org.jgap.gp.function.ArcCosine;
import org.jgap.gp.function.ArcSine;
import org.jgap.gp.function.ArcTangent;
import org.jgap.gp.function.Ceil;
import org.jgap.gp.function.Cosine;
import org.jgap.gp.function.Divide;
import org.jgap.gp.function.Equals;
import org.jgap.gp.function.Exp;
import org.jgap.gp.function.Floor;
import org.jgap.gp.function.ForLoop;
import org.jgap.gp.function.ForXLoop;
import org.jgap.gp.function.GreaterThan;
import org.jgap.gp.function.If;
import org.jgap.gp.function.IfDyn;
import org.jgap.gp.function.IfElse;
import org.jgap.gp.function.Increment;
import org.jgap.gp.function.LesserThan;
import org.jgap.gp.function.Log;
import org.jgap.gp.function.Loop;
import org.jgap.gp.function.Max;
import org.jgap.gp.function.Min;
import org.jgap.gp.function.Modulo;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.function.Multiply3;
import org.jgap.gp.function.Not;
import org.jgap.gp.function.Or;
import org.jgap.gp.function.Pop;
import org.jgap.gp.function.Pow;
import org.jgap.gp.function.Push;
import org.jgap.gp.function.Round;
import org.jgap.gp.function.Sine;
import org.jgap.gp.function.StoreTerminal;
import org.jgap.gp.function.SubProgram;
import org.jgap.gp.function.Subtract;
import org.jgap.gp.function.Tangent;
import org.jgap.gp.function.Tupel;
import org.jgap.gp.function.Xor;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.impl.GPPopulation;
import org.jgap.gp.impl.ProgramChromosome;
import org.jgap.gp.impl.TournamentSelector;
import org.jgap.gp.terminal.Constant;
import org.jgap.gp.terminal.Terminal;
import org.jgap.gp.terminal.Variable;
import org.jgap.util.NumberKit;
import org.jgap.util.SystemKit;

public class SymbolicRegression extends GPProblem {
//    private static transient Logger LOGGER = Logger.getLogger(SymbolicRegression.class);
    public static int numInputVariables;
    public static Variable[] variables;
    public static String[] variableNames;
    public static Integer outputVariable;
    public static int[] ignoreVariables;
    public static ArrayList<Double> constants = new ArrayList<Double>();
    public static int numRows;
    protected static Double[][] data;
    public static boolean foundPerfect = false;
    public static int minInitDepth = 2;
    public static int maxInitDepth = 4;
    public static int populationSize = 100;
    public static int maxCrossoverDepth = 8;
    public static int programCreationMaxTries = 5;
    public static int numEvolutions = 1800;
    public static boolean verboseOutput = true;
    public static int maxNodes = 21;
    public static double functionProb = 0.9D;
    public static float reproductionProb = 0.1F;
    public static float mutationProb = 0.1F;
    public static double crossoverProb = 0.9D;
    public static float dynamizeArityProb = 0.08F;
    public static double newChromsPercent = 0.3D;
    public static int tournamentSelectorSize = 0;
    public static double lowerRange = -10.0D;
    public static double upperRange = -10.0D;
    public static boolean terminalWholeNumbers = true;
    public static String returnType = "DoubleClass";
    public static String presentation = "";
    public static int adfArity = 0;
    public static String adfType = "double";
    public static boolean useADF = false;
    public static String[] functions = new String[]{"Multiply", "Divide", "Add", "Subtract"};
    public static String[] adfFunctions = new String[]{"Multiply3", "Divide", "Add3", "Subtract"};
    public static double scaleError = -1.0D;
    public static boolean bumpPerfect = false;
    public static Double bumpValue = Double.valueOf(0.0D);
    private static HashMap<String, Integer> foundSolutions = new HashMap<String, Integer>();
    public static long startTime;
    public static long endTime;
    public static double stopCriteria = -1.0D;
    public static boolean showPopulation = false;
    public static boolean showSimiliar = false;

    public SymbolicRegression(GPConfiguration a_conf) throws InvalidConfigurationException {
        super(a_conf);
    }

    public GPGenotype create()
			throws InvalidConfigurationException {
		GPConfiguration conf = getGPConfiguration();
		// At first, we define the return type of the GP program.
		// ------------------------------------------------------
		// Then, we define the arguments of the GP parts. Normally, only for ADF's
		// there is a specification here, otherwise it is empty as in first case.
		// -----------------------------------------------------------------------
		Class[] types;
		Class[][] argTypes;
		if (useADF) {
			if ("boolean".equals(adfType)) {
				types = new Class[] {CommandGene.DoubleClass, CommandGene.BooleanClass};
			}
			else if ("integer".equals(adfType)) {
				types = new Class[] {CommandGene.DoubleClass, CommandGene.IntegerClass};
			}
			else {
				types = new Class[] {CommandGene.DoubleClass, CommandGene.DoubleClass};
			}
			Class[] adfs = new Class[adfArity];
			for (int i = 0; i < adfArity; i++) {
				if ("boolean".equals(adfType)) {
					adfs[i] = CommandGene.BooleanClass;
				}
				else if ("integer".equals(adfType)) {
					adfs[i] = CommandGene.IntegerClass;
				}
				else {
					adfs[i] = CommandGene.DoubleClass;
				}
			}
			argTypes = new Class[][] { {}, adfs};
		}
		else {
			types = new Class[] {CommandGene.DoubleClass};
			argTypes = new Class[][] { {}
			};
		}
		// Configure desired minimum number of nodes per sub program.
		// Same as with types: First entry here corresponds with first entry in
		// nodeSets.
		// Configure desired maximum number of nodes per sub program.
		// First entry here corresponds with first entry in nodeSets.
		//
		// This is experimental!
		int[] minDepths;
		int[] maxDepths;
		if (useADF) {
			minDepths = new int[] {1, 1};
			maxDepths = new int[] {9, 9};
		}
		else {
			minDepths = new int[] {1};
			maxDepths = new int[] {9};
		}
		// Next, we define the set of available GP commands and terminals to use.
		// Please see package org.jgap.gp.function and org.jgap.gp.terminal
		// You can easily add commands and terminals of your own.
		// ----------------------------------------------------------------------
		CommandGene[] commands = makeCommands(conf, functions, lowerRange,
				upperRange, "plain");
		// Create the node sets
		int command_len = commands.length;
		CommandGene[][] nodeSets = new CommandGene[1][numInputVariables +
		                                              command_len];
		// the variables:
		//  1) in the nodeSets matrix
		//  2) as variables (to be used for fitness checking)
		// --------------------------------------------------
		variables = new Variable[numInputVariables];
		int variableIndex = 0;
		for (int i = 0; i < numInputVariables + 1; i++) {
			String variableName = variableNames[i];
			if (i != outputVariable) {
				if (variableNames != null && variableNames.length > 0) {
					variableName = variableNames[i];
				}
				variables[variableIndex] = Variable.create(conf, variableName,
						CommandGene.DoubleClass);
				nodeSets[0][variableIndex] = variables[variableIndex];
				System.out.println("input variable: " + variables[variableIndex]);
				variableIndex++;
			}
		}
		// assign the functions/terminals
		// ------------------------------
		for (int i = 0; i < command_len; i++) {
			System.out.println("function1: " + commands[i]);
			nodeSets[0][i + numInputVariables] = commands[i];
		}
		// ADF functions in the second array in nodeSets
		if (useADF) {
			CommandGene[] adfCommands = makeCommands(conf, adfFunctions, lowerRange,
					upperRange, "ADF");
			int adfLength = adfCommands.length;
			nodeSets[1] = new CommandGene[adfLength];
			for (int i = 0; i < adfLength; i++) {
				System.out.println("function2: " + adfCommands[i]);
				nodeSets[1][i] = adfCommands[i];
			}
		}
		// this is experimental.
		boolean[] full;
		if (useADF) {
			full = new boolean[] {true, true};
		}
		else {
			full = new boolean[] {true};
		}
		boolean[] fullModeAllowed = full;
		// Create genotype with initial population. Here, we use the
		// declarations made above:
		// ----------------------------------------------------------
		return GPGenotype.randomInitialGenotype(conf, types, argTypes, nodeSets,
				maxNodes, verboseOutput);
		// this is experimental
		// return GPGenotype.randomInitialGenotype(conf, types, argTypes, nodeSets,
		//                             minDepths,maxDepths, maxNodes, fullModeAllowed,verboseOutput);

	}

    public static void readFile(String file) {
        try {
            BufferedReader e = new BufferedReader(new FileReader(file));
            int lineCount = 0;
            boolean gotData = false;
			ArrayList<Double[]> theData = new ArrayList<Double[]> ();

            while(true) {
                while(true) {
                    String str;
                    int c;
                    int var17;
                    do {
                        do {
                            do {
                                if((str = e.readLine()) == null) {
                                    e.close();
                                    int var14 = theData.size();
                                    c = ((Double[])theData.get(0)).length;
                                    boolean var18 = false;
                                    if(ignoreVariables != null) {
                                        var17 = ignoreVariables.length;
                                    }

                                    Double[][] var19 = new Double[var14][c];

                                    for(int i = 0; i < var14; ++i) {
                                        Double[] this_row = (Double[])theData.get(i);

                                        for(int j = 0; j < c; ++j) {
                                            var19[i][j] = this_row[j];
                                        }
                                    }

                                    data = transposeMatrix(var19);
                                    return;
                                }

                                ++lineCount;
                                str = str.trim();
                            } while(str.startsWith("#"));
                        } while(str.startsWith("%"));
                    } while(str.length() == 0);

                    if("data".equals(str)) {
                        gotData = true;
                    } else {
                        String[] r;
                        if(!gotData) {
                            if(str.contains(":")) {
                                r = str.split(":\\s*");
                                if("return_type".equals(r[0])) {
                                    returnType = r[1];
                                } else if("presentation".equals(r[0])) {
                                    presentation = r[1];
                                } else if("num_input_variables".equals(r[0])) {
                                    numInputVariables = Integer.parseInt(r[1]);
                                } else if("num_rows".equals(r[0])) {
                                    numRows = Integer.parseInt(r[1]);
                                } else {
                                    String[] var16;
                                    if("terminal_range".equals(r[0])) {
                                        var16 = r[1].split("\\s+");
                                        lowerRange = Double.parseDouble(var16[0]);
                                        upperRange = Double.parseDouble(var16[1]);
                                    } else if("terminal_wholenumbers".equals(r[0])) {
                                        terminalWholeNumbers = Boolean.parseBoolean(r[1]);
                                    } else if("max_init_depth".equals(r[0])) {
                                        maxInitDepth = Integer.parseInt(r[1]);
                                    } else if("min_init_depth".equals(r[0])) {
                                        minInitDepth = Integer.parseInt(r[1]);
                                    } else if("program_creation_max_tries".equals(r[0])) {
                                        programCreationMaxTries = Integer.parseInt(r[1]);
                                    } else if("population_size".equals(r[0])) {
                                        populationSize = Integer.parseInt(r[1]);
                                    } else if("max_crossover_depth".equals(r[0])) {
                                        maxCrossoverDepth = Integer.parseInt(r[1]);
                                    } else if("function_prob".equals(r[0])) {
                                        functionProb = Double.parseDouble(r[1]);
                                    } else if("reproduction_prob".equals(r[0])) {
                                        reproductionProb = Float.parseFloat(r[1]);
                                    } else if("mutation_prob".equals(r[0])) {
                                        mutationProb = Float.parseFloat(r[1]);
                                    } else if("crossover_prob".equals(r[0])) {
                                        crossoverProb = Double.parseDouble(r[1]);
                                    } else if("dynamize_arity_prob".equals(r[0])) {
                                        dynamizeArityProb = Float.parseFloat(r[1]);
                                    } else if("new_chroms_percent".equals(r[0])) {
                                        newChromsPercent = Double.parseDouble(r[1]);
                                    } else if("num_evolutions".equals(r[0])) {
                                        numEvolutions = Integer.parseInt(r[1]);
                                    } else if("max_nodes".equals(r[0])) {
                                        maxNodes = Integer.parseInt(r[1]);
                                    } else if("bump".equals(r[0])) {
                                        bumpPerfect = Boolean.parseBoolean(r[1]);
                                    } else if("bump_value".equals(r[0])) {
                                        bumpValue = Double.valueOf(Double.parseDouble(r[1]));
                                    } else if("functions".equals(r[0])) {
                                        functions = r[1].split("[\\s,]+");
                                    } else if("adf_functions".equals(r[0])) {
                                        adfFunctions = r[1].split("[\\s,]+");
                                    } else if("variable_names".equals(r[0])) {
                                        variableNames = r[1].split("[\\s,]+");
                                    } else if("output_variable".equals(r[0])) {
                                        outputVariable = Integer.valueOf(Integer.parseInt(r[1]));
                                    } else if("ignore_variables".equals(r[0])) {
                                        var16 = r[1].split("[\\s,]+");
                                        ignoreVariables = new int[var16.length];

                                        for(var17 = 0; var17 < var16.length; ++var17) {
                                            ignoreVariables[var17] = Integer.parseInt(var16[var17]);
                                        }
                                    } else if("constant".equals(r[0])) {
                                        Double var15 = Double.valueOf(Double.parseDouble(r[1]));
                                        constants.add(var15);
                                    } else if("adf_arity".equals(r[0])) {
                                        adfArity = Integer.parseInt(r[1]);
                                        System.out.println("ADF arity " + adfArity);
                                        if(adfArity > 0) {
                                            useADF = true;
                                        }
                                    } else if("adf_type".equals(r[0])) {
                                        adfType = r[1];
                                    } else if("tournament_selector_size".equals(r[0])) {
                                        tournamentSelectorSize = Integer.parseInt(r[1]);
                                    } else if("scale_error".equals(r[0])) {
                                        scaleError = Double.parseDouble(r[1]);
                                    } else if("stop_criteria".equals(r[0])) {
                                        stopCriteria = Double.parseDouble(r[1]);
                                    } else if("show_population".equals(r[0])) {
                                        showPopulation = Boolean.parseBoolean(r[1]);
                                    } else if("show_similiar".equals(r[0])) {
                                        showSimiliar = Boolean.parseBoolean(r[1]);
                                    } else {
                                        System.out.println("Unknown keyword: " + r[0] + " on line " + lineCount);
                                        System.exit(1);
                                    }
                                }
                            }
                        } else {
                            r = str.split("[\\s,]+");
                            c = r.length;
                            Double[] numIgnore = new Double[c];

                            for(int dataTmp = 0; dataTmp < c; ++dataTmp) {
                                numIgnore[dataTmp] = Double.valueOf(Double.parseDouble(r[dataTmp]));
                            }

                            theData.add(numIgnore);
                        }
                    }
                }
            }
        } catch (IOException var13) {
            System.out.println(var13);
            System.exit(1);
        }
    }

    public static Double[][] transposeMatrix(Double[][] m) {
        int r = m.length;
        int c = m[0].length;
        Double[][] t = new Double[c][r];

        for(int i = 0; i < r; ++i) {
            for(int j = 0; j < c; ++j) {
                t[j][i] = m[i][j];
            }
        }

        return t;
    }

    static CommandGene[] makeCommands(GPConfiguration conf, String[] functions, Double lowerRange, Double upperRange, String type) {
		ArrayList<CommandGene> commandsList = new ArrayList<CommandGene> ();
        int len = functions.length;

        try {
            int commands;
            for(commands = 0; commands < len; ++commands) {
                if("Multiply".equals(functions[commands])) {
                    commandsList.add(new Multiply(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Multiply(conf, CommandGene.BooleanClass));
                    }
                } else if("Multiply3".equals(functions[commands])) {
                    commandsList.add(new Multiply3(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Multiply3(conf, CommandGene.BooleanClass));
                    }
                } else if("Add".equals(functions[commands])) {
                    commandsList.add(new Add(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Add(conf, CommandGene.BooleanClass));
                    }
                } else if("Divide".equals(functions[commands])) {
                    commandsList.add(new Divide(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Divide(conf, CommandGene.BooleanClass));
                    }
                } else if("Add3".equals(functions[commands])) {
                    commandsList.add(new Add3(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Add3(conf, CommandGene.BooleanClass));
                    }
                } else if("Add4".equals(functions[commands])) {
                    commandsList.add(new Add4(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Add4(conf, CommandGene.BooleanClass));
                    }
                } else if("Subtract".equals(functions[commands])) {
                    commandsList.add(new Subtract(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Subtract(conf, CommandGene.BooleanClass));
                    }
                } else if("Sine".equals(functions[commands])) {
                    commandsList.add(new Sine(conf, CommandGene.DoubleClass));
                } else if("ArcSine".equals(functions[commands])) {
                    commandsList.add(new ArcSine(conf, CommandGene.DoubleClass));
                } else if("Tangent".equals(functions[commands])) {
                    commandsList.add(new Tangent(conf, CommandGene.DoubleClass));
                } else if("ArcTangent".equals(functions[commands])) {
                    commandsList.add(new ArcTangent(conf, CommandGene.DoubleClass));
                } else if("Cosine".equals(functions[commands])) {
                    commandsList.add(new Cosine(conf, CommandGene.DoubleClass));
                } else if("ArcCosine".equals(functions[commands])) {
                    commandsList.add(new ArcCosine(conf, CommandGene.DoubleClass));
                } else if("Exp".equals(functions[commands])) {
                    commandsList.add(new Exp(conf, CommandGene.DoubleClass));
                } else if("Log".equals(functions[commands])) {
                    commandsList.add(new Log(conf, CommandGene.DoubleClass));
                } else if("Abs".equals(functions[commands])) {
                    commandsList.add(new Abs(conf, CommandGene.DoubleClass));
                } else if("Pow".equals(functions[commands])) {
                    commandsList.add(new Pow(conf, CommandGene.DoubleClass));
                } else if("Round".equals(functions[commands])) {
                    commandsList.add(new Round(conf, CommandGene.DoubleClass));
                } else if("Ceil".equals(functions[commands])) {
                    commandsList.add(new Ceil(conf, CommandGene.DoubleClass));
                } else if("Floor".equals(functions[commands])) {
                    commandsList.add(new Floor(conf, CommandGene.DoubleClass));
                } else if("Modulo".equals(functions[commands])) {
                    commandsList.add(new Modulo(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Modulo(conf, CommandGene.BooleanClass));
                    }
                } else if("ModuloD".equals(functions[commands])) {
                    commandsList.add(new ModuloD(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new ModuloD(conf, CommandGene.BooleanClass));
                    }
                } else if("Max".equals(functions[commands])) {
                    commandsList.add(new Max(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Max(conf, CommandGene.BooleanClass));
                    }
                } else if("Min".equals(functions[commands])) {
                    commandsList.add(new Min(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Min(conf, CommandGene.BooleanClass));
                    }
                } else if("Sqrt".equals(functions[commands])) {
                    commandsList.add(new Sqrt(conf, CommandGene.DoubleClass));
                } else if("Logistic".equals(functions[commands])) {
                    commandsList.add(new Logistic(conf, CommandGene.DoubleClass));
                } else if("Gaussian".equals(functions[commands])) {
                    commandsList.add(new Gaussian(conf, CommandGene.DoubleClass));
                } else if("Sigmoid".equals(functions[commands])) {
                    commandsList.add(new Sigmoid(conf, CommandGene.DoubleClass));
                } else if("Gamma".equals(functions[commands])) {
                    commandsList.add(new Gamma(conf, CommandGene.DoubleClass));
                } else if("Step".equals(functions[commands])) {
                    commandsList.add(new Step(conf, CommandGene.DoubleClass));
                } else if("Sign".equals(functions[commands])) {
                    commandsList.add(new Sign(conf, CommandGene.DoubleClass));
                } else if("Hill".equals(functions[commands])) {
                    commandsList.add(new Hill(conf, CommandGene.DoubleClass));
                } else if("LesserThan".equals(functions[commands])) {
                    commandsList.add(new LesserThan(conf, CommandGene.BooleanClass));
                } else if("GreaterThan".equals(functions[commands])) {
                    commandsList.add(new GreaterThan(conf, CommandGene.BooleanClass));
                } else if("If".equals(functions[commands])) {
                    commandsList.add(new If(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new If(conf, CommandGene.BooleanClass));
                    }
                } else if("IfElse".equals(functions[commands])) {
                    commandsList.add(new IfElse(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new IfElse(conf, CommandGene.BooleanClass));
                    }
                } else if("IfDyn".equals(functions[commands])) {
                    commandsList.add(new IfDyn(conf, CommandGene.BooleanClass, 1, 1, 5));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new IfDyn(conf, CommandGene.DoubleClass, 1, 1, 5));
                    }
                } else if("Loop".equals(functions[commands])) {
                    commandsList.add(new Loop(conf, CommandGene.DoubleClass, 3));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Loop(conf, CommandGene.BooleanClass, 3));
                    }
                } else if("Equals".equals(functions[commands])) {
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Equals(conf, CommandGene.BooleanClass));
                    }
                } else if("ForXLoop".equals(functions[commands])) {
                    commandsList.add(new ForXLoop(conf, CommandGene.IntegerClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new ForXLoop(conf, CommandGene.BooleanClass));
                    } else if(useADF && "integer".equals(adfType)) {
                        commandsList.add(new ForXLoop(conf, CommandGene.IntegerClass));
                    }
                } else if("ForLoop".equals(functions[commands])) {
                    commandsList.add(new ForLoop(conf, CommandGene.IntegerClass, 10));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new ForLoop(conf, CommandGene.BooleanClass, 10));
                    } else if(useADF && "integer".equals(adfType)) {
                        commandsList.add(new ForLoop(conf, CommandGene.IntegerClass, 10));
                    }
                } else if("Increment".equals(functions[commands])) {
                    commandsList.add(new Increment(conf, CommandGene.DoubleClass));
                    if(useADF && "boolean".equals(adfType)) {
                        commandsList.add(new Increment(conf, CommandGene.BooleanClass));
                    }
                } else if(!"Argument".equals(functions[commands])) {
                    if("StoreTerminal".equals(functions[commands])) {
                        commandsList.add(new StoreTerminal(conf, "dmem0", CommandGene.DoubleClass));
                        commandsList.add(new StoreTerminal(conf, "dmem1", CommandGene.DoubleClass));
                        if(useADF && "boolean".equals(adfType)) {
                            commandsList.add(new StoreTerminal(conf, "bmem0", CommandGene.DoubleClass));
                            commandsList.add(new StoreTerminal(conf, "bmem1", CommandGene.DoubleClass));
                        }
                    } else if("Pop".equals(functions[commands])) {
                        if(useADF && "boolean".equals(adfType)) {
                            commandsList.add(new Pop(conf, CommandGene.BooleanClass));
                        }
                    } else if("Push".equals(functions[commands])) {
                        commandsList.add(new Push(conf, CommandGene.DoubleClass));
                    } else if("And".equals(functions[commands])) {
                        commandsList.add(new And(conf));
                    } else if("Or".equals(functions[commands])) {
                        commandsList.add(new Or(conf));
                    } else if("Xor".equals(functions[commands])) {
                        commandsList.add(new Xor(conf));
                    } else if("Not".equals(functions[commands])) {
                        commandsList.add(new Not(conf));
                    } else if("AndD".equals(functions[commands])) {
                        commandsList.add(new AndD(conf));
                    } else if("OrD".equals(functions[commands])) {
                        commandsList.add(new OrD(conf));
                    } else if("XorD".equals(functions[commands])) {
                        commandsList.add(new XorD(conf));
                    } else if("NotD".equals(functions[commands])) {
                        commandsList.add(new NotD(conf));
                    } else if("SubProgram".equals(functions[commands])) {
                        if(useADF && "boolean".equals(adfType)) {
                            commandsList.add(new SubProgram(conf, new Class[]{CommandGene.BooleanClass, CommandGene.BooleanClass}));
                            commandsList.add(new SubProgram(conf, new Class[]{CommandGene.BooleanClass, CommandGene.BooleanClass, CommandGene.BooleanClass}));
                        }

                        commandsList.add(new SubProgram(conf, new Class[]{CommandGene.DoubleClass, CommandGene.DoubleClass}));
                        commandsList.add(new SubProgram(conf, new Class[]{CommandGene.DoubleClass, CommandGene.DoubleClass, CommandGene.DoubleClass}));
                    } else if("Tupel".equals(functions[commands])) {
                        if(useADF && "boolean".equals(adfType)) {
                            commandsList.add(new Tupel(conf, new Class[]{CommandGene.BooleanClass, CommandGene.BooleanClass}));
                        }
                    } else {
                        System.out.println("Unkown function: " + functions[commands]);
                        System.exit(1);
                    }
                }
            }

            commandsList.add(new Terminal(conf, CommandGene.DoubleClass, lowerRange.doubleValue(), upperRange.doubleValue(), terminalWholeNumbers));
            if(useADF && !"ADF".equals(type)) {
                commandsList.add(new ADF(conf, 1, adfArity));
            }

            if(constants != null) {
                for(commands = 0; commands < constants.size(); ++commands) {
                    Double constant = (Double)constants.get(commands);
                    commandsList.add(new Constant(conf, CommandGene.DoubleClass, constant));
                }
            }
        } catch (Exception var9) {
            System.out.println(var9);
        }

        CommandGene[] var10 = new CommandGene[commandsList.size()];
        commandsList.toArray(var10);
        return var10;
    }

    public static void main(String[] args) throws Exception {
//        LOGGER.addAppender(new ConsoleAppender(new SimpleLayout(), "System.out"));
        if(args.length > 0) {
            String config = args[0];
            readFile(config);
        } else {
            numRows = 21;
            numInputVariables = 3;
            int[][] var25 = new int[][]{{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946}, {1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711}, {2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657}, {3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, '딠'}};
            data = new Double[numInputVariables + 1][numRows];

            for(int problem = 0; problem < numInputVariables + 1; ++problem) {
                for(int gp = 0; gp < numRows; ++gp) {
                    data[problem][gp] = new Double((double)var25[problem][gp]);
                }
            }

            functions = "Multiply,Divide,Add,Subtract".split(",");
            variableNames = "F1,F2,F3,F4".split(",");
            presentation = "Fibonacci series";
        }

        System.out.println("Presentation: " + presentation);
        if(outputVariable == null) {
            outputVariable = Integer.valueOf(numInputVariables);
        }

        if(variableNames == null) {
            variableNames = new String[numInputVariables + 1];

            for(int var26 = 0; var26 < numInputVariables + 1; ++var26) {
                variableNames[var26] = "V" + var26;
            }
        }

        System.out.println("output_variable: " + variableNames[outputVariable.intValue()] + " (index: " + outputVariable + ")");
        GPConfiguration var27 = new GPConfiguration();
        var27.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
        var27.setMaxInitDepth(maxInitDepth);
        var27.setPopulationSize(populationSize);
        if(tournamentSelectorSize > 0) {
            var27.setSelectionMethod(new TournamentSelector(tournamentSelectorSize));
        }

        var27.setMaxCrossoverDepth(maxCrossoverDepth);
        var27.setFitnessFunction(new SymbolicRegression.FormulaFitnessFunction());
        var27.setStrictProgramCreation(false);
        var27.setFunctionProb(functionProb);
        var27.setReproductionProb(reproductionProb);
        var27.setMutationProb(mutationProb);
        var27.setDynamizeArityProb(dynamizeArityProb);
        var27.setNewChromsPercent(newChromsPercent);
        var27.setMinInitDepth(minInitDepth);
        var27.setProgramCreationMaxTries(programCreationMaxTries);
        SymbolicRegression var28 = new SymbolicRegression(var27);
        GPGenotype var29 = var28.create();
        var29.setVerboseOutput(false);
        startTime = System.currentTimeMillis();
        System.out.println("Creating initial population");
        System.out.println("Mem free: " + SystemKit.niceMemory(SystemKit.getTotalMemoryMB()) + " MB");
        IGPProgram fittest = null;
        double bestFit = -1.0D;
        String bestProgram = "";
        int bestGen = 0;
        HashMap<String, Integer> similiar = null;
        if(showSimiliar) {
            similiar = new HashMap<String, Integer>();
        }

        for(int elapsedTime = 1; elapsedTime <= numEvolutions; ++elapsedTime) {
            var29.evolve();
            var29.calcFitness();
            GPPopulation pop = var29.getGPPopulation();
            IGPProgram i$ = pop.determineFittestProgram();
            i$.setApplicationData("gen" + elapsedTime);
            ProgramChromosome p = i$.getChromosome(0);
            String program = p.toStringNorm(0);
            double fitness = i$.getFitnessValue();
            if(showSimiliar || showPopulation) {
                if(showPopulation) {
                    System.out.println("Generation " + elapsedTime + " (show whole population, sorted)");
                }

                pop.sortByFitness();
                IGPProgram[] arr$ = pop.getGPPrograms();
                int len$ = arr$.length;

                for(int i$1 = 0; i$1 < len$; ++i$1) {
                    IGPProgram p1 = arr$[i$1];
                    double fit = p1.getFitnessValue();
                    String prg;
                    if(showSimiliar && fit <= bestFit) {
                        prg = p1.toStringNorm(0);
                        if(!similiar.containsKey(prg)) {
                            similiar.put(prg, Integer.valueOf(1));
                        } else {
                            similiar.put(prg, Integer.valueOf(((Integer)similiar.get(prg)).intValue() + 1));
                        }
                    }

                    if(showPopulation) {
                        prg = p1.toStringNorm(0);
                        int sz = p1.size();
                        System.out.println("\tprogram: " + prg + " fitness: " + fit);
                    }
                }
            }

            if(bestFit < 0.0D || fitness < bestFit) {
                bestGen = elapsedTime;
                myOutputSolution(i$, elapsedTime);
                bestFit = fitness;
                fittest = i$;
                if(showSimiliar) {
                    similiar.clear();
                }
            }
        }

        System.out.println("\nAll time best (from generation " + bestGen + ")");
        myOutputSolution(fittest, numEvolutions);
        System.out.println("applicationData: " + fittest.getApplicationData());
        endTime = System.currentTimeMillis();
        long var30 = endTime - startTime;
        System.out.println("\nTotal time " + var30 + "ms");
        if(showSimiliar) {
            System.out.println("\nAll solutions with the best fitness (" + bestFit + "):");
            Iterator var31 = similiar.keySet().iterator();

            while(var31.hasNext()) {
                String var32 = (String)var31.next();
                System.out.println(var32 + " (" + similiar.get(var32) + ")");
            }
        }

        System.exit(0);
    }

    public static void myOutputSolution(IGPProgram a_best, int gen) {
        String freeMB = SystemKit.niceMemory(SystemKit.getFreeMemoryMB());
        System.out.println("Evolving generation " + gen + "/" + numEvolutions + ", memory free: " + freeMB + " MB");
        if(a_best == null) {
            System.out.println("No best solution (null)");
        } else {
            double bestValue = a_best.getFitnessValue();
            if(Double.isInfinite(bestValue)) {
                System.out.println("No best solution (infinite)");
            } else {
                System.out.println("Best solution fitness: " + NumberKit.niceDecimalNumber(bestValue, 2));
                System.out.println("Best solution: " + a_best.toStringNorm(0));
                String depths = "";
                int size = a_best.size();

                for(int i = 0; i < size; ++i) {
                    if(i > 0) {
                        depths = depths + " / ";
                    }

                    depths = depths + a_best.getChromosome(i).getDepth(0);
                }

                if(size == 1) {
                    System.out.println("Depth of chrom: " + depths);
                } else {
                    System.out.println("Depths of chroms: " + depths);
                }

            }
        }
    }

    public static class FormulaFitnessFunction extends GPFitnessFunction {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FormulaFitnessFunction() {
        }

        protected double evaluate(IGPProgram a_subject) {
            return this.computeRawFitness(a_subject);
        }

        public double computeRawFitness(IGPProgram ind) {
            double error = 0.0D;
            Object[] noargs = new Object[0];

            for(int chrom = 0; chrom < SymbolicRegression.numRows; ++chrom) {
                int program = 0;

                for(int ex = 0; ex < SymbolicRegression.numInputVariables + 1; ++ex) {
                    if(ex != SymbolicRegression.outputVariable.intValue()) {
                        SymbolicRegression.variables[program].set(SymbolicRegression.data[ex][chrom]);
                        ++program;
                    }
                }

                try {
                    double var12 = ind.execute_double(0, noargs);
                    error += Math.abs(var12 - SymbolicRegression.data[SymbolicRegression.outputVariable.intValue()][chrom].doubleValue());
                    if(Double.isInfinite(error)) {
                        return 1.7976931348623157E308D;
                    }
                } catch (ArithmeticException var9) {
                    System.out.println(ind);
                    throw var9;
                }
            }

            if(error <= SymbolicRegression.bumpValue.doubleValue() && SymbolicRegression.bumpPerfect) {
                if(!SymbolicRegression.foundPerfect) {
                    System.out.println("Found a perfect solution with err " + error + "!. Bump up the values!");
                    SymbolicRegression.foundPerfect = true;
                }

                ProgramChromosome var10 = ind.getChromosome(0);
                String var11 = var10.toStringNorm(0);
                if(!SymbolicRegression.foundSolutions.containsKey(var11)) {
                    System.out.println("PROGRAM:" + var11 + " error: " + error);
                    SymbolicRegression.foundSolutions.put(var11, Integer.valueOf(1));
                } else {
                    SymbolicRegression.foundSolutions.put(var11, Integer.valueOf(((Integer)SymbolicRegression.foundSolutions.get(var11)).intValue() + 1));
                }

                error = 0.1D;
            }

            if(SymbolicRegression.scaleError > 0.0D) {
                return error * SymbolicRegression.scaleError;
            } else {
                return error;
            }
        }
    }
}

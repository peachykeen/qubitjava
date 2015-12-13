import java.util.ArrayList; //imports the array capabilities
import java.util.Scanner; //imports the scanner which allows the user to input text

public class QuteMain //starts the class
{
	private static Scanner scan; //creates a scanner to input text
	private static String numberofqubits; //how many qubits you want to use
	private static int weight; //the weight of each qubit
	static ArrayList<Qubit> qubitlist; //declares the list that holds all of the qubits
	static ArrayList<Weights> weightlist; //holds all of the weights

	public static void main ( String [] args ) //starts the main method
	{
		System.out.println("Welcome to Helena's Qubit Simluator."); //displays the name
		System.out.println("Please insert the number of qubits that you want to use:"); // asking for an input
		scan = new Scanner(System.in); //defines the new scanner equal to in
		numberofqubits = scan.nextLine(); //making the input equal to the number of qubits you want to use
		System.out.println(numberofqubits); //displays the number of qubits
		
		qubitlist = new ArrayList<Qubit>(); //creates the qubit list
		
		for(int i = 0; i < Integer.parseInt(numberofqubits); i++) //opens the for loop
		{
			Qubit q; //declares q as a qubit
		    q = new Qubit(i); //it makes a new qubit
		    qubitlist.add(q); //puts the qubit in list
		}
		
		GetWeights(); //calls the GetWeights method
		long begintime; //declares the beginttime variable
		begintime = System.nanoTime(); //starts the timer
		FindLowestState(); //calls the FindLowestState method
		PrintAllQubits(); //calls the PrintAllQubits method
		System.out.println("Running total equals " + GetCurrentWeightTotal()); //displays the running total
		long endtime; //declares the endtime variable
		endtime = System.nanoTime(); //ends the timer
		System.out.println((endtime - begintime)/1000000000. + " seconds"); //displays the run time
	}
	
	public static void FindLowestState() //starts the FindLowestState method
	{
		SetQubitZero(); //calls the SetQubitZero method
		long min_w = Long.MAX_VALUE; //declares and sets min_w equal to infinity
		long state = 0; //declares and sets state equal to zero
		long min_state = Long.MAX_VALUE; //declares and sets min_state equal to infinity
		do //starts the do loop
		{
			long w1; //declares w1
			w1 = GetCurrentWeightTotal(); //sets w1 equal to the GetCurrentWeightTotal method
			if (w1 < min_w) //opens the for loop, it wants to know if w1 is less than min_w
			{
				min_w = w1; //min_w gets set equal to w1
				min_state = state; //min_state gets set equal to state
				state ++; //adds one to state
			}
		}
		while(NextState()); //ends the do while loop by calling the NextState method
		SetQubitZero(); //calls the SetQubitZero method
		while(state > 0) //starts a while loop so that when state is greater than zero
		{
			NextState(); //calls the NextState method
			state --; //removes one from state
		}
	}
	
	public static void SetQubitZero() //starts the SetQubitZero method
	{
		for(int i = 0; i < Integer.parseInt(numberofqubits); i++) //starts for loop: i < number of qubits
		{
			Qubit aqubit = qubitlist.get(i); //creates a new qubit called aqubit
			aqubit.iszero = true; //sets the value of aqubit for iszero to be true
			aqubit.isone = false; //sets the value of aqubit for isone to be false
		}
	}
	
	public static void PrintAllQubits() //starts the PrintAllQubits method
	{
		for(int i = 0; i < Integer.parseInt(numberofqubits); i++) //starts for loop: i < number of qubits
		{
			Qubit aqubit = qubitlist.get(i); //creates a new qubit called aqubit
			System.out.println("Qubit: " + aqubit); //prints the qubit with text
		}
	}
	
	public static void GetWeights() //starts the GetWeights method
	{
		weightlist = new ArrayList<Weights>();
		for(int i = 0; i < Integer.parseInt(numberofqubits); i++) //starts for loop: i < number of qubits
		{
			System.out.println("What is the weight of the qubit " + i + "?" );
			weight = scan.nextInt();
			Weights w;
		    w = new Weights(i, weight);
		    weightlist.add(w);
		}
		System.out.println("Enter the number of cross qubits."); //asks for cross qubit input
		int noxqubit = scan.nextInt(); //declares and inputs number 
		for(int i = 0; i < noxqubit; i++) //starts for loop: i < number of cross qubits
		{
			System.out.println("What is the number of the first qubit?");
			int xq1 = scan.nextInt();
			System.out.println("What is the number of the second qubit?");
			int xq2 = scan.nextInt();
			System.out.println("What is the weight of the cross qubit?");
			int crossweight = scan.nextInt();
			Weights w;
		    w = new Weights(xq1, xq2, crossweight);
		    weightlist.add(w);
		}
	}
	
	public static long GetCurrentWeightTotal()
	{
		long runningtotal = 0;
		for (Weights aweight: weightlist)
		{
			Qubit aqubit_a = qubitlist.get(aweight.qubit_a);
			if(aqubit_a.isone && aweight.qubit_b == -1)
			{
				runningtotal += aweight.coefficient_a;
			}
			else if(aweight.qubit_b != -1)
			{
				Qubit aqubit_b = qubitlist.get(aweight.qubit_b);
				if(aqubit_a.isone && aqubit_b.isone)
				{
					runningtotal += aweight.coefficient_a + aweight.coefficient_b;
				}
			}
			
		}
		return runningtotal;
	}
	public static boolean NextState()
	{
		boolean b1 = false;
		for(int i = 0; i < Integer.parseInt(numberofqubits); i++)
		{
			Qubit aqubit = qubitlist.get(i);
			if(aqubit.iszero)
			{
				aqubit.iszero = false;
				aqubit.isone = true;
				b1 = true;
				break;
			}
			else if(aqubit.isone)
			{
				aqubit.isone = false;
				aqubit.iszero = true;
			}
		}
		return b1;
	}
	
}

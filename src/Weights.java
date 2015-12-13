
public class Weights
{
	int coefficient_a = 0;
	int coefficient_b = 0;
	int qubit_a = -1;
	int qubit_b = -1;
	
	public Weights(int in_qubit_a, int in_coefficient_a)
	{
		coefficient_a = in_coefficient_a;
		qubit_a = in_qubit_a;
	}
	public Weights(int in_qubit_a, int in_qubit_b, int in_coefficient_a)
	{
		coefficient_a = in_coefficient_a;
		qubit_a = in_qubit_a;
		qubit_b = in_qubit_b;
	}

}

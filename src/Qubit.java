
public class Qubit
{
	boolean isone = true;
	boolean iszero = true;
	int qubitnum = 0;
	
	public Qubit(int in_qubitnum)
	{
		qubitnum = in_qubitnum;
	}
	public String toString()
	{
		if (isone && iszero)
		{
			return "Q" + qubitnum + " Both zero and one";
		}
		if (isone)
		{
			return "Q" + qubitnum + " Just one";
		}
		if (iszero)
		{
			return "Q" + qubitnum + " Just zero";
			
		}
		return "Q0 unknown";
	}

}

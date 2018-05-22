public class Pokemon 
{
	public String name;
	public int catchRate;
	
	public Pokemon(String in, int inp)
	{
		name = in;
		catchRate = inp;
	}
	
	public int getRate()
	{
		return catchRate;
	}
	
	public String getName()
	{
		return name;
	}
}


import java.util.ArrayList;
import java.util.Random;
public class Algorithm 
{
	private ArrayList<Pokemon> noWild = new ArrayList<Pokemon>();
	private ArrayList<Pokemon> all = new ArrayList<Pokemon>();
	private Pokemon poek;
	private String location;
	private String ball = "";
	private int randOne;
	private int status;
	private int hpFactor;
	private int randTwo;
	private int randPrime;
	
	public Algorithm(ArrayList<Pokemon> notWild, ArrayList<Pokemon> allPoke, String ballType, String pokemonIn, String locIn)
	{
		poek = new Pokemon("pokemonIn");
		noWild = notWild;
		all = allPoke;
		ball = ballType;
		location = locIn;
	}
	
	public boolean foundInWild()
	{
		for(Pokemon not : noWild)
		{
			if(poek.getName().equals(not.getName()))
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean marrowakGhost()
	{
		if(poek.getName().equals("marrowak") && location.equals("pokemon tower"))
		{
			return false;
		}
		return true;
	}
	
	public boolean isMasterBall()
	{
		if(ball.equals("master ball"))
		{
			return true;
		}
		return false;
	}
	
	public int genRandOne()
	{
		int randOne;
		Random rng = new Random();
		if(ball.equals("pokeball"))
		{
			randOne = rng.nextInt(256);
		}
		else if(ball.equals("great ball"))
		{
			randOne = rng.nextInt(201);
					
		}
		else
		{
			randOne = rng.nextInt(151);
		}
		this.randOne = randOne;
		return randOne;
	}
	
	public int status(String stat)
	{
		int statVar;
		if(stat.equals("asleep") || stat.equals("frozen"))
		{
			statVar = 25;
		}
		else if(stat.equals("poisoned") || stat.equals("burned")|| stat.equals("paralyzed"))
		{
			statVar = 12;
		}
		else
		{
			statVar = 0;
		}
		status = statVar;
		return statVar;
	}
	
	public int hitPointFactor(int maxHP, int curHP)
	{
		int factor;
		factor = maxHP*255;
		if(ball.equals("great ball"))
		{
			factor /= 8;
		}
		else
		{
			factor /= 12;
		}
		if((curHP/4) > 0)
		{
			factor /= (curHP/4);
		}
		if(factor > 255)
		{
			hpFactor = 255;
			return 255;
		}
		else
		{
			hpFactor = factor;
			return factor;
		}
		
	}
	public int randomPrime()
	{
		randPrime = randOne - status;
		return randPrime;
	}
	
	public boolean catchVersusRandPrime(Pokemon in)
	{
		int catchRate = 0;
		for(Pokemon cur : all)
		{
			if(cur.getName().equals(in.getName()))
			{
				catchRate = cur.getRate();
			}
		}
		if(catchRate < randPrime)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public int rand2()
	{
		Random random2 = new Random(256);
		return random2.nextInt(256);
	}
	
	public boolean compRand2()
	{
		if(randTwo <= hpFactor)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}

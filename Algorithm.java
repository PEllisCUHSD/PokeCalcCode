import java.util.ArrayList;
import java.util.Random;
public class Algorithm 
{
	private ArrayList<Pokemon> noWild = new ArrayList<Pokemon>();
	private ArrayList<Pokemon> all = new ArrayList<Pokemon>();
	private Pokemon poek;
	private String location;
	private String ball = "";
	private String statusEffect;
	private int maxHP;
	private int curHP;
	private int randOne;
	private int status;
	private int hpFactor;
	private int randTwo;
	private int randPrime;
	
	public Algorithm(ArrayList<Pokemon> notWild, ArrayList<Pokemon> allPoke, String ballType, String pokemonIn, String locIn, String statusEffectIn, int maxHPIn, int curHPIn)
	{
		poek = new Pokemon(pokemonIn);
		noWild = notWild;
		all = allPoke;
		ball = ballType;
		location = locIn;
		statusEffect = statusEffectIn;
		maxHP = maxHPIn;
		curHP = curHPIn;
	}
	
	public boolean foundInWild()
	{
		for(Pokemon not : noWild)
		{
			String notName = not.getName();
			String pokeName = poek.getName();
			//System.out.println(notName + " " + pokeName);
			if(pokeName.equalsIgnoreCase(notName))
			{
				return false;
			}
		}
		return true;
	}
	
	public boolean marrowakGhost()
	{
		if(poek.getName().equals("marowak") && location.equals("pokemon tower"))
		{
			return false;
		}
		return true;
	}
	
	public boolean isMasterBall()
	{
		if(ball.equals("master"))
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
		else if(ball.equals("great"))
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
	
	public int status()
	{
		int statVar;
		if(statusEffect.equals("asleep") || statusEffect.equals("frozen"))
		{
			statVar = 25;
		}
		else if(statusEffect.equals("poisoned") || statusEffect.equals("burned")|| statusEffect.equals("paralyzed"))
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
	
	public int hitPointFactor()
	{
		int factor;
		factor = maxHP*255;
		if(ball.equals("great"))
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
	
	public boolean catchVersusRandPrime()
	{
		int catchRate = 0;
		for(Pokemon cur : all)
		{
			if(cur.getName().equals(poek.getName()))
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
		Random random2 = new Random();
		randTwo = random2.nextInt(256);
		return randTwo;
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

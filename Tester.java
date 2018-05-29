import java.util.ArrayList;
public class Tester 
{
	public static void main(String[] args) 
	{
		PokeList allPoke = new PokeList();
		ArrayList<Pokemon> allPokeArray = new ArrayList<Pokemon> ();
		allPokeArray.addAll(allPoke.createList());
		/*testing to see if all are in the array propperly
		for(Pokemon cur : allPokeArray)
		{
			System.out.print(cur.getName() + "     ");
			System.out.println(cur.getRate());
		}
		*/
		ArrayList<Pokemon> notFoundInWildPoke = new ArrayList<Pokemon>();
	}
}

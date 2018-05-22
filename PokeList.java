import java.util.ArrayList;
public class PokeList
{
	Pokemon bulbasaur = new Pokemon("bulbasaur", 45);
	Pokemon ivasaur = new Pokemon("ivasaur", 45);
	Pokemon venasaur = new Pokemon("venasaur", 45);
	
	
	ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
	

	public ArrayList<Pokemon> createList()
	{
		pokemonList.add(bulbasaur);
		pokemonList.add(ivasaur);
		pokemonList.add(venasaur);
		
		
		
		return pokemonList;
	}
	
}

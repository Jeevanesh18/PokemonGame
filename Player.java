import java.util.ArrayList;

public class Player {
    String name;
    String location;
    ArrayList<Pokemon> pokemons;
    ArrayList<String> Badges;
    public Player(String name,String location){
        this.name=name;
        this.location=location;
        pokemons=new ArrayList<>();
        Badges=new ArrayList<>();
    }
}

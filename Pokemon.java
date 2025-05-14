import java.util.ArrayList;

public class Pokemon {
    String pokemon_name;
    String type;
    int health;
    int level;
    String move1Name;
    int move1Damage;
    String move2Name;
    int move2Damage;
    ArrayList<String> weakness;
    ArrayList<String> strength;
    int xp;
    int xp_total;
    public Pokemon(String pokemon_name,String type,
                   int level,int xp){
        this.pokemon_name=pokemon_name;
        this.type=type;
        this.level=level;
        this.health=35+(5*(level-1));
        this.xp=xp;
        this.move1Name=null;
        this.move1Damage=0;
        this.move2Name=null;
        this.move2Damage=0;
        weakness=new ArrayList<>();
        strength=new ArrayList<>();
        if(this.level<=10)
            this.xp_total=100;
        else if(this.level<=30)
            this.xp_total=200;
        else
            this.xp_total=300;
    }
    public String toString(){
        String text="";
        text+="Pokemon Name "+pokemon_name+"\n";
        text+="Pokemon Type "+type+"\n";
        text+="Pokemon Health "+health+"\n";
        text+="Pokemon XP "+xp+"\n";
        text+="Pokemon Level "+level+"\n";
        text+="Pokemon Move1Name "+move1Name+"\n";
        text+="Pokemon Move1Damage "+move1Damage+"\n";
        text+="Pokemon Move2Name "+move2Name+"\n";
        text+="Pokemon Move2Damage "+move2Damage+"\n";
        text+="Pokemon Weakness "+weakness+"\n";
        text+="Pokemon Strength "+strength+"\n";
        return text;
    }
}

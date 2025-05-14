import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class CeladonCity {
    public CeladonCity(Player player) throws SQLException {
        player.location="Celadon City";
        System.out.println("\n\n\n+----------------------------------------------------------------------+\n" +
                "You are currently in Celadon City:\n" +
                "+----------------------------------------------------------------------+\n" +
                "[1] Move to:\n" +
                "a.Saffron City   b.Fuchsia City\n" +
                "[2] Challenge Gym leader [Erika-Grass Type]\n" +
                "[3] Fight Wild Pokemon [Oddish, Venusaur, Leafeon]\n" +
                "[4] Player Options\n" +
                "a.Show map   b.Show My Pokemon   c.Show My badges   d.Save and Exit\n" +
                "+----------------------------------------------------------------------+");
        System.out.print("Your choice: ");
        Scanner sc=new Scanner(System.in);
        String choice=sc.nextLine();
        if(choice.equals("1a")){
            new SaffronCity(player);
        }else if(choice.equals("1b")){
            new FuchsiaCity(player);
        }else if(choice.equals("2")){
            System.out.println("You are about to challenge Gym Leader Erika!");
            System.out.println("Prepare yourself for an intense battle!");
            System.out.println("Your Pokemons: ");
            for(int i=0;i<player.pokemons.size();i++){
                System.out.println("Pokemon "+(i+1));
                System.out.println(player.pokemons.get(i));
            }
            System.out.print("Choose your Pokemon: ");
            Scanner sc2=new Scanner(System.in);
            Pokemon myPokemon=player.pokemons.get(sc2.nextInt()-1);
            System.out.println("+----------------------------------------------------------------------+");
            Pokemon enemy=createLeaderPokemon("Grass",1);
            System.out.println("Enemy Pokemon: ");
            System.out.println(enemy);
            int bonusMove1=0;
            int bonusMove2=0;
            if(myPokemon.strength.contains(enemy.type)){
                System.out.println(myPokemon.pokemon_name+" is strong against "+enemy.pokemon_name+"("+enemy.type+")");
                bonusMove1=(int) (myPokemon.move1Damage*0.2);
                bonusMove2=(int) (myPokemon.move2Damage*0.2);
            }else if(myPokemon.weakness.contains(enemy.type)){
                System.out.println(myPokemon.pokemon_name+" is weak against "+enemy.pokemon_name+"("+enemy.type+")");
                bonusMove1=(int) (0-myPokemon.move1Damage*0.2);
                bonusMove2=(int) (0-myPokemon.move2Damage*0.2);
            }
            int enemyCurrenthealth= enemy.health;
            int myPokemonCurrentHealth=myPokemon.health;
            int round=1;
            while(enemyCurrenthealth>0 && myPokemonCurrentHealth>0){
                System.out.println("Round "+round+": ");
                System.out.println(myPokemon.pokemon_name+" Moves:");
                System.out.println("1. "+myPokemon.move1Name);
                System.out.println("2. "+myPokemon.move2Name);
                System.out.println("Which move will "+myPokemon.pokemon_name+" use?");
                System.out.print("Your choice: ");
                Scanner sc3=new Scanner(System.in);
                int Mymove=sc3.nextInt();
                System.out.println("+----------------------------------------------------------------------+");
                Random random=new Random();
                int oppMove = random.nextInt(2) + 1;
                if(Mymove==1){
                    System.out.println("You used "+myPokemon.move1Name);
                    enemyCurrenthealth-=(myPokemon.move1Damage+bonusMove1);
                    if(enemyCurrenthealth<=0)
                        break;
                }else{
                    System.out.println("You used "+myPokemon.move2Name);
                    enemyCurrenthealth-=(myPokemon.move2Damage+bonusMove2);
                    if(enemyCurrenthealth<=0)
                        break;
                }if(oppMove==1){
                    System.out.println("Opponent used "+enemy.move1Name);
                    myPokemonCurrentHealth-=(enemy.move1Damage);
                    if(myPokemonCurrentHealth<=0)
                        break;
                }else{
                    System.out.println("Opponent used "+enemy.move1Name);
                    myPokemonCurrentHealth-=(enemy.move1Damage);
                    if(myPokemonCurrentHealth<=0)
                        break;
                }
                System.out.println("Your health: "+myPokemonCurrentHealth+"/"+myPokemon.health);
                System.out.println("Enemy health: "+enemyCurrenthealth+"/"+enemy.health);
            }
            System.out.println("Your health: "+myPokemonCurrentHealth+"/"+myPokemon.health);
            System.out.println("Enemy health: "+enemyCurrenthealth+"/"+enemy.health);
            if(myPokemonCurrentHealth<=0)
                System.out.println("You lost");
            else{
                System.out.println("You won");
                myPokemon.xp+=(5*enemy.level);
                if(myPokemon.xp>=myPokemon.xp_total){
                    myPokemon.xp=myPokemon.xp-myPokemon.xp_total;
                    myPokemon.level++;
                    if(myPokemon.level<=10)
                        myPokemon.xp_total=100;
                    else if(myPokemon.level<=20)
                        myPokemon.xp_total=200;
                    else
                        myPokemon.xp_total=300;
                    myPokemon.health=35+(5*(myPokemon.level-1));
                    myPokemon.move1Damage=myPokemon.move1Damage+2;
                    myPokemon.move2Damage=myPokemon.move2Damage+2;
                }
                new BadgeFrame("Rainbow", "rainbow");
                player.Badges.add("Rainbow Badge");
                player.pokemons.add(enemy);
            }
            new CeladonCity(player);
        }else if(choice.equals("3")){
            System.out.println("You are about to fight a wild pokemon");
            System.out.println("Prepare yourself for an intense battle!");
            System.out.println("Your Pokemons: ");
            for(int i=0;i<player.pokemons.size();i++){
                System.out.println("Pokemon "+(i+1));
                System.out.println(player.pokemons.get(i));
            }
            System.out.print("Choose your Pokemon: ");
            Scanner sc2=new Scanner(System.in);
            Pokemon myPokemon=player.pokemons.get(sc2.nextInt()-1);
            Random random = new Random();
            int Number = random.nextInt(3) + 1;
            Pokemon enemy;
            if(Number==1){
                enemy=createPokemon("Oddish", myPokemon.level-1);
            }else if(Number==2){
                enemy=createPokemon("Venusaur", myPokemon.level-1);
            }else{
                enemy=createPokemon("Leafeon", myPokemon.level-1);
            }
            System.out.println("Wild Pokemon: ");
            System.out.println(enemy);
            int bonusMove1=0;
            int bonusMove2=0;
            if(myPokemon.strength.contains(enemy.type)){
                System.out.println(myPokemon.pokemon_name+" is strong against "+enemy.pokemon_name+"("+enemy.type+")");
                bonusMove1=(int) (myPokemon.move1Damage*0.2);
                bonusMove2=(int) (myPokemon.move2Damage*0.2);
            }else if(myPokemon.weakness.contains(enemy.type)){
                System.out.println(myPokemon.pokemon_name+" is weak against "+enemy.pokemon_name+"("+enemy.type+")");
                bonusMove1=(int) (0-myPokemon.move1Damage*0.2);
                bonusMove2=(int) (0-myPokemon.move2Damage*0.2);
            }
            int enemyCurrenthealth= enemy.health;
            int myPokemonCurrentHealth=myPokemon.health;
            int round=1;
            while(enemyCurrenthealth>0 && myPokemonCurrentHealth>0){
                System.out.println("Round "+round+": ");
                System.out.println(myPokemon.pokemon_name+" Moves:");
                System.out.println("1. "+myPokemon.move1Name);
                System.out.println("2. "+myPokemon.move2Name);
                System.out.println("Which move will "+myPokemon.pokemon_name+" use?");
                System.out.print("Your choice: ");
                Scanner sc3=new Scanner(System.in);
                int Mymove=sc3.nextInt();
                System.out.println("+----------------------------------------------------------------------+");
                int oppMove = random.nextInt(2) + 1;
                if(Mymove==1){
                    System.out.println("You used "+myPokemon.move1Name);
                    enemyCurrenthealth-=(myPokemon.move1Damage+bonusMove1);
                    if(enemyCurrenthealth<=0)
                        break;
                }else{
                    System.out.println("You used "+myPokemon.move2Name);
                    enemyCurrenthealth-=(myPokemon.move2Damage+bonusMove2);
                    if(enemyCurrenthealth<=0)
                        break;
                }if(oppMove==1){
                    System.out.println("Opponent used "+enemy.move1Name);
                    myPokemonCurrentHealth-=(enemy.move1Damage);
                    if(myPokemonCurrentHealth<=0)
                        break;
                }else{
                    System.out.println("Opponent used "+enemy.move1Name);
                    myPokemonCurrentHealth-=(enemy.move1Damage);
                    if(myPokemonCurrentHealth<=0)
                        break;
                }
                System.out.println("Your health: "+myPokemonCurrentHealth+"/"+myPokemon.health);
                System.out.println("Enemy health: "+enemyCurrenthealth+"/"+enemy.health);
            }
            System.out.println("Your health: "+myPokemonCurrentHealth+"/"+myPokemon.health);
            System.out.println("Enemy health: "+enemyCurrenthealth+"/"+enemy.health);
            if(myPokemonCurrentHealth<=0)
                System.out.println("You lost");
            else{
                System.out.println("You won");
                myPokemon.xp+=(5*enemy.level);
                if(myPokemon.xp>=myPokemon.xp_total){
                    myPokemon.xp=myPokemon.xp-myPokemon.xp_total;
                    myPokemon.level++;
                    if(myPokemon.level<=10)
                        myPokemon.xp_total=100;
                    else if(myPokemon.level<=20)
                        myPokemon.xp_total=200;
                    else
                        myPokemon.xp_total=300;
                    myPokemon.health=35+(5*(myPokemon.level-1));
                    myPokemon.move1Damage=myPokemon.move1Damage+2;
                    myPokemon.move2Damage=myPokemon.move2Damage+2;
                }
                player.pokemons.add(enemy);
            }
            new CeladonCity(player);
        }else if(choice.equals("4a")){
            System.out.println("Map of Kanto\n" +
                    "[Pewter City]--------------------[Cerulean City]--------------------|\n" +
                    "    |                                       |                       |\n" +
                    "    |                                       |                       |\n" +
                    "    |                                       |                       |\n" +
                    "    |                                       |                       |\n" +
                    "    |               [**Celadon City**]----[Saffron City]---------[Lavender Town]\n" +
                    "    |                       |               |                -            |\n" +
                    "[Viridian City]             |               |              -              |\n" +
                    "    |                       |               |           -                 |\n" +
                    "    |                       |               |        -                    |\n" +
                    "    |                       |       [Vermillion City]                     |\n" +
                    "    |                       |       -                                     |\n" +
                    "[Pallet Town]               |      -                                      |\n" +
                    "    |                       |     -                                       |\n" +
                    "    |                [Fuchsia City]---------------------------------------|\n" +
                    "    |                       |\n" +
                    "    |                       |\n" +
                    "[Cinnabar Island]-----------|");
            new CeladonCity(player);
        }else if(choice.equals("4b")){
            for(Pokemon x:player.pokemons)
                System.out.println(x);
            System.out.println("+----------------------------------------------------------------------+");
            new CeladonCity(player);
        }else if(choice.equals("4c")){
            System.out.println("Badges:  "+player.Badges);
            new CeladonCity(player);
        }else if(choice.equals("4d")){
            saveexit(player);
            System.exit(0);
        }else{
            System.out.println("Invalid input");
            new CeladonCity(player);
        }

    }
    public static void saveexit(Player player) throws SQLException {
        String url="jdbc:mysql://localhost:3306/pokemon";
        String username="root";
        String password="Jeeva7396@";
        Connection con= DriverManager.getConnection(url,username,password);
        Statement stmt=con.createStatement();
        con.setAutoCommit(false);
        String sql="UPDATE players SET Location=\""+player.location+"\" WHERE Player_Name=\""+player.name+"\"";
        stmt.executeUpdate(sql);
        for(String x:player.Badges){
            String sql2="INSERT INTO players_badges SELECT '"+player.name+"', '"+x+"' "+
                    "WHERE NOT EXISTS ("+
                    "SELECT 1 FROM players_badges WHERE Player_Name = '"+player.name+"' AND Badges = '"+x+"');";
            stmt.executeUpdate(sql2);
        }

        for(Pokemon pokemon:player.pokemons){
            String sql2="DELETE FROM players_pokemon where Player_Name='"+player.name+"' AND Pokemon_Name='"+pokemon.pokemon_name+"'";
            stmt.executeUpdate(sql2);
        }
        for(Pokemon pokemon:player.pokemons){
            String sql2="INSERT INTO players_pokemon VALUES ('"+pokemon.pokemon_name+"',"+pokemon.level+",'"+player.name+"',"+pokemon.xp+")";
            stmt.executeUpdate(sql2);
        }

        con.commit();
    }
    public static Pokemon createPokemon(String pokemon_name,int pokemon_level) throws SQLException {
        String sql="SELECT * FROM pokemon WHERE Pokemon_Name=\""+pokemon_name+"\"";
        String url="jdbc:mysql://localhost:3306/pokemon";
        String username="root";
        String password="Jeeva7396@";
        Connection con= DriverManager.getConnection(url,username,password);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(sql);
        rs.next();
        Pokemon pokemon=new Pokemon(pokemon_name,rs.getString(2),pokemon_level,0);
        sql="SELECT * FROM pokemon_moves WHERE Pokemon_Name=\""+pokemon.pokemon_name+"\"";
        rs=st.executeQuery(sql);
        rs.next();
        pokemon.move1Name=rs.getString(2);
        pokemon.move1Damage=rs.getInt(3)+(pokemon.level-1*2);
        rs.next();
        pokemon.move2Name=rs.getString(2);
        pokemon.move2Damage=rs.getInt(3)+(pokemon.level-1*2);
        sql="SELECT * FROM pokemon_strength WHERE Pokemon_Name=\""+pokemon.pokemon_name+"\"";
        rs=st.executeQuery(sql);
        while(rs.next()){
            pokemon.strength.add(rs.getString(2));
        }
        sql="SELECT * FROM pokemon_weakness WHERE Pokemon_Name=\""+pokemon.pokemon_name+"\"";
        rs=st.executeQuery(sql);
        while(rs.next()){
            pokemon.weakness.add(rs.getString(2));
        }
        con.close();
        rs.close();
        return pokemon;
    }
    public static Pokemon createLeaderPokemon(String pokemon_type,int pokemon_level) throws SQLException {
        String sql="SELECT * FROM pokemon WHERE Pokemon_type=\""+pokemon_type+"\""+" ORDER BY RAND() LIMIT 1";
        String url="jdbc:mysql://localhost:3306/pokemon";
        String username="root";
        String password="Jeeva7396@";
        Connection con= DriverManager.getConnection(url,username,password);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(sql);
        rs.next();
        Pokemon pokemon=new Pokemon(rs.getString(1),rs.getString(2),pokemon_level,0);
        sql="SELECT * FROM pokemon_moves WHERE Pokemon_Name=\""+pokemon.pokemon_name+"\"";
        rs=st.executeQuery(sql);
        rs.next();
        pokemon.move1Name=rs.getString(2);
        pokemon.move1Damage=rs.getInt(3)+(pokemon.level-1*2);
        rs.next();
        pokemon.move2Name=rs.getString(2);
        pokemon.move2Damage=rs.getInt(3)+(pokemon.level-1*2);
        sql="SELECT * FROM pokemon_strength WHERE Pokemon_Name=\""+pokemon.pokemon_name+"\"";
        rs=st.executeQuery(sql);
        while(rs.next()){
            pokemon.strength.add(rs.getString(2));
        }
        sql="SELECT * FROM pokemon_weakness WHERE Pokemon_Name=\""+pokemon.pokemon_name+"\"";
        rs=st.executeQuery(sql);
        while(rs.next()){
            pokemon.weakness.add(rs.getString(2));
        }
        con.close();
        rs.close();
        return pokemon;
    }
}
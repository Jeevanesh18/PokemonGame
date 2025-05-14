//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*System.out.println("   |---------| |---------| |        -- |--------- |--              --| |---------| |-        |   ");
        System.out.println("   |         | |         | |      --   |          |  --          --  | |         | | -       |");
        System.out.println("   |         | |         | |    --     |          |    --      --    | |         | |  -      |       ");
        System.out.println("   |---------| |         | |  --       |          |      --  --      | |         | |   -     |      ");
        System.out.println("   |           |         | |--         |-----     |        --        | |         | |    -    |       ");
        System.out.println("   |           |         | |  --       |          |                  | |         | |     -   |      ");
        System.out.println("   |           |         | |    --     |          |                  | |         | |      -  |  ");
        System.out.println("   |           |         | |      --   |          |                  | |         | |       - |   ");
        System.out.println("   |           |---------| |        -- |--------- |                  | |---------| |        -|      ");*/
        System.out.println("                                  ,'\\\n" +
                "    _.----.        ____         ,'  _\\   ___    ___     ____\n" +
                "_,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.\n" +
                "\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |\n" +
                " \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |\n" +
                "   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |\n" +
                "    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |\n" +
                "     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |\n" +
                "      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |\n" +
                "       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |\n" +
                "        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |\n" +
                "                                `'                            '-._|");
        System.out.println("<------------------------------------------------------------------------------------------------------------>");
        System.out.println("[1] Load Game: ");
        String[] names=queryPlayersName();
        System.out.println("a. Save 1 - "+names[0]+"        b. Save 2 - "+names[1]+"        c. Save 3 - "+names[2]);
        System.out.println("[2] Start a new Adventure: ");
        for(int i=0;i<3;i++){
            if(!names[i].equals("empty"))
               System.out.print((char)('a'+i)+". Save "+(i+1)+" - Override        ");
            else
                System.out.print((char)('a'+i)+". Save "+(i+1)+" - new        ");
        }
        System.out.println("\n[3] Exit");
        System.out.println("<------------------------------------------------------------------------------------------------------------>");
        System.out.print("Your choice: ");
        Scanner sc=new Scanner(System.in);
        String choice=sc.nextLine();
        System.out.println("<------------------------------------------------------------------------------------------------------------>");
        Player player=null;
        if(choice.equals("1a")){
                  player=queryLoadPlayer(names[0]);
                  LoadPokemons(player);
        }else if(choice.equals("1b")){
                  player=queryLoadPlayer(names[1]);
                  LoadPokemons(player);
        }else if(choice.equals("1c")){
                  player=queryLoadPlayer(names[2]);
                  LoadPokemons(player);
        }else if(choice.equals("2a")){
                 if(names[0].equals("empty")){
                     player=createNewPlayer();
                 }else{
                     deletePlayer(names[0]);
                     player=createNewPlayer();
                 }
        }else if(choice.equals("2b")){
            if(names[0].equals("empty")){
                player=createNewPlayer();
            }else{
                deletePlayer(names[1]);
                player=createNewPlayer();
            }
        }else if(choice.equals("2c")){
            if(names[0].equals("empty")){
                player=createNewPlayer();
            }else{
                deletePlayer(names[2]);
                player=createNewPlayer();
            }
        }else{
            System.exit(0);
        }
        new startgame(player);
    }
    public static String[] queryPlayersName() throws SQLException {
        String sql="SELECT * FROM players";
        String url="jdbc:mysql://localhost:3306/pokemon";
        String username="root";
        String password="Jeeva7396@";
        Connection con= DriverManager.getConnection(url,username,password);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(sql);
        String[] names=new String[3];
        for(int i=0;i<3;i++){
            if(rs.next()) {
                names[i] = rs.getString(1);
            }
            else
                names[i]="empty";
        }
        con.close();
        st.close();
        rs.close();
        return names;
    }
    public static Player queryLoadPlayer(String name) throws SQLException {
        String sql="SELECT players.Player_Name, players.Location, players_badges.Badges FROM players LEFT JOIN players_badges ON players.Player_Name = players_badges.Player_Name WHERE players.Player_Name =\""+name+"\"";
        String url="jdbc:mysql://localhost:3306/pokemon";
        String username="root";
        String password="Jeeva7396@";
        Connection con= DriverManager.getConnection(url,username,password);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(sql);
        rs.next();
        Player player=new Player(rs.getString(1),rs.getString(2));
        if(rs.getString(3)!=null)
               player.Badges.add(rs.getString(3));
        while(rs.next()){
            player.Badges.add(rs.getString(3));
        }
        con.close();
        return player;
    }
    public static void LoadPokemons(Player player) throws SQLException {
        String sql="SELECT * FROM players_pokemon WHERE Player_Name=\""+player.name+"\"";
        String url="jdbc:mysql://localhost:3306/pokemon";
        String username="root";
        String password="Jeeva7396@";
        Connection con= DriverManager.getConnection(url,username,password);
        Statement st=con.createStatement();
        Statement st2= con.createStatement();
        ResultSet rs=st.executeQuery(sql);
        String sql2;ResultSet rs2;
        while(rs.next()){
                String pokemon_name=rs.getString(1);
                int pokemon_level=rs.getInt(2);
                int pokemon_xp=rs.getInt(4);
                sql2="SELECT * FROM pokemon WHERE Pokemon_Name=\""+pokemon_name+"\"";
                rs2=st2.executeQuery(sql2);
                rs2.next();
                player.pokemons.add(new Pokemon(pokemon_name,rs2.getString(2),pokemon_level,pokemon_xp));
        }
        for(Pokemon pokemon:player.pokemons){
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
        }
        con.close();
    }
    public static Player createNewPlayer() throws SQLException {
        Scanner sc=new Scanner(System.in);
        System.out.println("OAK:      Hello there! Welcome to the world of Pokemon! My name is Oak!\n" +
                "People call me the Pokemon Prof! This world is inhabitated by creatures\n"+
                 "called Pokemon! For some people, Pokemon are pets. Others use them for\n " +
                "fights. Myself... I study Pokemon as a profession.");
        System.out.println("\nOAK:      First, what is your name? ");
        System.out.println("<------------------------------------------------------------------------------------------------------------>");
        System.out.print("Enter your name: ");
        String name=sc.nextLine();
        System.out.println("<------------------------------------------------------------------------------------------------------------>");
        System.out.println("OAK:      Right! So your name is Amaan! Welcome to the world of Pokemon.\n " +
                "It's time to choose your starting pokemon");
        System.out.println("<------------------------------------------------------------------------------------------------------------>");
        System.out.println("[1] Bulbasaur [Grass - Level 5]");
        System.out.println("[2] Squirtle [Water - Level 5]");
        System.out.println("[3] Charmander [Fire - Level 5]");
        System.out.println("<------------------------------------------------------------------------------------------------------------>");
        System.out.print("Your choice: ");
        int choice=sc.nextInt();
        System.out.println("<------------------------------------------------------------------------------------------------------------>");
        Player player=new Player(name,"Pallet Town");
        if(choice==1){
            player.pokemons.add(new Pokemon("Bulbasaur","Grass",5,0));
            pokemon_attributes_load(player.pokemons.get(0));
        }else if(choice==2){
            player.pokemons.add(new Pokemon("Squirtle","Water",5,0));
            pokemon_attributes_load(player.pokemons.get(0));
        }else{
            player.pokemons.add(new Pokemon("Charmander","Fire",5,0));
            pokemon_attributes_load(player.pokemons.get(0));
        }
        insertPlayerPokemon(player);
        return player;
    }
    public static void pokemon_attributes_load(Pokemon pokemon) throws SQLException {
        String sql="SELECT * FROM pokemon_moves WHERE Pokemon_Name=\""+pokemon.pokemon_name+"\"";
        String url="jdbc:mysql://localhost:3306/pokemon";
        String username="root";
        String password="Jeeva7396@";
        Connection con= DriverManager.getConnection(url,username,password);
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(sql);
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
    }
    public static void deletePlayer(String player_Name) throws SQLException {
        String sql="DELETE FROM players WHERE Player_Name=\""+player_Name+"\"";
        String url="jdbc:mysql://localhost:3306/pokemon";
        String username="root";
        String password="Jeeva7396@";
        Connection con= DriverManager.getConnection(url,username,password);
        Statement stmt=con.createStatement();
        con.setAutoCommit(false);
        stmt.executeUpdate(sql);
        con.commit();
    }
    public static void insertPlayerPokemon(Player player) throws SQLException {
        String sql="INSERT INTO players VALUES (\""+player.name+"\",\""+player.location+"\"); " ;
        String sql2="INSERT INTO players_pokemon VALUES (\""+player.pokemons.get(0).pokemon_name+"\","+player.pokemons.get(0).level+",\""+player.name+"\","+player.pokemons.get(0).xp+")";
        String url="jdbc:mysql://localhost:3306/pokemon";
        String username="root";
        String password="Jeeva7396@";
        Connection con= DriverManager.getConnection(url,username,password);
        Statement stmt=con.createStatement();
        con.setAutoCommit(false);
        stmt.executeUpdate(sql);
        stmt.executeUpdate(sql2);
        con.commit();
    }
}
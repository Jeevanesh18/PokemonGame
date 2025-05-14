import java.sql.SQLException;

public class startgame {
    public startgame(Player player) throws SQLException {
        if(player.location.equals("Pallet Town"))
            new PalletTown(player);
        else if(player.location.equals("Celadon City"))
            new CeladonCity(player);
        else if(player.location.equals("Cerulean City"))
            new CeruleanCity(player);
        else if(player.location.equals("Cinnabar Island"))
            new CinnabarIsland(player);
        else if(player.location.equals("Fuchsia City"))
            new FuchsiaCity(player);
        else if(player.location.equals("Lavender Town"))
            new LavenderTown(player);
        else if(player.location.equals("Pewter City"))
            new PewterCity(player);
        else if(player.location.equals("Saffron City"))
            new SaffronCity(player);
        else if(player.location.equals("Vermillion City"))
            new VermillionCity(player);
        else if(player.location.equals("Viridian City"))
            new ViridianCity(player);
    }
}

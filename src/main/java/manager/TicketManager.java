package manager;

import model.Player;
import repository.dao.*;

public class TicketManager {

    private final TicketDAO dao = new TicketDAOSQL();

    public void listTicketsByPlayerId(int playerId) {
        PlayerDAO daoPlayer = new PlayerDAOSQL();
        ScapeRoomDAO daoScape = new ScapeRoomDAOSQL();
        Player player = daoPlayer.findById(playerId);

        if (player == null) {
            System.out.println("Player not found.");
            return;
        }

        System.out.printf("%-10s %-20s %-30s%n", "Ticket ID", "Player Name", "ScapeRoom Name");
        System.out.println("─".repeat(60)); // Adjusted separator length

        dao.findByPlayerId(playerId).forEach(ticket -> {
            String scapeRoomName = daoScape.findById(ticket.getScapeRoomId()).getName();

            System.out.printf("%-10d %-20s %-30s%n",
                    ticket.getId(),
                    player.getName(),
                    scapeRoomName
            );
        });
        System.out.println("-".repeat(60)); // footer separator

    }

}

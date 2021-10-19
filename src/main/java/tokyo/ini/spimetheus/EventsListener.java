package tokyo.ini.spimetheus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventsListener implements Listener, CommandExecutor {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Exporter.incNumOfPlayers();
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        Exporter.decNumOfPlayers();
    }

    // command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("spi")) {
            if(args[0].equals("sync")){
                Exporter.setNumOfPlayers(sender.getServer()
                        .getOnlinePlayers()
                        .size());
            }
            return true;
        }
        return false;
    }
}

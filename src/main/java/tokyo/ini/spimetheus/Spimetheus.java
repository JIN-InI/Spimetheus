package tokyo.ini.spimetheus;

import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public final class Spimetheus extends JavaPlugin{


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventsListener(), this);
        Exporter exporter = Exporter.getInstance();
        updater();
    }

    @Override
    public void onDisable() {
    }

    private void updater() {
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            double[] TPS = MinecraftServer.getServer().recentTps;
            Exporter.setTps(TPS);

            int numOfEntities = 0;
            for(World world : getServer().getWorlds()){
                numOfEntities += world.getEntities().size();
            }
            Exporter.setNumOfEntities(numOfEntities);

            int loadedChunks = 0;
            for(World world: getServer().getWorlds()){
                loadedChunks += world.getLoadedChunks().length;
            }
            Exporter.setLoadedChunks(loadedChunks);
        },0,200);
    }

}

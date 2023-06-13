package Manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationManager {
    private File configFile;
    private FileConfiguration config;

    public LocationManager() {
        configFile = new File(Bukkit.getPluginsFolder().getPath() + "locations.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void createConfigFileIfNeeded() {
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addLocation(String locationName, World world, Double x, Double y, Double z, Float yaw, Float pitch) {
        config.set(locationName + ".world", world.getName());
        config.set(locationName + ".x", x);
        config.set(locationName + ".y", y);
        config.set(locationName + ".z", z);
        config.set(locationName + ".yaw", yaw);
        config.set(locationName + ".pitch", pitch);

        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Location getLocation(String locationName) {
        if (config.contains(locationName)) {
            String worldName = config.getString(locationName + ".world");
            double x = config.getDouble(locationName + ".x");
            double y = config.getDouble(locationName + ".y");
            double z = config.getDouble(locationName + ".z");
            float yaw = (float) config.getDouble(locationName + ".yaw");
            float pitch = (float) config.getDouble(locationName + ".pitch");

            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                return new Location(world, x, y, z, yaw, pitch);
            }
        }
        return null; // Location nicht gefunden oder ungültig
    }
}
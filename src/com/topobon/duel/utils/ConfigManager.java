package com.topobon.duel.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import com.topobon.duel.DuelNRTN;


import java.io.File;
import java.io.IOException;

public class ConfigManager {

    DuelNRTN instance;
    public FileConfiguration fc;
    public File file;
    public int arenaID;

    
    public ConfigManager(DuelNRTN instance, Integer arenaID) {
   
        this.arenaID = arenaID;
        this.instance = instance;
    }



    /**
     * Gets the owner of the config
     *
     * @return The player as type bukkit.entity.Player
     */
    public Integer getArenaID() {
        return arenaID;
    }


   /**
     * Deletes the file
     *
     * @return True if the config was successfully deleted. If anything went
     *         wrong it returns false
     */
    public boolean delete() {
        return getFile().delete();
    }

    /**
     * Checks to make sure the config is null or not. This is only a check and
     * it wont create the config.
     *
     * @return True if it exists & False if it doesn't
     */
    public boolean exists() {
        if (fc == null || file == null) {
            File temp = new File(getDataFolder(), getArenaID() + ".yml");
            if (!temp.exists()) {
                return false;
            } else {
                file = temp;
            }
        }
        return true;
    }



    /**
     * Gets the plugin's folder. If none exists it will create it.
     *
     * @return The folder as type java.io.File
     */
    public File getDataFolder() {
        File dir = new File(ConfigManager.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " "));
        File d = new File(dir.getParentFile().getPath(), instance.getName());
        if (!d.exists()) {
            d.mkdirs();
        }
        return d;
    }

    /**
     * Gets the File for the owner. If none exists it will create it.
     *
     * @return The File as type java.io.File
     */
    public File getFile() {
        if (file == null) {
            file = new File(getDataFolder(), getArenaID() + ".yml");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    /**
     * Gets the config for the owner. If none exists it will create it.
     *
     * @return The config as type
     *         org.bukkit.configuration.file.FileConfiguration
     */
    public FileConfiguration getConfig() {
        if (fc == null) {
            fc = YamlConfiguration.loadConfiguration(getFile());
        }
        return fc;
    }

    /**
     * Reloads or "Gets" the file and config
     */
    public void reload() {
        if (file == null) {
            file = new File(getDataFolder(), getArenaID()+".yml");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fc = YamlConfiguration.loadConfiguration(file);
        }
    }

    /**
     * Deletes then creates the config
     */
    public void resetConfig() {
        delete();
        getConfig();
    }

    /**
     * Saves the config
     */
    public void saveConfig() {
        try {
            getConfig().save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
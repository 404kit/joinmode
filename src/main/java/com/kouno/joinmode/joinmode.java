package com.kouno.joinmode;

import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class joinmode extends JavaPlugin implements Listener {

    private GameMode defaultGameMode;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    private void loadConfig() {
        FileConfiguration config = getConfig();
        String defaultGameModeName = config.getString("defaultGameMode", "SURVIVAL");
        if (defaultGameModeName != null) {
            defaultGameMode = GameMode.valueOf(defaultGameModeName.toUpperCase());
        } else {
            defaultGameMode = GameMode.SURVIVAL;
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        GameMode gameMode = defaultGameMode;
        if (getConfig().contains("players." + event.getPlayer().getName())) {
            String playerGameModeName = getConfig().getString("players." + event.getPlayer().getName());
            if (playerGameModeName != null) {
                gameMode = GameMode.valueOf(playerGameModeName.toUpperCase());
            }
        }
        event.getPlayer().setGameMode(gameMode);
    }

}
//config.ymlファイルに「defaultGameMode」という設定値を追加し、その値に「ADVENTURE」、「CREATIVE」または「SURVIVAL」を設定することができます。
//また、プレイヤーごとに異なるゲームモードを設定することもできます。config.ymlファイルに「players.<プレイヤー名>」というキーを追加し、その値に「ADVENTURE」、「CREATIVE」または「SURVIVAL」を設定することで、特定のプレイヤーのゲームモードを設定できます。
package moe.caa.paper.chatsessionblocker.main;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatSessionBlocker extends JavaPlugin {

    @Override
    public void onEnable() {
        ProtocolLibrary.getProtocolManager().addPacketListener(
                new PacketAdapter(PacketAdapter.params()
                        .plugin(this)
                        .clientSide()
                        .types(PacketType.Play.Client.CHAT_SESSION_UPDATE)) {
                    @Override
                    public void onPacketReceiving(PacketEvent event) {
                        event.setReadOnly(false);
                        event.setCancelled(true);
                        getLogger().info("Packet ChatSessionUpdate from client " + event.getPlayer().getName() + " has been blocked.");
                    }
                }
        );
    }
}

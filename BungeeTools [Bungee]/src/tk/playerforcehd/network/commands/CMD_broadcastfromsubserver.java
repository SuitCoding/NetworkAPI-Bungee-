package tk.playerforcehd.network.commands;

import java.io.DataInputStream;
import java.io.IOException;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import tk.playerforcehd.network.api.NetworkCommand;

public class CMD_broadcastfromsubserver extends NetworkCommand {
    
	/**
	 * Set what the cmd is
	 */
	public CMD_broadcastfromsubserver() {
        super("broadcast");
    }

	/**
	 * What should be do when the command get executed
	 */
    @Override
    public void onRun(DataInputStream dataInputStream) {
        String msg = "";

        try {
            msg = dataInputStream.readUTF();
        } catch (IOException e) {
            ;
        }

        if(!msg.startsWith("Permission:")) {
            ProxyServer.getInstance().broadcast(new TextComponent(msg));
        } else {
            String permission = "";
            String message = "";

            try {
                permission = msg.replace("Permission:", "");
                message = dataInputStream.readUTF();
            } catch (IOException e1) {
                ;
            }

            for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()){
            	if(all.hasPermission(permission)) {
                    all.sendMessage(new TextComponent(message));
                }
            }
        }

    }
}

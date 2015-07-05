package tk.playerforcehd.network.commands;

import java.io.DataInputStream;
import java.io.IOException;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import tk.playerforcehd.network.api.NetworkCommand;
import tk.playerforcehd.network.packets.pluginmessage.MSG_sendCommand;

public class CMD_executecommandanywhere extends NetworkCommand {
    
	/**
	 * Set what the cmd is
	 */
	public CMD_executecommandanywhere() {
        super("executeCommand");
    }

	/**
	 * What should be do when the command get executed
	 */
    @Override
    public void onRun(DataInputStream dataInputStream) {
        String toSplit = "";
        ProxyServer.getInstance().broadcast(new TextComponent("§4Command came in"));

        try {
            toSplit = dataInputStream.readUTF();
        } catch (IOException var6) {
            ;
        }

        String[] compos = toSplit.split(":");
        if(compos[0].equalsIgnoreCase("bungee")) {
            ProxyServer.getInstance().getConsole().sendMessage(new TextComponent(compos[1]));
        } else if(compos[0].startsWith("server_")) {
            String p = compos[0].replace("server_", "").replace("Server_", "");
            ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(p);
            MSG_sendCommand.sendCommandToServer(serverInfo, compos[1]);
        } else {
            ProxiedPlayer p1 = ProxyServer.getInstance().getPlayer(compos[0]);
            if(p1 == null) {
                return;
            }

            MSG_sendCommand.sendCommandToPlayer(p1, compos[1]);
        }

    }
}

package tk.playerforcehd.network.packets.pluginmessage;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MSG_sendCommand {
    
	/**
	 * Create a new instance to execute the methods
	 */
	public MSG_sendCommand() {
    }

	/**
	 * Let's a player execute a command on his subserver
	 * @param player the player who execute it
	 * @param command the command which get executed
	 */
    public static void sendCommandToPlayer(ProxiedPlayer player, String command) {
        ByteArrayDataOutput stream = ByteStreams.newDataOutput();
        stream.writeUTF("executeCommand");
        stream.writeUTF(player.getName() + ":" + command);
        player.sendData("BungeeCord", stream.toByteArray());
    }

    /**
     * Let's a server execute a command in his console
     * @param server the server who executes it
     * @param command the command which get executed
     */
    public static void sendCommandToServer(ServerInfo server, String command) {
        ByteArrayDataOutput stream = ByteStreams.newDataOutput();
        stream.writeUTF("executeCommand");
        stream.writeUTF("Server:" + command);
        server.sendData("BungeeCord", stream.toByteArray());
    }
}

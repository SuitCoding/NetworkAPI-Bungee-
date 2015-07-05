package tk.playerforcehd.network.connection;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import tk.playerforcehd.network.Main;

public class CustomChannelCommands implements Listener {
    
	/**
	 * The main instance of the plugin
	 */
	private Main thePlugin;

	/**
	 * Creates a new instance of this
	 * @param thePlugin the main instance of the API
	 */
    public CustomChannelCommands(Main thePlugin) {
        this.thePlugin = thePlugin;
        this.thePlugin.getProxy();
        ProxyServer.getInstance().getPluginManager().registerListener(this.thePlugin, this);
    }

    /**
     * Checks for Plugin Messages and executes call the method which test for commands
     * @param e the Event
     */
    @EventHandler
    public void onPluginMessageRecieved(PluginMessageEvent e) {
        DataInputStream stream = new DataInputStream(new ByteArrayInputStream(e.getData()));

        try {
            this.thePlugin.getCommandManager().exec(stream.readUTF(), stream);
        } catch (IOException e1) {
        }

    }
}

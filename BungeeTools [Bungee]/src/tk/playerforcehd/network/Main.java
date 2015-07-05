package tk.playerforcehd.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import tk.playerforcehd.network.api.NetworkAPI;
import tk.playerforcehd.network.api.NetworkCommand;
import tk.playerforcehd.network.commands.CMD_broadcastfromsubserver;
import tk.playerforcehd.network.connection.CustomChannelCommands;
import tk.playerforcehd.network.connection.NetworkCommandManager;
import tk.playerforcehd.network.connection.NetworkManager;

public class Main extends Plugin {

	/**
	 * The main instance of the plugin
	 */
	@SuppressWarnings("unused")
	private Main thePlugin;
	
	/**
	 * The API
	 */
	@SuppressWarnings("unused")
	private NetworkAPI theAPI;
	
	/**
	 * The Logger of the Plugin
	 */
    private Logger theLogger;
    
    /**
     * A list of all network managers
     */
    private Collection<NetworkManager> theNetworkManagers;
    
    /**
     * A list of all registred commands
     */
    private Collection<NetworkCommand> theCommands;
    
    /**
     * A command manager which manages the commands
     */
    private NetworkCommandManager commandManager;
    
    /**
     * A listener to recieve NetworkCommands
     */
	@SuppressWarnings("unused")
	private CustomChannelCommands customChannelCommands;

	/**
	 * Get called when the Plugin gets enabled!
	 */
	@Override
    public void onEnable() {
        this.thePlugin = this;
        this.theLogger = this.getLogger();
        this.theLogger.log(Level.INFO, "Please make sure that you have installed NetworkAPI on all your Bukkit/Spigot Servers!");
        this.theLogger.log(Level.INFO, "Registring Channel: BungeeCord...");
        this.getProxy();
        ProxyServer.getInstance().registerChannel("BungeeCord");
        this.theLogger.log(Level.INFO, "Registring Channel: NetworkAPI...");
        this.getProxy();
        ProxyServer.getInstance().registerChannel("NetworkAPI");
        this.theLogger.log(Level.INFO, "Registring API...");
        this.commandManager = new NetworkCommandManager(this);
        this.customChannelCommands = new CustomChannelCommands(this);
        this.theNetworkManagers = new ArrayList<NetworkManager>();
        this.theCommands = new ArrayList<NetworkCommand>();
        this.theAPI = new NetworkAPI(this);
        this.theLogger.log(Level.INFO, "Done!");
        this.registerInternalCommands();
        this.theLogger.log(Level.INFO, "Enabled...");
    }

	/**
	 * Get called when the plugin get disabled
	 */
	@Override
    public void onDisable() {
        this.theLogger.log(Level.INFO, "Disabled...");
    }

	/**
	 * Returns the logger of the plugin
	 * @return the logger
	 */
    public Logger getTheLogger() {
        return this.theLogger;
    }

    /**
     * Returns all registred Networkmanagers
     * @return all registred Networkmanagers
     */
    public Collection<NetworkManager> getTheNetworkManagers() {
        return this.theNetworkManagers;
    }

    /**
     * Returns all registred NetworkCommands
     * @return all registred NetworkCommands
     */
    public Collection<NetworkCommand> getTheCommands() {
        return this.theCommands;
    }

    /**
     * Returs the NetworkCommandManager
     * @return the NetworkCommandManager
     */
    public NetworkCommandManager getCommandManager() {
        return this.commandManager;
    }

    /**
     * Registers internal commands
     */
    private void registerInternalCommands() {
        this.getCommandManager().addCommand(new CMD_broadcastfromsubserver());
    }
}

package tk.playerforcehd.network.api;

import java.util.Collection;
import net.md_5.bungee.api.plugin.Listener;
import tk.playerforcehd.network.Main;
import tk.playerforcehd.network.api.NetworkCommand;
import tk.playerforcehd.network.connection.NetworkCommandManager;
import tk.playerforcehd.network.connection.NetworkManager;

public class NetworkAPI implements Listener {
    
	/**
	 * The main instance of the plugin
	 */
	private Main thePlugin;
	
	/**
	 * This instance of the API
	 */
    private static NetworkAPI networkAPI;

    /**
     * Creates a new API instance
     * @param thePlugin
     */
    public NetworkAPI(Main thePlugin) {
        networkAPI = this;
        this.thePlugin = thePlugin;
    }

    /**
     * Returns the API
     * @return the API
     */
    public static NetworkAPI getNetworkAPI() {
        return networkAPI;
    }

    /**
     * Returns a collection of all registred NetworkManagers
     * @return a collection of all registred NetworkManagers
     */
    public Collection<NetworkManager> getManagers() {
        return this.thePlugin.getTheNetworkManagers();
    }

    /**
     * Returns the networkcommandmanager
     * @return the networkcommandmanager
     */
    private NetworkCommandManager getNetworkCommandManager() {
        return this.thePlugin.getCommandManager();
    }

    /**
     * Register a new NetworkManager
     * @param name the name of the manager
     * @param channel the channel on what it is listen
     * @return the new NetworkManager
     * @throws Exception should not be thrown
     */
    public NetworkManager addNetworkManager(String name, String channel) throws Exception {
        NetworkManager manager = new NetworkManager(name, channel, this, this.thePlugin);
        this.thePlugin.getTheNetworkManagers().add(manager);
        return manager;
    }

    /**
     * Add a new NetworkCommand
     * @param networkCommand you command to add
     */
    public void addNetworkCommand(NetworkCommand networkCommand) {
        this.getNetworkCommandManager().addCommand(networkCommand);
    }

    /**
     * Remove a NetworkCommand
     * @param networkCommand your command to remove
     */
    public void removeNetworkCommand(NetworkCommand networkCommand) {
        this.getNetworkCommandManager().removeCommand(networkCommand);
    }
}

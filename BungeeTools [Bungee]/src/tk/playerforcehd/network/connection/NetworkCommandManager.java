package tk.playerforcehd.network.connection;

import java.io.DataInputStream;

import tk.playerforcehd.network.Main;
import tk.playerforcehd.network.api.NetworkCommand;

public class NetworkCommandManager {
    
	/**
	 * The main instance of the plugin
	 */
	private Main thePlugin;

	/**
	 * Creates a new instance of this
	 * @param thePlugin the main instance of the plugin
	 */
    public NetworkCommandManager(Main thePlugin) {
        this.thePlugin = thePlugin;
    }

    /**
     * Register a new command
     * @param networkCommand the command to register
     */
    public void addCommand(NetworkCommand networkCommand) {
        this.thePlugin.getTheCommands().add(networkCommand);
    }

    /**
     * Remove a registred command
     * @param networkCommand the command to rmeove
     */
    public void removeCommand(NetworkCommand networkCommand) {
        this.thePlugin.getTheCommands().remove(networkCommand);
    }
    
    /**
     * Get called if a plugin message cames in
     * @param subchannel the channel or in this case the "command"
     * @param dataInputStream the stream which can be read
     */
    public void exec(String subchannel, DataInputStream dataInputStream) {
        for(NetworkCommand command : this.thePlugin.getTheCommands()){
        	if(command.getSubchannel().equalsIgnoreCase(subchannel)) {
                command.execute(dataInputStream);
                break;
            }
        }
    }
}

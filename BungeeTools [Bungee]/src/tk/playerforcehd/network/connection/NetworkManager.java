package tk.playerforcehd.network.connection;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Collection;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import tk.playerforcehd.network.Main;
import tk.playerforcehd.network.api.NetworkAPI;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class NetworkManager implements Listener {
    
	/**
	 * This manager
	 */
	private NetworkManager thisManager = this;
	
	/**
	 * The api instance
	 */
    @SuppressWarnings("unused")
	private NetworkAPI theAPI;
    
    /**
     * The main instance of the plugin
     */
    private Main thePlugin;
    
    /**
     * The channel where this manager is listen
     */
    private String channel;
    
    /**
     * The name of this manager
     */
    @SuppressWarnings("unused")
	private String name;

    /**
     * Creates a new manager to handle plugin messaging
     * @param name the name of this manager
     * @param channel the channel where this manager is listen
     * @param api the API instance
     * @param thePlugin the main instance of the plugin
     * @throws Exception should not get thrown
     */
    public NetworkManager(String name, String channel, NetworkAPI api, Main thePlugin) throws Exception {
        this.theAPI = api;
        this.thePlugin = thePlugin;
        this.channel = channel;
        this.name = name;
        this.thePlugin.getTheNetworkManagers().add(this.thisManager);
        this.thePlugin.getProxy();
        ProxyServer.getInstance().registerChannel(channel);
        this.thePlugin.getProxy();
        ProxyServer.getInstance().getPluginManager().registerListener(this.thePlugin, this);
    }

    /**
     * Send a plugin message to a speciefied player
     * @param proxiedPlayer the player who recieve it
     * @param subchannel the subchannel or "command"
     * @param messages the messages of the plugin message
     */
    public void sendMessageToPlayer(ProxiedPlayer proxiedPlayer, String subchannel, String... messages) {
    	ByteArrayDataOutput stream = ByteStreams.newDataOutput();
        stream.writeUTF(subchannel);
        for (String write : messages) {
          stream.writeUTF(write);
        }
        proxiedPlayer.sendData(this.channel, stream.toByteArray());
    }

    /**
     * Send a plugin message a speciefied server
     * @param server the server who recieve it
     * @param subchannel the subchannel or "command"
     * @param messages the messages of the plugin message
     * @throws Exception should not get thrown
     */
    public void sendMessageToServer(ServerInfo server, String subchannel, String... messages) throws Exception {
    	ByteArrayDataOutput stream = ByteStreams.newDataOutput();
        stream.writeUTF(subchannel);
        for (String write : messages) {
          stream.writeUTF(write);
        }
        server.sendData(this.channel, stream.toByteArray());
    }

    /**
     * Send a plugin message to all servers in the network
     * @param subchannel the subchannel or "command"
     * @param messages the messages of the plugin message
     * @throws Exception should not get thrown
     */
    public void broadcastPluginMessage(String subchannel, String... messages) throws Exception {
    	ByteArrayDataOutput stream = ByteStreams.newDataOutput();
        stream.writeUTF(subchannel);
        for (String write : messages) {
          stream.writeUTF(write);
        }
        Collection<ServerInfo> servers = this.thePlugin.getProxy().getServers().values();
        for (ServerInfo currentServer : servers) {
          currentServer.sendData(this.channel, stream.toByteArray());
        }
    }

    /**
     * Get called if a message cames in
     * @param e the event
     */
    @EventHandler
    public void onRecieveMessage(PluginMessageEvent e) {
        if(e.getTag().equalsIgnoreCase(this.channel)) {
            this.onRecieve(e.getSender(), e.getReceiver(), e.getData());
        }

    }

    /**
     * Do anything with the incomming message
     * @param sender the sender of the message
     * @param reciever the reciever of the message
     * @param data the messages
     */
    public void onRecieve(Connection sender, Connection reciever, byte[] data) {}

    /**
     * Create a DataInputStream by give a byte[] 
     * @param data the data which get converted into a DataInputStream
     * @return a new DataInputStream
     */
    public DataInputStream createInputStream(byte[] data) {
        return new DataInputStream(new ByteArrayInputStream(data));
    }

    
    /**
     * Get the next String from your DataInputStream
     * @param dataInputStream the input stream which get read
     * @return the next string from the stream
     * @throws IOException if anything went wrong 
     */
    public String readNextString(DataInputStream dataInputStream) throws IOException {
        return dataInputStream.readUTF();
    }

    /**
     * Get the next Int from your DataInputStream
     * @param dataInputStream the input stream which get read
     * @return the next int from the stream
     * @throws IOException if anything went wrong 
     */
    public int readNextInt(DataInputStream dataInputStream) throws IOException {
        return dataInputStream.readInt();
    }

    /**
     * Get the next boolean from your DataInputStream
     * @param dataInputStream the input stream which get read
     * @return the next boolean from the stream
     * @throws IOException if anything went wrong 
     */
    public boolean readNextBoolean(DataInputStream dataInputStream) throws IOException {
        return dataInputStream.readBoolean();
    }
}

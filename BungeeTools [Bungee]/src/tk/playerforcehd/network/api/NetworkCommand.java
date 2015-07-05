package tk.playerforcehd.network.api;

import java.io.DataInputStream;
import java.io.IOException;
import tk.playerforcehd.network.interfaces.ICommand;

public abstract class NetworkCommand implements ICommand {
    
	/**
	 * This is that what is called cmd in bukkit or bungee
	 */
	private String subchannel;
	
	/**
	 * The stream of the incomming bytes!
	 */
    private DataInputStream dataInputStream;

    /**
     * Creates a new Command
     * @param subchannel how the cmd is named
     */
    public NetworkCommand(String subchannel) {
        this.subchannel = subchannel;
    }

    /**
     * Returns the subchannel (cmd)
     * @return the subchannel (cmd)
     */
    public String getSubchannel() {
        return this.subchannel;
    }

    /**
     * Returns the DataInputStream
     * @return the DataInputStream
     */
    public DataInputStream getDataInputStream() {
        return this.dataInputStream;
    }

    /**
     * Sets the DataInputStream and runs the command
     * @param dataInputStream <
     */
    public void execute(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
        this.onRun(dataInputStream);
    }

    /**
     * This is the method to exyecute you command
     * @param stream the stream which get injected by the execute();
     */
    public abstract void onRun(DataInputStream stream);

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

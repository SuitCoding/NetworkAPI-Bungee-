package tk.playerforcehd.network.interfaces;

import java.io.DataInputStream;

public interface ICommand {
	
	/**
	 * A Interface method for the NetworkCommand class
	 * @param dataInputStream the incomming input stream
	 */
	public static void onRun(DataInputStream dataInputStream){}

}

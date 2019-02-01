package team.hdt.hrag;

/**
 * <em><b>Copyright (c) 2019 The Zerra Team.</b></em> <br>
 * </br>
 * The main starting point for the game.
 * 
 * @author Ocelot5836
 */
public class ClientLaunch
{
	/**
	 * Entry point for the game client.
	 * 
	 * @param args The arguments passed into the client.
	 */
	public static void main(String[] args)
	{
		new Thread(new TestRun(), "Client").start();
	}
}
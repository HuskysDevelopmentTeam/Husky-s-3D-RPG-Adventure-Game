package team.hdt.hrag;

import org.lwjgl.opengl.GL11;

public class TestRun extends Engine {

    private static TestRun instance;

    private boolean running;

    public TestRun()
    {
        super();
        instance = this;

        // TODO: Remove these later when we're happy the console colours work and look good
        // XXX AndrewAlfazy : ECLIPSE IS $H!T WITH IT
        LOGGER.fatal("Fatal test!");
        LOGGER.error("Error test!");
        LOGGER.warn("Warn test!");
        LOGGER.info("Info test!");
        LOGGER.debug("Debug test!");
        LOGGER.trace("Trace test!");
    }

    /**
     * Sets the game's running status to true.
     */
    @Override
    public synchronized void start()
    {
        if (this.running)
            return;

        LOGGER.info("Starting...");
        this.running = true;
    }

    /**
     * Shuts down the internal server and stops the game loop.
     */
    @Override
    public synchronized void stop()
    {
        if (!this.running)
            return;

        LOGGER.info("Stopping...");
        this.running = false;

        super.stop();
    }

    // TODO improve loop
    @Override
    public void run()
    {
        try
        {
            this.init();

            while (this.running)
            {
                checkRequestedExit();

                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            }
            this.cleanupResources();
        }
        catch (Exception e)
        {
            /*CrashReporter.error(e);
            this.cleanupResources();
            System.exit(CrashCodes.CRASH);*/
        }
    }

    /**
     * Initializes the game, including setting up the display, renderers, and input handlers.
     */
    @Override
    protected void init()
    {
        super.init();
        Display.createDisplay("Husky's 3D Voxel RPG Game v0.0.1", 700, 500);
        Display.setIcon(new Identifier("icons/16.png"), new Identifier("icons/32.png"));
    }

    @Override
    public boolean isClient()
    {
        return true;
    }

    /**
     * Checks whether or not a request to close the display has been made.
     */
    private void checkRequestedExit()
    {
        if (!Display.isCloseRequested())
            Display.update();
        else
            this.stop();
    }

    /**
     * Cleans up the resources when the game closes.
     */
    public void cleanupResources()
    {
        long startTime = System.currentTimeMillis();
        Display.destroy();
        this.pool.shutdown();
        instance = null;
//        logger().info("Cleaned up all resources in " + MiscUtils.secondsSinceTime(startTime));
    }

    /**
     * @return The client instance
     */
    public static TestRun getInstance()
    {
        return instance;
    }

    /**
     * @return Whether the game is running or not
     */
    public boolean isRunning()
    {
        return running;
    }

}
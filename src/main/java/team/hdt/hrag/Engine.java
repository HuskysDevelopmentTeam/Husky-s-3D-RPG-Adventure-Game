package team.hdt.hrag;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Engine implements Runnable {

    protected static final Logger LOGGER = LogManager.getLogger("Test");

    protected ExecutorService pool;
    protected ExecutorService single;
    protected boolean running;

    public Engine() {
        this.pool = Executors.newCachedThreadPool(r -> new Thread(r, getClass().getSimpleName()));
        this.single = Executors.newSingleThreadExecutor(r -> new Thread(r, getClass().getSimpleName() + "_Messages"));
    }

    public void run()
    {
    }

    public synchronized void start()
    {
    }

    public synchronized void stop()
    {
        pool.shutdown();
        single.shutdown();
    }

    protected void init()
    {
        this.start();
    }

    public abstract boolean isClient();

    public static Logger logger()
    {
        return LOGGER;
    }

    /**
     * Schedules a new task.
     *
     * @param runnable - The task to schedule.
     */
    public void schedule(Runnable runnable)
    {
        Validate.notNull(runnable);
        this.pool.execute(runnable);
    }


}

package ua.com.otpbank.tools;

/**
 * Created by Igor on 11.05.2015.
 */
public class Counter {
    protected Counter(){};

    private static volatile Counter instance;

    public static Counter getInstance() {
        Counter localInstance = instance;
        if (localInstance == null) {
            synchronized (Counter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Counter();
                }
            }
        }
        return localInstance;
    }

    private Integer counter=1;


    public synchronized void setCounter(Integer counter) {
        this.counter = counter;
    }

    public synchronized Integer getCounter() {
        return counter;
    }

    public synchronized void setNext() {
        setCounter(getCounter()+1);
    }
}

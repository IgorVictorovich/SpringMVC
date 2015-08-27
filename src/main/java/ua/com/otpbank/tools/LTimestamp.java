package ua.com.otpbank.tools;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Igor on 11.05.2015.
 */
public class LTimestamp{
    protected LTimestamp(){};

    private static volatile LTimestamp instance;

    public static LTimestamp getInstance() {
        LTimestamp localInstance = instance;
        if (localInstance == null) {
            synchronized (LTimestamp.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new LTimestamp();
                }
            }
        }
        return localInstance;
    }

    private Date timestamp;

    private synchronized void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public synchronized Date getTimestamp() {
        if(timestamp==null)
            return getNextTimestamp();
        return timestamp;
    }

    public synchronized Date getNextTimestamp(){
        setTimestamp(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        return getTimestamp();
    }
}

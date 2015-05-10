package ua.com.otpbank.tools;

import java.util.UUID;

/**
 * Created by Igor on 09.05.2015.
 */
public class UUIDgenerator {
    protected UUIDgenerator(){};

    private static volatile UUIDgenerator instance;

    public static UUIDgenerator getInstance() {
        UUIDgenerator localInstance = instance;
        if (localInstance == null) {
            synchronized (UUIDgenerator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new UUIDgenerator();
                }
            }
        }
        return localInstance;
    }

    private String uuid;

    private synchronized void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public synchronized String getUuid() {
        if(uuid==null)
            getNewUuid();
        return uuid;
    }

    public synchronized String getNewUuid(){
        UUID uuid = UUID.randomUUID();
        setUuid(uuid.toString());
        return this.uuid;
    }
}

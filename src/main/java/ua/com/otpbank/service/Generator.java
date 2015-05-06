package ua.com.otpbank.service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Igor on 25.04.2015.
 */

public class Generator {
    protected Generator(){};
    private static volatile Generator instance;

    public static Generator getInstance() {
        Generator localInstance = instance;
        if (localInstance == null) {
            synchronized (Generator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Generator();
                }
            }
        }
        return localInstance;
    }

    private  List<String> coffeeLovers;

    public synchronized void setCoffeeLovers(List<String> coffeeLovers) {
        this.coffeeLovers = coffeeLovers;
    }

    public synchronized List<String> getCoffeeLovers() {
        Collections.shuffle(coffeeLovers);
        return coffeeLovers;
    }
    public synchronized List<String> getShuffledCoffeeLovers() {
        return coffeeLovers;
    }

}
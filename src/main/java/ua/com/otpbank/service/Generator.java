package ua.com.otpbank.service;

import ua.com.otpbank.domain.ParticipantsService;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Igor on 25.04.2015.
 */

public class Generator {
    protected Generator(){}

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

    private String generatorMethod;

    private final int random_max_value=10000;

    public synchronized void setGeneratorMethod(String generatorMethod) {
        this.generatorMethod = generatorMethod;
    }

    public synchronized String getGeneratorMethod() {
        return generatorMethod;
    }

    public synchronized void setCoffeeLovers(List<String> coffeeLovers) {
        this.coffeeLovers = coffeeLovers;
    }

    public synchronized List<String> getCoffeeLovers() {
        if(getGeneratorMethod().isEmpty()){setGeneratorMethod("simple-shuffle");} //set default
        if(getGeneratorMethod().equals("simple-shuffle")){Collections.shuffle(coffeeLovers);}
        if(getGeneratorMethod().equals("random-based-shuffle")){
            Random random = new Random();
            for(int i=0; i<random.nextInt(random_max_value)+1; i++){
                Collections.shuffle(coffeeLovers);
            }
        }
        if(getGeneratorMethod().equals("statistics-based-shuffle")){
            ParticipantsService participantsService = new ParticipantsService();
            Collections.shuffle(coffeeLovers);
            participantsService.changeListOrder(coffeeLovers);
        }


        return coffeeLovers;
    }
    public synchronized List<String> getShuffledCoffeeLovers() {
        return coffeeLovers;
    }

}
package com.company;

public class MetronomeThread implements Runnable {

    Metronome runMetronome;
    Thread t;


    public MetronomeThread(Metronome runMetronome) {

        this.runMetronome = runMetronome;
    }

    public void start(){
        System.out.println();
        if(t == null){
            t = new Thread (this, "metronom");
            t.start();
    }
        else {
            t = new Thread (this, "metronom");
            t.start();
        }
    }

    @Override
    public void run() {

        runMetronome.playMetronome();
    }
}

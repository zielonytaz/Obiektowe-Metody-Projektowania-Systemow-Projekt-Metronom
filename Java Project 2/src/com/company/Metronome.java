package com.company;

public class Metronome {

    private double bpm;
    private String sound;
    private boolean loop = true;
    public Sounds s;

    public boolean isLoop() {
        return loop;
    }

    public void setBpm(double bpm) {
        this.bpm = bpm;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }


    public Metronome() {

        this.s=new Sounds();
    }


    public void playMetronome(){

        while(loop) {


            this.s.playSound(sound);
            try {
                Thread.sleep((long) bpm);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}

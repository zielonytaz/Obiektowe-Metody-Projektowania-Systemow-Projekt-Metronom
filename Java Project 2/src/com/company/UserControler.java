package com.company;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class UserControler extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Slider slider = new Slider(20,300,60);

        slider.valueProperty().addListener((obs, oldval, newVal) ->
               slider.setValue(newVal.intValue()));

        slider.setLayoutY(100);

        Label sliderValue = new Label(Double.toString(slider.getValue()));
        sliderValue.setLayoutX(50);
        sliderValue.setLayoutY(50);

        ComboBox musicList = new ComboBox();
        musicList.setValue("sounds/dźwięk1.wav");
        musicList.getItems().addAll("sounds/dźwięk1.wav","sounds/dźwięk2.wav");
        musicList.setLayoutX(150);
        musicList.setLayoutY(20);

        Metronome metronom = new Metronome();

        MetronomeThread metronomeThread = new MetronomeThread(metronom);

        Button buttonPlay = new Button("PLAY");
        buttonPlay.setLayoutX(10);
        buttonPlay.setLayoutY(10);
        buttonPlay.setOnAction(event -> {

            //1000 - bo milisekundy, trzeba odjac dlugosc pliku wav (ms)
            // Metronome metronom = new Metronome(1000/(slider.getValue()/60), musicList.getValue().toString());

            buttonPlay.setDisable(true);
            slider.setDisable(true);
            musicList.setDisable(true);

            //System.out.println(metronom.isLoop());
            metronom.setLoop(false);
            metronom.setLoop(true);

            //System.out.println(metronom.isLoop());

            metronom.setBpm(1000/(slider.getValue()/60));
            metronom.setSound(musicList.getValue().toString());

            metronomeThread.start();

            //  metronomeThread.stopThread();


        });

        Button buttonStop = new Button("STOP");
        buttonStop.setLayoutX(70);
        buttonStop.setLayoutY(10);
        buttonStop.setOnAction(event -> {

            buttonPlay.setDisable(false);
            slider.setDisable(false);
            musicList.setDisable(false);

            //System.out.println(metronom.isLoop());
            metronom.setLoop(false);
           //System.out.println(metronom.isLoop());
         //metronomeThread.stopThread();

        });


        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {

                sliderValue.setText(String.format("%.2f", new_val));
            }
        });


        Pane panel = new Pane();
        panel.getChildren().addAll(buttonPlay, buttonStop, slider, sliderValue, musicList);

        Scene window = new Scene(panel,600,400);

        primaryStage.setScene(window);
        primaryStage.show();


    }
}



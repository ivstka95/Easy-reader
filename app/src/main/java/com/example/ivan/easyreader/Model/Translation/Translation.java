package com.example.ivan.easyreader.Model.Translation;

import java.util.ArrayList;

/**
 * Created by Myryk on 03.05.2017.
 */

public class Translation {
    private String transcription;
    private String word;
    private ArrayList<String> tanslations = new ArrayList<String>();

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public ArrayList<String> getTanslations() {
        return tanslations;
    }

    public void setTanslations(ArrayList<String> tanslations) {
        this.tanslations = tanslations;
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}

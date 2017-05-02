package com.example.ivan.easyreader.Model;


import android.view.View;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Myryk on 28.04.2017.
 */

public class Book {
    private FileReader bookReader;
    private List<View> savedPages;

    public Book(File bookFile) {
        savedPages = new ArrayList<View>();
        try {
            this.bookReader = new FileReader(bookFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public View getSavedPage(int position) {
        return savedPages.get(position);
    }

    public String readWord() {
        String s = "";
        try {
            int c;
            while ((c = bookReader.read()) != -1 && !Character.isWhitespace(c)) {
                s += (char) c;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return s;
    }

    public int getSavedPageCount() {
        return savedPages.size();
    }

    public void savePage(View view) {
        savedPages.add(view);
    }
}

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
    private boolean endOfBook = false;

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
            int c = bookReader.read();
            while (!Character.isWhitespace(c)) {
                s += (char) c;
                c = bookReader.read();
                if (c == -1) {
                    bookReader.close();
                    endOfBook = true;
                    break;
                }
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

    public boolean isEndOfBook() {
        return endOfBook;
    }
}

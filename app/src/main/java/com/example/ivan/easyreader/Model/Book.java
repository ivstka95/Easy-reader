package com.example.ivan.easyreader.Model;

import android.net.Uri;
import android.util.Log;

import java.io.File;

/**
 * Created by Myryk on 28.04.2017.
 */

public class Book {
    private File bookFile;

    public Book(File bookFile) {
        this.bookFile = bookFile;
    }

    public String getFileUri() {
        return bookFile.getPath();
    }
}

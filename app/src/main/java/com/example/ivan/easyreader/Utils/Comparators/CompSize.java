package com.example.ivan.easyreader.Utils.Comparators;

import com.example.ivan.easyreader.Model.DirectoryItem;

import java.io.File;
import java.util.Comparator;

/**
 * Created by Myryk on 01.05.2017.
 */
public class CompSize implements Comparator<DirectoryItem> {
    @Override
    public int compare(DirectoryItem di1, DirectoryItem di2) {
        return (int) (new File(di1.getFilepath()).length() - (new File(di2.getFilepath()).length()));
    }
}
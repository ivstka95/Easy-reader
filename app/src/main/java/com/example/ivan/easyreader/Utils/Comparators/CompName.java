package com.example.ivan.easyreader.Utils.Comparators;

import com.example.ivan.easyreader.Model.DirectoryItem;

import java.util.Comparator;

/**
 * Created by Myryk on 01.05.2017.
 */
public class CompName implements Comparator<DirectoryItem> {
    @Override
    public int compare(DirectoryItem di1, DirectoryItem di2) {
        return Integer.valueOf(di1.getName().compareToIgnoreCase(di2.
                getName()));
    }
}
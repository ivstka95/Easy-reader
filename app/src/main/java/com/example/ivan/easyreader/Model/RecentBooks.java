package com.example.ivan.easyreader.Model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Myryk on 03.05.2017.
 */

public class RecentBooks {

    private static final String RECENT_BOOKS_FILE = "/sdcard/recentBooks.txt";

    public static void save(String s) {
        Set<String> books = getRecentBooks();
        books.add(s);
        try (FileWriter writer = new FileWriter(RECENT_BOOKS_FILE, false)) {
            for (String book : books) {
                writer.write(book);
                writer.append('\n');
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static Set<String> getRecentBooks() {
        Set<String> books = new HashSet<String>();
        String s = "";
        try (FileReader reader = new FileReader(RECENT_BOOKS_FILE)) {
            int c;
            while ((c = reader.read()) != -1) {
                if (((char)c) != '\n')
                    s += (char) c;
                else {
                    books.add(s);
                    s = "";
                }
            }
            reader.close();
        } catch (IOException ex) {
        }
        return books;
    }
}

package com.example.ivan.easyreader.Model;

import android.widget.CheckBox;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Иван on 12.01.2017.
 */

public class DirectoryItem {
    private String path;
    private String name;

    public DirectoryItem(String filePath) {
        int a = 0;
        for (int i = filePath.length() - 1; i >= 0; i--) {
            if (filePath.charAt(i) == '/') {
                a = i;
                break;
            }
        }
        this.name = filePath.substring(a + 1);
        this.path = filePath.substring(0, a);
    }

    public DirectoryItem(String path, String file) {
        this.name = file;
        this.path = path;
    }

    public static class CompSize implements Comparator<DirectoryItem> {
        @Override
        public int compare(DirectoryItem di1, DirectoryItem di2) {
            return (int) (new File(di1.getFilepath()).length() - (new File(di2.getFilepath()).length()));
        }
    }

    public static class CompDate implements Comparator<DirectoryItem> {
        @Override
        public int compare(DirectoryItem di1, DirectoryItem di2) {
            return Integer.valueOf((int) new File(di1.getFilepath()).lastModified() - (int) new File(di2.getFilepath()).lastModified());
        }
    }

    public static class CompName implements Comparator<DirectoryItem> {
        @Override
        public int compare(DirectoryItem di1, DirectoryItem di2) {
            return Integer.valueOf(di1.getName().compareToIgnoreCase(di2.
                    getName()));
        }
    }

    public String getIntentType() {
        if (getType().equalsIgnoreCase(".htm") || getType().equalsIgnoreCase(".html") ||
                getType().equalsIgnoreCase(".htmls") || getType().equalsIgnoreCase(".htt") ||
                getType().equalsIgnoreCase(".htx") || getType().equalsIgnoreCase(".java") ||
                getType().equalsIgnoreCase(".js") || getType().equalsIgnoreCase(".pl") ||
                getType().equalsIgnoreCase(".txt") || getType().equalsIgnoreCase(".xml"))
            return "text/*";

        return "view/*";
    }

    public String getFilepath() {
        String filepath;
        if (getPath().endsWith(File.separator)) {
            filepath = getPath() + getName();
        } else {
            filepath = getPath() + File.separator + getName();
        }
        return filepath;
    }

    public String getType() {
        String type = getName();
        int a = 0;
        for (int i = type.length() - 1; i >= 0; i--) {
            if (type.charAt(i) == '.') {
                a = i;
                break;
            }
        }
        return type.substring(a);
    }


    public String getSize() {
        long bytes = new File(getFilepath()).length();
        boolean si = true;
        int unit = si ? 1000 : 1024;
        if (bytes < unit) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    public String getLastModified() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss  MM.dd.yyyy");
        return dateFormat.format(new Date(new File(getFilepath()).lastModified()));
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}

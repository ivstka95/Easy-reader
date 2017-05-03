package com.example.ivan.easyreader.Model;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Иван on 12.01.2017.
 */

public class DirectoryItem {
    private String path;
    private String name;

    public DirectoryItem(String path, String file) {
        this.name = file;
        this.path = path;
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

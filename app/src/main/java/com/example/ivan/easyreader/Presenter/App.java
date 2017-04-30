package com.example.ivan.easyreader.Presenter;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.ivan.easyreader.DI.AppComponent;
import com.example.ivan.easyreader.DI.AppModule;
import com.example.ivan.easyreader.DI.DaggerAppComponent;
import com.example.ivan.easyreader.DI.ModelComponent;
import com.example.ivan.easyreader.DI.ModelModule;
import com.example.ivan.easyreader.DI.RESTComponent;
import com.example.ivan.easyreader.DI.RESTModule;
import com.example.ivan.easyreader.R;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ivan on 19.04.2017.
 */

public class App extends Application {
    private static final String FILE_NAME = "dictionary.txt";
    private static AppComponent component;
    private static RESTComponent restComponent;
    private static ModelComponent modelComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();

        if (!getApplicationContext().getFileStreamPath(FILE_NAME).exists()) {
            saveDictionary();
        } else {
            try {
                FileInputStream fin = null;
                try {
                    fin = openFileInput(FILE_NAME);
                    byte[] bytes = new byte[fin.available()];
                    fin.read(bytes, 0, 100);
                    String text = new String(bytes);
                    Log.e("*********", text);
                    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                } catch (IOException ex) {
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                } finally {

                    try {
                        if (fin != null)
                            fin.close();
                    } catch (IOException ex) {

                        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    public static AppComponent getComponent() {
        return component;
    }


    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static RESTComponent plusRESTComponent() {
        if (restComponent == null) {
            restComponent = component.plusRESTComponent(new RESTModule());
        }
        return restComponent;
    }

    public static ModelComponent plusModelComponent() {
        if (modelComponent == null) {
            modelComponent = component.plusModelComponent(new ModelModule());
        }
        return modelComponent;
    }

    public static void clearRESTComponent() {
        restComponent = null;
    }

    public static void clearModelComponent() {
        modelComponent = null;
    }

    private byte[] convertStreamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = is.read();
        while (i != -1) {
            baos.write(i);
            i = is.read();
        }
        return baos.toByteArray();
    }

    public void saveDictionary() {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(convertStreamToByteArray(getResources().openRawResource(R.raw.dictionary)));
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        Log.e("*********", "now___________________EXISTS");

    }
}
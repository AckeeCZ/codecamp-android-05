package cz.ackee.codecamp05;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * TODO add class description
 * Created by David Bilik[david.bilik@ackee.cz] on {07/04/16}
 **/
public class App extends Application {
    public static final String TAG = App.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}

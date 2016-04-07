package cz.ackee.codecamp05.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import cz.ackee.codecamp05.R;

/**
 * Activity that shows example usage of retain fragments
 * Created by David Bilik[david.bilik@ackee.cz] on {06/04/16}
 **/
public class SharedPreferencesActivity extends AppCompatActivity {
    public static final String TAG = SharedPreferencesActivity.class.getName();
    private static final String SP_NAME = "prefs";
    private static final String LAST_RUN_KEY = "last_run";
    private static final String DATA_KEY = "data";


    TextView txtNote;
    EditText editNote;
    Button btnAdd;

    /**
     * Get shared preferences
     *
     * @return instance of shared preferences
     */
    public SharedPreferences getPreferences() {
        // or getPreferences(Context.MODE_PRIVATE);
        return getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retain_fragment);
        txtNote = (TextView) findViewById(R.id.txt_result);
        editNote = (EditText) findViewById(R.id.edit_note);
        btnAdd = (Button) findViewById(R.id.btn_add);
        initText();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPreferences().edit().putString(DATA_KEY, editNote.getText().toString()).commit();
                initText();
            }
        });
    }

    private void initText() {
        long lastRun = getPreferences().getLong(LAST_RUN_KEY, -1);
        StringBuilder sb = new StringBuilder("App last run time: ");
        if (lastRun == -1) {
            sb.append("Never").append("\n");
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy HH:mm");
            Date d = new Date(lastRun);
            sb.append(sdf.format(d)).append("\n");
        }
        sb.append(getPreferences().getString(DATA_KEY, ""));
        txtNote.setText(sb.toString());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {//save time only if activity is finishing
            getPreferences().edit().putLong(LAST_RUN_KEY, System.currentTimeMillis()).apply();
        }
    }
}

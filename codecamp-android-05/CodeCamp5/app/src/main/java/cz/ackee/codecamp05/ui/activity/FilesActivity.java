package cz.ackee.codecamp05.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import cz.ackee.codecamp05.R;

/**
 * Activity that shows example usage of retain fragments
 * Created by David Bilik[david.bilik@ackee.cz] on {06/04/16}
 **/
public class FilesActivity extends AppCompatActivity {
    public static final String TAG = FilesActivity.class.getName();
    public static final String FILE_NAME = "data.txt";


    TextView txtNote;
    EditText editNote;
    Button btnAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retain_fragment);
        txtNote = (TextView) findViewById(R.id.txt_result);
        editNote = (EditText) findViewById(R.id.edit_note);
        btnAdd = (Button) findViewById(R.id.btn_add);
        txtNote.setText(getDataFromFile());
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set data to our holder and update ui
                saveDataToFile(editNote.getText().toString());
                txtNote.setText(editNote.getText().toString());
            }
        });

    }

    private File getDataFile() {
        File f = new File(getFilesDir(), FILE_NAME);
        return f;
    }

    private String getDataFromFile() {
        File f = getDataFile();
        if (!f.exists()) { //check for file existence
            return null;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
                    String receiveString;
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((receiveString = bufferedReader.readLine()) != null) {
                        stringBuilder.append(receiveString);
                    }
                    fis.close();
                    return stringBuilder.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void saveDataToFile(String data) {
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(getDataFile());
            outputStream.write(data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

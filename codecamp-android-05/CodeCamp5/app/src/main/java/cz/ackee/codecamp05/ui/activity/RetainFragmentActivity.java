package cz.ackee.codecamp05.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cz.ackee.codecamp05.R;

/**
 * Activity that shows example usage of retain fragments
 * Created by David Bilik[david.bilik@ackee.cz] on {06/04/16}
 **/
public class RetainFragmentActivity extends AppCompatActivity {
    public static final String TAG = RetainFragmentActivity.class.getName();

    /**
     * Retaining fragment that has some textual data.
     */
    public static class RetainFragment extends Fragment {

        String data;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }
    }

    TextView txtNote;
    EditText editNote;
    Button btnAdd;

    /**
     * Get retained fragment that holds data
     * @return instance of data holder
     */
    public RetainFragment getDataHolder() {
        RetainFragment rf = (RetainFragment) getSupportFragmentManager().findFragmentByTag(RetainFragment.class.getName());
        if (rf == null) { //first time retrieving
            rf = new RetainFragment();
            //note that we are not inserting fragment to any layout id
            getSupportFragmentManager().beginTransaction().add(rf, RetainFragment.class.getName()).commit();
        }
        return rf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retain_fragment);
        txtNote = (TextView) findViewById(R.id.txt_result);
        editNote = (EditText) findViewById(R.id.edit_note);
        btnAdd = (Button) findViewById(R.id.btn_add);
        txtNote.setText(getDataHolder().data);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set data to our holder and update ui
                getDataHolder().data = editNote.getText().toString();
                txtNote.setText(editNote.getText().toString());
            }
        });
    }
}

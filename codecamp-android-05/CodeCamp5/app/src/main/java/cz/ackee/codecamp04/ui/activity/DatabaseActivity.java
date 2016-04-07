package cz.ackee.codecamp04.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import cz.ackee.codecamp04.R;
import cz.ackee.codecamp04.db.DatabaseHelper;
import cz.ackee.codecamp04.ui.fragment.BaseDBFragment;
import cz.ackee.codecamp04.ui.fragment.UserListFragment;

/**
 * Activity that holds reference to SqliteOpenHelper and switches fragments inside
 * Created by David Bilik[david.bilik@ackee.cz] on {07/04/16}
 **/
public class DatabaseActivity extends AppCompatActivity implements BaseDBFragment.DBActivity {
    public static final String TAG = DatabaseActivity.class.getName();
    private DatabaseHelper dbHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        dbHelper = new DatabaseHelper(this);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportFragmentManager().findFragmentByTag(UserListFragment.class.getName()) == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new UserListFragment(), UserListFragment.class.getName()).commit();
        }

    }

    @Override
    public DatabaseHelper getHelper() {
        return dbHelper;
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.getClass().getName())
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }
}

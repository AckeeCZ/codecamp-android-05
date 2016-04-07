package cz.ackee.codecamp04.ui.fragment;

import android.support.v4.app.Fragment;

import cz.ackee.codecamp04.db.DatabaseHelper;

/**
 * Base fragment that has access to DatabaseActivity
 * Created by David Bilik[david.bilik@ackee.cz] on {07/04/16}
 **/
public class BaseDBFragment extends Fragment {
    public static final String TAG = BaseDBFragment.class.getName();


    public interface DBActivity {
        public DatabaseHelper getHelper();

        void replaceFragment(Fragment fragment);
    }

    public DBActivity getDBActivity() {
        return (DBActivity) getActivity();
    }
}

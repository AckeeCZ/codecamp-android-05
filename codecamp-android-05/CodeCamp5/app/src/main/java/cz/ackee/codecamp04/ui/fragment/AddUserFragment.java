package cz.ackee.codecamp04.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import cz.ackee.codecamp04.R;
import cz.ackee.codecamp04.domain.User;

/**
 * Fragment with form for adding user
 * Created by David Bilik[david.bilik@ackee.cz] on {07/04/16}
 **/
public class AddUserFragment extends BaseDBFragment{
    public static final String TAG = AddUserFragment.class.getName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText editName = (EditText) view.findViewById(R.id.edit_name);
        final EditText editSurname = (EditText) view.findViewById(R.id.edit_surname);
        final EditText editAge = (EditText) view.findViewById(R.id.edit_age);

        Button btnSave = (Button) view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(editName.getText().toString(), editSurname.getText().toString(), Integer.parseInt(editAge.getText().toString()));
                getDBActivity().getHelper().insertUser(user);
                UserListFragment userListFragment = (UserListFragment) getTargetFragment();
                if(userListFragment != null) {
                    userListFragment.reloadData();
                }
                getActivity().onBackPressed();
            }
        });
    }
}

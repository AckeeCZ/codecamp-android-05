package cz.ackee.codecamp04.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cz.ackee.codecamp04.R;
import cz.ackee.codecamp04.db.DatabaseHelper;
import cz.ackee.codecamp04.domain.User;
import cz.ackee.codecamp04.ui.adapter.UsersAdapter;
import cz.ackee.codecamp04.ui.adapter.decoration.DividerItemDecoration;

/**
 * Fragment that is showing list of users
 * Created by David Bilik[david.bilik@ackee.cz] on {07/04/16}
 **/
public class UserListFragment extends BaseDBFragment {
    public static final String TAG = UserListFragment.class.getName();


    UsersAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_with_tasks, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_tasks);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadData();
    }

    public void loadData() {
        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_tasks);
        DatabaseHelper helper = getDBActivity().getHelper();
        List<User> users = helper.getAllUsers();
        adapter = new UsersAdapter(users);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_user_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                AddUserFragment addUserFragment = new AddUserFragment();
                addUserFragment.setTargetFragment(this, 0);
                getDBActivity().replaceFragment(addUserFragment);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void reloadData() {
        DatabaseHelper helper = getDBActivity().getHelper();
        List<User> users = helper.getAllUsers();
        adapter.setData(users);
    }
}

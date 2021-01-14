package com.philencripted.fairmoneytest.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.philencripted.fairmoneytest.R;
import com.philencripted.fairmoneytest.adapter.UserAdapter;
import com.philencripted.fairmoneytest.model.User;
import com.philencripted.fairmoneytest.utils.Util;
import com.philencripted.fairmoneytest.viewmodel.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // declare viewmodel
    private UserViewModel userViewModel;

    private ProgressBar progressBar;
    private RecyclerView userRecyclerview;
    private UserAdapter userAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hold ceontext reference for non activity use
        Util.set_context(this);

        userRecyclerview = findViewById(R.id.user_recycler_view);
        progressBar = findViewById(R.id.progress_circular);

        //getting instance of the view model to render view
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        // observe view model live data for changes and update ui
        LiveData<List<User>> userliveData = userViewModel.getUsers();
        userliveData.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                progressBar.setVisibility(View.VISIBLE);
                setUpUserList(users);
                progressBar.setVisibility(View.GONE);
                // debug purpose
                for (int i = 0; i < users.size(); i++){
                    System.out.println(users.get(i).getAddress().getStreet());
                }
            }
        });
    }

    private void setUpUserList(List<User> userList){
        userRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        userRecyclerview.setHasFixedSize(true);
        userRecyclerview.setItemAnimator(new DefaultItemAnimator());
        userAdapter = new UserAdapter(this, userList);
        userRecyclerview.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();
    }
}
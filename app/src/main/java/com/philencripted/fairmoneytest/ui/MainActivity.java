package com.philencripted.fairmoneytest.ui;

import android.os.Bundle;

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
import com.philencripted.fairmoneytest.viewmodel.UserViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // declare viewmodel
    private UserViewModel userViewModel;

    // hold list of users
    List<User> userArrayList;

    private RecyclerView userRecyclerview;
    private UserAdapter userAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userRecyclerview = findViewById(R.id.user_recycler_view);

        //getting instance of the viewmodel to render view
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        // observe viewmodel livedata for changes and update ui
        LiveData<List<User>> userliveData = userViewModel.getUsers();
        userliveData.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //userArrayList = users;

                setUpUserList(users);

                for (int i = 0; i < users.size(); i++){
                    System.out.println(users.get(i).getAddress().getStreet());
                }
            }
        });

        userRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        userRecyclerview.setHasFixedSize(true);
        userRecyclerview.setItemAnimator(new DefaultItemAnimator());


    }

    private void setUpUserList(List<User> userList){
        userAdapter = new UserAdapter(this, userList);
        userRecyclerview.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();
    }
}
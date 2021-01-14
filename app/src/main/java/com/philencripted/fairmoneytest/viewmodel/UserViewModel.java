package com.philencripted.fairmoneytest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.philencripted.fairmoneytest.api.RestApi;
import com.philencripted.fairmoneytest.api.RestClient;
import com.philencripted.fairmoneytest.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserViewModel extends ViewModel {
    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<User>> usersData;

    //we will call this method to get the data
    // this will help save orientation change issues and retain our data with the help of viewmodel
    public LiveData<List<User>> getUsers() {
        //if the object is null
        if (usersData == null) {
            usersData = new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadUsers();
        }

        //finally we will return the objects
        return usersData;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadUsers() {

        //Obtain an instance of Retrofit by calling the static method.
       Retrofit retrofit =  RestClient.getRetrofitClient();
       /*
       The main purpose of Retrofit is to create HTTP calls from the Java interface based
       on the annotation associated with each method. This is achieved by just passing the interface
       class as parameter to the create method
       */
        RestApi userApi = retrofit.create(RestApi.class);

       /*
       Invoke the method corresponding to the HTTP request which will return a Call object. This Call
       object will used to send the actual network request with the specified parameters
       */

        Call<List<User>> call = userApi.getAllDummyUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                //finally we are setting the list to our MutableLiveData
                usersData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });


    }
}

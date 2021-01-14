package com.philencripted.fairmoneytest.api;

import com.philencripted.fairmoneytest.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {


/*
This request is to fetch user details by passing app id in the header
*/
    @GET("/users")
    Call<List<User>> getAllDummyUsers();
}

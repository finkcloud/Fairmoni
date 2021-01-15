package com.philencripted.fairmoneytest.api;

import com.philencripted.fairmoneytest.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RestApi {


/*
This request is to fetch user details by passing app id in the header
*/
    @Headers("Cache-Control: max-age=640000")
    @GET("/users")
    Call<List<User>> getAllDummyUsers();
}

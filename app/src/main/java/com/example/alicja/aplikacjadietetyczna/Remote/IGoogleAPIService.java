package com.example.alicja.aplikacjadietetyczna.Remote;

import android.test.suitebuilder.annotation.LargeTest;

import com.example.alicja.aplikacjadietetyczna.Model.MyPlaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Alicja on 2018-03-07.
 */

public interface IGoogleAPIService {
    @GET
    Call<MyPlaces> getNearByPlaces(@Url String url);

}

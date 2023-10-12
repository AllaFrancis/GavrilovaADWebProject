package ru.mirea.Gavrilova.movies;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface Service {
    @GET("https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json")
    Call<List<DTO>> getDTO();
}

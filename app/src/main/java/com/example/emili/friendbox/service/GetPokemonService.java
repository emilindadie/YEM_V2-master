package com.example.emili.friendbox.service;


import com.example.emili.friendbox.ui.mainActivity.Pokemon;
import com.example.emili.friendbox.ui.mainActivity.PokemonList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by emili on 28/11/2017.
 */

public interface GetPokemonService {

    @GET("pokemon")
    Observable<PokemonList> getAll();


    @GET("pokemon")
    Observable<PokemonList> getQuery(@Query("name") String pokemonName);

    @GET("pokemon/")
    Call<PokemonList> getAll2();

    @GET("{url}")
    Observable<Pokemon> getPokemon(@Path("url") String url);

    @GET("{url}")
    Call<Pokemon> getPokemon2(@Path("url") String url);

}

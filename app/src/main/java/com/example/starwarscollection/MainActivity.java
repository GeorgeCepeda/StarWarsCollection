/*George Cepeda
 * Application designed as part of a coding Challenge for the Company EShow.
 * App is to use SWAPI, and other Libraries (Retrofit 2, Gson) to create an app that is up to
 * specifications given in email.
 *
 * Start: 1/6/20
 * Due: 1/9/20 to be submitted to github
 */

package com.example.starwarscollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;

import androidx.fragment.app.FragmentActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends FragmentActivity {


    ArrayList<Film> movieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Creates Retrofit Object
        Retrofit rf = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Create the interface
        Api a = rf.create(Api.class);


        Call<FilmQuery> call = a.getQuery();
        call.enqueue(new Callback<FilmQuery>(){

            @Override
            public void onResponse(Call<FilmQuery> call, Response<FilmQuery> response) {
                FilmQuery films = response.body();

                /*
                ArrayList<Film> nFilms= films.getResults();

                String[] filmNames = new String[nFilms.size()];

                for(int i = 0; i < nFilms.size(); i++){
                    filmNames[i] = nFilms.get(i).getTitle();
                    //Log.d("Name:", filmNames[i]);
                }

                lv.setAdapter(
                        new ArrayAdapter<String>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                filmNames)
                );
                */

                /*Saves all info we just fetched*/
                movieInfo = films.getResults();
            }

            @Override
            public void onFailure(Call<FilmQuery> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

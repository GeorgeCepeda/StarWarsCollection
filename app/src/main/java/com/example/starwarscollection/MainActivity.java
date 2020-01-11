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

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends FragmentActivity implements TitleListFragmentFragment.OnListFragmentInteractionListener,FilmFragment.onExitButtonHitListener{

    ArrayList<Film> movieInfo = new ArrayList<Film>();

    private FragmentManager mFragManager;
    private FilmFragment fFrag;
    private TitleListFragmentFragment tFrag;

    private FrameLayout filmFrame;
    private FrameLayout titleFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filmFrame = findViewById(R.id.pHolder2);
        titleFrame= findViewById(R.id.pHolder);

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

                /*Saves all info we just fetched*/
                updateFilmList(films.getResults());
            }

            @Override
            public void onFailure(Call<FilmQuery> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    public void updateFilmList(ArrayList<Film> update){

        movieInfo = update;

        tFrag = new TitleListFragmentFragment(movieInfo);
        mFragManager = (FragmentManager)getSupportFragmentManager();
        FragmentTransaction tAct = mFragManager.beginTransaction();
        tAct.add(R.id.pHolder,tFrag);
        tAct.commit();

        mFragManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                setFragInView();
            }
        });
    }

    private void setFragInView(){
        //Check if the fragment exists
        if(!fFrag.isAdded()){


        }
        else{
            filmFrame.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,2f));
            titleFrame.setLayoutParams(new LinearLayout.LayoutParams(0,ViewGroup.LayoutParams.MATCH_PARENT,1f));
        }
    }



    @Override
    public void onListFragmentInteraction(Film item) {
        /*Do something when our list is interacted with.*/
        int ori = getResources().getConfiguration().orientation;

        if(ori == Configuration.ORIENTATION_PORTRAIT) {
            FragmentTransaction hFrag = mFragManager.beginTransaction();
            hFrag.remove(tFrag); //If in landscape mode we don't have to remove it...
            hFrag.commit();
        }


        if(fFrag == null) {
            FragmentTransaction nFrag = mFragManager.beginTransaction();
            fFrag = new FilmFragment(item);
            nFrag.add(R.id.pHolder2, fFrag);//pHolder2 is dedicated to films
            nFrag.commit();
        }
        else{
            //removes the existing fragment
            FragmentTransaction rFrag = mFragManager.beginTransaction();
            rFrag.remove(fFrag);
            rFrag.commit();

            FragmentTransaction nFrag = mFragManager.beginTransaction();
            fFrag = new FilmFragment(item);
            nFrag.add(R.id.pHolder2, fFrag);//pHolder2 is dedicated to films
            nFrag.commit();
        }


    }

    @Override
    public void onExitButtonHit(){
        /*When the escape button is hit*/
        //remove the fragment all together
        FragmentTransaction hFrag = mFragManager.beginTransaction();
        hFrag.remove(fFrag); //If in landscape mode we don't have to remove it...
        hFrag.commit();

        //check orientation in case we don't need to recreate this
        int ori = getResources().getConfiguration().orientation;
        if(ori == Configuration.ORIENTATION_PORTRAIT) {
            FragmentTransaction tAct = mFragManager.beginTransaction();
            tAct.replace(R.id.pHolder, tFrag);//pHolder1 is dedicated to the titles
            tAct.commit();
        }


    }
}

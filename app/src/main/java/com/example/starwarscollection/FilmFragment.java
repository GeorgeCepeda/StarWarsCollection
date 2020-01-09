package com.example.starwarscollection;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmFragment extends Fragment {

    private Film mFilm;
    private ArrayList<Character> chars = new ArrayList<Character>();
    RecyclerView rv;
    EditText title;
    TextView opCrawl, director, producer, release;
    Button exit;
    View mainView;
    ArrayList<String> names = new ArrayList<String>();

    private onExitButtonHitListener mListener;

    FilmFragment(Film movie){
        mFilm = movie;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_film, container, false);
        title = view.findViewById(R.id.titlebar);
        title.setText(mFilm.getTitle());

        opCrawl= view.findViewById(R.id.opcText);
        opCrawl.setText(mFilm.getOpening_crawl());

        director=view.findViewById(R.id.dText);
        director.setText(mFilm.getDirector());

        producer= view.findViewById(R.id.pText);
        producer.setText(mFilm.getProducer());

        release = view.findViewById(R.id.rdText);
        release.setText(mFilm.getRelease_date());

        exit = view.findViewById(R.id.esc);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onExitButtonHit(); //Notify the app that the exit button has been hit time to switch
            }
        });

        rv = view.findViewById(R.id.charSpin);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        rv.setLayoutManager(layoutManager);

        mainView = view;
        setCharacterSection(view);

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof onExitButtonHitListener) {
            mListener = (onExitButtonHitListener) context;
        }
    }

    public void setCharacterSection(View v){
        final CharacterAdapter mAdapter = new CharacterAdapter(names);
        Retrofit rt = new Retrofit.Builder()
                .baseUrl(CharacterAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CharacterAPI cApi = rt.create(CharacterAPI.class);
        for(String s: mFilm.getCharacters()) {
            Call<Character> call = cApi.getPerson(s);
            call.enqueue(new Callback<Character>() {
                @Override
                public void onResponse(Call<Character> call, Response<Character> response) {
                    mAdapter.newAddedData(response.body().getName());

                }

                @Override
                public void onFailure(Call<Character> call, Throwable t) {

                }
            });
        }
        setCharArray();
        //CharacterAdapter mAdapter = new CharacterAdapter(names);
        rv.setAdapter(mAdapter);
    }

    void addtoChar(Character c){
        String tmp = String.copyValueOf(c.getName().toCharArray());
        names.add(tmp);
    }

    private void setCharArray(){
        //Log.d("SHOW", "This many ="+mFilm.getCharacters().size());
        //Log.d("SHOW", "This many ="+names.size());

        rv.setAdapter(new CharacterAdapter(names));

    }

    public interface onExitButtonHitListener{
        void onExitButtonHit();
    }
}

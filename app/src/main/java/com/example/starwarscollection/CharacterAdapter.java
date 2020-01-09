package com.example.starwarscollection;

import android.content.Context;
import android.database.DataSetObserver;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView cName;
        public ViewHolder(View itemView){
            super(itemView);
            cName = itemView.findViewById(R.id.characterName);
        }
    }

    private ArrayList<String> mList;

    public CharacterAdapter(ArrayList<String> insert){
        mList = insert;
    }

    @Override
    public CharacterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View cView = inflater.inflate(R.layout.character_adapter,parent,false);

        ViewHolder viewH = new ViewHolder(cView);
        return viewH;
    }

    @Override
    public void onBindViewHolder(CharacterAdapter.ViewHolder vh, int position){
        TextView tv = vh.cName;
        tv.setText(mList.get(position));
    }

    @Override
    public int getItemCount(){
        return mList.size();
    }

    public void newAddedData(String newData){
        mList.add(newData);
    }
}

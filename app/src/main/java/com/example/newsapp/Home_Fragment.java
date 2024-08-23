package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Fragment extends Fragment {

    String apikey = "444dd97807774725b2a433bad5028cdf";
    String country = "in";
    Adapter adapter;
    ArrayList<ModelClass> modelClassArrayList;
    private RecyclerView Home_RecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.homefragment,null);

        Home_RecyclerView = v.findViewById(R.id.Home_RecyclerView);
        modelClassArrayList = new ArrayList<>();

        Home_RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        Home_RecyclerView.setAdapter(adapter);

        findNews();

        return v;
    }

    private void findNews() {

     // how to fetch the data from the api
        ApiUtilities.getApiInterface().getNews(country,100,apikey).enqueue(new Callback<MainNews>() {
            @Override
            public void onResponse(Call<MainNews> call, Response<MainNews> response) {

                if(response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<MainNews> call, Throwable t) {

            }
        });


    }
}

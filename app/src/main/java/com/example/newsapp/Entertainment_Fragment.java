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

public class Entertainment_Fragment extends Fragment {
    String apikey = "444dd97807774725b2a433bad5028cdf";
    String country = "in";
    Adapter adapter;
    ArrayList<ModelClass> modelClassArrayList;
    private RecyclerView entertainmentrecyclerview;
    private String category = "entertainment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.entertainmentfragment,null);

        entertainmentrecyclerview= v.findViewById(R.id.entertainment_RecyclerView);
        modelClassArrayList = new ArrayList<>();

       entertainmentrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(getContext(),modelClassArrayList);
        entertainmentrecyclerview.setAdapter(adapter);

        findNews();

        return v;
    }

    private void findNews() {

        // how to fetch the data from the api
        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,apikey).enqueue(new Callback<MainNews>() {
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
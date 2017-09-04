package com.im.elecbear.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import adapter.BitstampOrderBookAdapter;
import bitstamp_object.BitstampOrderBook;
import listener.EndlessRecyclerViewScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import service.BitstampService;
import service.ServiceGenerator;

public class Pocket_Bitstamp_Main extends AppCompatActivity {

    private BitstampService service;
    private BitstampOrderBook orderBook;
    private RecyclerView recyclerView;
    private EndlessRecyclerViewScrollListener scrollListener;
    private BitstampOrderBookAdapter adapter;

    Button service_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocket__bitstamp__main);
        service_button = (Button) findViewById(R.id.service_button);
        recyclerView = (RecyclerView) findViewById(R.id.bidAsk);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);

//        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                loadNextDataFromApi(page);
//            }
//        };

//        recyclerView.addOnScrollListener(scrollListener);

        service_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service = ServiceGenerator.createService(BitstampService.class);

                Call<BitstampOrderBook> call = service.getOrderBook("xrpusd");
                call.enqueue(callback);

            }
        });

    }

    public void loadNextDataFromApi(int offset){

    }

    private Callback<BitstampOrderBook> callback =
            new Callback<BitstampOrderBook>() {
                @Override
                public void onResponse(Call<BitstampOrderBook> call, retrofit2.Response<BitstampOrderBook> response) {
                    if (response.isSuccessful()) {
                        orderBook = response.body();
                        adapter = new BitstampOrderBookAdapter(orderBook.getBids().subList(0,50),
                                orderBook.getAsks().subList(0,50));
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<BitstampOrderBook> call, Throwable t) {
                    Log.e("Error order book", t.getMessage());
                }
            };

}






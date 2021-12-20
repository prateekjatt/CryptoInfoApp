package com.quantastudio.cryptoinfo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.Timer;
import java.util.TimerTask;


public class Home extends Fragment {
    private JSONObject[] arr = {};
    private RecyclerView recyclerview;
    private TextView textview_loading;
    private Timer timer = new Timer();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setEnterTransition(new Fade());
        setExitTransition(new Fade());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        textview_loading = view.findViewById(R.id.textview_loading);
        textview_loading.setText("Loading Data....");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!(textview_loading.getText().toString().equalsIgnoreCase(""))){
                    textview_loading.setText("Internet Slow\nPlease Wait....");
                }
            }
        },12000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                getData();
            }
        }, 0, 20000);

        super.onViewCreated(view, savedInstanceState);
    }

    public void getData(){
        String currency;
        SharedPreferences pref = getActivity().getSharedPreferences("prefs",Context.MODE_PRIVATE);
        currency = pref.getString("currency","INR");
        Coins coins = new Coins(currency);
        if(coins.getCode() == HttpURLConnection.HTTP_OK){
            Activity activity = getActivity();
            arr = coins.getCoins();
            if(activity != null){
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       recyclerview.setAdapter(new CustomAdapter(arr,currency));
                        textview_loading.setText("");
                    }
                });
            }
        }
    }

    @Override
    public void onDetach() {
        timer.cancel();
        super.onDetach();
    }
}

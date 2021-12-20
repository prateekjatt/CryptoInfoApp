package com.quantastudio.cryptoinfo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class About extends Fragment {

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
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        InputStream in = getResources().openRawResource(R.raw.about);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String res = "";
        String line ="";
        try{
            while((line = br.readLine()) != null){
                res += (line+"\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        ((TextView)view.findViewById(R.id.textview_about)).setText(res);
        super.onViewCreated(view, savedInstanceState);
    }
}
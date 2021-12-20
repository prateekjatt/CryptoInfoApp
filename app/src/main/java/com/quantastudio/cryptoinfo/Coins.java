package com.quantastudio.cryptoinfo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Coins {
    final private int SIZE = 100;
    private int code;
    private JSONObject[] arr = new JSONObject[SIZE];

    public Coins(String currency) {

        try{
            URL url = new URL(
                    "https://api.coingecko.com/api/v3/coins/markets?order=market_cap_desc" +
                            "&per_page="+SIZE+"&page=1&sparkline=false&vs_currency="+currency);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(10000);
            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStream in = new BufferedInputStream(con.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String res="";
                String line="";
                while((line = br.readLine()) != null){
                    res += line;
                }
                br.close();
                in.close();

                JSONArray a = new JSONArray(res);
                Log.i("jsonarray", String.valueOf(a));
                for(int i=0,j=0;i<a.length() && j<SIZE;i++,j++){
                    arr[j] = new JSONObject(a.getString(i));
                }
            }
            code = con.getResponseCode();
        }
        catch(IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject[] getCoins(){return arr;}
    public int getCode(){return code;}
}

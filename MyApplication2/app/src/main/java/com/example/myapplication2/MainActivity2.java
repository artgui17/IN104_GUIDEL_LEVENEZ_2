package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity2 extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt= findViewById(R.id.textView2);
        Intent intent=getIntent();
        String deplat = intent.getStringExtra("latdep");
        String deplon = intent.getStringExtra("londep");
        String arrlat = intent.getStringExtra("latarr");
        String arrlon = intent.getStringExtra("lonarr");
        String deplacement= intent.getStringExtra("deplacement");
        String BASE_URL="route.json?origin="+deplon+","+deplat+"&destination="+arrlon+","+arrlat+"&&method=DISTANCE&graphName="+deplacement;
    Retrofit retrofit= new Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Api api = retrofit.create(Api.class);
    Call<info> call = api.getData(BASE_URL);
    call.enqueue(new Callback<info>() {
        @Override
        public void onResponse(Call<info> call, Response<info> response) {
           info infos =response.body();
            Log.d("distance", infos.getDistance());
            Log.d("durée", infos.getDuration());
            String texte="";
            texte ="Distance : " +infos.getDistance() +
                    "\n Temps de trajet : " +infos.getDuration();
            txt.append(texte);

        }

        @Override
        public void onFailure(Call<info> call, Throwable t) {
            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });

    }
}
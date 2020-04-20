package com.example.yeld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFind = findViewById(R.id.btn_Find);

        btnFind.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick (View view){

            findViewById(R.id.btn_Find);
                // get data from screen ... address ... business type
                TextView address = findViewById(R.id.et_Address);
                TextView business = findViewById(R.id.et_Business);

                // intent to YELP class ... weather
                Intent intent = new Intent(MainActivity.this, Yelp.class);

                intent.putExtra("param_address", address.getText().toString());
                intent.putExtra("param_business", business.getText().toString());

                startActivity(intent);

            }
        });
    }


}

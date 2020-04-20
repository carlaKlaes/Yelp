package com.example.yeld;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.yeld.Model;
import com.example.yeld.R;
import java.util.Map;
import java.util.HashMap;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Yelp extends AppCompatActivity implements MyAdapter.OnNoteListener{

    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    private static final ArrayList<Model> arrayModel = new ArrayList<>();
    String api_key = "uL2rP2palLVBlcjcCA6qnJhWqGEFf4qcRJ59jq0dHOzSER9pcRTk5yFpV0pkYe6VvEbLL9ftg0pWTfEB5semyBromAS2Yc_fGq8_1OcIpELQaoprE3Vyvz-LFl-HXnYx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yelp);

        mRecyclerView = findViewById(R.id.recyclerview);
        // create a recicleview inside linear layout
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String parm_address = getIntent().getStringExtra("param_address");
        String parm_business = getIntent().getStringExtra("param_business");

        getYelpList (parm_address, parm_business);

        //getMyList();
        myAdapter = new MyAdapter(this, arrayModel,this);
        mRecyclerView.setAdapter(myAdapter);

    }

    private void getYelpList (String parm_address, String parm_business){

        //build URL
        String url = "";
        url = "https://api.yelp.com/v3/businesses/search";

        if (parm_address.length() < 1){
            parm_address = "8 park vista";
        }

        url = url + "?location=" + parm_address;

        if (parm_business.length() != 0){
            url = url + "&term=" + parm_business;
        }

        url = url + "&limit=20&sort_by=distance";

        int x=1;
        // get data
        //RequestQueue queue = Volley.newRequestQueue(Yelp.this);
        RequestQueueSignleton queueSingle = RequestQueueSignleton.getInstance(Yelp.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        //clear the array to be used more than 1 time
                        arrayModel.clear();

                        try {
                            JSONArray list = response.getJSONArray("businesses");
                            for (int i = 0; i < list.length(); i++) {

                                JSONObject object = list.getJSONObject(i);

                                //name, phone, URL, categories, price class and weekly operating hours
                                String businessId = object.getString("id");
                                String businessName = object.getString("name");
                                Double businessDistance = object.getDouble("distance");

                                //Save all data on the Object Model
                                Model m = new Model(businessId, businessName, businessDistance);

                                // add object to array list
                                arrayModel.add(m);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(Yelp.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            //add API KEY to he header of the request
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                //headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + api_key);
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        //queue.add(jsonObjectRequest);
        queueSingle.addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onNoteClick(int position){

        Model m = arrayModel.get(position);
        //Toast.makeText(Yelp.this, "ID:" + Integer.toString(position) + "   Business name: " + m.getBusinessName(), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, YelpDetail.class);
        intent.putExtra("param_id_business", m.getBusinessId());

        startActivity(intent);

    }

}
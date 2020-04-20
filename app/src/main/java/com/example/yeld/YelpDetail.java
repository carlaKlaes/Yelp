package com.example.yeld;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import android.text.method.*;
import android.text.util.Linkify;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YelpDetail extends AppCompatActivity {

    private String api_key = "uL2rP2palLVBlcjcCA6qnJhWqGEFf4qcRJ59jq0dHOzSER9pcRTk5yFpV0pkYe6VvEbLL9ftg0pWTfEB5semyBromAS2Yc_fGq8_1OcIpELQaoprE3Vyvz-LFl-HXnYx";
    private static String name = "";
    private static String phone = "";
    private static String price = "";
    private static String image = "https://s3-media3.fl.yelpcdn.com/bphoto/t-g4d_vCAgZH_6pCqjaYWQ/o.jpg";
    private static String category = "";
    private static String storeurl = "";

    private static final ArrayList<WeeklyHours> arrayWeekly = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_restaurant);

        String param_id_business = getIntent().getStringExtra("param_id_business");

        getYelpBusinessDetail (param_id_business);

        TextView tvname = findViewById(R.id.tv_name);
        TextView tvprice = findViewById(R.id.tv_Price);
        ImageView ivpicture = findViewById(R.id.iv_pictute);
        TextView tvphone = findViewById(R.id.tv_Phone);
        TextView tvurl = findViewById(R.id.tv_url);
        TextView tvcategory = findViewById(R.id.tv_Cartegories);

        tvname.setText(name);
        tvprice.setText(price);
        tvphone.setText(phone);
        Linkify.addLinks(tvphone, Linkify.PHONE_NUMBERS);


        String clickUrl = storeurl;
        tvurl.setText(clickUrl);
        tvurl.setMovementMethod(LinkMovementMethod.getInstance());

        tvcategory.setText(category);

        TextView tvday0 = findViewById(R.id.tv_day0);
        TextView tvhour0 = findViewById(R.id.tv_hour0);
        TextView tvday1 = findViewById(R.id.tv_day1);
        TextView tvhour1 = findViewById(R.id.tv_hour1);
        TextView tvday2 = findViewById(R.id.tv_day2);
        TextView tvhour2 = findViewById(R.id.tv_hour2);
        TextView tvday3 = findViewById(R.id.tv_day3);
        TextView tvhour3 = findViewById(R.id.tv_hour3);
        TextView tvday4 = findViewById(R.id.tv_day4);
        TextView tvhour4 = findViewById(R.id.tv_hour4);
        TextView tvday5 = findViewById(R.id.tv_day5);
        TextView tvhour5 = findViewById(R.id.tv_hour5);
        TextView tvday6 = findViewById(R.id.tv_day6);
        TextView tvhour6 = findViewById(R.id.tv_hour6);

        for (int x=0; x < arrayWeekly.size();x++) {

            int day = arrayWeekly.get(x).getDay();
            switch (day){
                case 0:
                    tvday0.setText(arrayWeekly.get(x).getWeekDay());
                    tvhour0.setText(arrayWeekly.get(x).getHours());
                    break;
                case 1:
                    tvday1.setText(arrayWeekly.get(x).getWeekDay());
                    tvhour1.setText(arrayWeekly.get(x).getHours());
                    break;
                case 2:
                    tvday2.setText(arrayWeekly.get(x).getWeekDay());
                    tvhour2.setText(arrayWeekly.get(x).getHours());
                    break;
                case 3:
                    tvday3.setText(arrayWeekly.get(x).getWeekDay());
                    tvhour3.setText(arrayWeekly.get(x).getHours());
                    break;
                case 4:
                    tvday4.setText(arrayWeekly.get(x).getWeekDay());
                    tvhour4.setText(arrayWeekly.get(x).getHours());
                    break;
                case 5:
                    tvday5.setText(arrayWeekly.get(x).getWeekDay());
                    tvhour5.setText(arrayWeekly.get(x).getHours());
                    break;
                case 6:
                    tvday6.setText(arrayWeekly.get(x).getWeekDay());
                    tvhour6.setText(arrayWeekly.get(x).getHours());
                    break;
                    default:
                        break;
            }

        }
        Picasso.get().load(image).into(ivpicture);
    }

    private void getYelpBusinessDetail (String param_id_business){
        //build URL
        String url = "";
        url = "https://api.yelp.com/v3/businesses/" + param_id_business;

        // get data
        //RequestQueue queue = Volley.newRequestQueue(YelpDetail.this);
        RequestQueueSignleton queueSingle = RequestQueueSignleton.getInstance(YelpDetail.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        int z = 1;
                        try {
                            //JSONObject object = response.getJSONObject("");
                            //name, phone, URL, categories, price class and weekly operating hours.
                            name = response.getString("name");

                            try{
                                phone = response.getString("phone");
                            }catch(JSONException e){
                                Toast.makeText(YelpDetail.this, e.toString(), Toast.LENGTH_LONG).show();
                            }

                            try{
                                price = response.getString("price");
                            }catch(JSONException e){
                                Toast.makeText(YelpDetail.this, e.toString(), Toast.LENGTH_LONG).show();
                            }

                            try{
                                image = response.getString("image_url");
                            }catch(JSONException e){
                                Toast.makeText(YelpDetail.this, e.toString(), Toast.LENGTH_LONG).show();
                            }

                            try{
                                storeurl = response.getString("url");
                            }catch(JSONException e){
                                Toast.makeText(YelpDetail.this, e.toString(), Toast.LENGTH_LONG).show();
                            }

                            //Categories
                            JSONArray categories = response.getJSONArray("categories");
                            category ="";
                            for (int x = 0; x < categories.length(); x++) {
                                JSONObject objCategory = categories.getJSONObject(x);
                                category = category + objCategory.getString("title") + " ";
                            }

                            //Weekly hours
                            arrayWeekly.clear();
                            JSONArray weekly = response.getJSONArray("hours");
                            JSONObject arrayOpen = weekly.getJSONObject(0);
                            JSONArray open = arrayOpen.getJSONArray("open");

                            for (int x = 0; x < open.length(); x++) {
                                JSONObject objOpen = open.getJSONObject(x);

                                WeeklyHours wh = new WeeklyHours();

                                wh.setDay(objOpen.getInt("day"));
                                wh.setStart(objOpen.getString("start"));
                                wh.setEnd(objOpen.getString("end"));

                                arrayWeekly.add(wh);
                            }

                        } catch (JSONException e) {
                            Toast.makeText(YelpDetail.this, e.toString(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(YelpDetail.this, error.toString(), Toast.LENGTH_LONG).show();
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

}

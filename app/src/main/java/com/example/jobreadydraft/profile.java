package com.example.jobreadydraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class profile extends AppCompatActivity {

   ;


    private TextView usernameTextView, locationTextView, aboutTextView, experienceTextView , skill1TextView;
    private String username, location, about, experience, skill1;

    private static final String MY_PREFS_NAME = "MyPrefsFile";
    private static final String USERNAME_KEY = "username";

    private String url = "http://10.0.2.2/R/GetUserProfile.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameTextView = findViewById(R.id.username);
        locationTextView = findViewById(R.id.location);
        aboutTextView = findViewById(R.id.about);
        experienceTextView = findViewById(R.id.experience);



        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredUsername = prefs.getString(USERNAME_KEY, "");
        Log.d("username",restoredUsername);
        if (restoredUsername.equals("No username defined")) {
            //Do something ...
        }

        getUserInfoFromDatabase(restoredUsername);
    }

    private void getUserInfoFromDatabase(String username) {
      StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("profile", "Response: " + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String location = jsonObject.getString("location");
                            String about = jsonObject.getString("about");
                            String experience = jsonObject.getString("headline");


                            usernameTextView.setText(username);
                            locationTextView.setText(location);
                            aboutTextView.setText(about);
                            experienceTextView.setText(experience);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(profile.this, "JSON Exception", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(profile.this, "Error occurred", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(profile.this);
        requestQueue.add(stringRequest);

    }

}




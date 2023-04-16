package com.example.jobreadydraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class post extends AppCompatActivity {

    private TextView usernameTextView , postTextView;
    private String url = "http://10.0.2.2/R/addPost.php";
    private Button postBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        usernameTextView = findViewById(R.id.username);
        postTextView = findViewById(R.id.post);
        postBtn = findViewById(R.id.postbtn);

        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");
        String user_id = intent.getStringExtra("USERID");


        usernameTextView.setText(username);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String post = postTextView.getText().toString().trim();

                if (post.isEmpty()) {
                    Toast.makeText(post.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("postaAtivity", "Response: " + response);


                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        int success = jsonObject.getInt("success");

                                        if (success == 1) {
                                            Toast.makeText(post.this, "posted", Toast.LENGTH_SHORT).show();



                                            Intent intent = new Intent(post.this, profile.class);
                                            startActivity(intent);

                                        } else {
                                            Toast.makeText(post.this, "", Toast.LENGTH_SHORT).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(post.this, "JSON Exception", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    Toast.makeText(post.this, "Error occurred", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("user_id", user_id);
                            params.put("post", post);
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(post.this);
                    requestQueue.add(stringRequest);
                }
            }
        });


    }
}
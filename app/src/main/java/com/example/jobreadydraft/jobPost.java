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
import com.example.jobreadydraft.R;
import com.example.jobreadydraft.profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class jobPost extends AppCompatActivity {

    private TextView companyNameTextView , jobTitleTextView, jobDescriptionTextView;
    private String url = "http://10.0.2.2/R/addjob.php";
    private Button postBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_post);


        companyNameTextView = findViewById(R.id.companyName);
        jobTitleTextView = findViewById(R.id.jobTitle);
        jobDescriptionTextView = findViewById(R.id.jobDescription);
        postBtn = findViewById(R.id.postbtn);

        Intent intent = getIntent();
        String compnayName = intent.getStringExtra("COMPANYNAME");
        String company_id = intent.getStringExtra("COMPANY_ID");


        companyNameTextView.setText(compnayName);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Title = jobTitleTextView.getText().toString().trim();
                final String Description = jobDescriptionTextView.getText().toString().trim();

                if (Title.isEmpty()||Description.isEmpty()) {
                    Toast.makeText(com.example.jobreadydraft.jobPost.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("jobPostActivity", "Response: " + response);


                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        int success = jsonObject.getInt("success");

                                        if (success == 1) {
                                            Toast.makeText(com.example.jobreadydraft.jobPost.this, "posted", Toast.LENGTH_SHORT).show();





                                        } else {
                                            Toast.makeText(com.example.jobreadydraft.jobPost.this, "", Toast.LENGTH_SHORT).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(com.example.jobreadydraft.jobPost.this, "JSON Exception", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    Toast.makeText(com.example.jobreadydraft.jobPost.this, "Error occurred", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("company_id", company_id);
                            params.put("Title", Title);
                            params.put("Description", Description);

                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(com.example.jobreadydraft.jobPost.this);
                    requestQueue.add(stringRequest);
                }
            }
        });


    }
}
package com.rafsan.class270databasequerysearch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    EditText edName;
    Button buttonSearch;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edName);
        buttonSearch = findViewById(R.id.buttonSearch);
        textView = findViewById(R.id.textView);



        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myName = edName.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                String url = "https://ali71.000webhostapp.com/apps/querysearch.php?name="+myName;
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                Log.d("serverRes", String.valueOf(response));
                                textView.setText(response.toString());

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Search Error")
                                .setMessage(error.toString())
                                .show();
                        
                    }
                }
                );
                queue.add(jsonArrayRequest);

            }
        });

    }
}
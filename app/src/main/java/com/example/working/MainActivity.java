package com.example.working;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String url = "http://178.128.127.249:5000/api/habits";
    private ProgressDialog dialog;
    private List<item> array;
    private ListView listView;
    private ItemAdapter itemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //array
        array= new ArrayList<>();

        listView = findViewById(R.id.list_item);
        itemAdapter = new ItemAdapter(this , array);


        listView.setAdapter(itemAdapter);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading.....");
        dialog.show();

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                hideDialog();



                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject obj = response.getJSONObject(i);


                        item item = new item();

                        Log.i("working ", "Description :" + obj.getString("description"));
                        Log.i("working ", "Id :" + obj.getString("id"));
                        Log.i("working ", "Name :" + obj.getString("name"));
                        Log.i("working ", "Reward :" + obj.getString("reward"));
                        Log.i("working ", "Punishment :" + obj.getString("punishment"));




                        item.setName(obj.getString("name"  ));
                        item.setDescription(obj.getString("description"));
                        item.setPunishment(obj.getString("punishment"));
                        item.setId(obj.getInt("id"));
                        item.setReward(obj.getString("reward"));

                        array.add(item);
                        itemAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        AppController.getmInstance().addToRquestQueue(jsonArrayRequest);
    }


    public void hideDialog(){
        if (dialog != null){

            dialog.dismiss();
            dialog = null;

        }
    }
}

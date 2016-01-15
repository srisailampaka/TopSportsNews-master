package com.topsportsnews.app.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.topsportsnews.activities.R;
import com.topsportsnews.app.ServiceConnections.ConnectionDetector;
import com.topsportsnews.app.ServiceConnections.ResultsCallback;
import com.topsportsnews.app.ServiceConnections.SportsAsyncTask;
import com.topsportsnews.app.adapters.SportsAdapter;
import com.topsportsnews.app.models.SportsItem;
import com.topsportsnews.app.utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ResultsCallback {

    private RecyclerView mRecyclerView;
    private SportsAdapter adapter;
    private String category="sports";
    private String mainUrl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainUrl= Utils.baseUrl+category+"/30.json?api-key="+Utils.key;
        // Initialize recycler view
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ConnectionDetector connectionDetector = new ConnectionDetector(MainActivity.this);
        if(connectionDetector.isOnline()){
            getSportsNews();
        }else{

            showAlertDialogue(getResources().getString(R.string.network_not_available_message));
        }
    }

    private void showAlertDialogue(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(message)
                .setTitle(R.string.dialog_title);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                //Toast.makeText(MainActivity.this, "You clicked yes button", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void getSportsNews() {
        // Downloading data from below url
        new SportsAsyncTask(this,MainActivity.this).execute(mainUrl);
    }


    @Override
    public void onSuccess(List<SportsItem> sportsItemList) {
        adapter = new SportsAdapter(MainActivity.this, sportsItemList);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onFailure(String errorMessage) {
        showAlertDialogue(errorMessage);
    }
}

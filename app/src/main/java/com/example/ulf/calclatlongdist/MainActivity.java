package com.example.ulf.calclatlongdist;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    static String TAG = "MainActivity";
    public String lat1;
    public String lat2;
    public String long1;
    public String long2;
    public double distbet;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private Haversine myHav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        final TextView txtViewDist = (TextView)(findViewById(R.id.txtViewDist));
        final EditText txtLat1 = (EditText)findViewById(R.id.txtLat1);
        final EditText txtLat2 = (EditText)findViewById(R.id.txtLat2);
        final EditText txtLong1 = (EditText)findViewById(R.id.txtLong1);
        final EditText txtLong2 = (EditText)findViewById(R.id.txtLong2);
        Button btnCalculate = (Button)findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        lat1 = txtLat1.getText().toString();
                        lat2 = txtLat2.getText().toString();
                        long1 = txtLong1.getText().toString();
                        long2 = txtLong2.getText().toString();

                        Integer lat1Int = Integer.valueOf(lat1);
                        Integer lat2Int = Integer.valueOf(lat2);
                        Integer long1Int = Integer.valueOf(long1);
                        Integer long2Int = Integer.valueOf(long2);

                        txtViewDist.setText(txtLat1.getText().toString());

                        myHav = new Haversine();
                        distbet = myHav.HaversineInKM(lat1Int, long1Int, lat2Int, long2Int);
                        String distbetstr = String.valueOf(distbet);

                        Log.i(TAG,"Do the math");

                        txtViewDist.setText(distbetstr);
                    }
                }
        );
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.ulf.calclatlongdist/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.ulf.calclatlongdist/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

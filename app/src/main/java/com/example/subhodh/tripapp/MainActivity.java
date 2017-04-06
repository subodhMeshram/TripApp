package com.example.subhodh.tripapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    Date interestingDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Button startButton= (Button)findViewById(R.id.start);
        Button endButton=(Button)findViewById(R.id.end);
        assert startButton != null;
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map=new Intent(getApplicationContext(),MapsActivity.class);
                interestingDate = new Date();
                System.out.println("Time on first"+interestingDate.getTime());
                startActivity(map);
            }
        });
        if (endButton != null) {
            endButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(interestingDate==null){
                       return;
                    }
                    long diffTime=(new Date()).getTime() - interestingDate.getTime();
                    String properdiff=null;
                try {
                     properdiff = String.format("%02d:%02d:%02d",
                            TimeUnit.MILLISECONDS.toHours(diffTime),
                            TimeUnit.MILLISECONDS.toMinutes(diffTime) -
                                    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diffTime)),
                            TimeUnit.MILLISECONDS.toSeconds(diffTime) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diffTime)));
                }
                catch(Exception e) {
                    Toast.makeText(MainActivity.this, "String Errr", Toast.LENGTH_SHORT).show();
                    }
                    double dd=MapsActivity.DistanceTravelled;
                    int disM=(int)dd;
                    DecimalFormat df = new DecimalFormat("#0.000");
                    double disKM=dd/1000;
                    System.out.println("Total distance travelled From your Path in Meters:"+dd);
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Distance Travelled");
                    alertDialog.setMessage("Total Distance Traveled in Km:"+df.format(disKM)+"\n Distance Traveled in Meter:"+disM+"\n Time Taken to travel:"+properdiff);
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();

                }
            });
        }

    }
    /*private void startTrip(){
        Intent map=new Intent(this,MapsActivity.class);
        startActivity(map);
    }*/
}

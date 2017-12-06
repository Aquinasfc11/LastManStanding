package com.example.niall.lastmanstanding;

/**
 * Created by Niall on 06/12/2017.
 */

//imports used for the home page
import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

/**
 * Created by Dave on 07/03/2017.
 */

public class Home extends AppCompatActivity {

    //Declaring variables
    public static String variable = "username";
    Button startquiz;
    Button upload;
    Button leaderboard;
    Button help;
    Button Logout;
    TextView data;
    TextView welcome;

    //on create method for when the page loads
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //use the home.xml code for XML
        setContentView(R.layout.home);

        //Locate and reference textview by ID found in XML to be used in methods
        welcome = (TextView) findViewById(R.id.welcomeID);

        //Get intent from previous page, if it exists then set as Username
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //Set text of textview as the Username store in extras of intent
            welcome.setText("" + extras.getString("USERNAME"));
            variable = extras.getString("USERNAME");
        }
        //Reference buttons by their ID found within the XML to be used in on click listener
        upload = (Button) findViewById(R.id.button2);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent to change to Upload class
                Intent intent = new Intent(Home.this, Upload.class);
                startActivity(intent);
            }
        });

        startquiz = (Button) findViewById(R.id.buttonstart);
        startquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent to change to Categories class
                Intent intent = new Intent(Home.this, Categories.class);
                startActivity(intent);
            }
        });

        leaderboard = (Button) findViewById(R.id.leaderboard);
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent to change to Leaderboard class
                Intent intent = new Intent(Home.this, Leaderboard.class);
                startActivity(intent);
            }
        });
        help = (Button) findViewById(R.id.helpButton);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent to change to Help class
                Intent intent = new Intent(Home.this, Help.class);
                startActivity(intent);
            }
        });
        //Initially set logout as button found using ID stored in XML
        Logout = (Button) findViewById(R.id.logout);
        //Create logout listener, which will clear intents and restart app, to return to login page via the splash screen
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}


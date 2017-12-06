package com.example.niall.lastmanstanding;

/**
 * Created by Niall on 06/12/2017.
 */

import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

/**
 * Created by Niall on 07/03/2017.
 */

public class Login extends AppCompatActivity {

    //variables declared
    Button loginButton;
    Button register;
    EditText user, pass;
    DatabaseHelper db;
    Cursor cursor;

    //on create method when page loads
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final DatabaseHelper db = new DatabaseHelper(this);

//references items by ID within the XML to be used in methods
        user = (EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);
        loginButton = (Button) findViewById(R.id.button);
        register = (Button) findViewById(R.id.button2);
        //OnClickListener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View argO) {
                //Referenced edittect from XML
                EditText a = (EditText) findViewById(R.id.editText);
                String str = a.getText().toString();
                String userTest = user.getText().toString();
                //Referenced edittect from XML
                EditText p = (EditText) findViewById(R.id.editText2);
                String pass = p.getText().toString();
                String password = db.searchPass(str);

                //validation to ensure data is entered in the required fields
                if (str.equals("") || pass.equals("")) {
                    Toast.makeText(Login.this, "Required Fields", Toast.LENGTH_LONG).show();
                    return;
                }
                //if password  is correct then log the user in and take them to the home page
                if (pass.equals(password)) {
                    Intent i = new Intent(Login.this, Home.class);
                    i.putExtra("USERNAME", str);
                    startActivity(i);
                    //welcome message with the user's username
                    Toast.makeText(Login.this, "Welcome  " + userTest, Toast.LENGTH_SHORT).show();
                    //if credentials are wrong them display a unsuccessful message
                } else {
                    Toast.makeText(Login.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //intent to bring the user from the login to register page if they haven't registered yet
        register = (Button) findViewById(R.id.button2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}

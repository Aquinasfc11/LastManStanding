package com.example.niall.lastmanstanding;

/**
 * Created by Niall on 06/12/2017.
 */

//import statements used for register page
import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;
        import android.content.Context;

/**
 * Created by Niall on 07/03/2017.
 */

public class Register extends AppCompatActivity {

    //Variables Declared
    DatabaseHelper myDB;
    Button submit;
    Context context = this;
    EditText editName, editSurname, editConfirmPassword;

    //On Create method when page loads
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Using XMl code from register.xml
        setContentView(R.layout.register);
        //Using the database
        myDB = new DatabaseHelper(this);

        //references items by ID within the XML to be used in methods
        editName = (EditText) findViewById(R.id.editText3);
        editSurname = (EditText) findViewById(R.id.editText5);
        editConfirmPassword = (EditText) findViewById(R.id.editText6);
        submit = (Button) findViewById(R.id.button3);
        //Add data method
        AddData();
    }

    public void AddData() {
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//Gets the Text from the Textbox and storing as a string
                        String editNamez = editName.getText().toString();
                        String editSurnamez = editSurname.getText().toString();
                        String editConfirmPass = editConfirmPassword.getText().toString();
//Validation to ensure username is longer than 4 characters
                        if (editNamez.length() < 4) {
                            Toast.makeText(Register.this, "Username too short, minimum of 4 characters required", Toast.LENGTH_LONG).show();
                            return;
                        }
                        //Validation to ensure password is longer than 6 characters
                        if (editSurnamez.length() < 6) {
                            Toast.makeText(Register.this, "Password too short, minimum of 6 characters required", Toast.LENGTH_LONG).show();
                            return;
                        }
                        //Validation to ensure that something is entered
                        if (editNamez.equals("") || editSurnamez.equals("") || editConfirmPass.equals("")) {
                            Toast.makeText(Register.this, "Required Fields", Toast.LENGTH_LONG).show();
                            return;
                        }
                        //Validation to ensure that passwords match
                        if (!editSurnamez.equals(editConfirmPass)) {
                            Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                            return;
                        }
                        //Insert statement to insert the data into the database table users
                        else {
                            boolean isInserted = myDB.insertData(editName.getText().toString(), editSurname.getText().toString());
                            if (isInserted == true)
                                Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                        }
                    }


                });
    }
}






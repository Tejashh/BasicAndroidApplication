//
// Name: Tejash Contractor
// CS 478
// Project 1
// Description: Creating Android applicaiton which contains two buttons. First is where if user press the
// button, it takes them to SecondAcitivity where user has to input their name. After hitting done or enter
// it will take the app to main acitivity where user has to press other button. If the name which was
// input in the second activity legal the it will take the application to new contact menu. if the name
// is not legal, then it will just say that its not legal.
//
// MainActivity.java
//
package com.example.tejashcontractor.cs478project1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Global Variables:
    private static final int PICK_NAME_REQUEST = 0;
    String name;
    boolean valid_name;
    Button second_button;

    @Override   //onCreate method
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //second_button Action Listener
        second_button = (Button) findViewById(R.id.button2);
        second_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAddContectMenu(v);
            }
        }); //end of the OnClickListener():
    }//end of the onCreate:

    //Creation of Button1:
    public void Button1(View view){
        Intent newActivity = new Intent(this,SecondActivity.class);
        startActivityForResult(newActivity,PICK_NAME_REQUEST);
    }//End of the Button1:

    //
    //method that deals with the result returned from the second activity
    //
    //Reference: https://developer.android.com/training/basics/intents/result#java
    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        // CHekcing the request that we want:
        if (requestCode == PICK_NAME_REQUEST) {
            //Sucessful request:
            if (resultCode == RESULT_OK) {
                name = resultIntent.getStringExtra("Name");
                //System.out.println("------------->" + str + "<-----------------");
                valid_name = true;
            }
            else{ // not sucess:
                name = resultIntent.getStringExtra("Name");
                valid_name = false;
            }
        }
    }//end of the onActivityResult:


    //
    // displayAddContectMenu which displays the add contact menu where the
    // name is displayed:
    // Reference: https://www.techjini.com/blog/insert-and-modify-contact-in-android/
    //            https://stackoverflow.com/questions/14278587/insert-a-new-contact-intent
    //
    public void displayAddContectMenu(View view)
    {
        //Legal name is inputed:
        if ( valid_name == true)
        {
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.NAME,name);
            startActivity(intent);
        }
        //name is not legal:
        else if ( valid_name == false)
        {
            //toast with makeText to see if the non-legal name is added therefore its not gonna be display in the called:
            //
            // Reference: https://stackoverflow.com/questions/3500197/how-to-display-toast-in-android
            //
            Toast.makeText(getActivity(),"The input name " + name + "is not legal",Toast.LENGTH_LONG).show();
        }
    }

    // getActivity Method:
    public Context getActivity() {
        return MainActivity.this;
    }   //end of the getActivity:
}//En of the MainActivity.java


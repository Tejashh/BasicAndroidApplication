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
// SecondActivity.java
//

package com.example.tejashcontractor.cs478project1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.isUpperCase;

//SecondActivity class
public class SecondActivity extends AppCompatActivity {

    String name;
    EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nameInput = (EditText) findViewById(R.id.nameInput);

        nameInput.setOnEditorActionListener(new EditText.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //if done or enter button is pressed:
                if ( event != null &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                        || actionId == EditorInfo.IME_ACTION_DONE)
                {
                    sendResult(v);  //Send result method called:
                    return true;
                } else {
                    return false;
                }
            }
        });
    }   //end of the onCreate overide:

    //
    //sendResult method sends the REULT_OK or RESULT_CANCELED depending on the Legal name or not:
    //
    public void sendResult(View view){

        // taking the input from nameInput textView as string.
        String name = nameInput.getText().toString();

        //Splitting string into First name and last name avoiding space inbetween:
        String[] split_name_string = name.split("\\s+");

        char first_char_first_name = split_name_string[0].charAt(0);
        char first_char_last_name = split_name_string[1].charAt(0);

        boolean checking_first_char_first_name = isStringAlpha(split_name_string[0]);
        boolean checking_first_char_last_name = isStringAlpha(split_name_string[1]);

        //System.out.println(isUpperCase(first_char_first_name) + "->" + isUpperCase(first_char_last_name) + "->" + flag1 + flag2);

        //IF the name is legal:

        if( isUpperCase(first_char_first_name) == true &&
                isUpperCase(first_char_last_name) == true &&
                checking_first_char_first_name == true && checking_first_char_last_name == true)
        {
            //System.out.println("Name is correct");

            //Sending RESULT_OK to MainActivity
            Intent i = new Intent();
            i.putExtra("Name", name );
            setResult(Activity.RESULT_OK, i);
            finish();
        }
        //if the name is not legal:
        else {
            //System.out.println("Name is NOt correct");

            //Sendint RESULT_CANCELED to MainActivity
            Intent i = new Intent();
            i.putExtra("Name", name );
            setResult(Activity.RESULT_CANCELED, i);
            finish();
        }
    }   //end of the sendResult method:

    //
    // Reference: https://codereview.stackexchange.com/questions/150597/checking-to-see-if-a-string-is-alphabetic
    //
    // isStringAlpha boolean method which takes string and check
    // to see if the string contains only alphabets:
    //
    public boolean isStringAlpha(String aString) {
        int i = 0;
        while(i != aString.length())
        {

            //Checking each character of string contains something other than alphabet if yes then return false
            if(!Character.isLetter((aString.charAt(i))))
            {
                return false;
            }
            ++i;    //increment while loop
        }
        return true;
    }   //end of the isStringAlpha method:


}   //end of the secondActivity Class:

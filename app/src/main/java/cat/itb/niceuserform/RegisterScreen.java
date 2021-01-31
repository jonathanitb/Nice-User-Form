package cat.itb.niceuserform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class RegisterScreen extends AppCompatActivity {
    DatePickerDialog picker;
    TextInputLayout til_username,til_Gender,til_password,til_repeatpassword,til_email,til_name,til_surname,til_birth;
    AutoCompleteTextView dropDownText;
    TextInputEditText username,password,repeat_password,email,name,surname,birth;
    Button register;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        dropDownText=findViewById(R.id.gender);
        register=findViewById(R.id.register_register);

        username=findViewById(R.id.register_username);
        birth=findViewById(R.id.register_birth);
        password=findViewById(R.id.register_password);
        repeat_password=findViewById(R.id.register_repeatpassword);
        email=findViewById(R.id.register_email);
        name=findViewById(R.id.register_name);
        surname=findViewById(R.id.register_surname);


        til_password=findViewById(R.id.til_register_password);
        til_username=findViewById(R.id.til_register_username);
        til_Gender=findViewById(R.id.til_gender);
        til_repeatpassword=findViewById(R.id.til_repeatpassword);
        til_email=findViewById(R.id.til_email);
        til_name=findViewById(R.id.til_name);
        til_surname=findViewById(R.id.til_surname);
        til_birth=findViewById(R.id.til_birth);

        createDropDown();
        birthPicker();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( fieldFilled(username, til_username) && fieldFilled(birth, til_birth)&& fieldFilled(email,til_email) && fieldFilled(name,til_name) &&
                        fieldFilled(surname,til_surname) &&fieldFilled(dropDownText,til_Gender)&&passwordControl(password,til_password)&& passwordControl(repeat_password,til_repeatpassword)){
                    Intent  intent=new Intent(RegisterScreen.this,WelcomeScreen.class);
                    startActivity(intent);

                }else {
                    fieldFilled(username, til_username);
                    fieldFilled(birth, til_birth);
                    fieldFilled(email,til_email);
                    fieldFilled(name,til_name);
                    fieldFilled(surname,til_surname);
                    passwordControl(password,til_password);
                    passwordControl(repeat_password,til_repeatpassword);
                    fieldFilled(dropDownText,til_Gender);

                }

            }
        });

    }

    private void createDropDown(){
        String[]items=new String[]{
                "Male",
                "Female",
                "Not binary"
        };
        ArrayAdapter<String>adapter=new ArrayAdapter<>(RegisterScreen.this,R.layout.dropdown_items,items);
        dropDownText.setAdapter(adapter);
    }

    private void birthPicker(){
       birth.setInputType(InputType.TYPE_NULL);
        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr= Calendar.getInstance();
                int day= cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);

                picker=new DatePickerDialog(RegisterScreen.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                birth.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                            }
                        },year,month,day);
                picker.show();
            }
        });
    }

    public static boolean fieldFilled(TextInputEditText editText,TextInputLayout til){
        String text=editText.getText().toString().trim();
        if (text.isEmpty()){
            til.setError("This field cannot be empty");
            return false;
        }else {
            til.setErrorEnabled(false);
            return true;
        }
    }

    private boolean fieldFilled(AutoCompleteTextView editText,TextInputLayout til){
        String text=editText.getText().toString().trim();
        if (text.isEmpty()){
            til.setError("This field cannot be empty");
            return false;
        }else {
            til.setErrorEnabled(false);
            return true;
        }
    }

    private boolean passwordControl(TextInputEditText editText,TextInputLayout til){
        String password=editText.getText().toString().trim();
        if (password.isEmpty()){
            til.setError("This field cannot be empty");
            return false;
        }else if(password.length()<8){
            til.setError("The password must have at least 8 characters");
            return false;
        }else {
            til.setErrorEnabled(false);
            return true;
        }

    }


}
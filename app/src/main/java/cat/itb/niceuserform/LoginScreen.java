package cat.itb.niceuserform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginScreen extends AppCompatActivity {
    TextInputLayout til_username,til_password;
    TextInputEditText username,password;
    Button register,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        til_username=findViewById(R.id.til_log_user);
        til_password=findViewById(R.id.til_log_pass);

        username=findViewById(R.id.login_username);
        password=findViewById(R.id.login_password);

        login=findViewById(R.id.login_login);
        register=findViewById(R.id.login_register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RegisterScreen.fieldFilled(username,til_username)&&RegisterScreen.fieldFilled(password,til_password)){
                    Intent intent=new Intent(LoginScreen.this,WelcomeScreen.class);
                    startActivity(intent);
                }else {
                    RegisterScreen.fieldFilled(username,til_username);
                    RegisterScreen.fieldFilled(password,til_password);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginScreen.this,RegisterScreen.class);
                startActivity(intent);
            }
        });

    }
}
package me.solacruz.instagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private EditText usernameIn;
    private EditText passwordIn;
    private Button logIn;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameIn=findViewById(R.id.evUsername);
        passwordIn=findViewById(R.id.evPassword);
        logIn=findViewById(R.id.bvLogIn);
        signUp=findViewById(R.id.bvSignUp);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameIn.getText().toString();
                final String password = passwordIn.getText().toString();

                login(username, password);
            }
        });
    }

    private void login(String username, String password){
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e==null){
                    Log.d("LoginActivity", "Login Successful!");

                    Intent intent= new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);

                    finish();
                }
                else{
                    Log.e("LoginActivity","Login Failed");
                    e.printStackTrace();
                }
            }
        });
    }
}

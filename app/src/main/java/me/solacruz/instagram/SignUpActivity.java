package me.solacruz.instagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    private EditText newUsername;
    private EditText newEmail;
    private EditText newPassword;
    private Button createAccount;
    private Button returnToLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        newUsername=findViewById(R.id.evInputUsername);
        newEmail=findViewById(R.id.evInputEmail);
        newPassword=findViewById(R.id.evInputPassword);
        createAccount=findViewById(R.id.bvCreateAccount);
        returnToLogIn=findViewById(R.id.bvReturnToLogIn);
    }
}

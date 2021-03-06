package me.solacruz.instagram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {

    private Button logOut;

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

       logOut=findViewById(R.id.bvLogOut);

       logOut.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view) {
               ParseUser.logOut();
               ParseUser currentUser = ParseUser.getCurrentUser();

               Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
               startActivity(intent);
               finish();
           }
       });

       bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent intent=new Intent(ProfileActivity.this, HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_profile:
                        return true;
                    case R.id.action_upload:
                        Intent intent3=new Intent(ProfileActivity.this, CameraActivity.class);
                        startActivity(intent3);
                        return true;
                }
                return true;
            }
        });
    }
}

package me.solacruz.instagram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CameraActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent intent=new Intent(CameraActivity.this, HomeActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_profile:
                        Intent intent2=new Intent(CameraActivity.this, ProfileActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.action_upload:
                        return true;
                }
                return true;
            }
        });
    }
}

package me.solacruz.instagram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.solacruz.instagram.model.Post;



public class HomeActivity extends AppCompatActivity {

    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPosts;

    BottomNavigationView bottomNavigationView;
    SwipeRefreshLayout swipeContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvPosts = findViewById(R.id.rvPost);
        posts=new ArrayList<>();
        postAdapter= new PostAdapter(posts);

        rvPosts.setLayoutManager(new LinearLayoutManager(this));
        rvPosts.setAdapter(postAdapter);

        loadTopPosts();

        bottomNavigationView= findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        return true;
                    case R.id.action_profile:
                        Intent intent2=new Intent(HomeActivity.this, ProfileActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.action_upload:
                        Intent intent3=new Intent(HomeActivity.this, CameraActivity.class);
                        startActivity(intent3);
                        return true;
                }
                return true;
            }
        });

        // Lookup the swipe container view
        swipeContainer = findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // once the network request has completed successfully.
                loadTopPosts();
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }


    private void loadTopPosts(){
        final Post.Query postsQuery= new Post.Query();
        postsQuery.getTop().withUser();

        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if(e==null){
                    Post post;
                    posts.clear();
                    postAdapter.notifyDataSetChanged();
                    for(int i=0; i<objects.size(); i++){
                        post= objects.get(i);
                        posts.add(0, post);
                        postAdapter.notifyItemInserted(0);
                        Log.d("HomeActivity", "Post [" +i + "]="
                                +objects.get(i).getDescription()
                                + "\nusername  = "+objects.get(i).getUser().getUsername()
                        );
                    }
                    swipeContainer.setRefreshing(false);
                }
                else{
                    Log.e("HomeActivity", "Query Failed");
                    e.printStackTrace();
                }
            }
        });
    }


}

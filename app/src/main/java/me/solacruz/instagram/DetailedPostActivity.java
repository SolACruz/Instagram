package me.solacruz.instagram;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.GetCallback;
import com.parse.ParseException;

import me.solacruz.instagram.model.Post;

public class DetailedPostActivity extends AppCompatActivity {

    private ImageView userImage;
    private TextView username;
    private TextView date;
    private TextView description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_post);

        String postId=getIntent().getStringExtra("post");

        userImage=findViewById(R.id.ivUserImage2);
        username=findViewById(R.id.tvUsername2);
        date=findViewById(R.id.tvDate2);
        description=findViewById(R.id.tvDescription2);

        Post.Query query=new Post.Query();

        query.withUser();

        query.getInBackground(postId, new GetCallback<Post>() {
            @Override
            public void done(Post object, ParseException e) {
                if(e==null){
                    username.setText(object.getUser().getUsername());
                    date.setText(object.getCreatedAt().toString());
                    description.setText(object.getDescription());
                    Glide.with(DetailedPostActivity.this).load(object.getImage().getUrl()).into(userImage);
                }
            }
        });

    }
}

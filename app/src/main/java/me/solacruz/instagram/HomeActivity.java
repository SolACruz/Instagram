package me.solacruz.instagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rvPosts = findViewById(R.id.rvPost);
        posts=new ArrayList<>();
        postAdapter= new PostAdapter(posts);

        loadTopPosts();

    }

    private void loadTopPosts(){
        final Post.Query postsQuery= new Post.Query();
        postsQuery.getTop().withUser();

        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if(e==null){
                    for(int i=0; i<objects.size(); i++){
                        posts.add(objects.get(i));
                        postAdapter.notifyItemInserted(posts.size()-1);
                        Log.d("HomeActivity", "Post [" +i + "]="
                                +objects.get(i).getDescription()
                                + "\nusername  = "+objects.get(i).getUser().getUsername()
                        );
                    }
                }
                else{
                    Log.e("HomeActivity", "Query Failed");
                    e.printStackTrace();
                }
            }
        });
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if(resultCode==RESULT_OK_CODE && requestCode==REQUEST_CODE){
//            Tweet tweet= Parcels.unwrap(data.getParcelableExtra(Tweet.class.getSimpleName()));
//            tweets.add(0, tweet);
//            tweetAdaptor.notifyItemInserted(0);
//            rvTweets.scrollToPosition(0);
//            Toast.makeText(this, "Tweet Posted!", Toast.LENGTH_LONG);
//        }
//
//        // Use data parameter
//        //Tweet tweet = (Tweet) data.getSerializableExtra("tweet");
//    }
//
//    private final int REQUEST_CODE = 20;
//    private final int RESULT_OK_CODE=20;
//    // FirstActivity, launching an activity for a result
//    public void composeMessage() {
//        Intent i = new Intent(this, ComposeTweet.class);
//        i.putExtra("mode", 2); // pass arbitrary data to launched activity
//        startActivityForResult(i, REQUEST_CODE);
//    }
}

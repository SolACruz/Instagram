package me.solacruz.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import me.solacruz.instagram.model.Post;

public class ParseApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        final Parse.Configuration configuration=new Parse.Configuration.Builder(this)
                .applicationId("InstaAppId")
                .clientKey("SolInstaMasterKey")
                .server("http://solacruz-instagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }
}

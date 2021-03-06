package me.solacruz.instagram;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import me.solacruz.instagram.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{
    private List<Post> mPost;
    Context context;
    Post post;
    //pass in the tweets array into the constructor
    public PostAdapter(List<Post> posts){
        mPost=posts;
    }

    // getRelativeTimeAgo("Mon Apr 01 21:16:23 +0000 2014");
    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

    //for each row, inflate the layout and cache references in the ViewHolder

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View tweetView= inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder= new ViewHolder(tweetView);
        return viewHolder;
    }


    //bind the values based on the position of the element in the row view

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //get the data according to its position
        post=mPost.get(position);

        //give the views the data in the correct order/populate the views
        holder.tvUsername.setText(post.getUser().getUsername());
        holder.tvDescription.setText(post.getDescription());
        holder.tvDate.setText(post.getCreatedAt().toString());

        Glide.with(context).load(post.getImage().getUrl()).into(holder.ivUserImage);
    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    //create the viewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView ivUserImage;
        public TextView tvUsername;
        public TextView tvDescription;
        public TextView tvDate;


        public ViewHolder (View itemView){
            super(itemView);

            //Find view by id
            ivUserImage=itemView.findViewById(R.id.ivUserImage);
            tvUsername=itemView.findViewById(R.id.tvUsername);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            tvDate=itemView.findViewById(R.id.tvDate);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, DetailedPostActivity.class);
            String object=post.getObjectId();
            intent.putExtra("post", object);
            context.startActivity(intent);
        }
    }

    public void clear(){
        mPost.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> posts){
        mPost.addAll(posts);
        notifyDataSetChanged();
    }

}

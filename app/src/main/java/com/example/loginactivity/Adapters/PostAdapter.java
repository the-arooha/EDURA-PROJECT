package com.example.loginactivity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginactivity.Model.Post;
import com.example.loginactivity.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends  RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public Context mContext;
    public List<Post> mPostList;

    public PostAdapter(Context mContext, List<Post> mPostList) {
        this.mContext = mContext;
        this.mPostList = mPostList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.questions_retrived_layout,parent,false);

        return new PostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Post post=mPostList.get(position);
        if (post.getQuestionimage()==null){
            holder.questionImage.setVisibility(View.GONE);
        }
        holder.questionImage.setVisibility(View.VISIBLE);
        Glide.with(mContext).load(post.getQuestionimage()).into(holder.questionImage);
        holder.expandable_text.setText(post.getQuestion());
        holder.topicTextView.setText(post.getTopic());
        holder.askedOnTextView.setText(post.getDate());

    }

    @Override
    public int getItemCount() {
        return mPostList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView publisher_profile_image;
        public TextView asked_by_Textview,likes,dislikes,comments;
        public ImageView more,questionImage,like,dislike,comment,save;
        public TextView topicTextView,askedOnTextView;
        public ExpandableTextView expandable_text;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            publisher_profile_image=itemView.findViewById(R.id.publisher_profile_image);
            asked_by_Textview=itemView.findViewById(R.id.asked_by_Textview);
            likes=itemView.findViewById(R.id.likes);
            dislikes=itemView.findViewById(R.id.dislikes);
            comments=itemView.findViewById(R.id.comments);
            more=itemView.findViewById(R.id.more);
            questionImage=itemView.findViewById(R.id.questionImage);
            like=itemView.findViewById(R.id.like);
            dislike=itemView.findViewById(R.id.dislike);
            comment=itemView.findViewById(R.id.comment);
            save=itemView.findViewById(R.id.save);
            topicTextView=itemView.findViewById(R.id.topicTextView);
            askedOnTextView=itemView.findViewById(R.id.askedOnTextView);
            expandable_text=itemView.findViewById(R.id.expand_text_view);

        }
    }
}

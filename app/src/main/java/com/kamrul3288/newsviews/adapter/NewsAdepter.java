package com.kamrul3288.newsviews.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kamrul3288.newsviews.R;
import com.kamrul3288.newsviews.model.NewsList;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdepter extends RecyclerView.Adapter<NewsAdepter.NewsViewHolder>{

    private final Activity activity;
    private NewsList newsList = new NewsList();

    public NewsAdepter(Activity activity) {
        this.activity = activity;
    }


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_news_item,viewGroup,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Picasso.get().load(newsList.getArticles().get(position).getUrlToImage()).placeholder(R.drawable.loading).into(holder.newsImage);
        holder.title.setText(newsList.getArticles().get(position).getTitle());
        holder.author.setText("Author: "+newsList.getArticles().get(position).getAuthor());
        holder.source.setText("Source: "+newsList.getArticles().get(position).getSource().getName());
    }

    @Override
    public int getItemCount() {
        return newsList.getArticles().size();
    }

    public void setItem(NewsList newsList){
        this.newsList = newsList;
        notifyDataSetChanged();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.news_item_image)
        ImageView newsImage;

        @BindView(R.id.news_item_title)
        TextView title;

        @BindView(R.id.news_item_author)
                TextView author;
        @BindView(R.id.news_item_source)
                TextView source;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

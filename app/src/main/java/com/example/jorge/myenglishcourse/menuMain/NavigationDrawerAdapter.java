package com.example.jorge.myenglishcourse.menuMain;

/**
 * Created by sspbr on 05/05/2016.
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jorge.myenglishcourse.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by jorgesantos on 19/01/16.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyViewHolder> {
    List<NavDrawerItem> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public NavigationDrawerAdapter(Context context, List<NavDrawerItem> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);

        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavDrawerItem current = data.get(position);
        //if (modulo.position == position) {
        //    holder.title.setTextColor(Color.parseColor("#F83F9B"));
        //}else{
        holder.title.setTextColor(Color.parseColor("#ffffff"));
        //}
        holder.title.setTag(Integer.toString(position));
        holder.title.setText(current.getTitle());
        holder.title.setTypeface(Typeface.createFromAsset(holder.title.getContext().getAssets(), "fonts/Signika-Regular.ttf"));

        if (position == 0) {
            holder.ImageViewtitle.setImageResource(R.mipmap.ic_launcher);
        }else if (position == 1) {
            holder.ImageViewtitle.setImageResource(R.mipmap.ic_launcher);
        }else if (position == 2) {
            holder.ImageViewtitle.setImageResource(R.mipmap.ic_launcher);
        }else if (position == 3) {
            holder.ImageViewtitle.setImageResource(R.mipmap.ic_launcher);
        }else if (position == 4) {
            holder.ImageViewtitle.setImageResource(R.mipmap.ic_launcher);
        }



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView ImageViewtitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            ImageViewtitle = (ImageView) itemView.findViewById(R.id.ImageViewtitle);
            title.setTypeface(Typeface.createFromAsset(title.getContext().getAssets(), "fonts/Signika-Regular.ttf"));
        }
    }
}

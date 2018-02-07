package com.example.jorge.myenglishcourse.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jorge.myenglishcourse.R;
import com.example.jorge.myenglishcourse.model.Topic;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jorge on 07/02/2018.
 * Adapter Topic for fill list for Retrofit
 */

public class AdapterTopic extends RecyclerView.Adapter<AdapterTopic.ViewHolder> {

    /*
 * An on-click handler that we've defined to make it easy for an Activity to interface with
 * our RecyclerView
 */
    private static AdapterTopicOnClickHandler mClickHandler;
    private final List<Topic> data;
    private Context mContext;

    /**
     * Constructs the class
     **/
    public AdapterTopic(AdapterTopicOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
        data = null;
    }

    /**
     * create lit de Adapter Department
     **/
    public AdapterTopic(List<Topic> data) {
        this.data = data;
    }

    /**
     * Create information View holder
     **/
    @Override
    public AdapterTopic.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_detail, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(v);
    }

    /**
     * Create filed bind hold full
     **/
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        /** Create filed bind hold full **/
        Topic topic = ((Topic) data.get(position));
        holder.mNameTextView.setText(topic.getName());
    }

    /**
     * Returns the total Adapter
     **/
    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<Topic> getData() {
        return data;
    }

    /**
     * The interface that receives onClick messages.
     */
    public interface AdapterTopicOnClickHandler {
        void onClick(Topic topic);
    }

    /**
     * class view holder
     **/
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_name_product)
        TextView mNameTextView;

        /**
         * get field of the main for show recyclerView
         **/
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
            v.setOnClickListener(this);
        }

        /**
         * configuration the Event onclick. Pass o Object Department
         **/
        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Topic topic = data.get(adapterPosition);
            mClickHandler.onClick(topic);

        }
    }
}

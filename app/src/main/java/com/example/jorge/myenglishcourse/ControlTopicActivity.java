package com.example.jorge.myenglishcourse;

import android.content.Context;
import android.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jorge.myenglishcourse.model.ListTopic;
import com.example.jorge.myenglishcourse.persistentData.DataBase;
import com.example.jorge.myenglishcourse.common.Common;
import com.example.jorge.myenglishcourse.common.Utilite;
import com.example.jorge.myenglishcourse.interfaceAPI.InterfaceTopic;
import com.example.jorge.myenglishcourse.model.Topic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ControlTopicActivity extends Fragment {

    public View fragmentView;

    private InterfaceTopic mInterfaceTopic;
    private DataBase mDataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.activity_control_topic, container, false);



        if (savedInstanceState == null) {

            /* Once all of our views are setup, we can load the weather data. */
            if (Common.isOnline(getActivity())) {

                // Call Api with Retrofit
                createTopicAPI();

                // Configuration Interface
                mInterfaceTopic.getTopic().enqueue(topicCallback);


            } else {
                Context context = getActivity();
                Toast toast = Toast.makeText(context, R.string.Error_Access, Toast.LENGTH_SHORT);
                toast.show();
            }

        }

        return  fragmentView;


    }


    /**
     * Call Get Information Repositories .
     */
    private Callback<ListTopic<Topic>> topicCallback = new Callback<ListTopic<Topic>>() {
        @Override
        public void onResponse(Call<ListTopic<Topic>> call, Response<ListTopic<Topic>> response) {
            try {
                if (response.isSuccessful()) {
                    List<Topic> data = new ArrayList<>();
                    data.addAll(response.body().items);

                    // Persistent Data for SQLLite
                    mDataBase = new DataBase(getActivity());
                    mDataBase.insertTopic(data);

                } else {
                    Log.d("QuestionsCallback", "Code: " + response.code() + " Message: " + response.message());
                }
            } catch (NullPointerException e) {
                System.out.println("onActivityResult consume crashed");

                        Context context = getActivity();
                        Toast toast = Toast.makeText(context, R.string.Error_Access_empty, Toast.LENGTH_SHORT);
                        toast.show();

            }
        }

        @Override
        public void onFailure(Call<ListTopic<Topic>> call, Throwable t) {
            t.printStackTrace();
        }
    };

    /**
     * Open connect with URL for get JSON  .
     */
    private void createTopicAPI() {
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utilite.BASE_URL_TOPIC)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        mInterfaceTopic = retrofit.create(InterfaceTopic.class);
    }

}

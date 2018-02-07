package com.example.jorge.myenglishcourse.interfaceAPI;


import com.example.jorge.myenglishcourse.model.ListTopic;
import com.example.jorge.myenglishcourse.model.Topic;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.example.jorge.myenglishcourse.common.Utilite.BASE_URL_TOPIC_COMPLEMENT;

/**
 * Created by jorge on 07/02/2018.
 * interface for get Topic
 */

public interface InterfaceTopic {
    @GET(BASE_URL_TOPIC_COMPLEMENT)
    Call<ListTopic<Topic>> getTopic() ;
}

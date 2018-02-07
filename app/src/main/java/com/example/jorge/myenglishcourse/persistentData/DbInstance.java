package com.example.jorge.myenglishcourse.persistentData;

import android.content.Context;

/**
 * Created by jorge on 16/01/2018.
 * Get only instance
 */

public class DbInstance {

    private static DataBase instance;


    public static DataBase getInstance(Context context) {


        if ( instance == null)
        {
            instance = new DataBase(context);


        }
        return instance;


    }

}

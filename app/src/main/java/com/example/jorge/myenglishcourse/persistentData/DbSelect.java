package com.example.jorge.myenglishcourse.persistentData;

/**
 * Created by jorge on 16/01/2018.
 * SQL with Topic and Content
 */

public class DbSelect {
    public static String GET_SQL_TOPIC =
            " SELECT "
                    + Field.FIELD_ID + ","
                    + Field.FIELD_NAME + ","
                    + Field.FIELD_AUDIO + ","
                    + Field.FIELD_TYPE + ","
                    + Field.FIELD_YOUTUBE + ","
                    + Field.FIELD_GLOSSARY +
            " FROM " + DbCreate.TABLE_TOPIC ;

    public static String GET_SQL_CONTENT =
            " SELECT "
                    + Field.FIELD_ID_TOPIC + ","
                    + Field.FIELD_ID_CONTENT + ","
                    + Field.FIELD_CONTENT_ENGLISH + ","
                    + Field.FIELD_CONTENT_PORTUGUESE + ","
                    + Field.FIELD_TIME +
            " FROM " + DbCreate.TABLE_CONTENT +
            " WHERE " + Field.FIELD_ID_TOPIC + " = ? ";



}

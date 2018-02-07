package com.example.jorge.myenglishcourse.persistentData;

/**
 * Created by jorge on 16/01/2018.
 * Sql with create All tables system
 */

public class DbCreate {
    public static final int DB_VERSION = 1;
    public static String DB_NAME = "DB_COURSE.db";

    public static String TABLE_TOPIC = "TABLE_TOPIC";
    public static String CREATE_TABLE_TOPIC =
            "CREATE TABLE IF NOT EXISTS TABLE_TOPIC (" +
                    Field.FIELD_ID + " INTEGER," +
                    Field.FIELD_NAME + " VARCHAR(500), " +
                    Field.FIELD_AUDIO + " VARCHAR(500), " +
                    Field.FIELD_TYPE + " VARCHAR(500), " +
                    Field.FIELD_YOUTUBE + " VARCHAR(500), " +
                    Field.FIELD_GLOSSARY + " VARCHAR(500) "  +
                    ");";


    public static String TABLE_CONTENT = "TABLE_CONTENT";
    public static String CREATE_TABLE_CONTENT =
            "CREATE TABLE IF NOT EXISTS TABLE_CONTENT (" +
                    Field.FIELD_ID_TOPIC + " INTEGER," +
                    Field.FIELD_ID_CONTENT + " INTEGER," +
                    Field.FIELD_CONTENT_ENGLISH + " VARCHAR(500), " +
                    Field.FIELD_CONTENT_PORTUGUESE + " VARCHAR(500), " +
                    Field.FIELD_TIME + " VARCHAR(500) "  +
                    ");";









}

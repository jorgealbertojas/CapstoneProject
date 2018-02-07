package com.example.jorge.myenglishcourse.persistentData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jorge.myenglishcourse.model.Content;
import com.example.jorge.myenglishcourse.model.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 16/01/2018.
 * DataBase With insert and Select SQL
 */

public class DataBase extends SQLiteOpenHelper {

    private SQLiteDatabase mDb;
    private Context context;



    public DataBase(Context context){
        super(context,DbCreate.DB_NAME,null,DbCreate.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

    };

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Drop in tablet PullRequest e User
        db.execSQL(" DROP TABLE IF EXISTS " + DbCreate.TABLE_TOPIC );

        // Drop in tablet Repositories Owner
        db.execSQL(" DROP TABLE IF EXISTS " + DbCreate.TABLE_CONTENT );

        db.execSQL(DbCreate.CREATE_TABLE_TOPIC);
        db.execSQL(DbCreate.CREATE_TABLE_CONTENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase ndb, int oldVersion, int newVersion) {

    }

    public void insertTopic(List<Topic> listTopic){

        for (int i = 0; i < listTopic.size() ; i++) {
            // Insert Topic
            ContentValues obj = new ContentValues();
            obj.put(Field.FIELD_ID, listTopic.get(i).getId());
            obj.put(Field.FIELD_NAME, listTopic.get(i).getName());
            obj.put(Field.FIELD_AUDIO, listTopic.get(i).getAudio());
            obj.put(Field.FIELD_TYPE, listTopic.get(i).getType());
            obj.put(Field.FIELD_YOUTUBE, listTopic.get(i).getYoutube());
            obj.put(Field.FIELD_GLOSSARY, listTopic.get(i).getGlossary());
            this.onInsert(context,obj, DbCreate.TABLE_TOPIC);

        }
    }

    public void insertContent(List<Content> listContent, int idTopic){

        for (int i = 0; i < listContent.size() ; i++) {
            // Insert PullRequest
            ContentValues obj = new ContentValues();
            obj.put(Field.FIELD_ID_TOPIC, idTopic);
            obj.put(Field.FIELD_ID_CONTENT, listContent.get(i).getId_content());
            obj.put(Field.FIELD_CONTENT_ENGLISH, listContent.get(i).getContent_english());
            obj.put(Field.FIELD_CONTENT_PORTUGUESE, listContent.get(i).getContent_portuguese());
            obj.put(Field.FIELD_TIME, listContent.get(i).getTime());

            this.onInsert(context,obj, DbCreate.TABLE_CONTENT);

        }
    }

    public List<Topic> getListTopic() {

        List<Topic> listTopic = new ArrayList<Topic>();

        mDb = this.getWritableDatabase();

        Cursor cursor = mDb.rawQuery(DbSelect.GET_SQL_TOPIC,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast() ){
            Topic topic = new Topic();
            List<Content> lisContent = new ArrayList<>();
            try {
                topic.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Field.FIELD_ID))));
                topic.setName(cursor.getString(cursor.getColumnIndex(Field.FIELD_NAME)));
                topic.setAudio(cursor.getString(cursor.getColumnIndex(Field.FIELD_AUDIO)));
                topic.setType(cursor.getString(cursor.getColumnIndex(Field.FIELD_TYPE)));
                topic.setYoutube(cursor.getString(cursor.getColumnIndex(Field.FIELD_YOUTUBE)));
                topic.setGlossary(cursor.getString(cursor.getColumnIndex(Field.FIELD_GLOSSARY)));

                lisContent.addAll(getListContent(cursor.getString(cursor.getColumnIndex(Field.FIELD_ID))));

                topic.setContent(lisContent);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            listTopic.add(topic);
            cursor.moveToNext();
        }
        cursor.close();
        return listTopic;

    }

    public List<Content> getListContent(String idTopic) {

        List<Content> listContent = new ArrayList<Content>();

        mDb = this.getWritableDatabase();

        Cursor cursor = mDb.rawQuery(DbSelect.GET_SQL_CONTENT,new String[]{idTopic});

        cursor.moveToFirst();
        while(!cursor.isAfterLast() ){
            Content content = new Content();
            try {
                content.setId_content(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Field.FIELD_ID_CONTENT))));
                content.setContent_english(cursor.getString(cursor.getColumnIndex(Field.FIELD_CONTENT_ENGLISH)));
                content.setContent_portuguese(cursor.getString(cursor.getColumnIndex(Field.FIELD_CONTENT_PORTUGUESE)));
                content.setTime(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Field.FIELD_TIME))));

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            listContent.add(content);
            cursor.moveToNext();
        }
        cursor.close();
        return listContent;

    }

    private void onInsert(Context context, ContentValues obj, String nTabela) {
        try{
            DbInstance.getInstance( context ).insert( nTabela, obj );
        }
        catch (Throwable ex){

        }

    }

    public long insert(String table, ContentValues values){

        mDb = this.getWritableDatabase();

        long row = mDb.insert(table, null, values);
        mDb.close();

        return row;
    }










}

package com.example.jorge.myenglishcourse.persistentData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.jorge.myenglishcourse.DrawerActivity;
import com.example.jorge.myenglishcourse.R;
import com.example.jorge.myenglishcourse.adapter.AdapterTopic;
import com.example.jorge.myenglishcourse.common.Utilite;
import com.example.jorge.myenglishcourse.fragment.RecyclerViewFragment;
import com.example.jorge.myenglishcourse.interfaceAPI.InterfaceTopic;
import com.example.jorge.myenglishcourse.model.Topic;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopicActivity extends DrawerActivity implements AdapterTopic.AdapterTopicOnClickHandler{

    AdapterTopic mAdapterTopic;
    @BindView(R.id.rv_topic)
    RecyclerView mRecyclerView;

    @BindView(R.id.materialViewPager)     MaterialViewPager mViewPager;

    private SQLiteDatabase mDb;
    private DataBase mDataBase;
    private InterfaceTopic mInterfaceTopic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        ButterKnife.bind(this);

        if (savedInstanceState == null) {

            final Toolbar toolbar = mViewPager.getToolbar();
            if (toolbar != null) {
                setSupportActionBar(toolbar);
            }


            configurationViewPager();
            // Start RecyclerView with Adapter
            initRecyclerView();
            getDataForRecyclerView();

        } else {
            /**
             * I ued this for get State of the Recycler for don't have without the need get API again
             */
            initRecyclerView();
            getDataForRecyclerView();

        }

    }

    /**
     * use RecyclerView for list the Category .
     */
    private void initRecyclerView() {

           mRecyclerView.setHasFixedSize(true);
           mAdapterTopic = new AdapterTopic(this);
           mRecyclerView.setLayoutManager(new LinearLayoutManager(TopicActivity.this));

    }


    private void getDataForRecyclerView(){
        // Persistent Data for SQLLite
        mDataBase = new DataBase(getApplicationContext());
        mDb = mDataBase.getReadableDatabase();

        List<Topic> dataPersistent = new ArrayList<>();
        dataPersistent = mDataBase.getListTopic();

        if (dataPersistent != null) {
            mAdapterTopic = new AdapterTopic(dataPersistent);
            mRecyclerView.setAdapter(mAdapterTopic);
        }
    }




    @Override
    public void onClick(Topic topic) {

    }


    public void configurationViewPager(){
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (position % 4) {
                    //case 0:
                    //    return RecyclerViewFragment.newInstance();
                    //case 1:
                    //    return RecyclerViewFragment.newInstance();
                    //case 2:
                    //    return WebViewFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 4) {
                    case 0:
                        return "Selection";
                    case 1:
                        return "Actualités";
                    case 2:
                        return "Professionnel";
                    case 3:
                        return "Divertissement";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "http://phandroid.s3.amazonaws.com/wp-content/uploads/2014/06/android_google_moutain_google_now_1920x1080_wallpaper_Wallpaper-HD_2560x1600_www.paperhi.com_-640x400.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://www.hdiphonewallpapers.us/phone-wallpapers/540x960-1/540x960-mobile-wallpapers-hd-2218x5ox3.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                }

                //execute others actions if needed (ex : modify your header logo)

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        final View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), "Yes, the title is clickable", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }



}

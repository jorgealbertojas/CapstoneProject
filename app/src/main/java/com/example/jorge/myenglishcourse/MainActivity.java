package com.example.jorge.myenglishcourse;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.jorge.myenglishcourse.persistentData.DataBase;
import com.example.jorge.myenglishcourse.common.Utilite;
import com.example.jorge.myenglishcourse.adapter.AdapterTopic;
import com.example.jorge.myenglishcourse.fragment.RecyclerViewFragment;
import com.example.jorge.myenglishcourse.menuMain.NavigationDrawer;
import com.example.jorge.myenglishcourse.model.Topic;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationDrawer.FragmentDrawerListener {



    private Toolbar mToolbar;
    private NavigationDrawer drawerFragment;

    private FragmentTransaction ft;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        ButterKnife.bind(this);


        loadMenu();





        ButterKnife.bind(this);


        drawerFragment = (NavigationDrawer) getSupportFragmentManager().findFragmentById(R.id.drawer_layout);


        ControlTopicActivity nMainFragment = new ControlTopicActivity();
        ft = getFragmentManager().beginTransaction();
        ft.replace( R.id.container_body, nMainFragment);
        ft.commit();







    }

    private void loadMenu() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // actionBarTitle = (TextView) mToolbar.findViewById(R.id.actionbarTitle);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (NavigationDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);

        final DrawerLayout nDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerFragment.setDrawerListener(this);

       /* actionBarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });*/
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        Utilite.position = position;


        if (position == 0){
            // Intent controlTopicActivity = new Intent(MainActivity.this, ControlTopicActivity.class);
           //  startActivity(controlTopicActivity);
            // MainFragment MainFragment = new MainFragment();
           //  ft = getFragmentManager().beginTransaction();
           //  ft.replace( R.id.container_body, MainFragment);
            ControlTopicActivity controlTopicActivity = new ControlTopicActivity();
            ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.container_body, controlTopicActivity);
            ft.commit();
           //  ft.commit();
        } else if (position == 1){
            //InternetFragment internetFragment = new InternetFragment();
            //ft = getFragmentManager().beginTransaction();
            //ft.replace(R.id.container_body, internetFragment);
            //ft.commit();
        }else if (position == 2){
       //     InformacaoFragment informacaoFragment = new InformacaoFragment();
      //      ft = getFragmentManager().beginTransaction();
       //     ft.replace(R.id.container_body, informacaoFragment);
      //      ft.commit();
        }else if (position == 3){
            finish();
        }
    }







}


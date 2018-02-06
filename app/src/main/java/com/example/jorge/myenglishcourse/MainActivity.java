package com.example.jorge.myenglishcourse;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.jorge.myenglishcourse.Utilite.Common;
import com.example.jorge.myenglishcourse.fragment.RecyclerViewFragment;
import com.example.jorge.myenglishcourse.menuMain.NavigationDrawer;
import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends DrawerActivity implements NavigationDrawer.FragmentDrawerListener{

    @BindView(R.id.materialViewPager)     MaterialViewPager mViewPager;

    private Toolbar mToolbar;
    private NavigationDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        ButterKnife.bind(this);

        loadMenu();

        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

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

    private void loadMenu() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // actionBarTitle = (TextView) mToolbar.findViewById(R.id.actionbarTitle);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (NavigationDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);

        final DrawerLayout nDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

     //   drawerFragment.setDrawerListener(this);

       /* actionBarTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });*/
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        Common.position = position;


        if (position == 0){
            // Intent nLogin = new Intent(MainActivity.this, OcorrenciaActivityNEW.class);
            //  startActivity(nLogin);
         //   MainFragment MainFragment = new MainFragment();
         //   ft = getFragmentManager().beginTransaction();
         //   ft.replace( R.id.container_body, MainFragment);
        //    ft.commit();
        } else if (position == 1){
        //    InternetFragment internetFragment = new InternetFragment();
        //    ft = getFragmentManager().beginTransaction();
        //    ft.replace(R.id.container_body, internetFragment);
       //     ft.commit();
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


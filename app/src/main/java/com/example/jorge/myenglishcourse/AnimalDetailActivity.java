package com.example.jorge.myenglishcourse;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by jorge on 06/02/2018.
 */

public class AnimalDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);
        supportPostponeEnterTransition();

      //  Bundle extras = getIntent().getExtras();
      //  AnimalItem animalItem = extras.getParcelable(Common.EXTRA_ITEM);

        ImageView imageView = (ImageView) findViewById(R.id.iv_image_product);
        TextView textView = (TextView) findViewById(R.id.tv_name_product);
        textView.setText("teste");

        String imageUrl = "https://images-americanas.b2w.io/produtos/01/00/item/128261/4/128261488G1.jpg";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        //    String imageTransitionName = extras.getString(Common.EXTRA_IMAGE_TRANSITION);
            imageView.setTransitionName("https://images-americanas.b2w.io/produtos/01/00/item/128261/4/128261488G1.jpg");
        }

        Picasso.with(this)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

}
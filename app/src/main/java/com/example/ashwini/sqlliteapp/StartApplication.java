package com.example.ashwini.sqlliteapp;

import android.content.Intent;
import android.media.Image;
import android.opengl.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by RajuReddy on 4/12/2016.
 */
public class StartApplication extends MainActivity{
    Button buttonContact, buttonChange;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        buttonContact = (Button) findViewById(R.id.btnContact);
        buttonChange = (Button) findViewById(R.id.btnChangeImage);


        addListenerOnButton();
        viewContact();

    }


    public void addListenerOnButton() {
        image = (ImageView) findViewById(R.id.imageView1);


        buttonChange.setOnClickListener(new View.OnClickListener() {
            int[] images = new int[]{R.mipmap.ic_launcher1xx, R.mipmap.ic_launcher2,
                    R.mipmap.ic_launcher3, R.mipmap.ic_launcher4, R.mipmap.ic_launcher55};
            int i;
            @Override

            public void onClick(View v) {


                image.setImageResource(images[i]);
                i++;

//               image.setImageResource(R.mipmap.ic_launcher2);
//                image.setImageResource(R.mipmap.ic_launcher3);
//                image.setImageResource(R.mipmap.ic_launcher4);
//                image.setImageResource(R.mipmap.ic_launcher55);

            }
        });
    }

    public void viewContact() {
        buttonContact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartApplication.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}

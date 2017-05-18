package com.davenotdavid.slide_upanimsample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView mGridView;

    private static final int COLUMNS = 8;
    private static final int DIMENSIONS = COLUMNS * COLUMNS;

    private int mColumnWidth, mColumnHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setDimensions();
    }

    private void init() {
        mGridView = (GridView) findViewById(R.id.grid);
        mGridView.setNumColumns(COLUMNS);
    }

    private void setDimensions() {
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight = getStatusBarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth / COLUMNS;
                mColumnHeight = requiredHeight / COLUMNS;

                display();
            }
        });
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private void display() {
        List<Button> buttons = new ArrayList<>();
        Button button;

        for (int i = 0; i < DIMENSIONS; i++) {
            button = new Button(this);

            if (i < 10) {
                button.setBackgroundResource(R.drawable.jewel_amethyst);
            } else if (i < 20) {
                button.setBackgroundResource(R.drawable.jewel_citrine);
            } else if (i < 30) {
                button.setBackgroundResource(R.drawable.jewel_emerald);
            } else if (i < 40) {
                button.setBackgroundResource(R.drawable.jewel_ruby);
            } else if (i < 50) {
                button.setBackgroundResource(R.drawable.jewel_sapphire);
            } else {
                button.setBackgroundResource(R.drawable.jewel_topaz);
            }

            buttons.add(button);
        }

        Collections.shuffle(buttons);

        mGridView.setAdapter(new CustomAdapter(this, buttons, mColumnWidth, mColumnHeight));
    }

    public void onAnimBtnClicked(View v) {
        display();
    }
}

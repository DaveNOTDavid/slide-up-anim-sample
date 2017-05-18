package com.davenotdavid.slide_upanimsample;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context mContext;

    private List<Button> mButtons;

    private int mColumnWidth, mColumnHeight;

    private long mDelay;

    private boolean mNoAnimate;

    public CustomAdapter(Context context, List<Button> buttons, int columnWidth,
                         int columnHeight) {
        mContext = context;
        mButtons = buttons;
        mColumnWidth = columnWidth;
        mColumnHeight = columnHeight;
    }

    @Override
    public int getCount() {
        return mButtons.size();
    }

    @Override
    public Object getItem(int position) {return (Object) mButtons.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null) {
            button = mButtons.get(position);
        } else {
            button = (Button) convertView;
        }

        android.widget.AbsListView.LayoutParams params =
                new android.widget.AbsListView.LayoutParams(mColumnWidth, mColumnHeight);
        button.setLayoutParams(params);

        // Only runs the following should the flag be false since the first jewel (position 0) is
        // bugged where it gets displayed again after the last jewel.
        if (!mNoAnimate) {
            Animation slideUpAnim = AnimationUtils.loadAnimation(mContext, R.anim.slide_up);
            slideUpAnim.setStartOffset(mDelay);
            button.setAnimation(slideUpAnim);
            mDelay += 20;
        }

        if (position == getCount() - 1) {
            mNoAnimate = true;
        }

        return button;
    }
}

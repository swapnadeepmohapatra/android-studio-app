package com.example.homepc.onboardingapp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter (Context context){

        this.context = context;

    }

    public int[] slide_images = {

            R.drawable.eat,
            R.drawable.sleep,
            R.drawable.code,
            R.drawable.sleep,
    };

    public String[] slide_headings = {
            "Eat",
            "Sleep",
            "Code",
            "Repeat"
    };

    public String[] slide_message = {
            "Eat Light",
            "Sleep Enough",
            "Code Much",
            "Repeat Once"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.textView);
        TextView slideMessage = (TextView) view.findViewById(R.id.messageView);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideMessage.setText(slide_message[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container,int position, Object object){

        container.removeView((RelativeLayout)object);
    }
}

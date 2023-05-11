package com.example.apphai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.apphai.R;
import com.example.apphai.model.SlideMonAN;

import java.util.List;

public class SlideMonAnAdapter extends PagerAdapter {
    private Context mContext;
    private List<SlideMonAN> mListSlide;

    public SlideMonAnAdapter(Context mContext, List<SlideMonAN> mListSlide) {
        this.mContext = mContext;
        this.mListSlide = mListSlide;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slide,container,false);
        ImageView imgSlide = view.findViewById(R.id.img_slide);
        SlideMonAN slide = mListSlide.get(position);
        if(slide !=null){
            Glide.with(mContext).load(slide.getResourceId()).into(imgSlide);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        if(mListSlide!=null){
            return mListSlide.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==object;
    }
}

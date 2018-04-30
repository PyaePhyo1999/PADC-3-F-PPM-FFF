package com.example.acer.findforfood.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifBitmapProvider;
import com.example.acer.findforfood.R;
import com.example.acer.findforfood.data.vo.FeaturedVO;
import com.example.acer.findforfood.viewitems.ImagesInShopDetailViewItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Acer on 1/5/2018.
 */

public class ImagesInShopDetailsAdapter extends PagerAdapter {
    private List<FeaturedVO> mFeaturedList;

    public ImagesInShopDetailsAdapter() {
        this.mFeaturedList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mFeaturedList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (View) object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Context context = container.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ImagesInShopDetailViewItem view = (ImagesInShopDetailViewItem) layoutInflater.inflate(R.layout.items_shop_details_image, container, false);
        view.setFeatured(mFeaturedList.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setFeature(List<FeaturedVO> featureList) {
        mFeaturedList = featureList;
        notifyDataSetChanged();
    }
}





package com.example.acer.findforfood.viewitems;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.findforfood.R;
import com.example.acer.findforfood.data.vo.FeaturedVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Acer on 1/5/2018.
 */

public class ImagesInShopDetailViewItem extends FrameLayout {

    @BindView(R.id.tv_feature_tag)
    TextView tvFeature;

    @BindView(R.id.tv_title_newly_opened)
    TextView tvTitle;

    @BindView(R.id.tv_sub_title)
    TextView tvSubtitle;

    @BindView(R.id.iv_shop_image)
    ImageView ivShopImage;



    public ImagesInShopDetailViewItem(@NonNull Context context) {

        super(context);
    }

    public ImagesInShopDetailViewItem(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImagesInShopDetailViewItem(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }
    public void setFeatured(FeaturedVO featured){
        tvFeature.setText(featured.getFeatureTag());
        tvTitle.setText(featured.getFeatureTitle());
        tvSubtitle.setText(featured.getFeatureDescription());

        Glide.with(ivShopImage.getContext())
                .load(featured.getFeatureImage())
                .into(ivShopImage);




    }
}



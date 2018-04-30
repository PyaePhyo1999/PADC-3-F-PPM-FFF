package com.example.acer.findforfood.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.example.acer.findforfood.R;
import com.example.acer.findforfood.data.vo.LoginVO;
import com.example.acer.findforfood.delegates.LoginUserDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Acer on 1/26/2018.
 */

public class LoginUserViewPod extends RelativeLayout{
    private LoginUserDelegate mDelegate;
   @BindView(R.id.tv_name)
    TextView tvname;
   @BindView(R.id.tv_phone_number)
   TextView tvphoneNo;
   @BindView(R.id.iv_cover_url)
    ImageView ivcoverUrl;
   @BindView(R.id.iv_user_picture)
   ImageView ivuserPicture;
    public LoginUserViewPod(Context context) {
        super(context);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }
    public void bindData(LoginVO login){
       tvname.setText(login.getName());
       tvphoneNo.setText(login.getPhoneNo());

        Glide.with(ivcoverUrl.getContext())
                .load(login.getCoverUrl())
                .into(ivcoverUrl);
        Glide.with(ivuserPicture.getContext())
                .load(login.getProfileUrl())
                .into(ivuserPicture);

    }
    public void setDelegate(LoginUserDelegate loginUserDelegate){
           mDelegate = loginUserDelegate;
    }
    @OnClick (R.id.btn_logout)
    public void onTapLogout(View view ){
         mDelegate.onTapLogout();
    }

}

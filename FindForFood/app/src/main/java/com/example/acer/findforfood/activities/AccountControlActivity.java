package com.example.acer.findforfood.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.acer.findforfood.R;
import com.example.acer.findforfood.fragments.LoginFragment;
import com.example.acer.findforfood.fragments.SignUpFragment;

import butterknife.ButterKnife;

/**
 * Created by Acer on 1/26/2018.
 */

public class AccountControlActivity extends AppCompatActivity {
     private static final String IE_SCREEN_TYPE="IE_SCREEN_TYPE";
    private static final int LOGIN_SCREEN_TYPE=1;
    private static final int SIGN_UP_SCREEN_TYPE=2;


     public static Intent newLoginIntent(Context context){
         Intent intent = new Intent(context,AccountControlActivity.class);
         intent.putExtra(IE_SCREEN_TYPE,LOGIN_SCREEN_TYPE);
         return intent;
     }
    public static Intent newSignUpIntent(Context context){
        Intent intent = new Intent(context,AccountControlActivity.class);
        intent.putExtra(IE_SCREEN_TYPE,SIGN_UP_SCREEN_TYPE);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_control);
        ButterKnife.bind(this,this);
        int screenType = getIntent().getIntExtra(IE_SCREEN_TYPE,-1);
        if(screenType == LOGIN_SCREEN_TYPE){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container,new LoginFragment())
                    .commit();
        }
        else if(screenType == SIGN_UP_SCREEN_TYPE){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container,new SignUpFragment())
                    .commit();
        }
    }
}

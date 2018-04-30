package com.example.acer.findforfood.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.acer.findforfood.FindForFoodApp;
import com.example.acer.findforfood.R;
import com.example.acer.findforfood.adapters.DetailsNewsAdapter;
import com.example.acer.findforfood.adapters.DetailsTrendingVenuesAdapter;
import com.example.acer.findforfood.adapters.ImagesInShopDetailsAdapter;
import com.example.acer.findforfood.adapters.PromotionShopNewsAdapter;
import com.example.acer.findforfood.adapters.ShopGuidesAdapter;
import com.example.acer.findforfood.data.vo.models.FeatureModel;
import com.example.acer.findforfood.data.vo.models.GuideModel;
import com.example.acer.findforfood.data.vo.models.LoginUserModel;
import com.example.acer.findforfood.data.vo.models.PromotionModel;
import com.example.acer.findforfood.delegates.BeforeLoginDelegates;
import com.example.acer.findforfood.delegates.LoginUserDelegate;
import com.example.acer.findforfood.events.LoadFeaturedEvent;
import com.example.acer.findforfood.events.LoadGuideEvent;
import com.example.acer.findforfood.events.LoadPromotionEvent;
import com.example.acer.findforfood.viewpods.AccountControlViewPod;
import com.example.acer.findforfood.viewpods.BeforeLoginUserViewPod;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BeforeLoginDelegates,LoginUserDelegate {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.movie_navigation_view)
    NavigationView navigationView;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;

    @BindView(R.id.rv_trending_venues)
    RecyclerView rvTrendingVenues;

    @BindView(R.id.rv_burpple_guide)
    RecyclerView rvBurppleGuide;

    @BindView(R.id.rv_promotion_news)
    RecyclerView rvPromotionShopNews;

    @BindView(R.id.vg_shop)
    ViewPager vpShopImage;

    @BindView(R.id.toolbar)
     Toolbar toolbar;

    private DetailsNewsAdapter mDetailsNewsAdapter;
    private PromotionShopNewsAdapter mPromotionShopNewsAdapter;
    private ImagesInShopDetailsAdapter mImagesInShopDetailsAdapter;
    private ShopGuidesAdapter mShopGuidesAdapter;
    private DetailsTrendingVenuesAdapter mDetailsTrendingVenuesAdapter;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this,this);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);


        mImagesInShopDetailsAdapter = new ImagesInShopDetailsAdapter();
        vpShopImage.setAdapter(mImagesInShopDetailsAdapter);

        mPromotionShopNewsAdapter = new PromotionShopNewsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        rvPromotionShopNews.setAdapter(mPromotionShopNewsAdapter);
        rvPromotionShopNews.setLayoutManager(linearLayoutManager);

        mShopGuidesAdapter = new ShopGuidesAdapter();
        LinearLayoutManager linearLayoutManagerForShopGuides = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        rvBurppleGuide.setAdapter(mShopGuidesAdapter);
        rvBurppleGuide.setLayoutManager(linearLayoutManagerForShopGuides);

        mDetailsNewsAdapter = new DetailsNewsAdapter();
        LinearLayoutManager linearLayoutManagerForNews = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rvNews.setAdapter(mDetailsNewsAdapter);
        rvNews.setLayoutManager(linearLayoutManagerForNews);

        mDetailsTrendingVenuesAdapter = new DetailsTrendingVenuesAdapter();
        LinearLayoutManager linearLayoutManagerForTrendingVenues = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        rvTrendingVenues.setLayoutManager(linearLayoutManagerForTrendingVenues);
        rvTrendingVenues.setAdapter(mDetailsTrendingVenuesAdapter);

        PromotionModel.getsObjInstance().LoadPromotion();
        GuideModel.getsObjInstance().LoadGuide();
                                      FeatureModel.getsObjInstance().loadFeatured();

       if (getSupportActionBar()!=null)
           getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);

           AccountControlViewPod vpAccountControlViewPod = (AccountControlViewPod) navigationView.getHeaderView(0);
           vpAccountControlViewPod.setDelegate((BeforeLoginDelegates)this);
           vpAccountControlViewPod.setDelegate((LoginUserDelegate)this);

    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id==android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPromotionLoaded(LoadPromotionEvent event){
        Log.d(FindForFoodApp.LOG_TAG,"PromotionLoaded"+event.getPromotionList().size());
        mPromotionShopNewsAdapter.setPromotion(event.getPromotionList());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGuideLoaded(LoadGuideEvent event){
        Log.d(FindForFoodApp.LOG_TAG,"GuideLoaded"+event.getGuideList().size());
        mShopGuidesAdapter.setGuide(event.getGuideList());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFeatureLoaded(LoadFeaturedEvent event){
        Log.d(FindForFoodApp.LOG_TAG,"FeatureLoaded"+event.getFeaturedList().size());
        mImagesInShopDetailsAdapter.setFeature(event.getFeaturedList());
    }


    @Override
    public void onTapLogin() {
        Intent intent = AccountControlActivity.newLoginIntent(getApplicationContext());
        startActivity(intent);
    }

    @Override
    public void onTapSignUp() {
        Intent intent = AccountControlActivity.newSignUpIntent(getApplicationContext());
        startActivity(intent);

    }

    @Override
    public void onTapLogout() {
        LoginUserModel.getObjInstance().logout();
    }
}

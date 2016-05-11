package com.example.eltonmelo.videos;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import Adapter.MyPagerAdapter;
import Helper.Constants;
import Model.TOPhoto;
import WS.RestClient;

/**
 * Created by elton.melo on 05/05/2016.
 */
@EActivity(R.layout.photo_view_page)
public class PhotoPageActivity extends AppCompatActivity {

    MyPagerAdapter myPagerAdapter;

    @RestService
    RestClient restClient;

    @ViewById
    ViewPager myViewPager;

    @ViewById
    TextView titleScrenPage;

    int idMovie;

    String titleMovie;

    TOPhoto toPhoto;

    @AfterViews
    void AfterViews() {
        Bundle b = getIntent().getExtras();
        long value = b.getLong(Constants.EXTRA_PHOTO_ID_VIDEO);
        titleMovie = b.getString(Constants.EXTRA_PHOTO_TITLE_VIDEO);
        idMovie = (int) value;
        titleScrenPage.setText(titleMovie);
        getPhotos();
    }

    @Background
    void getPhotos() {
        toPhoto = restClient.getPhotos(idMovie);
        updateView();
    }

    @UiThread
    void updateView() {
        myPagerAdapter = new MyPagerAdapter(this, toPhoto.getPosters());
        myViewPager.setAdapter(myPagerAdapter);
    }

}

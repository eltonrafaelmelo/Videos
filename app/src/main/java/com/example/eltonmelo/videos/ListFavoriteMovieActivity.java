package com.example.eltonmelo.videos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import Adapter.DetailMovieAdapter;
import Adapter.EndlessScrollListener;
import Adapter.ImageAdapter;
import Helper.Constants;
import Model.DetailMovieModel;
import Model.Movie;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by eltonmelo on 5/11/16.
 */
@EActivity(R.layout.list_favorite_movies)
public class ListFavoriteMovieActivity extends AppCompatActivity{

    @ViewById
    GridView gridview1;

    DetailMovieAdapter detailAdapter;

    @AfterViews
    void AfterViews() {
        getMovie();
    }

    private void getMovie() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);
        RealmResults<DetailMovieModel> tasks = realm.where(DetailMovieModel.class).findAll();
        updateView(tasks);
    }

    @UiThread
    void updateView(RealmResults<DetailMovieModel> listMovies) {
        if (detailAdapter!= null){
            detailAdapter = null;
        }
        detailAdapter = new DetailMovieAdapter(this, listMovies);
        gridview1.setAdapter(detailAdapter);
    }

    @ItemClick
    public void gridview1(int item) {
        DetailMovieModel movie = (DetailMovieModel) detailAdapter.getItem(item);
        openMovieSelected(movie);
    }

    public  void openMovieSelected(DetailMovieModel movie) {
        Intent intent = new Intent(this, DetailFavoriteMovieActivity_.class);
        Bundle b = new Bundle();
        b.putLong(Constants.EXTRA_VIDEO, movie.getIdDetailMovie());
        intent.putExtras(b);
        startActivity(intent);
    }

}

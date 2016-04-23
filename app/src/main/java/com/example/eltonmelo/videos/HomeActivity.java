package com.example.eltonmelo.videos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import Adapter.ImageAdapter;
import Helper.Constants;
import Model.Movie;
import Model.TOMovieLIst;
import WS.RestClient;

@EActivity(R.layout.activity_home)
@OptionsMenu(R.menu.menu_home)
public class HomeActivity extends AppCompatActivity {

    @ViewById
    Toolbar toolbar;

    @ViewById
    GridView gridview1;

    ImageAdapter imageAdapter;

    @RestService
    RestClient restClient;

    @AfterViews
    void AfterViews() {
        setSupportActionBar(toolbar);
        getMovie();
    }

    @OptionsItem(R.id.action_settings)
    void myMethod() {
        // You can specify the ID in the annotation, or use the naming convention
    }

    @OptionsItem(R.id.action_ordenar_por_data)
    void ordenarPorData() {
        // You can specify the ID in the annotation, or use the naming convention
    }

    @OptionsItem(R.id.action_ordenar_por_Favorito)
    void ordenarPorFavorito() {
        // You can specify the ID in the annotation, or use the naming convention
    }

    @ItemClick
    public void gridview1(int item) {
        Movie movie = (Movie) imageAdapter.getItem(item);
//        Toast.makeText(HomeActivity.this, movie.getOriginalTitle(),
//                Toast.LENGTH_SHORT).show();
        openMovieSelected(movie);
    }

    public  void openMovieSelected(Movie movie) {
        Intent intent = new Intent(this, DetailMovieActivity_.class);
        Bundle b = new Bundle();
        b.putLong(Constants.EXTRA_VIDEO, movie.getId());
        intent.putExtras(b);
        startActivity(intent);
    }

    @Background
    void  getMovie() {
        TOMovieLIst toMovieLIst = restClient.getTOMovieLIst();
        updateView(toMovieLIst);
//        TOMovieLIst listMovie = restClient.getTOMoviePage(2);
    }

    @UiThread
    void updateView(TOMovieLIst toMovieLIst) {
        imageAdapter = new ImageAdapter(this, toMovieLIst.getResults());
        gridview1.setAdapter(imageAdapter);
    }

}

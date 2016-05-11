package com.example.eltonmelo.videos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

import Adapter.EndlessScrollListener;
import Adapter.ImageAdapter;
import Helper.Constants;
import Model.Movie;
import Model.TOMovieLIst;
import WS.RestClient;

@EActivity(R.layout.activity_home)
@OptionsMenu(R.menu.menu_home)
public class HomeActivity extends AppCompatActivity implements EndlessScrollListener.OnLoadMoreListener{

    enum SortByList {
        DATE_RELEASE,
        POPULARITY,
        BEST_VOTE,
        CLEAR
    }

    SortByList sortByList;

    @ViewById
    Toolbar toolbar;

    @ViewById
    GridView gridview1;

    ImageAdapter imageAdapter;

    @RestService
    RestClient restClient;

    TOMovieLIst toMovieLIst;

    int paginaAtual;
    ProgressDialog dialog;

    @AfterViews
    void AfterViews() {
        sortByList = SortByList.DATE_RELEASE;
        setSupportActionBar(toolbar);
        getMovie();
    }

    @OptionsItem(R.id.action_settings)
    void myMethod() {
        sortByList = SortByList.POPULARITY;
        sortListMovie();
    }

    @OptionsItem(R.id.action_ordenar_por_data)
    void ordenarPorData() {
        sortByList = SortByList.DATE_RELEASE;
        sortListMovie();
    }

    @OptionsItem(R.id.action_ordenar_por_Favorito)
    void ordenarPorFavorito() {
        sortByList = SortByList.BEST_VOTE;
        sortListMovie();
    }

    @OptionsItem(R.id.action_update)
    void touchButtonUpdate() {
        sortByList = SortByList.CLEAR;
        sortListMovie();
    }

    @OptionsItem(R.id.action_favorite)
    void touchButtonFavorite() {
        Intent intent = new Intent(this, ListFavoriteMovieActivity_.class);
        startActivity(intent);
    }

    @ItemClick
    public void gridview1(int item) {
        Movie movie = (Movie) imageAdapter.getItem(item);
        openMovieSelected(movie);
    }

    public  void openMovieSelected(Movie movie) {
        Intent intent = new Intent(this, DetailMovieActivity_.class);
        Bundle b = new Bundle();
        b.putLong(Constants.EXTRA_VIDEO, movie.getIdMovie());
        intent.putExtras(b);
        startActivity(intent);
    }

    @Background
    void  getMovie() {
        toMovieLIst = restClient.getTOMovieLIst();
        paginaAtual = toMovieLIst.getPage();
        updateView(toMovieLIst.getResults());
    }

    @UiThread
    void updateView(ArrayList<Movie> listM) {
        if (imageAdapter!= null){
            imageAdapter = null;
        }
        imageAdapter = new ImageAdapter(this, listM);
        gridview1.setAdapter(imageAdapter);
        gridview1.setOnScrollListener(new EndlessScrollListener(this));
    }

    void sortListMovie() {
        ArrayList<Movie> arrayList;
        switch (sortByList) {
            case DATE_RELEASE:
                arrayList = sortListDate(toMovieLIst.getResults());
                updateView(arrayList);
                break;
            case POPULARITY:
                arrayList = updateList(toMovieLIst.getResults());
                updateView(arrayList);
                break;
            case BEST_VOTE:
                arrayList = sortListAvaliado(toMovieLIst.getResults());
                updateView(arrayList);;
                break;
            case CLEAR:
                arrayList = toMovieLIst.getResults();
                arrayList.clear();
                toMovieLIst.setResults(arrayList);
                paginaAtual = 0;
                getMovie();
                break;
        }
    }

    ArrayList<Movie> updateList(ArrayList<Movie> listMovie){

        Collections.sort(listMovie, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return Double.compare(o1.getPopularity(), o2.getPopularity());
            }
        });
        return listMovie;
    }

    ArrayList<Movie> sortListAvaliado(ArrayList<Movie> listMovie){

        Collections.sort(listMovie, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.compareTo(o2);
            }
        });
        return listMovie;
    }

    ArrayList<Movie> sortListDate(ArrayList<Movie> listMovie){

        Collections.sort(listMovie, new Comparator<Movie>() {
            public int compare(Movie o1, Movie o2) {

                try {
                    return o2.compareToDate(o1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return 0;
            }
        });
        return listMovie;
    }

    @Override
    public void onLoadMore(int page, int totalItemsCount) throws InterruptedException {

        paginaAtual = paginaAtual + 1;
        int total = totalItemsCount;

//        dialog = new ProgressDialog(this);
//        dialog.setTitle("Loading");
//        dialog.setMessage("Wait while loading...");
//        dialog.show();

        new AsyncTask<Void, Void, ArrayList<Movie>>() {

            @Override
            protected ArrayList<Movie> doInBackground(Void... voids) {
                //Simulating delay to get more items from an API.
                try {
                    Thread.sleep(1000);
                    TOMovieLIst listMovie = restClient.getTOMoviePage(paginaAtual);
                    return listMovie.getResults();
                } catch (InterruptedException e) {
                }
                return null;
            }

            @Override
            protected void onPostExecute(ArrayList<Movie> items) {
                for (Movie item : items) {
                    addMovie(item);
                }
                imageAdapter.notifyDataSetChanged();
            }
        }.execute();
//        dialog.dismiss();
    }

    public void addMovie(Movie movie) {
            toMovieLIst.getResults().add(movie);
    }
}

package com.example.eltonmelo.videos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

import Adapter.ImageAdapter;
import DAO.DatabaseManager;
import Helper.Constants;
import Model.DetailMovieModel;
import Model.GenreModel;
import Model.SpokenLanguage;
import Model.TOMovieLIst;
import Model.TOPhoto;
import Model.TOTrailerModel;
import WS.RestClient;

/**
 * Created by eltonmelo on 4/23/16.
 */

@EActivity(R.layout.detail_movie)
public class DetailMovieActivity extends AppCompatActivity {

    @RestService
    RestClient restClient;

    @ViewById
    TextView released;

    @ViewById
    TextView data;

    @ViewById
    TextView minutos;

    @ViewById
    TextView descricao;

    @ViewById
    TextView genero;

    @ViewById
    TextView idioma;

    @ViewById
    ImageView imageView2;

    @ViewById
    ImageButton buttonAddFavorites;

    int idMovie;

    DetailMovieModel detailMovie;

    @AfterViews
    void AfterViews() {
//        setSupportActionBar(toolbar);
        Bundle b = getIntent().getExtras();
        long value = b.getLong(Constants.EXTRA_VIDEO);
        idMovie = (int) value;
        getDetailMovie(idMovie);
    }

    @Background
    void getDetailMovie(int id) {
        detailMovie = restClient.getDetailMovie(id);
        updateView(detailMovie);
    }

    @UiThread
    void updateView(DetailMovieModel detailMovieModel) {
        released.setText(detailMovieModel.getStatus());
//        data.setText(detailMovieModel.getReleaseDate());
        minutos.setText(String.valueOf(detailMovieModel.getRuntime()) + " Minutos");
        descricao.setText(detailMovieModel.getOverview());

        String genero = "";
        for (GenreModel genreModel : detailMovieModel.getGenres()) {
            genero = genero + "" + genreModel.getName() + " |";
        }

        this.genero.setText(genero);

        String idioma = "";
        for (SpokenLanguage spokenLanguage : detailMovieModel.getSpokenLanguages()) {
            idioma = idioma + "" + spokenLanguage.getName() + " |";
        }

        this.idioma.setText(idioma);

        String[] separated = detailMovieModel.getReleaseDate().split("-");
        String dia = separated[2];
        String mes = separated[1];
        String ano = separated[0];
        data.setText(dia + "/" + mes + "/" + ano);


        String url = "http://image.tmdb.org/t/p/w500" + detailMovieModel.getPosterPath();
        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(this) //
                .load(url) //
                .placeholder(R.drawable.sample_0) //
                .error(R.drawable.error) //
                .fit() //
                .tag(this) //
                .into(imageView2);

    }

    @Click(R.id.buttonTrailes)
    void touchButtonTrailes() {
        getTrailes();
    }

    @Click(R.id.buttonPhotos)
    void touchButtonPhotos() {
        getPhotos();
    }

    @Click (R.id.buttonAddFavorites)
    public void touchButtoFavorite() {
        buttonAddFavorites.setImageResource(R.drawable.favorite);
    }

    @Background
    void getTrailes() {
        TOTrailerModel toTrailerModel = restClient.getTrailer(idMovie);
        callScreenTrailers(toTrailerModel);
    }

    @Background
    void getPhotos() {
        TOPhoto toPhoto = restClient.getPhotos(idMovie);
        callScreenPhotos(toPhoto);
    }

    @UiThread
    void callScreenPhotos(TOPhoto toPhoto) {

//        DatabaseManager databaseManager =  DatabaseManager.init(this);
//
//        List<DetailMovieModel> list = databaseManager.getInstance().findAllDetailMovie();

        if (toPhoto.getPosters().size() > 0) {
            Intent intent = new Intent(this, PhotoPageActivity_.class);
            Bundle b = new Bundle();
            b.putLong(Constants.EXTRA_PHOTO_ID_VIDEO, idMovie);
            b.putString(Constants.EXTRA_PHOTO_TITLE_VIDEO, detailMovie.getTitle());
            intent.putExtras(b);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Os Poster ainda não estão disponíveis",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @UiThread
    void callScreenTrailers(TOTrailerModel toTrailerModel) {

        if (toTrailerModel.getResults().size() > 0) {
//            Toast.makeText(this, "Existem Trailers",
//                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, TrailersActivity_.class);
            Bundle b = new Bundle();
            b.putLong(Constants.EXTRA_TRAILERS, idMovie);
            intent.putExtras(b);
            startActivity(intent);
        } else {
            Toast.makeText(this, "O Trailer ainda não estar disponível",
                    Toast.LENGTH_SHORT).show();
        }
    }

}



package com.example.eltonmelo.videos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import Adapter.ImageAdapter;
import Helper.Constants;
import Model.DetailMovieModel;
import Model.GenreModel;
import Model.SpokenLanguage;
import Model.TOMovieLIst;
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

    int idMovie;

    @AfterViews
    void AfterViews() {
//        setSupportActionBar(toolbar);
        Bundle b = getIntent().getExtras();
        long value = b.getLong(Constants.EXTRA_VIDEO);
        idMovie = (int) value;
        getDetailMovie(idMovie);
    }

    @Background
    void  getDetailMovie(int id) {
        DetailMovieModel detailMovie = restClient.getDetailMovie(id);
        updateView(detailMovie);
    }

    @UiThread
    void updateView(DetailMovieModel detailMovieModel) {
        released.setText(detailMovieModel.getStatus());
//        data.setText(detailMovieModel.getReleaseDate());
        minutos.setText(String.valueOf(detailMovieModel.getRuntime()) + " Minutos" );
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

    @Background
    void  getTrailes() {
        TOTrailerModel toTrailerModel = restClient.getTrailer(idMovie);
        callScreenTrailers(toTrailerModel);
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
        } else  {
            Toast.makeText(this, "O Trailer ainda não estar disponível",
                    Toast.LENGTH_SHORT).show();
        }
    }

}



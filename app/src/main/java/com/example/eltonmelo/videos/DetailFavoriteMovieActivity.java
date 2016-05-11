package com.example.eltonmelo.videos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import Helper.Constants;
import Model.DetailMovieModel;
import Model.GenreModel;
import Model.SpokenLanguage;
import Model.TOPhoto;
import Model.TOTrailerModel;
import WS.RestClient;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by eltonmelo on 5/11/16.
 */
@EActivity(R.layout.detail_favorite_movie)
@OptionsMenu(R.menu.menu_detail_favorite)
public class DetailFavoriteMovieActivity extends AppCompatActivity {

    @RestService
    RestClient restClient;

    @ViewById
    TextView releasedFavorite;

    @ViewById
    TextView dataFavorite;

    @ViewById
    TextView minutosFavorite;

    @ViewById
    TextView descricaoFavorite;

    @ViewById
    TextView generoFavorite;

    @ViewById
    TextView idiomaFavorite;

    @ViewById
    ImageView imageViewFavorite;

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

    void getDetailMovie(int id) {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);
        RealmResults<DetailMovieModel> tasks = realm.where(DetailMovieModel.class).equalTo("idDetailMovie", id).findAll();
        detailMovie = tasks.get(0);
        updateView(detailMovie);
    }

    @UiThread
    void updateView(DetailMovieModel detailMovieModel) {
        releasedFavorite.setText(detailMovieModel.getStatus());
        minutosFavorite.setText(String.valueOf(detailMovieModel.getRuntime()) + " Minutos");
        descricaoFavorite.setText(detailMovieModel.getOverview());

        String genero = "";
        for (GenreModel genreModel : detailMovieModel.getGenres()) {
            genero = genero + "" + genreModel.getName() + " |";
        }

        this.generoFavorite.setText(genero);

        String idioma = "";
        for (SpokenLanguage spokenLanguage : detailMovieModel.getSpokenLanguages()) {
            idioma = idioma + "" + spokenLanguage.getName() + " |";
        }

        this.idiomaFavorite.setText(idioma);

        String[] separated = detailMovieModel.getReleaseDate().split("-");
        String dia = separated[2];
        String mes = separated[1];
        String ano = separated[0];
        dataFavorite.setText(dia + "/" + mes + "/" + ano);


        String url = "http://image.tmdb.org/t/p/w500" + detailMovieModel.getPosterPath();
        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(this) //
                .load(url) //
                .placeholder(R.drawable.sample_0) //
                .error(R.drawable.error) //
                .fit() //
                .tag(this) //
                .into(imageViewFavorite);

    }

    @Click(R.id.buttonTrailesFavorite)
    void touchButtonTrailes() {
        getTrailes();
    }

    @Click(R.id.buttonPhotosFavorite)
    void touchButtonPhotos() {
        getPhotos();
    }

    @OptionsItem(R.id.action_favorite_delete)
    void touchButtonDelete() {
        Toast.makeText(this, "O filme foi removido dos favoritos.",
                Toast.LENGTH_SHORT).show();

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this).build();
        Realm realm = Realm.getInstance(realmConfig);

        realm.beginTransaction();
        detailMovie.deleteFromRealm();
        realm.commitTransaction();
        finish();

    }

    @Background
    void getTrailes() {
        TOTrailerModel toTrailerModel = restClient.getTrailer(idMovie);
        callScreenTrailers(toTrailerModel);
    }

    @UiThread
    void callScreenTrailers(TOTrailerModel toTrailerModel) {

        if (toTrailerModel.getResults().size() > 0) {
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

    @Background
    void getPhotos() {
        TOPhoto toPhoto = restClient.getPhotos(idMovie);
        callScreenPhotos(toPhoto);
    }

    @UiThread
    void callScreenPhotos(TOPhoto toPhoto) {

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
}

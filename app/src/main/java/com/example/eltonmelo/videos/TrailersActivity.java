package com.example.eltonmelo.videos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import Adapter.TrailerAdapter;
import Helper.Constants;
import Model.TOTrailerModel;
import WS.RestClient;

/**
 * Created by eltonmelo on 4/25/16.
 */
@EActivity(R.layout.trailers_layout)
public class TrailersActivity extends AppCompatActivity {

    @RestService
    RestClient restClient;

    @ViewById
    ListView listTrailer;

    int idMovie;

    TrailerAdapter trailerAdapter;

    @AfterViews
    void AfterViews() {
        Bundle b = getIntent().getExtras();
        long value = b.getLong(Constants.EXTRA_TRAILERS);
        idMovie = (int) value;
        getTrailers(idMovie);
    }

    @Background
    void  getTrailers(int cod) {
        TOTrailerModel toTrailerModel = restClient.getTrailer(cod);
        updateView(toTrailerModel);
    }

    @UiThread
    void updateView(TOTrailerModel t) {
        trailerAdapter = new TrailerAdapter(this, t);
        listTrailer.setAdapter(trailerAdapter);
    }

}

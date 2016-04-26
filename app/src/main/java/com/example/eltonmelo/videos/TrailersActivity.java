package com.example.eltonmelo.videos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.rest.RestService;

import Adapter.TrailerAdapter;
import Helper.Constants;
import Model.TOTrailerModel;
import Model.TrailerModel;
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

    @ItemClick
    public void  listTrailer(int item) {
        TrailerModel t = (TrailerModel) trailerAdapter.getItem(item);
        Intent intent = new Intent(this, WebViewActivity_.class);
        Bundle b = new Bundle();
        b.putString(Constants.URA_TRAILER, t.getKey());
        intent.putExtras(b);
        startActivity(intent);

//        String url = "https://www.youtube.com/watch?v=" + t.getKey();
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));


    }

}

package com.example.eltonmelo.videos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import Helper.Constants;

/**
 * Created by eltonmelo on 4/25/16.
 */

@EActivity(R.layout.web_view)
public class WebViewActivity extends AppCompatActivity {

    @ViewById
    WebView webViewTrailer;

    @AfterViews
    void AfterViews() {
        Bundle b = getIntent().getExtras();
        String value = b.getString(Constants.URA_TRAILER);

//        String url = "https://www.youtube.com/watch?v=" + value;

        webViewTrailer.setWebViewClient(new MyBrowser());
        webViewTrailer.getSettings().setJavaScriptEnabled(true);
        webViewTrailer.getSettings().setLoadsImagesAutomatically(true);

        final String mimeType = "text/html";
        final String encoding = "UTF-8";
        String html = getHTML(value);
        webViewTrailer.loadDataWithBaseURL("", html, mimeType, encoding, "");
//        webViewTrailer.loadUrl(url);
    }

    public String getHTML(String videoId) {

        String html =
                "<iframe class=\"youtube-player\" "
                        + "style=\"border: 0; width: 100%; height: 96%;"
                        + "padding:0px; margin:0px\" "
                        + "id=\"ytplayer\" type=\"text/html\" "
                        + "src=\"http://www.youtube.com/embed/" + videoId
                        + "?fs=0\" frameborder=\"0\" " + "allowfullscreen autobuffer "
                        + "controls onclick=\"this.play()\">\n" + "</iframe>\n";

/**
 * <iframe id="ytplayer" type="text/html" width="640" height="360"
 * src="https://www.youtube.com/embed/WM5HccvYYQg" frameborder="0"
 * allowfullscreen>
 **/

        return html;
    }
}

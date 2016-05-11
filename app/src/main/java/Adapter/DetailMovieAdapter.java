package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.eltonmelo.videos.R;
import com.squareup.picasso.Picasso;

import Model.DetailMovieModel;
import Model.Movie;
import io.realm.RealmResults;

/**
 * Created by eltonmelo on 5/11/16.
 */
public class DetailMovieAdapter extends BaseAdapter {

    private Context mContext;
    private RealmResults<DetailMovieModel> results;

    public DetailMovieAdapter (Context c, RealmResults<DetailMovieModel> results) {
        mContext = c;
        this.results = results;
    }

    @Override
    public int getCount() {
        return this.results.size();
    }

    @Override
    public Object getItem(int position) {
        return this.results.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            imageView = (ImageView) convertView;
        }

        DetailMovieModel movie = (DetailMovieModel) getItem(position);
        String url = "http://image.tmdb.org/t/p/w500" + movie.getPosterPath();
        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(mContext) //
                .load(url) //
                .placeholder(R.drawable.sample_0) //
                .error(R.drawable.error) //
                .fit() //
                .tag(mContext) //
                .into(imageView);

        return imageView;
    }
}

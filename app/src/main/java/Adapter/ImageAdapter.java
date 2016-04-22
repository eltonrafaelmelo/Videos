package Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.eltonmelo.videos.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Model.Movie;

/**
 * Created by eltonmelo on 4/19/16.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Movie> results;

    public ImageAdapter(Context c, ArrayList<Movie> results) {
        mContext = c;
        this.results = results;
    }

    public int getCount() {
        return this.results.size();
    }

    public Object getItem(int position) {
        return this.results.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            imageView = (ImageView) convertView;
        }

        Movie movie =  results.get(position);
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

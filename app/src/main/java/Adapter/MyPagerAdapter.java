package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eltonmelo.videos.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Model.Movie;
import Model.PosterModel;

/**
 * Created by elton.melo on 05/05/2016.
 */
public class MyPagerAdapter extends PagerAdapter {

    private Context mContext;
    private  ArrayList<PosterModel> posters;

    public MyPagerAdapter(Context c, ArrayList<PosterModel> posters) {
        mContext = c;
        this.posters = posters;
    }

    @Override
    public int getCount() {
        return this.posters.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {


        TextView textView = new TextView(mContext);
//        textView.setTextColor(0x003399);
        textView.setTextColor(Color.GRAY);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText(String.valueOf(position + 1) + " de " + getCount());

        ImageView imageView = new ImageView(mContext);

        String url = "http://image.tmdb.org/t/p/w500" + posters.get(position).getFilePath();
        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(mContext) //
                .load(url) //
                .placeholder(R.drawable.sample_0) //
                .error(R.drawable.error) //
                .fit() //
                .tag(this) //
                .into(imageView);

        ViewGroup.LayoutParams imageParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(imageParams);

        LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.VERTICAL);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        layout.setBackgroundColor(backgroundcolor[position]);
        layout.setLayoutParams(layoutParams);
        layout.addView(textView);
        layout.addView(imageView);

        final int page = position;
        layout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,
                        "Page " + page + " clicked",
                        Toast.LENGTH_LONG).show();
            }});

        container.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}

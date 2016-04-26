package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.eltonmelo.videos.R;

import Model.TOTrailerModel;
import Model.TrailerModel;

/**
 * Created by eltonmelo on 4/25/16.
 */
public class TrailerAdapter extends BaseAdapter {

    private Context context;
    private TOTrailerModel toTrailerModel;

    public TrailerAdapter(Context c, TOTrailerModel t) {
        context = c;
        toTrailerModel = t;
    }

    @Override
    public int getCount() {
        return toTrailerModel.getResults().size();
    }

    @Override
    public Object getItem(int position) {
        return toTrailerModel.getResults().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TrailerModel trailerModel = toTrailerModel.getResults().get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_list_trailer, null);

        TextView textnome = (TextView) view.findViewById(R.id.textview_name);
//        textnome.setText("Trailer - " + String.valueOf(position));
        textnome.setText(trailerModel.getName());

        return view;
    }
}

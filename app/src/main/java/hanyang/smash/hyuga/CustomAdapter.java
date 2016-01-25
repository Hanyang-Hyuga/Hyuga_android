package hanyang.smash.hyuga;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by nayunhwan on 16. 1. 24..
 */
public class CustomAdapter extends BaseAdapter {

    private ArrayList<String> m_list;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomAdapter(){
        m_list = new ArrayList<String>();
    }

    public void add(String input){
        m_list.add(input);
    }

    @Override
    public int getCount() {
        return m_list.size();
    }

    @Override
    public Object getItem(int position) {
        return m_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView == null){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            TextView textView = (TextView) convertView.findViewById(R.id.textView);
            textView.setText(getItem(position).toString());

            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();

            NetworkImageView thumbNail = (NetworkImageView) convertView
                    .findViewById(R.id.imgView);

            thumbNail.setImageUrl("http://placehold.it/30x30", imageLoader);
        }
        return convertView;
    }

    private class ImageLoad{

    }
}

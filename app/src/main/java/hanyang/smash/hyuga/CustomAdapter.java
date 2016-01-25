package hanyang.smash.hyuga;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by nayunhwan on 16. 1. 24..
 */
public class CustomAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private Context mContext = null;

    ImageView imgView;

    private ArrayList<String[]> m_list;
//    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomAdapter() {
        m_list = new ArrayList<String[]>();
    }

    public void add(String title, String thumbnail_url, String price) {
        String data[] = {title, thumbnail_url, price};
        m_list.add(data);
    }

    public void add(String title, String thumbnail_url) {
        String data[] = {title, thumbnail_url};
        m_list.add(data);
    }

    @Override
    public int getCount() {
        return m_list.size();
    }

    @Override
    public Object[] getItem(int position) {
        return m_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();
//        if (inflater == null)
//            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {



            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);

            TextView textView = (TextView) convertView.findViewById(R.id.textView);
            textView.setText(getItem(position)[0].toString());

            imgView = (ImageView) convertView.findViewById(R.id.imgView);

            Log.d("TAG", "NA URL : " + getItem(position)[1]);
            new LoadImageConnect().execute("http:" + getItem(position)[1]);
        }

        return convertView;
    }


    // AsyncTask<Params,Progress,Result>
    private class LoadImageConnect extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            HttpURLConnection connection = null;
            try {
                Log.d("TAG", "NAYUNHWAN : Success!!");
//                URL url;
//                url = new URL(params[0]);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream input = connection.getInputStream();
//                Bitmap myBitmap = BitmapFactory.decodeStream(input);
//                return myBitmap;

                URL url = new URL(params[0]);
                Log.d("TAG", "NAYUNHWAN" + params[0]);
                URLConnection conn = url.openConnection();
                conn.connect();
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                Bitmap bitmap = BitmapFactory.decodeStream(bis);
                bis.close();
//                Log.d("TAG", "NAYUNHWAN" + bitmap.toString());
                return bitmap;
            } catch (IOException e) {
                Log.d("TAG", "SSSSSSSSSSSSSIIIIIII" + e + toString());
                e.printStackTrace();
                return null;
            } finally {
                if (connection != null) connection.disconnect();
            }
        }


        protected void onPostExecute(Bitmap result) {
//            Bitmap bitmap = result;
            Log.d("TAG", "NAYUNHWAN postExecute");
            imgView.setImageBitmap(result);
        }


    }

}

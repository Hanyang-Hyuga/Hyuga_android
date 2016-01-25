package hanyang.smash.hyuga;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    CustomAdapter mAdapter;
    ListView listView;
    Elements titles;
    Elements thumbnails;
    Document doc;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayAdapter mAdapter = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item);
        mAdapter = new CustomAdapter();
        listView = (ListView) findViewById(R.id.listView);
        new ProcessWebConnect().execute();
        listView.setAdapter(mAdapter);
//        mAdapter.add("test");


    }

    private class ProcessWebConnect extends AsyncTask<Void, Void, Void>{


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
//            mAdapter.add("test1");
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("데이터를 가져오는 중 입니다.");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try{
//                mAdapter.add("test2");
                String url = "http://www.ssg.com/search.ssg?target=all&query=삼겹살";
                doc = Jsoup.connect(url).userAgent("Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52").get();
                titles = doc.select("p.summary > a > span.subject");
                thumbnails = doc.select("div.thumbnail > a > img");

            }
            catch (IOException e){
                Log.d("TAG", e.getCause().toString());

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
//            mAdapter.add("test3");
            for(int i = 0; i < titles.size(); i++) {
                Log.d("TAG", "NA : " + titles.get(i).text().toString());
                Log.d("TAG", "NA : " + thumbnails.get(i).attr("src").toString());
                mAdapter.add(titles.get(i).text().toString(), thumbnails.get(i).attr("src"));
            }
            mAdapter.notifyDataSetChanged();
            mProgressDialog.dismiss();

        }
    }
}

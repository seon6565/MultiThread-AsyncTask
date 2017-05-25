package comwow2778.naver.blog.app16;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {
    TextView tv;
    ProgressBar pb;
    myTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tv  = (TextView)findViewById(R.id.textviews);
        pb = (ProgressBar)findViewById(R.id.progressBar);
    }

    class myTask extends AsyncTask<Integer,Integer,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setProgress(0);
            tv.setTextColor(Color.RED);
            tv.setText("진행률 : 0%");
        }


        @Override
        protected Void doInBackground(Integer... params) {
            for(int i = 1; i<101; i++){
                if(isCancelled() == true){
                    return null;
                }
                try {
                    Thread.sleep(200);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]);
            tv.setText("진행률 :" + values[0]);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pb.setProgress(100);
            tv.setText("완료 되었습니다.");
        }

        @Override
        protected void onCancelled() {
            tv.setText("취소 되었습니다.");
            super.onCancelled();
        }
    }

    public void onClick(View v){
        if(v.getId() == R.id.start){
            task = new myTask();
            task.execute(0);//params
        }
        else{
            task.cancel(true);
        }
    }
}

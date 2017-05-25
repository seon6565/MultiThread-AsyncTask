package comwow2778.naver.blog.app16;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {
    EditText et1;
    TextView tv1;
    ImageView iv1;
    myTask task;
    int seconds = 1000;
    int i = 0;
    int[] img = {R.drawable.banana, R.drawable.grape, R.drawable.kiwi, R.drawable.orange};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        setTitle("무슨과일을 선택하지");

        et1 = (EditText)findViewById(R.id.eds);
        tv1 = (TextView)findViewById(R.id.texttime);
        iv1 = (ImageView)findViewById(R.id.imageView1);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(task == null){
                    task = new myTask();
                    task.execute(0);
                }
                else{
                    //멈추는거
                }
            }
        });
    }
    class CountTask extends AsyncTask<Integer,Integer,Void>{

        @Override
        protected Void doInBackground(Integer... params) {
            return null;
        }
    }

    class myTask extends AsyncTask<Integer,Integer,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            iv1.setImageResource(0);
            if(et1.getText().toString().equals("")){
                seconds = 1000;
            }
            else {
                seconds = Integer.valueOf(et1.getText().toString()) * 1000;
            }
        }


        @Override
        protected Void doInBackground(Integer... params) {
            for(i = 0; i<101; i++){
                if(isCancelled() == true){
                    return null;
                }
                try {
                    Thread.sleep(seconds);
                    publishProgress(i % 4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv1.setText("시작 부터" + i +"초");
            iv1.setImageResource(img[values[0]]);

        }
        @Override
        protected void onCancelled() {
            iv1.setImageResource(R.drawable.play);
            i = 0;
            super.onCancelled();
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            iv1.setImageResource(R.drawable.play);
            tv1.setText("");
        }

    }





    public void onClick(View v){
        task.cancel(true);
    }
}

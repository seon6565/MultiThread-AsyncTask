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
    int[] img = {R.drawable.banana, R.drawable.grape, R.drawable.kiwi, R.drawable.orange};
    int count, seconds,index;
    int startcount=0;
    String[] imgname = {"바나나", "포도", "키위", "오렌지"};
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
                startcount++;
                if(startcount%2 == 1){
                    task = new myTask();
                    task.execute(0);
                }
                else{
                    task.cancel(true);
                }
            }
        });
    }

    class myTask extends AsyncTask<Integer,Integer,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            index = 0;
            if(et1.getText().toString().equals("")){
                count = 1;
            }
            else {
                count = Integer.parseInt(et1.getText().toString());
            }
            seconds = 0;
            tv1.setVisibility(View.VISIBLE);
            tv1.setText("시작부터 " + seconds + "초");
        }


        @Override
        protected Void doInBackground(Integer... params) {
            while(isCancelled()==false){
                seconds++;
                try {
                    Thread.sleep(1000);
                    if(seconds%count == 0){
                        if(index>=3) {
                            index = -1;
                        }
                        publishProgress(seconds, ++index);
                    }
                    else {
                        publishProgress(seconds, index);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cancel(true);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv1.setText("시작 부터" + values[0] +"초");
            iv1.setImageResource(img[values[1]]);

        }
        @Override
        protected void onCancelled() {
            tv1.setText(imgname[index] + "선택" +"(" + seconds+" 초)");
            super.onCancelled();
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

    }





    public void onClick(View v) {
        if (v.getId() == R.id.button3) {
            iv1.setImageResource(R.drawable.play);
            tv1.setVisibility(View.INVISIBLE);
            et1.setText("");
        }
    }
}

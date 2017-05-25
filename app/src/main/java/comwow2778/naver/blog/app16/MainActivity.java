package comwow2778.naver.blog.app16;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    TextView tv;

    int index = 0;
    Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            tv.setText("숫자"+index);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView);

    }

    public void onClick(View v){
        MyThread thread = new MyThread();
        thread.start();
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            for(index  = 1; index<10; index++){
                try{
                    Thread.sleep(1000);
                    //tv.setText("숫자"+index);
                    Message msg = mhandler.obtainMessage();
                    mhandler.sendMessage(msg);
                    /*
                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText("숫자"+index);
                        }
                    });*/
                }
                catch (InterruptedException e){
                    e.printStackTrace();

                }
            }
        }
    }
}

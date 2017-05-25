package comwow2778.naver.blog.app16;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
    EditText et1,et2;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SubThread.subHandler.getLooper().quit();
    }

    Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
                String name2 = (String)msg.obj;
            et2.setText(name2+"안녕하세요");
        }
    };
    MyThread SubThread = new MyThread();

    class MyThread extends Thread{
        SubHandler subHandler = new SubHandler();
        @Override
        public void run() {
            Looper.prepare();
            Looper.loop();
        }
    }
    class SubHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String name = (String)msg.obj;

            Message msg2 = Message.obtain();
            msg2.obj = name;
            mhandler.sendMessage(msg2);
        }
    }
    class SubThread extends Thread {
        SubHandler subHandler = new SubHandler();
        @Override
        public void run() {
            Looper.prepare();
            Looper.loop();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        SubThread.start();
    }

    public void onClick(View v){
        String name = et1.getText().toString();
        Message msg = Message.obtain();
        msg.obj = name;
        SubThread.subHandler.sendMessage(msg);

    }

}

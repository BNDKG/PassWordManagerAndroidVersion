package com.example.zhangmingchen.zdsfsdf;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.widget.Toast;


public class MainActivity extends Activity {


    private Button zbutton2;
    private Button zbutton1;
    private Button zbutton3;
    private TextView ztextview1;
    private EditText zedittext1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zbutton1 =findViewById(R.id.button);
        zbutton2=findViewById(R.id.button2);
        zbutton3=findViewById(R.id.button3);

        ztextview1 =findViewById(R.id.textView);
        zedittext1 =findViewById(R.id.editText);


        zbutton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一个点击事件

                createFileCatalogue();

                String []s = {"aa","abc","ks"};//String变量转换utf8测试
                Gson gson = new Gson();
                String ss = gson.toJson(s);

                byte b[] = ss.getBytes();
                String t = new String(b);

                String [] te = gson.fromJson(t,String[].class);


            }
        });
        zbutton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread SendMsgTD = new Thread(new Runnable(){
                    @Override

                    public void run()
                    {
                        try {

                            final Socket socket = new Socket( "192.168.3.9", 13000);
                            OutputStream outputStream = socket.getOutputStream();
                            //byte buffer[] = new byte[256];

                            String []s = {"aa","abc","ks"};//String变量转换utf8测试
                            Gson gson = new Gson();
                            String ss = gson.toJson(s);

                            byte[] buffer = ss.getBytes();

                            outputStream.write(buffer, 0, buffer.length);

                            outputStream.flush();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                },"");

                SendMsgTD.start();
                System.out.println("客户端启动成功");

            }
        });
        zbutton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String xxxd= zedittext1.getText().toString();
                ClipboardManager myClipboard;
                myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

                ClipData myClip;
                //String text = "hello world";
                myClip = ClipData.newPlainText("text", xxxd);
                myClipboard.setPrimaryClip(myClip);


                //zzzzsecondbutton.setText(xxxd);
            }
        });


        //Socket socket = null;

    }

    private boolean mIsExit;//双击返回键退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { if (keyCode == KeyEvent.KEYCODE_BACK) { if (mIsExit) { this.finish();
    } else {
        Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
        mIsExit = true; new Handler().postDelayed(new Runnable() { @Override public void run() {
            mIsExit = false;
        }
        }, 2000);
    } return true;
    } return super.onKeyDown(keyCode, event);
    }


    // 定义文件夹目录地址
    static String zPSW = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "zPSW";

    /**
     * 创建文件目录
     */
    private void createFileCatalogue() {
        File destDir = new File(zPSW);
        if (!destDir.exists()) {
            boolean zmc=destDir.mkdirs();
            int ds=10;//判断是否开了权限
        }
    }


}

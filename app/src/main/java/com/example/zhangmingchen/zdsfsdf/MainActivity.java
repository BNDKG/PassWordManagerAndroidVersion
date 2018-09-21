package com.example.zhangmingchen.zdsfsdf;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.example.zhangmingchen.zdsfsdf.zlistview.ListChoiceActivity;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    private Button zbutton1;
    private Button zbutton2;
    private Button zbutton3;
    private Button zbutton4;
    private TextView ztextview1;
    private EditText zedittext1;

    private byte[] Getbuffer;
    private List<PSW> curpswlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zbutton1 =findViewById(R.id.button);
        zbutton2=findViewById(R.id.button2);
        zbutton3=findViewById(R.id.button3);
        zbutton4=findViewById(R.id.button4);

        ztextview1 =findViewById(R.id.textView);
        zedittext1 =findViewById(R.id.editText);


        zbutton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //第一个点击事件
                String userflo2=zPSW+File.separator+"BNDKG";
                //删除保存的所有文件夹
                deleteDir(userflo2);
                //新建名为xxx的文件夹
                createFileCatalogue("BNDKG");

                createfiletest("BNDKG","config","fuck the world!");
                //createfiletest("eee","thfdht");

                /*
                String keyz="12345678876543211234567887654abc";
                String ee="111";
                String out =Aes.encrypt(keyz,ee);

                String back=Aes.decrypt(keyz,out);

                int dsdfff=1;

                ztextview1.setText(ee);



                String []s = {"aa","abc","ks"};//String变量转换utf8测试
                Gson gson = new Gson();
                String ss = gson.toJson(s);

                byte b[] = ss.getBytes();
                String t = new String(b);

                String [] te = gson.fromJson(t,String[].class);
                */

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
                            //byte Sendbuffer[] = new byte[256];

                            Gson gson = new Gson();

                            String[]usermassage2= { "1", "BNDKG", "kkk", "fdgr" };
                            String ss2 = gson.toJson(usermassage2);
                            byte[] Sendbuffer2 = ss2.getBytes();
                            int xdfsdf=Sendbuffer2.length;
                            String msglen=String.format("%04d",xdfsdf);

                            String []s = {"002", msglen};//String变量转换utf8测试
                            String ss = gson.toJson(s);
                            byte[] Sendbuffer = ss.getBytes();


                            outputStream.write(Sendbuffer, 0, Sendbuffer.length);
                            outputStream.write(Sendbuffer2, 0, Sendbuffer2.length);

                            byte [] data1=new byte[28];

                            InputStream inputStream=socket.getInputStream();

                            inputStream.read(data1,0,data1.length);

                            String t22 = new String(data1);
                            String [] te = gson.fromJson(t22,String[].class);

                            int blen = Integer.parseInt(te[1]);
                            int clen = Integer.parseInt(te[2]);
                            int dlen = Integer.parseInt(te[3]);

                            byte [] data2=new byte[blen];
                            byte [] data3=new byte[clen];
                            byte [] data4=new byte[dlen];

                            inputStream.read(data2,0,data2.length);
                            inputStream.read(data3,0,data3.length);
                            inputStream.read(data4,0,data4.length);

                            String receive2=new String(data2);
                            String[]receive22=gson.fromJson(receive2,String[].class);
                            String receive3=new String(data3);
                            String[]receive33=gson.fromJson(receive3,String[].class);
                            String receive4=new String(data4);
                            String[]receive44=gson.fromJson(receive4,String[].class);

                            curpswlist = new ArrayList<PSW>();
                            int ii = 0;
                            for (String name : receive22) {
                                PSW pswtest=new PSW();
                                pswtest.name=name;
                                pswtest.psw=receive33[ii];
                                pswtest.info=receive44[ii];
                                curpswlist.add(pswtest);
                                ii++;
                            }


                            outputStream.flush();


                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                },"");

                SendMsgTD.start();


            }
        });
        zbutton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //读取本地文件夹目录
                List<String> zmctest2=searchFiles(zPSW+"/"+"BNDKG","txt",false,new ArrayList<String>());
                List<String> PSWnames=new ArrayList<String>();
                for(String item : zmctest2){
                    String savename=getFileName(item);
                    PSWnames.add(savename);

                }
                for(PSW curpsw:curpswlist){
                    String searchinfo=curpsw.info;
                    if(zmctest2.contains(searchinfo)){
                        //todo
                    }
                    else {
                        createfiletest("BNDKG",searchinfo,curpsw.psw);
                    }

                }

                /*
                String xxxd= zedittext1.getText().toString();
                ClipboardManager myClipboard;
                myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

                ClipData myClip;
                //String text = "hello world";
                myClip = ClipData.newPlainText("text", xxxd);
                myClipboard.setPrimaryClip(myClip);
                */

                //zzzzsecondbutton.setText(xxxd);
            }
        });
        zbutton4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ListChoiceActivity.class);
                startActivity(intent);
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
    //static String oriPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;

    //删除文件夹和文件夹里面的文件
    public static void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWithFile(dir);
    }

    public static void deleteDirWithFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWithFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }


    /**
     * 创建文件目录
     */
    private void createFileCatalogue(String Username) {
        String userflo=zPSW+File.separator+Username;

        File destDir = new File(userflo);
        if (!destDir.exists()) {
            boolean zmc=destDir.mkdirs();
            if(!zmc){
                int ds=10;//判断是否开了权限
            }
        }
    }
    private void createfiletest(String username,String filename,String psw){
        String Savepath=zPSW+"/"+username+"/"+filename+".txt";
        File file = new File(Savepath);

        try {

            FileOutputStream fos = new FileOutputStream(file);

            //写入用户名和密码，以name##passwd的格式写入

            fos.write((psw).getBytes());

            //关闭输出流

            fos.close();

        } catch (Exception e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }


    }

    public String getFileName(String pathandname){

        int start=pathandname.lastIndexOf("/");
        int end=pathandname.lastIndexOf(".");
        if(start!=-1 && end!=-1){
            return pathandname.substring(start+1,end);
        }else{
            return null;
        }

    }

    /**

     * 搜索文件夹下指定扩展名

     * @param Path 搜索目录

     * @param Extension 扩展名

     * @param IsIterative 是否递归遍历子文件夹

     * @param resultList  结果,即文件名列表

     * @return

     */
    public static ArrayList<String> searchFiles(String Path, String Extension, boolean IsIterative, ArrayList<String> resultList)

    {

        File[] files = new File(Path).listFiles();

        if(files == null)

            return null;

        for (int i = 0; i < files.length; i++)

        {

            File f = files[i];

            if (f.isFile())

            {

                String ext = Extension.toLowerCase();

                String currExt = f.getPath().substring(f.getPath().length() - Extension.length()).toLowerCase();

                if (ext.equals(currExt))

                    resultList.add(f.getPath());

            }

            else {

                if (IsIterative) {

                    if (f.isDirectory() && f.getPath().indexOf("/.") == -1)

                        resultList = searchFiles(f.getPath(), Extension, IsIterative, resultList);

                }

                else

                    continue;;

            }

        }

        return resultList;

    }

}

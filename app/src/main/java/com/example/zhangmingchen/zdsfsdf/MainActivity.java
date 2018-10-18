package com.example.zhangmingchen.zdsfsdf;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
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
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;


public class MainActivity extends Activity {

    private Button zbutton1;
    private Button zbutton2;
    private Button zbutton3;
    private Button zbutton4;
    private Button zbutton5;

    private TextView ztextview1;
    private EditText zedittext1=null;
    private EditText zpswtext1=null;

    private CheckBox zcheckbox;

    private byte[] Getbuffer;
    public static List<PSW> curpswlist;

    public static String[] zdatas={"Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple"};

    private static final String CONTENT = "content";

    public static String curaeskey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zbutton1 =findViewById(R.id.button);
        zbutton2=findViewById(R.id.button2);
        zbutton3=findViewById(R.id.button3);
        zbutton4=findViewById(R.id.button4);
        zbutton5=findViewById(R.id.button5);

        ztextview1 =findViewById(R.id.textView);
        zedittext1 =findViewById(R.id.editText);
        zpswtext1 =findViewById(R.id.editText2);

        zcheckbox=findViewById(R.id.checkBox);

        if (null!=savedInstanceState&&savedInstanceState.containsKey(CONTENT))
        {
            //异常退出恢复
            zedittext1.setText(savedInstanceState.getString(CONTENT));
        }
        String loadpath=zPSW+"/Setting/setting";
        try {
            List<String>zzz=readFile(loadpath);
            if(zzz.get(0).equals(" ")){

            }
            else {
                zedittext1.setText(zzz.get(0));
                zpswtext1.setText(zzz.get(1));
                zcheckbox.setChecked(true);
                //zedittext1.setText(loadFromSDFile(loadpath));
            }


        }
        catch(Exception e) {}



        zbutton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String curuser=zedittext1.getText().toString();
                String upsw=zpswtext1.getText().toString();
                //"BNDKG"
                //第一个点击事件
                String userflo2=zPSW+File.separator+curuser;
                //删除保存的所有文件夹
                deleteDir(userflo2);
                //新建名为xxx的文件夹
                createFileCatalogue(curuser);
                //新建此用户密码
                createUser(curuser,upsw);



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

                String curuser=zedittext1.getText().toString();
                String upsw=zpswtext1.getText().toString();

                if(CheeckUserLocal(curuser,upsw)){
                    Thread SendMsgTD = new Thread(new Runnable(){
                        @Override

                        public void run()
                        {
                            try {
                                String curuser=zedittext1.getText().toString();
                                String upsw=zpswtext1.getText().toString();

                                //final Socket socket = new Socket( "45.40.201.83", 13000);

                                final Socket socket = new Socket( );
                                InetSocketAddress socAddress = new InetSocketAddress("45.40.201.83", 13000);
                                socket.connect(socAddress, 5000);
                                OutputStream outputStream = socket.getOutputStream();

                                Gson gson = new Gson();

                                String[]usermassage2= { "1", curuser, upsw, "Superbndkg" };

                                String ss2 = gson.toJson(usermassage2);
                                byte[] Sendbuffer2 = ss2.getBytes();
                                int xdfsdf=Sendbuffer2.length;
                                String msglen=String.format("%04d",xdfsdf);

                                String []usermessage1 = {"002", msglen};//String变量转换utf8测试
                                String ss = gson.toJson(usermessage1);
                                byte[] Sendbuffer = ss.getBytes();


                                outputStream.write(Sendbuffer, 0, Sendbuffer.length);
                                outputStream.write(Sendbuffer2, 0, Sendbuffer2.length);

                                byte [] data1=new byte[26];

                                InputStream inputStream=socket.getInputStream();


                                Thread.sleep(2000);
                                inputStream.read(data1,0,data1.length);

                                String t22 = new String(data1);
                                String [] te = gson.fromJson(t22,String[].class);

                                int error=Integer.parseInt(te[0]);
                                if(error==1){
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

                                    //String curuser=zedittext1.getText().toString();
                                    //String upsw=zpswtext1.getText().toString();

                                    //读取本地文件夹目录
                                    List<String> zmctest2=searchFiles(zPSW+"/"+curuser,"txt",false,new ArrayList<String>());
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
                                            createfiletest(curuser,searchinfo,curpsw.psw);
                                        }

                                    }

                                    Looper.prepare();
                                    Toast.makeText(MainActivity.this,"同步成功",Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }
                                else{
                                    outputStream.flush();
                                    Looper.prepare();
                                    Toast.makeText(MainActivity.this,"同步失败",Toast.LENGTH_SHORT).show();
                                    Looper.loop();
                                }



                            } catch (IOException e) {
                                Looper.prepare();
                                Toast.makeText(MainActivity.this,"服务器连接失败",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }catch (Exception e){
                                Looper.prepare();
                                Toast.makeText(MainActivity.this,"数据获取错误",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                        }

                    },"");

                    SendMsgTD.start();
                }
                else {
                    Toast.makeText(MainActivity.this,"账户密码错误",Toast.LENGTH_SHORT).show();
                }

            }
        });
        zbutton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Thread SendMsgTD = new Thread(new Runnable(){
                    @Override

                    public void run()
                    {
                        try {
                            String curuser=zedittext1.getText().toString();
                            String upsw=zpswtext1.getText().toString();

                            final Socket socket = new Socket( );
                            InetSocketAddress socAddress = new InetSocketAddress("45.40.201.83", 13000);
                            socket.connect(socAddress, 3000);

                            OutputStream outputStream = socket.getOutputStream();
                            //byte Sendbuffer[] = new byte[256];

                            Gson gson = new Gson();

                            String[]usermassage2= { "0", curuser, upsw, "Superbndkg" };

                            String ss2 = gson.toJson(usermassage2);
                            byte[] Sendbuffer2 = ss2.getBytes();
                            int xdfsdf=Sendbuffer2.length;
                            String msglen=String.format("%04d",xdfsdf);

                            String []usermessage1 = {"002", msglen};//String变量转换utf8测试
                            String ss = gson.toJson(usermessage1);
                            byte[] Sendbuffer = ss.getBytes();


                            outputStream.write(Sendbuffer, 0, Sendbuffer.length);
                            outputStream.write(Sendbuffer2, 0, Sendbuffer2.length);

                            byte [] data1=new byte[16];

                            InputStream inputStream=socket.getInputStream();


                            Thread.sleep(2000);
                            inputStream.read(data1,0,data1.length);

                            String t22 = new String(data1);
                            String [] te = gson.fromJson(t22,String[].class);

                            int error=Integer.parseInt(te[0]);
                            if(error==1){

                                Looper.prepare();
                                Toast.makeText(MainActivity.this,"服务器连接正常",Toast.LENGTH_SHORT).show();
                                Looper.loop();


                            }
                            else{
                                Looper.prepare();
                                Toast.makeText(MainActivity.this,"服务器忙",Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            }
                            outputStream.flush();


                        } catch (IOException e) {
                            Looper.prepare();
                            Toast.makeText(MainActivity.this,"服务器连接失败",Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            e.printStackTrace();
                        } catch (InterruptedException e) {

                            e.printStackTrace();
                        }catch (Exception e){

                        }
                    }

                },"");

                SendMsgTD.start();

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

                //==============从存储中读取================//
                curpswlist = new ArrayList<PSW>();

                String curuser=zedittext1.getText().toString();
                String upsw=zpswtext1.getText().toString();

                if(CheeckUserLocal(curuser,upsw)){
                    //读取本地文件夹目录
                    List<String> zmctest2=searchFiles(zPSW+"/"+curuser,"txt",false,new ArrayList<String>());
                    //List<String> PSWnames=new ArrayList<String>();
                    for(String item : zmctest2){
                        String temp=loadFromSDFile(item);
                        String savename=getFileName(item);

                        PSW pswtest=new PSW();
                        pswtest.name=savename;
                        pswtest.psw=temp;
                        pswtest.info=savename;
                        curpswlist.add(pswtest);
                        //PSWnames.add(savename);
                    }
                }
                //更新aes key
                curaeskey=Aes.getMD5(zpswtext1.getText().toString());

                //================开启text view====================//
                Intent intent=new Intent(MainActivity.this, ListChoiceActivity.class);
                startActivity(intent);
            }
        });
        zbutton5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                deleteDir(zPSW);


            }
        });
        //Socket socket = null;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState)//保存Activity的状态
    {
        super.onSaveInstanceState(outState);

        String content=zedittext1.getText().toString();
        outState.putString(CONTENT,content);
    }
    @Override
    public void finish() {
        String curname=zedittext1.getText().toString();
        String curpsw=zpswtext1.getText().toString();
        String setpath=zPSW+"/Setting";
        File destDir = new File(setpath);
        if (!destDir.exists()) {
            boolean zmc=destDir.mkdirs();
            if(!zmc){
                int ds=10;//判断是否开了权限
            }
        }

        zcheckbox =findViewById(R.id.checkBox);

        if (zcheckbox.isChecked()) {
            SaveSettings(curpsw,curname);
        }
        else {
            SaveSettings(" "," ");
        }

        super.finish();
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

    private void createUser(String username,String psw){
        String Savepath=zPSW+"/"+username+"/Config.upsw";
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



    private void SaveSettings(String ipadd,String name){
        String Savepath=zPSW+"/Setting/setting";
        File file = new File(Savepath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //写入用户名和密码，以name##passwd的格式写入
            fos.write((name).getBytes());
            fos.write("\r\n".getBytes());
            fos.write((ipadd).getBytes());
            //关闭输出流
            fos.close();

        } catch (Exception e) {

            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    }
    private boolean CheeckUserLocal(String username,String psw){
        String Savepath=zPSW+"/"+username+"/Config.upsw";
        String Loadpsw=loadFromSDFile(Savepath);
        if(psw.equals(Loadpsw)){
            return true;
        }
        else{
            Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
            return false;
        }

    }
    //写入文件
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

    private String loadFromSDFile(String fname) {

        String result=null;

        try {

            File f=new File(fname);

            int length=(int)f.length();

            byte[] buff=new byte[length];

            FileInputStream fin=new FileInputStream(f);

            fin.read(buff);

            fin.close();

            result=new String(buff,"UTF-8");

        }catch (Exception e){

            e.printStackTrace();

            //Toast.makeText(MainActivity.this,"没有找到指定文件",Toast.LENGTH_SHORT).show();

        }

        return result;

    }
    /**读取文件
     * @param strFilePath
     */
    public List<String> readFile(String strFilePath){
        List<String> txtList = new ArrayList<>();
        File file = new File(strFilePath);
        if (file.isDirectory()){
            Log.d(TAG, "The File doesn't not exist.");
        }else{
            try{
                InputStream instream = new FileInputStream(file);
                if (instream != null){
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //逐行读取
                    while (( line = buffreader.readLine()) != null){
                        txtList.add(line);
                    }
                    instream.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return txtList;
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

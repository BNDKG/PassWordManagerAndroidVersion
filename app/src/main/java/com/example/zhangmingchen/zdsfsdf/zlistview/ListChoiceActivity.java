package com.example.zhangmingchen.zdsfsdf.zlistview;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.widget.ListView;

import com.example.zhangmingchen.zdsfsdf.Aes;
import com.example.zhangmingchen.zdsfsdf.R;

public class ListChoiceActivity extends Activity {

    private String[] data={"Apple","Banana","Orange","Watermelon","Pear","Grape","Pineapple"};

    private ListView mLV1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_choice);

        mLV1=findViewById(R.id.zListview);
        mLV1.setAdapter(new ZListAdapter(ListChoiceActivity.this));

    }

    public void onCbd(){

        ClipboardManager myClipboard;
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        ClipData myClip;
        //String text = "hello world";
        myClip = ClipData.newPlainText("text", "复制的内容");
        myClipboard.setPrimaryClip(myClip);


    }
    public void aeschanges(){
        String keyz="12345678876543211234567887654abc";
        String ee="111";
        String out = Aes.encrypt(keyz,ee);

        String back=Aes.decrypt(keyz,out);
    }

}

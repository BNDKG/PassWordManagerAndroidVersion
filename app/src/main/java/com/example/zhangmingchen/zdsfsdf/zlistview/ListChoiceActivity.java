package com.example.zhangmingchen.zdsfsdf.zlistview;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.zhangmingchen.zdsfsdf.Aes;
import com.example.zhangmingchen.zdsfsdf.MainActivity;
import com.example.zhangmingchen.zdsfsdf.R;

public class ListChoiceActivity extends Activity {


    private ListView mLV1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_choice);




        mLV1=findViewById(R.id.zListview);
        mLV1.setAdapter(new ZListAdapter(ListChoiceActivity.this));
        mLV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String Pswoutput=Aes.decrypt("12345678876543211234567887654abc",MainActivity.curpswlist.get(position).psw);

                onCbd(Pswoutput);
                //Toast.makeText(ListChoiceActivity.this,"pos:"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onCbd(String cbdcontent){

        ClipboardManager myClipboard;
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        ClipData myClip;
        //String text = "hello world";
        myClip = ClipData.newPlainText("text", cbdcontent);
        myClipboard.setPrimaryClip(myClip);


    }
    public void aeschanges(){
        String keyz="12345678876543211234567887654abc";
        String ee="111";
        String out = Aes.encrypt(keyz,ee);

        String back=Aes.decrypt(keyz,out);
    }

}

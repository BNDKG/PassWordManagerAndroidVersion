package com.example.zhangmingchen.zdsfsdf.zlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangmingchen.zdsfsdf.MainActivity;
import com.example.zhangmingchen.zdsfsdf.PSW;
import com.example.zhangmingchen.zdsfsdf.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangmingchen on 2018/9/21.
 */

public class ZListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<PSW> zmList;

    public ZListAdapter(Context context){

        //zmList=new ArrayList<String>();
        //for (String zdata: MainActivity.zdatas) {zmList.add(zdata);}
        zmList =MainActivity.curpswlist;
        this.mContext=context;
        mLayoutInflater=LayoutInflater.from(context);


    }



    @Override
    public int getCount() {
        return zmList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView tvtitle,tvtime,tvinfo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder =null;
        if(convertView==null){
            convertView = mLayoutInflater.inflate(R.layout.zlayout_list_item,null);
            holder = new ViewHolder();
            holder.imageView=convertView.findViewById(R.id.iv);
            holder.tvtitle=convertView.findViewById((R.id.tv_title));
            holder.tvtime=convertView.findViewById((R.id.tv_time));
            holder.tvinfo=convertView.findViewById((R.id.tv_info));

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();

        }
        //给控件赋值
        holder.tvtitle.setText(zmList.get(position).name);
        holder.tvtime.setText(zmList.get(position).psw);
        holder.tvinfo.setText(zmList.get(position).name);
        //Glide.with(mContext)

        return convertView;
    }
}

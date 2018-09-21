package com.example.zhangmingchen.zdsfsdf.zlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangmingchen.zdsfsdf.R;

/**
 * Created by zhangmingchen on 2018/9/21.
 */

public class ZListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ZListAdapter(Context context){
        this.mContext=context;
        mLayoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 10;
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
        holder.tvtitle.setText("fuck new z");
        holder.tvtime.setText("fuck time sss");
        holder.tvinfo.setText("fuck 3");
        //Glide.with(mContext)

        return convertView;
    }
}

package com.example.myapplication.base.adapter;


import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.ZgwApplication;


import java.util.List;

/**
 * Created by Administrator on 2018/12/3.
 */



    public abstract class BaseAdapter<D> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    private List<D> dataList;

    public abstract int getLayoutId();

    public BaseAdapter(List<D> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        createHolder(holder, dataList.get(position),position);
    }

    @Override
    public int getItemCount() {
        if(dataList!=null){
            return dataList.size();
        }else {
            return  0;
        }

    }

    public abstract void addAll(List<D> list,int page);

    public abstract void createHolder(ViewHolder holder, D d,int position);

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View rootView;
        private SparseArray<View> views = new SparseArray<>();

        public ViewHolder(View itemView) {
            super(itemView);
            rootView=itemView;
        }

        public <T extends View> T get(int id) {
            T view = (T) views.get(id);
            if (view == null) {
                view = (T) rootView.findViewById(id);
                views.put(id, view);
            }
            return view;
        } //TextView设置数据 public void setText(int viewId, String txt) { TextView mTextView = get(viewId); mTextView.setText(txt); } //设置图片 public void setPic(int viewId, String url) { ImageView mImageView = get(viewId); Picasso.with(mContext).load(url).fit().error(R.drawable.ledian).placeholder(mImageView.getDrawable()).memoryPolicy(MemoryPolicy.NO_CACHE).into(mImageView); } } }

        public void setText(int viewId, String txt) {
            TextView mTextView = get(viewId);
            mTextView.setText(txt);
        }

        public void setPic(int viewId, Object url) {
            ImageView mImageView = get(viewId);



                Glide.with(ZgwApplication.getContext()).load(url).into(mImageView);


        }
        public void setNoPic(int viewID){
            ImageView mImageView = get(viewID);

        }


    }
}
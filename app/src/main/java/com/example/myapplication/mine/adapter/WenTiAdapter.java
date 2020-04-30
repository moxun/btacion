package com.example.myapplication.mine.adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.bean.WenTiBean;


import java.util.List;

public class WenTiAdapter extends RecyclerView.Adapter {

    private List<WenTiBean.DataBean.ListBean> mList;

    public WenTiAdapter(List<WenTiBean.DataBean.ListBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wenti, null);
        return new Holder(inflate);
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Holder vo = (Holder) holder;
        vo.mText_title.setText(mList.get(position).getTitle());
        vo.mText_contentText.setText(mList.get(position).getContentText());

        vo.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int visibility = vo.mText_contentText.getVisibility();
                if(visibility==8){//隐藏状态
                    vo.mText_contentText.setVisibility(View.VISIBLE);
                    vo.mImage.setImageResource(R.drawable.icon_xiangxia);
                }else if(visibility==0){//显示状态
                    vo.mText_contentText.setVisibility(View.GONE);
                    vo.mImage.setImageResource(R.drawable.icon_xiangshang);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final TextView mText_title;
        private final ImageView mImage;
        private final TextView mText_contentText;

        public Holder(@NonNull View itemView) {
            super(itemView);
            mText_title = itemView.findViewById(R.id.text_title);
            mImage = itemView.findViewById(R.id.image);
            mText_contentText = itemView.findViewById(R.id.text_contentText);
        }
    }
}

package com.bourne.fragment_viewpaper_demo.viewpaper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bourne.fragment_viewpaper_demo.R;

import java.util.List;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.NormalViewHolder> {

    private Context mContext;
    private List<String> mListData;
    private LayoutInflater mInflater;

    public interface OnViewClickListener {
        void OnItemClick(View view, int pos);

        void onLongItemClick(View view, int pos);
    }

    private OnViewClickListener mOnViewClickListener;

    public void setOnViewClickListener(OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }

    public Adapter1(Context context, List<String> listData) {
        this.mListData = listData;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public Adapter1.NormalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        NormalViewHolder holder = new NormalViewHolder(mInflater.inflate(
                R.layout.item_fragment1_list, parent, false));
        return holder;

    }

    @Override
    public void onBindViewHolder(final Adapter1.NormalViewHolder holder, final int position) {
        holder.setDataAndRereshUI(mListData.get(position), position);

        if (mOnViewClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnViewClickListener.OnItemClick(view, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnViewClickListener.onLongItemClick(view, pos);
                    return true;
                }
            });
        }
    }

    public void setDataAndRefreshUI(List<String> listData) {
        this.mListData = listData;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;


        public NormalViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);

        }

        private void setDataAndRereshUI(String bean, int pos) {

            tv_name.setText("节点 " + bean);

        }
    }
}
package com.bourne.fragment_viewpaper_demo.viewpaper;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bourne.fragment_viewpaper_demo.R;
import com.bourne.fragment_viewpaper_demo.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Fragment1 extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    RecyclerView rv_list;

    private Adapter1 mGuessAdapter;
    private List<String> mList = new ArrayList<String>();

    private SwipeRefreshLayout mSwipeLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        for (int i = 0; i < 20; i++) {
            mList.add(i + "");
        }

        View view = inflater.inflate(R.layout.fragment1, container, false);
        rv_list = (RecyclerView) view.findViewById(R.id.rv_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_list.setLayoutManager(layoutManager);

        mGuessAdapter = new Adapter1(getActivity(), mList);
        rv_list.setAdapter(mGuessAdapter);
        //设置item间距，30dp
        rv_list.addItemDecoration(new SpaceItemDecoration(30));
        mGuessAdapter.setOnViewClickListener(mViewClickListener);

        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_ly);

        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

        return view;
    }


    Adapter1.OnViewClickListener mViewClickListener = new Adapter1.OnViewClickListener() {
        @Override
        public void OnItemClick(View view, int pos) {
            Toast.makeText(getActivity(), "" + pos, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLongItemClick(View view, int pos) {


            new AlertDialog.Builder(getActivity())
                    .setTitle(null)
                    .setCancelable(true)
                    .setMessage("确定删除？")
                    .setNegativeButton(
                            "取消",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(
                                        DialogInterface dialog,
                                        int which) {

                                }
                            }).
                    setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).
                    show();
        }
    };


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "刷新完毕", Toast.LENGTH_LONG).show();
                mSwipeLayout.setRefreshing(false);
            }
        }, 2000);

    }


}

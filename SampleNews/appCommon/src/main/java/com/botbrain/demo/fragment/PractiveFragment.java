package com.botbrain.demo.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.botbrain.demo.R;
import com.botbrain.demo.activity.AdSettingActivity;
import com.botbrain.demo.activity.BasicActivity;
import com.botbrain.demo.adapter.BaseRecyclerAdapter;
import com.botbrain.demo.adapter.SmartViewHolder;

import java.util.Arrays;

import ai.botbrain.ttcloud.api.BotBrain;
import ai.botbrain.ttcloud.sdk.view.activity.ReadNewsActivity;
import ai.botbrain.ttcloud.sdk.view.activity.SearchNewsActivity;

import static android.R.layout.simple_list_item_2;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Description：
 * Creator: Created by peter.
 * Date: 2017/10/20.
 */

public class PractiveFragment extends Fragment implements AdapterView.OnItemClickListener {

    private enum Item {
        //Basic("基础集成(日/夜间模式切换)", BasicActivity.class),
        Login("登录和注销操作", BasicActivity.class),
        AdSettings("向feed流中添加广告", AdSettingActivity.class),
        OpenReadNews("打开新闻阅读页面", ReadNewsActivity.class),
        OpenSearchNews("打开新闻搜索页面", SearchNewsActivity.class),;

        public String name;
        public Class<?> clazz;

        Item(String name, Class<?> clazz) {
            this.name = name;
            this.clazz = clazz;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_practive, container, false);
    }

    @Override
    public void onViewCreated(View root, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(root, savedInstanceState);
        //StatusBarUtil.setPaddingSmart(getContext(), root.findViewById(R.id.toolbar));

        View view = root.findViewById(R.id.recyclerView);
        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
            recyclerView.setAdapter(new BaseRecyclerAdapter<Item>(Arrays.asList(Item.values()), simple_list_item_2, this) {
                @Override
                protected void onBindViewHolder(SmartViewHolder holder, Item model, int position) {
                    holder.text(android.R.id.text1, model.name());
                    holder.text(android.R.id.text2, model.name);
                    holder.textColorId(android.R.id.text2, R.color.colorTextAssistant);
                }
            });
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if ((Item.values()[position].clazz) == ReadNewsActivity.class) {
            /**打开阅读页**/
            BotBrain.newInstance().openReadNews(getActivity(), "AODgzMTExNDM3Njg");
            return;
        }
        if ((Item.values()[position].name().equals("Login"))) {
            AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
            builder.setItems(new String[]{"登录操作", "注销操作", "判断登录状态"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == 0) {
                        BotBrain.newInstance().login("botbrain001", "布本智能", "defaut", 10);
                    } else if (which == 1) {
                        BotBrain.newInstance().logout();
                    } else if (which == 2) {
                        Snackbar.make(getView(), "登录状态" + BotBrain.newInstance().isLogin(), Snackbar.LENGTH_LONG).show();
                    }
                }
            });
            builder.show();
            return;
        }
        startActivity(new Intent(getContext(), Item.values()[position].clazz));
    }
}

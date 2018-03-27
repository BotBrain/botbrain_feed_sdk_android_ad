package com.botbrain.demo.listener;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.botbrain.demo.R;

import ai.botbrain.ttcloud.api.ReadNewsActivityListener;
import ai.botbrain.ttcloud.api.ReadNewsView;

/**
 * Description：
 * Creator: Created by peter.
 * Date: 2017/10/27.
 */

public class MyReadNewsActivityListener implements ReadNewsActivityListener {

    private static final String TAG = MyReadNewsActivityListener.class.getSimpleName();

    private Activity mActivity;

    String[] str = new String[]{
            "设置导航栏背景颜色",
            "设置导航栏返回图标",
            "设置导航栏右侧菜单",
            "设置导航栏标题颜色",
            "隐藏底部评论框",
            "设置分享icon",
            "设置点赞的icon",
            "隐藏点赞",
    };

    @Override
    public void getReadNewsView(Activity activity, ReadNewsView view) {
        mActivity = activity;
    }

    @Override
    public void onClickShare(String json) {
        Log.i(TAG, json);

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("回调信息(onClickShare)");
        builder.setMessage(json);
        builder.show();
    }

    @Override
    public void onClickLike(String json) {
        Log.i(TAG, json);

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("回调信息(onClickLike)");
        builder.setMessage(json);
        builder.show();
    }

    @Override
    public void onComment(String json) {
        Log.i(TAG, json);
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("回调信息(onComment)");
        builder.setMessage(json);
        builder.show();
    }

    @Override
    public void onClickMore(String json) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("回调信息(onClickMore)");
        builder.setMessage(json);
        builder.show();
    }

    /********************************* 生命周期回调 ************************************/
    @Override
    public void onCreate(Activity activity) {
        Log.i(TAG, "onCreate()");
    }

    @Override
    public void onStart(Activity activity) {
        Log.i(TAG, "onStart()");
    }

    @Override
    public void onResume(Activity activity) {
        Log.i(TAG, "onResume()");
    }

    @Override
    public void onPause(Activity activity) {
        Log.i(TAG, "onPause()");
    }

    @Override
    public void onStop(Activity activity) {
        Log.i(TAG, "onStop()");
    }

    @Override
    public void onDestroy(Activity activity) {
        Log.i(TAG, "onDestroy()");
    }

    @Override
    public void onMultipleClick(final Activity activity, final ReadNewsView view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setItems(str, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        view.setToolBarBackgroundColor(activity.getResources().getColor(R.color.color_e19797));
                        break;
                    // 设置导航栏返回图标
                    case 1:
                        view.setToolBarNavigationIcon(R.drawable.ic_time_to_leave_black_24dp);
                        break;
                    //设置导航栏右侧菜单
                    case 2:
                        view.setToolBarInflateMenu(R.menu.menu_tip);
                        break;
                    //设置导航栏标题颜色
                    case 3:
                        view.setToolBarTitleColor(activity.getResources().getColor(R.color.colorPrimary));
                        break;
                    case 4:
                        //隐藏底部评论框
                        view.hideComment();
                        break;
                    //设置分享ICON
                    case 5:
                        view.setShareImageRes(R.drawable.ic_share_black_24dp);
                        break;
                    // 设置点赞的icon
                    case 6:
                        view.setLikeImageRes(R.drawable.ic_thumb_up_black_24dp, R.drawable.ic_thumb_up_red_24dp);
                        break;
                    // 隐藏点赞
                    case 7:
                        view.hideLikeView();
                }
            }
        });
        builder.show();
    }

}

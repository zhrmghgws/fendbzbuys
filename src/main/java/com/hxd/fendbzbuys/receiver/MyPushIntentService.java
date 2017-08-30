package com.hxd.fendbzbuys.receiver;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;

import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageService;
import com.umeng.message.common.UmLog;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.entity.UMessage;
import com.umeng.message.tag.TagManager;

import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;

/**
 * Created by lichao on 17/8/30.
 */

public class MyPushIntentService extends UmengMessageService {
    private static final String TAG = MyPushIntentService.class.getName();

    @Override
    public void onMessage(Context context, Intent intent) {
        try {
            //可以通过MESSAGE_BODY取得消息体
            String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            UMessage msg = new UMessage(new JSONObject(message));
            UmLog.e(TAG, "message=" + message);      //消息体
            UmLog.e(TAG, "custom=" + msg.custom);    //自定义消息的内容
            UmLog.e(TAG, "title=" + msg.title);      //通知标题
            UmLog.e(TAG, "text=" + msg.text);        //通知内容
            // code  to handle message here
            // ...

            // 对完全自定义消息的处理方式，点击或者忽略
           /* boolean isClickOrDismissed = true;
            if (isClickOrDismissed) {
                //完全自定义消息的点击统计
                UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
            } else {
                //完全自定义消息的忽略统计
                UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
            }*/

            //完全自定义消息的忽略统计
            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);


            // 使用完全自定义消息来开启应用服务进程的示例代码
            // 首先需要设置完全自定义消息处理方式
            // mPushAgent.setPushIntentServiceClass(MyPushIntentService.class);
            // code to handle to start/stop service for app process
            //发送用户为vip的推送内容 {"action":"vip"}
            JSONObject json = new JSONObject(msg.custom);
            String action = json.getString("action");
            UmLog.e(TAG, "action=" + action);
            if("vip".equals(action)){
                UmLog.e(TAG, ":::::::用户是vip=");
                //添加vip tag
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PushAgent.getInstance(MyPushIntentService.this).getTagManager().update(new TagManager.TCallBack() {
                            @Override
                            public void onMessage(final boolean isSuccess, final ITagManager.Result result) {
                                UmLog.e(TAG, ":::::::将用户更改为vip=");
                            }
                        }, "vip");
                    }
                }).start();
            }
            //发推送开启服务,不需要.
           /* if (topic != null && topic.equals("appName:startService")) {
                // 在友盟+portal上新建自定义消息，自定义消息文本如下
                //{"topic":"appName:startService"}
                if (Helper.isServiceRunning(context, NotificationService.class.getName()))
                    return;
                Intent intent1 = new Intent();
                intent1.setClass(context, NotificationService.class);
                context.startService(intent1);
            } else if (topic != null && topic.equals("appName:stopService")) {
                // 在友盟+portal上新建自定义消息，自定义消息文本如下
                //{"topic":"appName:stopService"}
                if (Helper.isServiceRunning(context,NotificationService.class.getName()))
                    return;
                Intent intent1 = new Intent();
                intent1.setClass(context, NotificationService.class);
                context.stopService(intent1);
            }*/
        } catch (Exception e) {
            UmLog.e(TAG, e.getMessage());
        }
    }
}

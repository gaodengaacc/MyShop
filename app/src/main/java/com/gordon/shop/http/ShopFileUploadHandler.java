package com.gordon.shop.http;

import android.os.Message;

import com.android.volley.VolleyError;

/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public abstract class ShopFileUploadHandler extends ShopFileUploadBaseHandler {

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        String resultKey = msg.getData().getString("result-key");
        switch (msg.what) {
            case START:
                onStart();
                break;
            case FINISH:
                onSuccess((ShopAPIResult) ShopAPIResultQueue.getResult(resultKey));
                break;
            case ERROR:
                VolleyError error = (VolleyError) ShopAPIResultQueue.getResult(resultKey);
                onError(error);
                // 显示错误信息
//                ToastUtil.showTips(AppApplication.getInstance(), ToastUtil.TOAST_FELIA, VolleyErrorHelper.getMessage(AppApplication.getInstance(), error));
                break;
            case PROGRESS:
                onProgress(msg.getData().getLong("transferedBytes"), msg.getData().getLong("totalBytes"));
                break;
            default:
                break;
        }
    }

}

package com.gordon.shop.http;

import android.os.Message;

import com.android.volley.VolleyError;
import com.gordon.shop.ShopApplication;
import com.gordon.shop.utils.ToastUtil;

/**
 * @author Gordon
 * @since 2016/3/18
 * do(相应处理)
 */
public abstract class ShopAPIResponseHandler extends ShopAPIResponseBaseHandler<ShopAPIResult> {


    public ShopAPIResponseHandler() {
        super();
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        try {
            String resultKey = msg.getData().getString("result-key");
            switch (msg.what) {
                case 0:
                    ShopAPIResult result = (ShopAPIResult) ShopAPIResultQueue.getResult(resultKey);
                    onSuccess(result);
                    // 登录超时
                    if (result.getStatus() == ShopAPIResultStatus.INVALIDATE_TOKEN) {
                        // 将登录记录设置为false
                        // AppApplication.getInstance().setLogined(false);
                        // AppApplication.getInstance().showLogin();
                        LoginTimeOutHandler.getInstance().onTimeOut();
                    }
                    break;
                case 1:
                    VolleyError error = (VolleyError) ShopAPIResultQueue.getResult(resultKey);
                    onError(error);
                    // 显示错误信息
                    ToastUtil.showTips(ShopApplication.getInstance(), ToastUtil.TOAST_FELIA, VolleyErrorHelper.getMessage(ShopApplication.getInstance(), error));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void onSuccess(ShopAPIResult result);

    protected abstract void onError(VolleyError error);

}

package com.gordon.shop.http;


import com.gordon.shop.ShopApplication;

/**
 * @author Gordon
 * @since 2016/3/18
 * do()
 */
public class LoginTimeOutHandler {

    private static LoginTimeOutHandler instance;

    private static ShopApplication application;

    private boolean inProcessing = false;

    public static LoginTimeOutHandler getInstance(){
        return instance;
    }

    public static void init(ShopApplication application){
        instance = new LoginTimeOutHandler();
        instance.application = application;
    }

    public void onTimeOut(){
        if(inProcessing){
            return;
        }
        inProcessing = true;
        application.onLoginTimeOut();
    }

    public boolean isInProcessing() {
        return inProcessing;
    }

    public void setInProcessing(boolean inProcessing) {
        this.inProcessing = inProcessing;
    }
}

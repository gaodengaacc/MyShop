package com.gordon.shop.http;

/**
 * @author Gordon
 * @since 2016/3/18
 * do(网络状态标识)
 */
public class ShopAPIResultStatus {

    /** 0 成功（登陆成功，有相关数据等） **/
    public static final int SUCCESS = 0;
    /** 1 失败（可能网络问题，暂时无相关数据) **/
    public static final int FAILD = 1;
    /** 2 服务器异常 **/
    public static final int SERVER_EXCEPTION_ERROR = 2;
    /** 3 参数错误 **/
    public static final int PARAMS_ERROR = 3;
    /** 4 请重新登录 **/
    public static final int LOGIN_TIMEOUT = 4;
    /** 5 密码错误 **/
    public static final int PASSWORD_ERROR = 5;
    /** 6 用户不存在 **/
    public static final int USER_NO_EXITS = 6;
    /** 7 用户已存在 **/
    public static final int USER_EXITS = 7;
    /** 8 新旧密码相同 **/
    public static final int SAME_OLD_PASSWORD_ERROR = 8;
    /** 9 验证码错误 **/
    public static final int SMSCODE_ERROR = 9;
    /** 10 用户名或密码不能为空 **/
    public static final int USERNAME_OR_PASSWORD_CANT_BE_NULL = 10;
    /** 11 查询结果为空 **/
    public static final int RESULT_IS_NULL = 0;
    /** 12 记录已存在 **/
    public static final int RECORD_EXIST = 12;
    /** 13 appId不能为空 **/
    public static final int APPID_CANT_BE_NULL = 13;
    /** 14 appId非法 **/
    public static final int APPID_ILLEGAL = 14;
    /** 15 图片文件不存在 **/
    public static final int PIC_NOT_EXIST = 15;
    /** 16 openId不能为空 **/
    public static final int OPENID_CANT_BE_NULL = 16;
    /** 17 用户未认证 **/
    public static final int USER_NOT_AUTH = 17;
    /** 18 请通过用户端登录 **/
    public static final int SHOULD_LOGIN_FROM_USER = 18;
    /** 19 请通过律师端登录 **/
    public static final int SHOULD_LOGIN_FROM_LAWYER = 19;
    /** 20 tokey失效,请重新登录 **/
    public static final int INVALIDATE_TOKEN = 20;
    /** 20 余额不足 **/
    public static final int ACCOUNT_BALANCE_NOT_ENOUGH = 29;


}

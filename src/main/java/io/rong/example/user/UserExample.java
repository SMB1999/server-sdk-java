package io.rong.example.user;

import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.*;
import io.rong.models.response.*;
import io.rong.models.user.ExpireModel;
import io.rong.models.user.UserModel;

/**
 * Demo class
 *
 * @author RongCloud
 */
public class UserExample {

    /**
     * 此处替换成您的appKey
     */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     */
    private static final String appSecret = "appSecret";
    /**
     * 自定义api地址
     */
    private static final String api = "http://api-cn.ronghub.com";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        // 自定义 api 地址方式
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
        // 使用 百度 HTTPDNS 获取最快的 IP 地址进行连接
        // BaiduHttpDNSUtil.setHostTypeIp("account_id", "secret", rongCloud.getApiHostType());

        // 设置连接超时时间
        // rongCloud.getApiHostType().setConnectTimeout(10000);
        // 设置读取超时时间
        // rongCloud.getApiHostType().setReadTimeout(10000);
        // 获取备用域名List
        // List<HostType> hosttypes = rongCloud.getApiHostListBackUp();
        // 设置连接、读取超时时间
        // for (HostType hosttype : hosttypes) {
        //     hosttype.setConnectTimeout(10000);
        //     hosttype.setReadTimeout(10000);
        // }

        User User = rongCloud.user;

        /**
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/user/user.html#register
         *
         * 注册用户，生成用户在融云的唯一身份标识 Token
         */
        UserModel user = new UserModel()
            .setId("userxxd2")
            .setName("username")
            .setPortrait("http://www.rongcloud.cn/images/logo.png");
        TokenResult result = User.register(user);
        System.out.println("getToken:  " + result.toString());

        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/user/user.html#refresh
         *
         * 刷新用户信息方法
         */
        Result refreshResult = User.update(user);
        System.out.println("refresh:  " + refreshResult.toString());

        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/user/user.html#get
         *
         * 查询用户信息方法
         */
        UserResult userResult = User.get(user);
        System.out.println("getUserInfo:  " + userResult.toString());

        /**
         * 用户注销
         */
        ResponseResult abandon = User.abandon(user);
        System.out.println("user abandon:  " + abandon.toString());

        /**
         * 注销用户列表
         */
        UserAbandonResult abandonList = User.abandonList(1, 20);
        System.out.println("user abandon list:  " + abandonList.toString());

        /**
         * 用户激活
         */
        ResponseResult active = User.activate(user);
        System.out.println("user activate set:  " + active.toString());

        /**
         *
         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/user/user.html#get
         *
         * 查询用户所在群组
         */
        UserGroupQueryResult userGroupResult = User.getGroups(user);
        System.out.println("getGroups:  " + userGroupResult.toString());


        ExpireModel expireModel = new ExpireModel()
                .setUserId(new String[]{"CHIQ1", "CHIQ2"})
                .setTime(1623123911000L);
        /**
         *
         * API 文档: https://docs.rongcloud.cn/v4/5X/views/im/server/user/expire.html
         *
         * Token 失效
         */
        refreshResult = User.expire(expireModel);
        System.out.println("expire:  " + refreshResult.toString());


    }
}

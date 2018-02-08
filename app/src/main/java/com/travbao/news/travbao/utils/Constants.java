package com.travbao.news.travbao.utils;

/**
 * Created by lijinbo on 18/1/1.
 */

public class Constants {

    public static final String Title_URL = "goabraod/security/getNewsByType.do";
    public static final String Title_VIDEO = "视频";
    public static final String Title_City = "财经";

    // 1 登录
//    http://qhb.2dyt.com/MyInterface/userAction_login.action
//      必须
//    user.phone , user.password ,  user.sign , user.secretkey 可选 user.lat , user.lng

//    2 注册
// http://qhb.2dyt.com/MyInterface/userAction_add.action
//
//    必须
//      user.phone , user.password ,  user.nickname, user.gender,
//    user.area   user.age , user.introduce ,user.sign , user.secretkey
//  可选 user.lat , user.lng

//    3 上传形象照 必须登录
//    http://qhb.2dyt.com/MyInterface/userAction_uploadImage.action
//    必须
//      user.file  user.currenttimer  user.sign user.picWidth , user.picHeight

//    4 将照片上传到相册中去 必须登录
//    http://qhb.2dyt.com/MyInterface/userAction_uploadPhotoAlbum.action
    //    必须
//        user.file  user.currenttimer  user.sign , user.picWidth , user.picHeight
//
//    5 查询用户基本信息
//   http://qhb.2dyt.com/MyInterface/userAction_selectUserById.action
    //    必须
//   user.userId,user.sign
//    6 获取用户列表
//      http://qhb.2dyt.com/MyInterface/userAction_selectAllUser.action
//     user.sign , user.currenttimer
//
//
//    7 添加好友 必须登录
//      http://qhb.2dyt.com/MyInterface/userAction_addFriends.action
//     user.sign , relationship.friendId
//    8  好友列表 必须登录
//      http://qhb.2dyt.com/MyInterface/userAction_selectAllUserAndFriend.action
//     user.sign , user.currenttimer


}

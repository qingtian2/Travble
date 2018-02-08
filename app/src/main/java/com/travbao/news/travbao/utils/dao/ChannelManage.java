package com.travbao.news.travbao.utils.dao;

import android.database.SQLException;

import com.travbao.news.travbao.bean.NewsBean;
import com.travbao.news.travbao.bean.TitleBean;

import java.util.List;

/**
 * 频道管理
 */
public class ChannelManage {
	public static ChannelManage channelManage;
	/**
	 * 默认的用户选择频道列表
	 * */
	public static List<TitleBean> defaultUserChannels;
	/**
	 * 默认的其他频道列表
	 * */
	public static List<TitleBean> defaultOtherChannels;
	private RequestDao channelDao;
	/** 判断数据库中是否存在用户数据 */
	private boolean userExist = false;

	private ChannelManage(OpenDb paramDBHelper) throws SQLException {
		if (channelDao == null){
			channelDao = new RequestDao(paramDBHelper.getContext());}
		defaultOtherChannels = channelDao.queryInSe(1);
		defaultOtherChannels =channelDao.queryInSe(0);
		return;
	}

	public static ChannelManage getManage(OpenDb dbHelper)throws SQLException {
		if (channelManage == null)
			channelManage = new ChannelManage(dbHelper);
		return channelManage;
	}

	/**
	 * 清除所有的频道
	 */
	public void deleteAllChannel() {
		channelDao.clearFeedTable();
	}
	/**
	 * 获取其他的频道
	 * @return 数据库存在用户配置 ? 数据库内的用户选择频道 : 默认用户选择频道 ;
	 */
	public List<TitleBean> getUserChannel() {
		List<TitleBean> dataBeans = channelDao.queryInSe(1);
		return dataBeans;
	}

	/**
	 * 获取其他的频道
	 * @return 数据库存在用户配置 ? 数据库内的其它频道 : 默认其它频道 ;
	 */
	public List<TitleBean> getOtherChannel() {
		List<TitleBean> cacheList = channelDao.queryInSe(0);
		return cacheList;

	}

	/**
	 * 保存用户频道到数据库
	 * @param userList
	 */
	public void saveUserChannel(List<TitleBean> userList) {

		for (int i = 0; i < userList.size(); i++) {
			TitleBean channelItem =userList.get(i);
			channelItem.setOrderId(i);
			channelItem.setSelected(Integer.valueOf(1));
			channelDao.add(channelItem.getNewstype(),channelItem.getOrderId(),channelItem.getSelected());
		}
	}

	/**
	 * 保存其他频道到数据库
	 * @param otherList
	 */
	public void saveOtherChannel(List<TitleBean> otherList) {
		for (int i = 0; i < otherList.size(); i++) {
			TitleBean channelItem = otherList.get(i);
			channelItem.setOrderId(i);
			channelItem.setSelected(Integer.valueOf(0));
			channelDao.add(channelItem.getNewstype(),channelItem.getOrderId(),channelItem.getSelected());
		}
	}

}

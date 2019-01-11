package com.hisi.service;

import java.util.Date;
import java.util.List;

import com.hisi.model.HisiCarryPerson;
import com.hisi.model.HisiOrderinfoBasic;
import com.hisi.model.HisiOrderinfoOther;
import com.hisi.model.HisiOrderinfoPhoto;
import com.hisi.model.HisiScreenshotPic;
import com.hisi.model.HisiUnpack;
import com.hisi.model.OrderVo;

public interface OrderService {

	int savePath(String orderId, String returnPath);

	int addOrder(HisiOrderinfoBasic order);

	int  deleteUser(String orderId);
	List<OrderVo> getOrder(String channelId);

	List<HisiOrderinfoPhoto> getPhoto(String orderId);

	int editOrder(Integer id, String orderId,
			String flightId,int num,
			String carryPerson,Date date);


	int deleteOther(String orderId);

	List<HisiOrderinfoOther> findDetail(String orderId);

	int updateStartTime(String orderId, Date date, String channelId);

	int addOther(String orderId, Date date,String channelId);

	int updateEndTime(String orderId, Date date,String userName);

	int updateOtherEndTime(String orderId, Date date,String userName);

	HisiOrderinfoBasic findByOrderId(String orderId);

	int updatePauseTime(String orderId, Date date);

	int updateOtherPauseTime(String orderId, Date date);

	int updateStatus(String orderId);

	List<HisiOrderinfoBasic> selectAll();

	int updateOtherStatus(String orderId1);

	int updateOtherStatus1(String orderId1);

	List<HisiOrderinfoBasic> selectAllByPage(int pageNum, int pageSize);

	List<OrderVo> findOrder_site(
			String createTime, String qiantian, String[] flightIds,String conditions,String channelId);

	List<HisiOrderinfoBasic> findOrder_office(int pageNum, int pageSize,
			String createTime, String channelId,
			String conditions);

	List<HisiUnpack> findOpenDetail(String orderId);

	List<OrderVo> getAllOrder();

	int updateChannelId(String orderId, String channelId);

	List<String> getFlightId();

	int updateStatusToFive(String orderId1);

	int updateStatusToThree(String orderId1);

	OrderVo getCheckingOrder(String channelId);

	List<OrderVo> getUncheckingorder();

	List<OrderVo> getOrder_site(String channelId);

	List<HisiCarryPerson> findCarryPersonBycondition(String condition);

	HisiOrderinfoOther findCheckingOrder(String channelId);

	List<String> getCheckingChannelId();

	List<String> getUnCheckingChannelId();

	HisiOrderinfoBasic getOrderInfo(String channelId);

	List<HisiOrderinfoPhoto> getOrderinfoPhotoByChannelId(String channelId);

	List<Integer> getOldIds(String orderId);

	int deleteUnUsePath(Integer id);

	int deletePath(String orderId);

	HisiOrderinfoBasic findDetailByOrderId(String orderId);

	int deleteUserByOrderId(String orderId);





}

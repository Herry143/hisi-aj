package com.hisi.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiOrderinfoBasic;
import com.hisi.model.OrderVo;
import com.hisi.model.vo.ComprehensiveOrderVo;

public interface HisiOrderinfoBasicMapper extends MyMapper<HisiOrderinfoBasic> {
	int editOrder(@Param(value="id")int id, @Param(value="orderId")String orderId,
			@Param(value="flightId")String flightId, @Param(value="num")int num,
			@Param(value="carryPerson")String carryPerson
			,@Param(value="date")Date date);

	List<HisiOrderinfoBasic> findByCreateTime(@Param(value="date")String date,@Param(value="channelId")String channelId);

	List<HisiOrderinfoBasic> findByChannelId(String channelId);

	List<HisiOrderinfoBasic> findByFlightId(@Param(value="flightId")String flightId);

	List<HisiOrderinfoBasic> findByConditions(@Param(value="conditions")String conditions,@Param(value="channelId")String channelId);

	
	int updateStartTime(@Param(value="orderId")String orderId, @Param(value="date")Date date,@Param(value="channelId")String channelId);

	int updateEndTime(@Param(value="orderId")String orderId, @Param(value="date")Date date,@Param(value="userName")String userName);

	HisiOrderinfoBasic findByOrderId(String orderId);

	int updatePauseTime(@Param(value="orderId")String orderId, @Param(value="date")Date date);

	List<OrderVo> selectByStatus(String channelId);

	int updateStatus(String orderId);

	List<HisiOrderinfoBasic> findByDate(String channelId);

	List<HisiOrderinfoBasic> findByCreateTime_office(String date);

	List<HisiOrderinfoBasic> findByConditions_office(String conditions);

	int updateOtherStatus(String orderId1);

	int updateOtherStatus1(String orderId1);

	int deleteById(String orderId);

	List<OrderVo> findOrder_site(@Param(value="createTime")String createTime,
			@Param(value="qiantian")String qiantian,@Param(value="flightIds")String[] flightIds,
			@Param(value="conditions")String conditions,@Param(value="channelId")String channelId);

	List<HisiOrderinfoBasic> findOrder_office(@Param(value="createTime")String createTime,
			@Param(value="channelId")String channelId,
			@Param(value="conditions") String conditions);

	List<OrderVo> getAllOrder();

	int updateChannelId( @Param(value="orderId")String orderId,@Param(value="channelId")String channelId);

	List<String> getFlightId();

	int updateStatusToFive(String orderId1);

	int updateStatusToThree(String orderId1);

	OrderVo getCheckingOrder(String channelId);

	List<OrderVo> getUncheckingorder();

	List<HisiOrderinfoBasic> selectAllByPage();

	List<OrderVo> getOrder_site(String channelId);

	List<ComprehensiveOrderVo> getUnpackOrder();

	List<ComprehensiveOrderVo> getPackOrder();

	List<ComprehensiveOrderVo> getComprehensiveOrderByCondition(
			@Param(value="isUnpack")String isUnpack, @Param(value="createTime")String createTime, 
			@Param(value="proxyName") String proxyName,
			@Param(value="channelId")String channelId,
			@Param(value="condition")String condition);

	List<ComprehensiveOrderVo> getOrder();

	HisiOrderinfoBasic getOrderInfo(String channelId);

	String getUserName(String userId);

	int deleteUserByOrderId(String orderId);
}
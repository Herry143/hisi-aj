package com.hisi.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hisi.mapper.HisiCarryPersonMapper;
import com.hisi.mapper.HisiOrderinfoBasicMapper;
import com.hisi.mapper.HisiOrderinfoOtherMapper;
import com.hisi.mapper.HisiOrderinfoPhotoMapper;
import com.hisi.mapper.HisiScreenshotPicMapper;
import com.hisi.mapper.HisiUnpackMapper;
import com.hisi.model.HisiCarryPerson;
import com.hisi.model.HisiOrderinfoBasic;
import com.hisi.model.HisiOrderinfoOther;
import com.hisi.model.HisiOrderinfoPhoto;
import com.hisi.model.HisiUnpack;
import com.hisi.model.OrderVo;
import com.hisi.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private HisiOrderinfoPhotoMapper hisiOrderinfoPhotoMapper;
	@Autowired
	private HisiOrderinfoBasicMapper hisiOrderinfoBasicMapper;
	@Autowired
	private HisiOrderinfoOtherMapper hisiOrderinfoOtherMapper;
	@Autowired
	private HisiUnpackMapper hisiUnpackMapper;
	@Autowired
	private HisiCarryPersonMapper hisiCarryPersonMapper;
	@Autowired
	private HisiScreenshotPicMapper hisiScreenshotPicMapper;
	@Override
	public int savePath(String orderId, String returnPath) {
		HisiOrderinfoPhoto photo=new HisiOrderinfoPhoto();
		photo.setOrderId(orderId);
		photo.setPhotoPath(returnPath);
		return hisiOrderinfoPhotoMapper.insert(photo);
	}
	@Override
	public int addOrder(HisiOrderinfoBasic order) {
		return hisiOrderinfoBasicMapper.insert(order);
	}
	@Override
	public int deleteUser(String orderId) {
		return hisiOrderinfoBasicMapper.deleteById(orderId);
	}
	@Override
	public List<OrderVo> getOrder(
			String channelId) {
		List<OrderVo> re = hisiOrderinfoBasicMapper.selectByStatus(channelId);
		return re;
	}

	@Override
	public List<HisiOrderinfoPhoto> getPhoto(String orderId) {
		return hisiOrderinfoPhotoMapper.getPhoto(orderId) ;
	}
	@Override
	public int editOrder(Integer id,String orderId, String flightId,
			int num, String carryPerson,Date date) {
		return hisiOrderinfoBasicMapper.editOrder(id,orderId,flightId,num,carryPerson,date);
	}
	@Override
	public int deleteOther(String orderId) {
		return hisiOrderinfoOtherMapper.deleteOther(orderId);
	}
	@Override
	public List<HisiOrderinfoOther> findDetail(String orderId) {
		List<HisiOrderinfoOther> hisiOrderinfoOthers = hisiOrderinfoOtherMapper.selectByOrderId(orderId);
		return hisiOrderinfoOthers;
	}
	@Override
	public int updateStartTime(String orderId, Date date,String channelId) {
		return hisiOrderinfoBasicMapper.updateStartTime(orderId,date,channelId);
	}
	@Override
	public int addOther(String orderId, Date date,String channelId) {
		HisiOrderinfoOther hisiOrderinfoOther=new HisiOrderinfoOther();
		hisiOrderinfoOther.setOrderId(orderId);
		hisiOrderinfoOther.setStartTime(date);
		hisiOrderinfoOther.setChannelid(channelId);
		return hisiOrderinfoOtherMapper.insert(hisiOrderinfoOther);
	}
	@Override
	public int updateEndTime(String orderId, Date date,String userName) {
		return hisiOrderinfoBasicMapper.updateEndTime(orderId,date, userName);
	}
	@Override
	public int updateOtherEndTime(String orderId, Date date,String userName) {
		return hisiOrderinfoOtherMapper.updateOtherEndTime(orderId,date,userName);
	}
	@Override
	public HisiOrderinfoBasic findByOrderId(String orderId) {
		return hisiOrderinfoBasicMapper.findByOrderId(orderId);
	}
	@Override
	public int updatePauseTime(String orderId, Date date) {
		return hisiOrderinfoBasicMapper.updatePauseTime(orderId,date);
	}
	@Override
	public int updateOtherPauseTime (String orderId, Date date) {
		return hisiOrderinfoOtherMapper.updateOtherPauseTime(orderId,date);
	}
	@Override
	public int updateStatus(String orderId) {
		return hisiOrderinfoBasicMapper.updateStatus(orderId);
	}
	public List<HisiOrderinfoBasic> selectAll() {
		return hisiOrderinfoBasicMapper.selectAll();
	}
	@Override
	public int updateOtherStatus(String orderId1) {
		return hisiOrderinfoBasicMapper.updateOtherStatus(orderId1);
	}
	@Override
	public int updateOtherStatus1(String orderId1) {
		return hisiOrderinfoBasicMapper.updateOtherStatus1(orderId1);
	}
	@Override
	public List<HisiOrderinfoBasic> selectAllByPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return hisiOrderinfoBasicMapper.selectAllByPage();
	}
	@Override
	public List<OrderVo> findOrder_site(
			String createTime, String qiantian,String[] flightIds,String conditions,String channelId) {
		return hisiOrderinfoBasicMapper.findOrder_site(createTime,qiantian,flightIds,conditions,channelId);
	}
	@Override
	public List<HisiOrderinfoBasic> findOrder_office(int pageNum, int pageSize,
			String createTime, String channelId, 
			String conditions) {
		PageHelper.startPage(pageNum, pageSize);
		return hisiOrderinfoBasicMapper.findOrder_office(createTime,channelId,conditions);
	}
	@Override
	public List<HisiUnpack> findOpenDetail(String orderId) {
		List<HisiUnpack> hisiUnpacks= hisiUnpackMapper.findOpenDetail(orderId);
		return hisiUnpacks;
	}
	@Override
	public List<OrderVo> getAllOrder() {
		List<OrderVo> re = hisiOrderinfoBasicMapper.getAllOrder();
		return re;
	}
	@Override
	public int updateChannelId(String orderId, String channelId) {
		return hisiOrderinfoBasicMapper.updateChannelId(orderId,channelId);
	}
	@Override
	public List<String> getFlightId() {
		return hisiOrderinfoBasicMapper.getFlightId();
	}
	@Override
	public int updateStatusToFive(String orderId1) {
		return hisiOrderinfoBasicMapper.updateStatusToFive(orderId1);
	}
	@Override
	public int updateStatusToThree(String orderId1) {
		return hisiOrderinfoBasicMapper.updateStatusToThree(orderId1);
	}
	@Override
	public OrderVo getCheckingOrder(String channelId) {
		return hisiOrderinfoBasicMapper.getCheckingOrder(channelId);
	}
	@Override
	public List<OrderVo> getUncheckingorder() {
		return hisiOrderinfoBasicMapper.getUncheckingorder();
	}
	@Override
	public List<OrderVo> getOrder_site(String channelId) {
		return hisiOrderinfoBasicMapper.getOrder_site(channelId);
	}
	@Override
	public List<HisiCarryPerson> findCarryPersonBycondition(String condition) {
		return hisiCarryPersonMapper.findCarryPersonBycondition(condition);
	}
	@Override
	public HisiOrderinfoOther findCheckingOrder(String channelId) {
		return hisiOrderinfoOtherMapper.findCheckingOrder(channelId);
	}
	@Override
	public List<String> getCheckingChannelId() {
		return hisiOrderinfoOtherMapper.getCheckingChannelId();
	}
	@Override
	public List<String> getUnCheckingChannelId() {
		return hisiOrderinfoOtherMapper.getUnCheckingChannelId();	}
	@Override
	public HisiOrderinfoBasic getOrderInfo(String channelId) {
		return hisiOrderinfoBasicMapper.getOrderInfo(channelId);
	}
	@Override
	public List<HisiOrderinfoPhoto> getOrderinfoPhotoByChannelId(
			String channelId) {
		return hisiOrderinfoPhotoMapper.getOrderinfoPhotoByChannelId(channelId);
	}
	@Override
	public List<Integer> getOldIds(String orderId) {
		return hisiOrderinfoPhotoMapper.getOldIds(orderId);
	}
	@Override
	public int deleteUnUsePath(Integer id) {
		return hisiOrderinfoPhotoMapper.deleteUnUsePath(id);
	}
	@Override
	public int deletePath(String orderId) {
		return hisiOrderinfoPhotoMapper.deletePath(orderId);
	}
	@Override
	public HisiOrderinfoBasic findDetailByOrderId(String orderId) {
		return hisiOrderinfoBasicMapper.findByOrderId(orderId);
	}
	@Override
	public int deleteUserByOrderId(String orderId) {
		return hisiOrderinfoBasicMapper.deleteUserByOrderId(orderId);
	}
	

	
	
}

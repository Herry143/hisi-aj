package com.hisi.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hisi.model.HisiDevice;

public interface DeviceService {

	int insertDevice(HisiDevice hisiDevice);

	int deleteDevice(Integer id);

	List<HisiDevice> getAllDevice(Integer pageNum,Integer pageSize) throws Exception;

	int editDevice(HisiDevice hisiDevice);

	List<HisiDevice> findDeviceByStatus(String status);

	List<HisiDevice> findDeviceByChannelId(String channelId);

	List<HisiDevice> findDeviceByDate(String date);

	List<HisiDevice> findDeviceByRole(String role);

	List<HisiDevice> findDeviceByConditions(String conditions);

	String getChannelIdByIp(String ip);

	PageInfo<HisiDevice> findDevice(int pageNum, int pageSize, String date,
			String role, String channelId, String conditions, String status);

	int selectRoleIdByIp(String ip);

	String selectRoleNameByIp(String ip);

	List<HisiDevice> getCreamaDevice(String channelId);

	List<HisiDevice> findCreamaByChannelId(String channelId);

	String selectRoleTypeByIp(String ip);

}
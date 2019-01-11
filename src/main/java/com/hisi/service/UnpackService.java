package com.hisi.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hisi.model.HisiUnpackGoodsPic;
import com.hisi.model.HisiUnpackNecessaryPic;
import com.hisi.model.vo.HisiTrackInfo;
import com.hisi.model.vo.HisiUnpackInfo;
import com.hisi.model.vo.HisiUnpackReturnInfo;

public interface UnpackService {

	Integer insertUnpack(HisiUnpackInfo hisiUnpackInfo);

	Integer deleteUnpack(int id);

	PageInfo<HisiUnpackReturnInfo> getUnpack(int pageNum, int pageSize,
			String global);

	HisiTrackInfo getTracking(String trackingNumber);

	String getPicBase64(String url);

	List<String> getUnpackPhoto(String orderId,String startTime,String endTime);

	boolean deleteUnPackGoodsPic(HisiUnpackGoodsPic e);

	HisiUnpackGoodsPic insertUnPackGoodsPic(HisiUnpackGoodsPic e);

	List<HisiUnpackNecessaryPic> findNecessaryPic(String orderId, String channelId);

}
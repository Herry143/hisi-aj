package com.hisi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiChannel;

public interface HisiChannelMapper extends MyMapper<HisiChannel> {

	int deleteById(String channelId);

	int updateById(@Param(value="id")int id, @Param(value="channelId")String channelId, @Param(value="channelAddress")String channelAddress);

	List<HisiChannel> selectByStatus(int i);

	List<String> getALlChannelId();

	List<HisiChannel> findChannel(@Param("channelId")String channelId, @Param("channelAddress")String channelAddress);
}
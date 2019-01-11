package com.hisi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hisi.common.util.YfslResult;
import com.hisi.model.HisiChannel;
import com.hisi.service.ChannelService;

@RestController
@RequestMapping("/channel")
@Api(value = "HisiUser", description = "通道管理")
public class ChannelController {
	@Autowired
	private ChannelService channelService;
	@ApiOperation(value = "新增通道", httpMethod="POST",response=YfslResult.class,notes = "新增通道")
	@RequestMapping(value="/addChannel",method=RequestMethod.POST,headers ="Content-Type=application/json")
	public YfslResult addChannel(@RequestBody Map<String, String> map){
		String channelId = map.get("channelId");
		String channelAddress = map.get("channelAddress");
		List<HisiChannel> channels=channelService.findChannel(channelId,channelAddress);
		if(!channels.isEmpty()){
			return YfslResult.fail("通道号或ip重复");
		}
		HisiChannel hisiChannel=new HisiChannel();
		hisiChannel.setChannelId(channelId);
		hisiChannel.setChannelAddress(channelAddress);
		int i=channelService.insertChannel(hisiChannel);
		if(i>0){  
			return YfslResult.ok("新增成功");
		}
		return YfslResult.fail("新增失败");
	}
	@ApiOperation(value = "获取通道列表", httpMethod="GET",response=YfslResult.class,notes = "获取通道列表")
	@RequestMapping(value="/getAllChannel",method=RequestMethod.GET)
	public YfslResult getAllChannel(HttpServletRequest request,@RequestParam(required = true) @ApiParam("页数，首页为1") int pageNum,
			@RequestParam(required = true) @ApiParam("每页数据条数") int pageSize){
		List<HisiChannel> hisiChannels=channelService.getAllChannel(pageNum,pageSize);
		return YfslResult.ok(new PageInfo<HisiChannel>(hisiChannels));
	}
	@ApiOperation(value = "删除通道", httpMethod="DELETE",response=YfslResult.class,notes = "删除通道")
	@RequestMapping(value="/deleteChannel",method=RequestMethod.DELETE)
	public YfslResult deleteChannel(@ApiParam(name="channelId",value="通道号",required=true)String channelId){
		int i=channelService.deleteChannel(channelId);
		if(i>0){
			return YfslResult.ok("删除成功");
		}
		return YfslResult.fail("删除失败");
	}
	@ApiOperation(value = "编辑通道", httpMethod="POST",response=YfslResult.class,notes = "编辑通道")
	@RequestMapping(value="/updateChannel",method=RequestMethod.POST,headers ="Content-Type=application/json")
	public YfslResult updateChannel(@RequestBody Map<String, String> map){
		 
		String id=map.get("id");
		String channelId=map.get("channelId");
		String channelAddress=map.get("channelAddress");
		int id1 = Integer.parseInt(id);
		System.out.println(id);
		int i=channelService.updateChannel(id1,channelId,channelAddress);
		if(i>0){
			return YfslResult.ok("修改成功");
		}
		return YfslResult.fail("修改失败");
	}
	@ApiOperation(value = "得到所有通道号", httpMethod="GET",response=YfslResult.class,notes = "得到所有通道号")
	@RequestMapping(value="/getALlChannelId",method=RequestMethod.GET)
	public List<String> getALlChannelId(HttpServletRequest request){
		List<String> ChannelIds=channelService.getALlChannelId();
		return ChannelIds;
	}
}

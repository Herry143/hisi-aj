package com.hisi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hisi.common.util.ListSort;
import com.hisi.common.util.YfslResult;
import com.hisi.model.HisiDevice;
import com.hisi.model.HisiOrderinfoBasic;
import com.hisi.model.HisiOrderinfoOther;
import com.hisi.model.HisiUnpack;
import com.hisi.model.vo.CheckVo;
import com.hisi.model.vo.ComprehensiveOrderVo;
import com.hisi.model.vo.FileVo;
import com.hisi.service.ComprehensiveQueryService;
import com.hisi.service.DeviceService;
import com.hisi.service.OrderService;
import com.hisi.service.UnpackService;
import com.hisi.service.UnpackVerifyService;
import com.hisi.service.UserService;

@RestController
// 综合查询
@RequestMapping("/comprehensiveQuery")
@Api(description = "综合查询")
public class ComprehensiveQueryController {
	@Autowired
	private ComprehensiveQueryService comprehensiveQueryService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private UnpackVerifyService unpackVerifyService;
	@Autowired
	private UnpackService unpackService;
	@Autowired
	private UserService userService;
	@Value("${ftp.pic}")
	private String ftp;

	@ApiOperation(value = "获取代理人列表", httpMethod = "GET")
	@GetMapping(value = "/getProxyPerson")
	public List<String> getProxyPerson() {
		return userService.getProxyPerson();
	}

	@ApiOperation(value = "获取运单列表", httpMethod = "GET")
	@GetMapping(value = "/getOrder")
	public YfslResult getOrder(
			@ApiParam("页数，首页为1") @RequestParam(required = true) int pageNum,
			@ApiParam("每页数据条数") @RequestParam(required = true) int pageSize) {
		List<ComprehensiveOrderVo> comprehensiveOrderVos = comprehensiveQueryService
				.getOrder(pageNum, pageSize);
		ListSort listSort = new ListSort();
		listSort.ListSort1(comprehensiveOrderVos);// list排序
		// PageHelper.startPage(pageNum, pageSize);
		return YfslResult.ok(new PageInfo<ComprehensiveOrderVo>(
				comprehensiveOrderVos));
	}

	@ApiOperation(value = "查找运单", httpMethod = "GET")
	@GetMapping(value = "/getComprehensiveOrderByCondition")
	public YfslResult getComprehensiveOrderByCondition(
			@ApiParam("页数，首页为1") @RequestParam(required = true) int pageNum,
			@ApiParam("每页数据条数") @RequestParam(required = true) int pageSize,
			@ApiParam("是否开包") @RequestParam(required = false) String isUnpack,
			@ApiParam("创建日期") @RequestParam(required = false) String createTime,
			@ApiParam("代理人") @RequestParam(required = false) String proxyName,
			@ApiParam("通道号") @RequestParam(required = false) String channelId,
			@ApiParam("多条件") @RequestParam(required = false) String condition) {
		List<ComprehensiveOrderVo> comprehensiveOrderVos = comprehensiveQueryService
				.getComprehensiveOrderByCondition(isUnpack, createTime,
						proxyName, channelId, condition, pageNum, pageSize);
		return YfslResult.ok(new PageInfo<ComprehensiveOrderVo>(
				comprehensiveOrderVos));
	}

	@ApiOperation(value = "查看详情-图片", httpMethod = "GET")
	@GetMapping(value = "/getComprehensiveOrderPhoto")
	public List getComprehensiveOrderPhoto(
			@ApiParam("运单号") @RequestParam(required = true) String orderId,
			@ApiParam("页数，首页为1") @RequestParam(required = true) int pageNum,
			@ApiParam("每页数据条数") @RequestParam(required = true) int pageSize,
			@ApiParam("开始时间") @RequestParam(required = false) String startTime,
			@ApiParam("结束时间") @RequestParam(required = false) String endTime) {
		// 截屏卡的图片，即所有图片
		List<String> screenShotPhotoPathList = unpackVerifyService
				.getScreenShotPhoto(orderId, pageNum, pageSize, startTime,
						endTime);

		for (int i = 0; i < screenShotPhotoPathList.size(); i++) {
			String path = screenShotPhotoPathList.get(i);
			path = ftp + path;
			screenShotPhotoPathList.set(i, path);
		}
		// 标记过的图片
		List<String> unpackPhotoList = unpackService.getUnpackPhoto(orderId,
				startTime, endTime);
		for (int i = 0; i < unpackPhotoList.size(); i++) {
			String path = unpackPhotoList.get(i);
			path = ftp + path;
			unpackPhotoList.set(i, path);
		}
		// 实拍货物图片 后面再统一图片服务器地址 开包图片时间由前端处理
		List<String> goodsPhotoList = unpackVerifyService.getGoodsPhoto(
				orderId, startTime, endTime);
		for (int i = 0; i < goodsPhotoList.size(); i++) {
			String path = goodsPhotoList.get(i);
			path = ftp + path;
			goodsPhotoList.set(i, path);
		}
		for (int i = 0; i < goodsPhotoList.size(); i++) {
			unpackPhotoList.add(goodsPhotoList.get(i));
		}
		List list = new ArrayList();
		list.add(new PageInfo<String>(screenShotPhotoPathList));
		list.add(unpackPhotoList);
		return list;
	}

	@ApiOperation(value = "查看详情-进度条", httpMethod = "GET")
	@GetMapping(value = "/getComprehensiveOrderDetail")
	public List getComprehensiveOrderDetail(
			@ApiParam("运单号") @RequestParam(required = true) String orderId) {
		List<HisiOrderinfoOther> orderinfoOthers = orderService
				.findDetail(orderId);
		List<HisiUnpack> hisiUnpacks = orderService.findOpenDetail(orderId);
		HisiOrderinfoBasic order = orderService.findByOrderId(orderId);
		List<CheckVo> checkVos = new ArrayList<CheckVo>();
		for (int i = 0; i < orderinfoOthers.size(); i++) {
			HisiOrderinfoOther orderOther = orderinfoOthers.get(i);
			Date startTime = orderOther.getStartTime();
			Date pauseTime = orderOther.getPauseTime();
			Date endTime = orderOther.getEndTime();
			// 开始安检
			CheckVo checkVo = new CheckVo();
			CheckVo checkVo1 = new CheckVo();
			checkVo.setTime(startTime);
			checkVo.setInfo("第" + (i + 1) + "次开始安检");
			checkVo.setStatus("开始");
			checkVo.setPeople(null);
			// 暂停安检
			if (endTime == null) {
				checkVo1.setTime(pauseTime);
				checkVo1.setInfo("第" + (i + 1) + "次暂停安检");
				checkVo1.setStatus("暂停");
				checkVo1.setPeople(null);
			} else {// 结束安检
				checkVo1.setTime(endTime);
				checkVo1.setInfo("结束安检");
				checkVo1.setStatus("结束");
				checkVo1.setPeople(null);
			}
			checkVos.add(checkVo);
			checkVos.add(checkVo1);
		}
		for (int j = 0; j < hisiUnpacks.size(); j++) {
			CheckVo checkVo2 = new CheckVo();
			checkVo2.setTime(hisiUnpacks.get(j).getTime());
			checkVo2.setInfo("第" + (j + 1) + "次开包");
			checkVo2.setStatus("开包");
			checkVo2.setPeople(hisiUnpacks.get(j).getUnpackAccount());
			checkVos.add(checkVo2);
		}
		// 根据时间排序开始暂停开包等流程
		ListSort listSort = new ListSort();
		listSort.ListSort(checkVos);
		List list = new ArrayList();
		list.add(checkVos);
		List<HisiDevice> devices = deviceService.findCreamaByChannelId(order
				.getChannelId());
		list.add(devices);
		return list;
	}

	@ApiOperation(value = "下载图片", httpMethod = "POST")
	@GetMapping(value = "/downloadPhoto")
	/*,headers = "Content-Type=application/json"
	 * @ApiParam("要下载的图片服务器上的路径")@RequestParam(required = true) String[] paths,
	 * 
	 * @ApiParam("保存路径")@RequestParam(required = true)String savePath
	 */
	public YfslResult downloadPhoto(@RequestParam String[] paths,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String downloadFilename = "图片.zip";// 文件的名称
			downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");// 转换中文否则可能会产生乱码
			response.setContentType("application/octet-stream");// 指明response的返回对象是文件流
			response.setHeader("Content-Disposition", "attachment;filename="
					+ downloadFilename);// 设置在下载框默认显示的文件名
			ZipOutputStream zos = new ZipOutputStream(
					response.getOutputStream());
				for(int i=0;i<paths.length;i++){
				URL url=new URL(paths[i]);
				String[] strs=paths[i].split("/");
				String name=strs[strs.length-1];
				String[] name1=name.split(".JPG");
				zos.putNextEntry(new ZipEntry(name1[0]+ ".jpg"));
				InputStream fis = url.openConnection().getInputStream();
				byte[] buffer = new byte[1024];
				int r = 0;
				while ((r = fis.read(buffer)) != -1) {
					zos.write(buffer, 0, r);
				}
				fis.close();
			}
			zos.flush();
			zos.close();
			System.out.println("下载成功");
			return YfslResult.ok("下载成功");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return YfslResult.fail("下载失败");
		} catch (IOException e) {
			e.printStackTrace();
			return YfslResult.fail("下载失败");
		}
	}
	@ApiOperation(value = "查看详情", httpMethod = "GET")
	@GetMapping(value = "/findDetailByOrderId")
	public HisiOrderinfoBasic findDetailByOrderId(
			@ApiParam("运单号") @RequestParam(required = true) String orderId) {
		return orderService.findDetailByOrderId(orderId);
	}
}
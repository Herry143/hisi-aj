package com.hisi.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisi.arcsoft.AFD_FSDKLibrary;
import com.hisi.arcsoft.AFD_FSDK_COMMON;
import com.hisi.arcsoft.AFD_FSDK_USE;
import com.hisi.arcsoft.AFR_FSDKLibrary;
import com.hisi.arcsoft.AFR_FSDK_FACEMODEL;
import com.hisi.arcsoft.CLibrary;
import com.hisi.arcsoft.ConfigInfo;
import com.hisi.arcsoft._AFD_FSDK_OrientPriority;
import com.hisi.common.util.PasswordUtil;
import com.hisi.common.util.UserVo;
import com.hisi.mapper.HisiLoginRecordMapper;
import com.hisi.mapper.HisiUserMapper;
import com.hisi.model.HisiLoginRecord;
import com.hisi.model.HisiUser;
import com.hisi.service.LoginService;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.PointerByReference;
@Service
public class LoginServiceImpl implements LoginService {
	public static final int FD_WORKBUF_SIZE = 20 * 1024 * 1024;
	public static final int FR_WORKBUF_SIZE = 40 * 1024 * 1024;
	public static final int MAX_FACE_NUM = 50;
	public static final String APPID = "3eDu2UTVungPP9xX5LX2aoFeZLnGGyFBY4UN1LHzryKN";
	public static final String FD_SDKKEY = "ETcJoAnZkJHqjb5BxSwuqZYSjUAP9utgZKjbffNFA9TZ";
	public static final String FR_SDKKEY = "ETcJoAnZkJHqjb5BxSwuqZYpDfwtW2jg6VhyBq1tgH4T";
	@Autowired
	private HisiUserMapper hisiUserMapper;
	@Autowired
	private HisiLoginRecordMapper hisiLoginRecordMapper;
	@Autowired
	private ConfigInfo configInfo;
	@Autowired
	private PasswordUtil passwordUtil;
	
	@Override
	public UserVo compareImage(String imageUrl) throws Exception {
		UserVo user=new UserVo();
		if (AFD_FSDK_COMMON.sdk.isEmpty()) {
			String t1 = configInfo.getFdsdk();
			System.out.println(t1);
			String t2 = configInfo.getFrsdk();
			System.out.println(t2);
			AFD_FSDK_COMMON.sdk.put(0, t1);
			AFD_FSDK_COMMON.sdk.put(1, t2);
		}
		Pointer pFDWorkMem = CLibrary.INSTANCE.malloc(FD_WORKBUF_SIZE);
		Pointer pFRWorkMem = CLibrary.INSTANCE.malloc(FR_WORKBUF_SIZE);

		PointerByReference phFDEngine = new PointerByReference();
		NativeLong ret = AFD_FSDKLibrary.INSTANCE.AFD_FSDK_InitialFaceEngine(
				APPID, FD_SDKKEY, pFDWorkMem, FD_WORKBUF_SIZE, phFDEngine,
				_AFD_FSDK_OrientPriority.AFD_FSDK_OPF_0_HIGHER_EXT, 16,
				MAX_FACE_NUM);
		if (ret.intValue() != 0) {
			CLibrary.INSTANCE.free(pFDWorkMem);
			CLibrary.INSTANCE.free(pFRWorkMem);
			System.out.println("AFD_FSDK_InitialFaceEngine ret == " + ret);
			System.exit(0);
		}

		// print FDEngine version
		Pointer hFDEngine = phFDEngine.getValue();

		PointerByReference phFREngine = new PointerByReference();
		ret = AFR_FSDKLibrary.INSTANCE.AFR_FSDK_InitialEngine(APPID, FR_SDKKEY,
				pFRWorkMem, FR_WORKBUF_SIZE, phFREngine);
		if (ret.intValue() != 0) {
			AFD_FSDKLibrary.INSTANCE.AFD_FSDK_UninitialFaceEngine(hFDEngine);
			CLibrary.INSTANCE.free(pFDWorkMem);
			CLibrary.INSTANCE.free(pFRWorkMem);
			System.out.println("AFD_FSDK_InitialFaceEngine ret == " + ret);
			System.exit(0);
		}
		// print FREngine version
		Pointer hFREngine = phFREngine.getValue();
		// 登录的照片
		byte[] imageByteB = AFD_FSDK_USE.generateByte(imageUrl, APPID, FD_SDKKEY, FR_SDKKEY,configInfo);
		if(imageByteB==null){
			return user;
		}
		AFR_FSDK_FACEMODEL facefeatureB=AFR_FSDK_FACEMODEL.fromByteArray(imageByteB);
		FloatByReference fSimilScore=new FloatByReference();
		List<HisiUser> userList = hisiUserMapper.selectAllAirportUser();
		for(int i=0;i<userList.size();i++){
			byte[] iamgeByteA = userList.get(i).getIamge();
			if(iamgeByteA!=null){//没有录人脸底片的记录不处理
			AFR_FSDK_FACEMODEL facefeatureA=AFR_FSDK_FACEMODEL.fromByteArray(iamgeByteA);
			NativeLong ret1 = AFR_FSDKLibrary.INSTANCE.AFR_FSDK_FacePairMatching(
					hFREngine, facefeatureA, facefeatureB, fSimilScore);
			//facefeatureA.freeUnmanaged();
			//facefeatureB.freeUnmanaged();
			if (ret1.intValue() != 0) {
				System.out.println("AFR_FSDK_FacePairMatching failed:ret == " + ret1);
			}
			float score=fSimilScore.getValue();
			System.out.println(score);
			if(score>0.6){
				System.out.println("相似度为："+score);
				String userId = userList.get(i).getUserid();
				//String password=userList.get(i).getPassword();
				String password1 = userList.get(i).getPassword1();
				user.setUserId(userId);
				user.setPassword(password1);
				user.setScore(score);
				return user;
			}
			}
		}
		return user;
	}
	@Override
	public boolean acountLogin(String userId,String password) {
		HisiUser hisiUser=new HisiUser();
		hisiUser=hisiUserMapper.selectByUserId(userId);
		if(hisiUser==null){
			return false;
		}
		Object password1=passwordUtil.generateScret(userId,password);
		password=password1.toString();
		if(hisiUser.getPassword().equals(password)){
			return true;
		}
		return false;
	}
	@Override
	public HisiUser selectStatus(String userId) {
		HisiUser user=hisiUserMapper.selectStatus(userId);
		return user;
	}
	@Override
	public int addLoginRecord(HisiLoginRecord loginRecord) {
		return hisiLoginRecordMapper.insert(loginRecord);
	}
	@Override
	public String findLoginUserNameByChannelId(String channelId) {
		return hisiLoginRecordMapper.findLoginUserNameByChannelId(channelId);
	}
	
}

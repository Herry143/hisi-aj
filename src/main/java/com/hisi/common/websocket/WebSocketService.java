package com.hisi.common.websocket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.hisi.model.HisiOrderinfoBasic;
import com.hisi.model.vo.HisiUnpackNecessaryInfo;
import com.hisi.model.vo.InfoVo;
import com.hisi.model.vo.SceneOrderVo;

@Service
public class WebSocketService {

	@Autowired
	private SimpMessagingTemplate template;

	/**
	 * 广播 发给所有在线用户
	 *
	 * @param msg
	 */
	public void sendMsg(WiselyResponse msg) {
		template.convertAndSend("/topic/getResponse", msg);
	}

	/**
	 * 发送给指定用户
	 * 
	 * @param string
	 * @param hisiUnpackNecessaryInfo
	 */
	public void send2Users(String user,
			HisiUnpackNecessaryInfo hisiUnpackNecessaryInfo) {
		template.convertAndSendToUser(user, "/msg", hisiUnpackNecessaryInfo);
	}

	public void send2UsersString(String user, WiselyResponse e) {
		template.convertAndSendToUser(user, "/msg", e);
	}
	public void send2Users1(List<String> users, SceneOrderVo order) {
		users.forEach(userName -> {
			template.convertAndSendToUser(userName, "/msg", order);
		});
	}
	public void send2Users2(List<String> users, InfoVo infoVo) {
		users.forEach(userName -> {
			template.convertAndSendToUser(userName, "/msg", infoVo);
		});
	}
	public void send2Users3(List<String> users, HisiOrderinfoBasic order) {
		users.forEach(userName -> {
			template.convertAndSendToUser(userName, "/msg", order);
		});
	}
}
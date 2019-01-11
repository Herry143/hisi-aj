package com.hisi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hisi.common.util.YfslResult;
import com.hisi.model.HisiDepartment;
import com.hisi.model.HisiUser;

public interface UserService {

	byte[] generateByte(String imageUrl);

	int addUser(HisiUser hisiUser);

	HisiUser findUserByUserId(String userId);

	int updatePassword(String userId, String newPassword,String newSecretPassword);

	YfslResult batchImport(String fileName, MultipartFile file,String name);

	int updateDepartment(String userId, String departmentName);

	int deleteUser(Integer id);

	int disabledUser(Integer id);

	int resetPassword(String userId);

	int editUser(int id, String userId, String userName, String telPhone,
			String groupName, byte[] imageByte,
			String savePath,String password,String roleName);

	List<HisiUser> findByGroup(String groupName);

	List<HisiUser> findByConditions(String conditions,Integer pageNum,Integer pageSize,String groupName,String roleName,int departmentId);

	HisiDepartment getDepartmentId(String userId);

	List<HisiUser> findAllUsers(Integer pageNum,Integer pageSize,int departmentId);

	int undisabledUser(Integer id);

	String findPathById(int id);

	List<HisiUser> findAllUserId();

	int editPart(int id, String userId, String userName, String telPhone,
			String groupName,String password,String roleName);

	HisiUser findUserByTelPhone(String telPhone);

	int updateRole(String userId, String deviceRoleName);

	String findRoleByUserId(String userId);


	List<HisiUser> findProxyByCondition(int pageNum, int pageSize,
			String condition);

	List<HisiUser> getProxyList(int pageNum, int pageSize);

	int editProxy(String userId, String userName, String manager,
			String telphone,String cardId, int id,String roleName);

	String getUserName(String userId);

	HisiUser findProxyByCardId(String cardId);

	List<String> getProxyPerson();

	List<HisiUser> findUserByGroupName(String groupName);


}
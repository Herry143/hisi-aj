package com.hisi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hisi.common.util.MyMapper;
import com.hisi.model.HisiDepartment;
import com.hisi.model.HisiUser;

public interface HisiUserMapper extends MyMapper<HisiUser> {
	HisiUser selectByUserId(String userId);

	int updatePassword(@Param(value="userId")String userId, @Param(value="newPassword")String newPassword,@Param(value="newSecretPassword")String newSecretPassword);

	HisiUser selectStatus(String userId);

	void saveUsers(HisiUser hisiUser1);

	int updateDepartment(@Param(value="userId")String userId,@Param(value="departmentName") String departmentName);

	int disabledUser(Integer id);

	int resetPassword(@Param(value="userId")String userId, @Param(value="password")String password);

	int editUser(@Param(value="id")int id, @Param(value="userId")String userId, @Param(value="userName")String userName, @Param(value="telPhone")String telPhone,
			@Param(value="groupName")String groupName, @Param(value="imageByte")byte[] imageByte,
		@Param(value="savePath")String savePath,@Param(value="password")String password,@Param(value="roleName")String roleName);

	List<HisiUser> findByGroup(String groupName);

	List<HisiUser> findByConditions(@Param(value="conditions")String conditions,
			@Param(value="groupName")String groupName,
			@Param(value="roleName")String roleName,@Param(value="departmentId")int departmentId);

	HisiDepartment getDepartmentId(String userId);

	int undisabledUser(Integer id);

	String findPathById(int id);

	List<HisiUser> findAllUserId();

	int editPart(@Param(value="id")int id, @Param(value="userId")String userId, @Param(value="userName")String userName, @Param(value="telPhone") String telPhone,
			@Param(value="groupName")String groupName,@Param(value="password")String password,@Param(value="roleName")String roleName);

	HisiUser findUserByTelPhone(String telPhone);

	int updateRole(@Param(value="userId")String userId, @Param(value="deviceRoleName")String deviceRoleName);

	String findRoleByUserId(String userId);

	List<HisiUser> findProxyByCondition(@Param(value="condition")String condition);

	List<HisiUser> getProxyList();

	int editProxy(@Param(value="userId")String userId, @Param(value="userName")String userName, @Param(value="manager")String manager,
			@Param(value="telphone")String telphone,@Param(value="cardId")String cardId, @Param(value="id")int id, @Param(value="roleName")String roleName);
	List<HisiUser> findAllUsers(int departmentId);

	String getUserName(String userId);

	List<HisiUser> selectAllAirportUser();

	HisiUser findProxyByCardId(String cardId);

	List<String> getProxyPerson();

	List<HisiUser> findUserByGroupName(String groupName);
}
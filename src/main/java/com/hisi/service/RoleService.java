package com.hisi.service;

import java.util.List;

import com.hisi.model.HisiRole;

public interface RoleService {

	int addRole(String roleType,String roleName, String roleDesc);

	int editRole(Integer id, String roleName, String roleDesc,String roleType);

	List<HisiRole> getRole(Integer pageNum,Integer pageSize);

	int deleteRole(Integer id);

	List<String> findAllRole();

	List<HisiRole> getRoles();

	int deleteRolePermission(Integer id);

	List<String> getRoleTypeList_user();

	List<String> getRoleTypeList_device();

	List<String> getRoleTypeList_proxy();

	List<String> getRoleListByType(String roleType);

	int getRoleId(String userRoleName);

	List<HisiRole> getAllRoles();

	String findRoleTypeByName(String userRoleName1);

}

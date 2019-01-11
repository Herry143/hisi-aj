package com.hisi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisi.mapper.HisiPageOperateMapper;
import com.hisi.mapper.HisiPermissionMapper;
import com.hisi.mapper.HisiRolePermissionMapper;
import com.hisi.model.HisiPermission;
import com.hisi.model.HisiRolePermission;
import com.hisi.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private HisiPageOperateMapper hisiPageOperateMapper;
	@Autowired
	private HisiRolePermissionMapper hisiRolePermissionMapper;
	@Autowired
	private HisiPermissionMapper hisiPermissionMapper;
	@Override
	public List<HisiPermission> getAllPermissionsByPageName(String pageName) {
		return hisiPermissionMapper.getAllPermissionsByPageName(pageName);
	}
	@Override
	public int addPermission(HisiRolePermission hisiRolePermission) {
		return hisiRolePermissionMapper.insert(hisiRolePermission);
	}
	@Override
	public int deletePermission(HisiRolePermission hisiRolePermission) {
		return hisiRolePermissionMapper.delete(hisiRolePermission);
	}
	@Override//
	public List<HisiPermission> getPermissionsByRoleId(int roleId) {
		return hisiRolePermissionMapper.getPermissionsByRoleId(roleId);
	}
	@Override
	public List<HisiPermission> getPermissionsByRoleName(String roleName) {
		return hisiPermissionMapper.getPermissionsByRoleName(roleName);
	}
	@Override
	public List<HisiPermission> getPermissionsByPageName(int roleId,String pageName) {
		return hisiPermissionMapper.getPermissionsByPageName(roleId,pageName);
	}
	@Override
	public int getPermissionId(String permissionName) {
		return hisiPermissionMapper.getPermissionId(permissionName);
	}


}

package com.hisi.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hisi.model.HisiDepartment;
import com.hisi.model.TreeGroup;

public interface DepartmentService {

	int firstAdd(String departmentName);

	int editDepartmentName(Integer id, String departmentName,HttpServletRequest request);

	List<TreeGroup> getGroupById(int groupId);

	int getParentId(String parentName);

	int addGroup(int id, String groupName);

	int deleteGroup(int id);

	int editgroupName(Integer id, String groupName, HttpServletRequest request);

	List<HisiDepartment> getGroupById1(Integer groupId);

	String getName(int departmentId);

	/*List<String> findGroupName(int departmentId);*/

}
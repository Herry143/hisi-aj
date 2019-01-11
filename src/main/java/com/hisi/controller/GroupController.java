package com.hisi.controller;

import java.io.UnsupportedEncodingException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hisi.common.util.YfslResult;
import com.hisi.service.DepartmentService;
import com.hisi.service.GroupService;

@RestController
@RequestMapping("/group")
@Api(value = "HisiGroup", description = "班组管理")
public class GroupController {
	@Autowired
	private GroupService groupService;
	
	@ApiOperation(value = "增加班组",httpMethod="GET",response=String.class,notes = "增加班组")
	@RequestMapping(value="/addGroup",method=RequestMethod.GET)
	public YfslResult addGroup(@ApiParam(name="departmentName",value="部门名称",required=true)@RequestParam String departmentName,
			@ApiParam(name="groupName",value="班组名称",required=true)@RequestParam String groupName ){
		int i=groupService.addGroup(departmentName,groupName);
		if(i>0){
			return YfslResult.ok(groupName) ;
		}
		return YfslResult.fail("班组名重复");
	}
	@ApiOperation(value = "删除班组",httpMethod="GET",response=String.class,notes = "删除班组")
	@RequestMapping(value="/deleteGroup",method=RequestMethod.GET)
	public YfslResult deleteGroup(@ApiParam(name="groupName",value="班组名称",required=true)@RequestParam String groupName ){
		int i=groupService.deleteGroup(groupName);
		if(i>0){
			return YfslResult.ok("删除成功") ;
		}
		return YfslResult.fail("删除失败");
	}
}
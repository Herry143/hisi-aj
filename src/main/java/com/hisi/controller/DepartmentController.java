package com.hisi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hisi.common.util.YfslResult;
import com.hisi.model.HisiDepartment;
import com.hisi.model.HisiUser;
import com.hisi.model.TreeGroup;
import com.hisi.model.TreeGroup1;
import com.hisi.service.DepartmentService;
import com.hisi.service.UserService;

@RestController
@RequestMapping("/department")
@Api(value = "HisiDepartment", description = "部门管理")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private UserService userService;
	@ApiOperation(value = "初始化部门",httpMethod="GET",response=String.class,notes = "初始化部门")
	@RequestMapping(value="/firstAdd",method=RequestMethod.GET)
	public YfslResult firstAdd(@ApiParam(name="departmentName",value="部门名称",required=true)@RequestParam String departmentName,
			HttpServletRequest request){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		String userId=(String) session.getAttribute("userId");
		int i=departmentService.firstAdd(departmentName);
		if(i>0){
			session.setAttribute("departmentName", departmentName);
			//插入管理员的部门信息
			int n=userService.updateDepartment(userId,departmentName);
			if(n>0){
				return YfslResult.ok(departmentName);
			}
			else{
				return YfslResult.fail("更新员工部门信息失败");
			}
		}
		return YfslResult.fail("部门名字重复");
	}
	@ApiOperation(value = "修改部门名字",httpMethod="GET",response=String.class,notes = "初始化部门")
	@RequestMapping(value="/editDepartmentName",method=RequestMethod.GET)
	public YfslResult editDepartmentName(@ApiParam(name="id",value="主键id",required=true)@RequestParam Integer id,
			@ApiParam(name="departmentName",value="部门名称",required=true)@RequestParam String departmentName,
			HttpServletRequest request){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Object userId1 = session.getAttribute("userId");
		//防止抛空指针异常
		if(userId1==null){
			return YfslResult.fail("登录超时，请重新登录");
		}
		String userId=userId1.toString();
		int i=departmentService.editDepartmentName(id,departmentName,request);
		int j=userService.updateDepartment(userId, departmentName);
		if(i>0&&j>0){
			return YfslResult.ok(departmentName);
		}
		return YfslResult.fail("名字已重复");
	}
	@ApiOperation(value = "修改分组名字",httpMethod="GET",response=String.class,notes = "初始化部门")
	@RequestMapping(value="/editgroupName",method=RequestMethod.GET)
	public YfslResult editgroupName(@ApiParam(name="id",value="id",required=true)@RequestParam Integer id,
			@ApiParam(name="groupName",value="部门名称",required=true)@RequestParam String groupName,
			HttpServletRequest request){
		int i=departmentService.editgroupName(id,groupName,request);
		if(i>0){
			return YfslResult.ok(groupName);
		}
		return YfslResult.fail("名字已重复");
	}
	@ApiOperation(value = "获取部门",httpMethod="GET",response=String.class,notes = "获取部门")
	@RequestMapping(value="/getGroupTreeById",method=RequestMethod.GET)
	public YfslResult getGroupTreeById(HttpServletRequest request){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		System.out.println("getGroupTreeById  sessionId:"+session.getId());
		String userId = (String) session.getAttribute("userId");
		HisiDepartment hisiDepartment=userService.getDepartmentId(userId);
		if(hisiDepartment==null){
			return YfslResult.ok("[]");
		}
		return YfslResult.ok(hisiDepartment);
	}
	@ApiOperation(value = "获取分组列表",httpMethod="GET",response=String.class,notes = "获取分组列表")
	@RequestMapping(value="/getChildGroupTreeById",method=RequestMethod.GET)
	public List<HisiDepartment> getChildGroupTreeById(HttpServletRequest request){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		System.out.println("getChildGroupTreeById  sessionId:"+session.getId());
		String userId = (String) session.getAttribute("userId");
		//String userId="guanliyuan";
		HisiDepartment hisiDepartment=userService.getDepartmentId(userId);
		if(hisiDepartment==null){
			return null;
		}
		Integer groupId = hisiDepartment.getId();//部门id即parentId
		//List<TreeGroup> gruops=departmentService.getGroupById(groupId);//无限极的
		List<HisiDepartment>  hisiDepartments=departmentService.getGroupById1(groupId);
		return hisiDepartments;
	}
	@ApiOperation(value = "增加部门分组",httpMethod="GET",response=String.class,notes = "增加部门分组")
	@RequestMapping(value="/addGroup",method=RequestMethod.GET)
	public YfslResult addGroup(@ApiParam(name="parentName",value="父亲名字",required=true)@RequestParam String parentName,
			@ApiParam(name="groupName",value="节点名字",required=true)@RequestParam String groupName){
		int id=departmentService.getParentId(parentName);//父节点的主键id
		int i=departmentService.addGroup(id,groupName);
		if(i>0){
			return YfslResult.ok("增加成功");
		}
		return YfslResult.fail("增加失败");
	}
	@ApiOperation(value = "删除分组",httpMethod="GET",response=String.class,notes = "删除分组")
	@RequestMapping(value="/deleteGroup",method=RequestMethod.GET)
	public YfslResult deleteGroup(@ApiParam(name="groupName",value="节点名字",required=true)@RequestParam String groupName){
		List<HisiUser> user=userService.findUserByGroupName(groupName);
		if(user!=null){
			return YfslResult.fail("该节点正在使用，不能删除");
		}
		int id=departmentService.getParentId(groupName);
		int i=departmentService.deleteGroup(id);
		if(i>0){
			return YfslResult.ok("删除成功");
		}
		return YfslResult.fail("删除失败");
	}
}
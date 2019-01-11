package com.hisi.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisi.mapper.HisiDepartmentMapper;
import com.hisi.mapper.HisiGroupMapper;
import com.hisi.model.HisiDepartment;
import com.hisi.model.TreeGroup;
import com.hisi.service.DepartmentService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private HisiDepartmentMapper hisiDepartmentMapper;
	@Autowired
	private HisiGroupMapper hisiGroupMapper;
	@Override
	public int firstAdd(String departmentName) {
		List<HisiDepartment> departments=hisiDepartmentMapper.findByDepartmentName(departmentName);
		if (!departments.isEmpty()){
			return 0;
		}
		HisiDepartment hisiDepartment=new HisiDepartment();
		hisiDepartment.setName(departmentName);
		hisiDepartment.setParentId(null);
		int i = hisiDepartmentMapper.insert(hisiDepartment);
		return i;
	}
	@Override
	public int editDepartmentName(Integer id, String departmentName,HttpServletRequest request) {
		HisiDepartment hisiDepartment = hisiDepartmentMapper.selectByPrimaryKey(id);
		String oldName = hisiDepartment.getName();
		//所有的子分组
		List<HisiDepartment> hisiDepartments = hisiDepartmentMapper.getGroupById1(id);
		//List<HisiDepartment> hisiDepartments=hisiDepartmentMapper.findById(id);
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		for(int i=0;i<hisiDepartments.size();i++){
			if(hisiDepartments.get(i).getName().equals(departmentName)){
				return 0;
			}
		}
		if(oldName.equals(departmentName)){
			return 0;
		}
		int i=hisiDepartmentMapper.editDepartmentName(id,departmentName);
		session.setAttribute("departmentName", departmentName);
		return i;
	}
	@Override
	public List<TreeGroup> getGroupById(int groupId) {
		List<HisiDepartment> department= hisiDepartmentMapper.getGroupById(groupId);
		List<TreeGroup> children = transformTreePos(department);
		List<TreeGroup> groups=buildGroupTree(children);
		return groups;
	}
	private List<TreeGroup> buildGroupTree(List<TreeGroup> root) {
		for(int i=0;i<root.size();i++){
			String key = root.get(i).getKey();
			int parentId=Integer.parseInt(key);
			List<HisiDepartment> childrenPo=hisiDepartmentMapper.getGroupsByParentId(parentId);
			List<TreeGroup> children = transformTreePos(childrenPo);
			buildGroupTree(children);
			root.get(i).setChildren(children);
		}
		return root;
	}
	private List<TreeGroup> transformTreePos(List<HisiDepartment> Groups) {
		List<TreeGroup> treeGroups=new ArrayList<TreeGroup>();
		for(int j=0;j<Groups.size();j++){
			HisiDepartment groupPo = Groups.get(j);
			treeGroups.add(transformTreePo(groupPo));
		}
		return treeGroups;
	}
	private TreeGroup transformTreePo(HisiDepartment groupPo) {
		TreeGroup treeGroup=new TreeGroup();
		if(groupPo.getId()>=0){
			treeGroup.setKey(groupPo.getId()+"");
			treeGroup.setValue(groupPo.getId()+"");
		}
		if(!"".equals(groupPo.getName())){
			treeGroup.setLabel(groupPo.getName());
		}
		return treeGroup;
	}
	@Override
	public int getParentId(String parentName) {
		int i=hisiDepartmentMapper.getParentId(parentName);
		return i;
	}
	@Override
	public int addGroup(int id, String groupName) {//父主键id
		/*HisiDepartment hisiDepartment = hisiDepartmentMapper.selectByPrimaryKey(id);
		String oldName = hisiDepartment.getName();
		List<HisiDepartment> hisiDepartments = hisiDepartmentMapper.getGroupById1(id);
		for(int i=0;i<hisiDepartments.size();i++){
			if(hisiDepartments.get(i).getName().equals(groupName)){
				return 0;
			}
		}
		if(oldName.equals(groupName)){
			return 0;
		}*/
		List<HisiDepartment> departments= hisiDepartmentMapper.findByDepartmentName(groupName);
		if(!departments.isEmpty()){
			return 0;
		}
		HisiDepartment hisiDepartment1=new HisiDepartment();
		hisiDepartment1.setName(groupName);
		hisiDepartment1.setParentId(id);
		int i = hisiDepartmentMapper.insert(hisiDepartment1);
		return i;
	}
	@Override
	public int deleteGroup(int id) {
		int i=hisiDepartmentMapper.deleteGroup(id);
		return i;
	}
	@Override
	public int editgroupName(Integer id, String groupName,HttpServletRequest request) {
		HisiDepartment hisiDepartment = hisiDepartmentMapper.selectByPrimaryKey(id);
		Integer parentId = hisiDepartment.getParentId();
		String oldName = hisiDepartment.getName();
		List<HisiDepartment> hisiDepartments = hisiDepartmentMapper.getGroupById1(parentId);
		//List<HisiDepartment> hisiDepartments=hisiDepartmentMapper.findByParentId(parentId);
		for(int i=0;i<hisiDepartments.size();i++){
			if(hisiDepartments.get(i).getName().equals(groupName)){
				return 0;
			}
		}
		if(oldName.equals(groupName)){
			return 0;
		}
		int i=hisiDepartmentMapper.editgroupName(id,groupName);
		return i;
	}
	@Override
	public List<HisiDepartment> getGroupById1(Integer groupId) {
		return hisiDepartmentMapper.getGroupById1(groupId);
	}
	@Override
	public String getName(int departmentId) {
		return hisiDepartmentMapper.getName(departmentId);	
		}
}
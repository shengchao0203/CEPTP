package cn.edu.tit.role.Iservice.serivceImp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.tit.role.Idao.IRoleDao;
import cn.edu.tit.role.Iservice.IRoleService;
import cn.edu.tit.role.bean.Role;

/**
 * 角色类业务逻辑实现
 * @author huang
 *
 */
@Service
public class RoleServiceImp implements IRoleService {
	
	@Autowired
	private IRoleDao roleDao;

	@Override
	public Role schRoleInfo(String role_id) {
		//通过角色id获得相关用户信息并封装为role对象
		Role role = roleDao.findRoleById(role_id);
		return role;
	}

	@Override
	public void addRole(Role role) {
		/*
		 * 根据父角色id获取当前角色id
		 * 获取对象信息
		 */
		String create_user = role.getCreate_user();
		int num = roleDao.findChildRoleNumById(create_user);
		char name = getLetterByNum(num);
		String role_id = create_user+name;   //获得id
		String role_name = role.getRole_name();  //获得角色名
		String update_user = create_user;  //更新人
		Date create_time = new Date(); //获得创建时间
		Date update_time = create_time; //获得更新时间
		role.setRole_id(role_id);  //封装为完整的Role对象
		/*
		 * 调用持久层方法存储role
		 */
		roleDao.addRole(role_id, role_name, create_user,create_time,update_user,update_time);
		List<Integer> privilegeList = role.getPrivilegeIdList();   //获取role-privilege 并存储
		for(int privilege_id : privilegeList){
			roleDao.addRolePrivilege(role_id, privilege_id, create_user, create_time, update_user, update_time);
		}
	}

	@Override
	public void editRole(String role_id) {
		
		
	}

	@Override
	public void addUserForRole(String role_id,String create_user, List<String> staff_idList) {
		//子角色绑定选定用户
		String update_user = create_user; 
		Date create_time = new Date();
		Date update_time = create_time;
		for(String staffid : staff_idList ){
			roleDao.addUserForRole(role_id, staffid, create_user, create_time, update_user, update_time);
		}
	}

	@Override
	public List<Role> schAllRole() {
		// 查询所有角色并返回
		return null;
	}
	
	@Override
	public void addRolePrivilege(String role_id,String create_user,List<Integer> privilegeIdList) {
		//获取修改时间,和更新相关
		Date create_time = new Date();  //获得创建时间
		Date update_time = create_time; //获得更新时间
		String update_user = create_user;
		//为选中的子角色增加权限
		for(int privilegeId : privilegeIdList){
			roleDao.addRolePrivilege(role_id, privilegeId, create_user, create_time, update_user, update_time);
		}
		
	}

	@Override
	public void delRolePrivilege(String role_id, List<Integer> privilegeIdList) {
		// 获得被修改角色id及其所有子角色id
		List<String> roleIdList = roleDao.getAllChildRoleId(role_id);
		// 删除获得的所有角色id的相关权限
		for(String roleid : roleIdList){
			for(int privilege_id : privilegeIdList){
				roleDao.delRolePrivilegeById(roleid, privilege_id);
			}
		}
	}

	@Override
	public List<Role> schAllChildRole(String role_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> schPrivilegeById(String role_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 私有方法
	 * 将传入的数字作为脚标获得26个英文字母中对应的字母
	 * 此方法用于判断当前角色属于其父角色的第几个孩子以获取其相应id
	 * @param num
	 * 查出的兄弟角色数量
	 * @return
	 * 对应的英文字母
	 */
	private char getLetterByNum(int num){
		char name =' ';
		switch (num) {
		case 0:{
			name = 'A';
			break;
		}
		case 1:{
			name = 'B';
			break;
		}
		case 2:{
			name = 'C';
			break;
		}
		case 3:{
			name = 'D';
			break;
		}
		case 4:{
			name = 'E';
			break;
		}
		case 5:{
			name = 'F';
			break;
		}
		case 6:{
			name = 'G';
			break;
		}
		case 7:{
			name = 'H';
			break;
		}
		case 8:{
			name = 'I';
			break;
		}
		case 9:{
			name = 'J';
			break;
		}
		case 10:{
			name = 'K';
			break;
		}
		case 11:{
			name = 'L';
			break;
		}
		case 12:{
			name = 'M';
			break;
		}
		case 13:{
			name = 'N';
			break;
		}
		case 14:{
			name = 'O';
			break;
		}
		case 15:{
			name = 'P';
			break;
		}
		case 16:{
			name = 'Q';
			break;
		}
		case 17:{
			name = 'R';
			break;
		}
		case 18:{
			name = 'S';
			break;
		}
		case 19:{
			name = 'T';
			break;
		}
		case 20:{
			name = 'U';
			break;
		}
		case 21:{
			name = 'V';
			break;
		}
		case 22:{
			name = 'W';
			break;
		}
		case 23:{
			name = 'X';
			break;
		}
		case 24:{
			name = 'Y';
			break;
		}
		case 25:{
			name = 'Z';
			break;
		}
		default:
			break;
		}
		return name;
	}

	

}

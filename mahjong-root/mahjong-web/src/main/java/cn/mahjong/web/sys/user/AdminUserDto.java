package cn.mahjong.web.sys.user;

import org.springframework.util.CollectionUtils;

import cn.mahjong.model.sys.role.Role;
import cn.mahjong.model.sys.user.AdminUser;

public class AdminUserDto {
	
	private AdminUser target;
	
	public AdminUserDto(AdminUser user){
		target = user;
	}
	
	public long getId(){
		return target.getId();
	}
	
	public String getUsername(){
		return target.getUsername();
	}
	
	public String getNickname(){
		return target.getNickname();
	}
	
	public String getSex(){
		return String.valueOf(target.getSex().getVal());
	}
	
	public String getSexText(){
		return target.getSex().getDescription();
	}
	
	public String getUserStatus(){
		return String.valueOf(target.getUserStatus().getVal());
	}
	
	public String getUserStatusText(){
		return target.getUserStatus().getDescription();
	}
	
	public String getRoleName(){
		if (CollectionUtils.isEmpty(target.getRoleSet())) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (Role role : target.getRoleSet()) {
			sb.append(role.getName());
			sb.append(",");
		}
		return sb.substring(0, sb.length()-1);
	}
	
	public String getRoleVal(){
		if (CollectionUtils.isEmpty(target.getRoleSet())) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (Role role : target.getRoleSet()) {
			sb.append(role.getId());
			sb.append(",");
		}
		return sb.substring(0, sb.length()-1);
	}
	
}

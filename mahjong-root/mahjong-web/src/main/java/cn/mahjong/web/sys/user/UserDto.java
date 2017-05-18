package cn.mahjong.web.sys.user;

import cn.mahjong.model.sys.user.User;

public class UserDto {
	
	private User target;
	
	public UserDto(User user){
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
		return target.getSex().getDescription();
	}
	
	public String getUserStatus(){
		return target.getUserStatus().getDescription();
	}
	
}

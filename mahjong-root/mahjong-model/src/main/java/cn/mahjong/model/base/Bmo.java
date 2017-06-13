package cn.mahjong.model.base;

import java.util.Date;

import cn.mahjong.model.sys.user.User;

public interface Bmo extends BaseObject {

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getUpdateDate();

	public void setUpdateDate(Date updateDate);

	public Boolean getIsDelete();

	public void setIsDelete(Boolean isDelete);
	
	public User getCreateUser();

	public void setCreateUser(User createUser);

	public User getUpdateUser();

	public void setUpdateUser(User updateUser);
	
}

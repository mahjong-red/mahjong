package cn.mahjong.model.base;

import java.util.Date;

import cn.mahjong.model.sys.user.AdminUser;

public interface Bmo extends BaseObject {

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getUpdateDate();

	public void setUpdateDate(Date updateDate);

	public Boolean getIsDelete();

	public void setIsDelete(Boolean isDelete);
	
	public AdminUser getCreateUser();

	public void setCreateUser(AdminUser createUser);

	public AdminUser getUpdateUser();

	public void setUpdateUser(AdminUser updateUser);
	
}

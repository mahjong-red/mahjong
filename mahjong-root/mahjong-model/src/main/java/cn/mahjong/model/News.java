package cn.mahjong.model;

import java.util.Date;

public interface News {

	public Long getId();

	public void setId(Long id);

	public String getAuthor();

	public void setAuthor(String author);

	public String getTitle();

	public void setTitle(String title);

	public String getContent();

	public void setContent(String content);

	public Date getCreateTime();

	public void setCreateTime(Date createTime);

}

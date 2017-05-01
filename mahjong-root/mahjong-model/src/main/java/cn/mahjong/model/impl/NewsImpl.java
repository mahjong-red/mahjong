package cn.mahjong.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import cn.mahjong.enums.persist.Sex;
import cn.mahjong.model.News;

@Entity
@Table(name = "t_news")
@Proxy(lazy = true, proxyClass = News.class)
public class NewsImpl implements News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "sex", nullable = false, length = 1)
	@Type(type="cn.mahjong.enums.persist.PersistEnumType",
    parameters={@Parameter(name="enumClass",value="cn.mahjong.enums.persist.Sex")})
	private Sex sex;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = true)
	private Date createTime;

	public NewsImpl() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "NewsImpl [id=" + id + ", author=" + author + ", title=" + title + ", content=" + content + ", sex=" + sex.getDescription() + ", createTime=" + createTime + "]";
	}

	public NewsImpl(Long id, String author, String title, String content, Sex sex, Date createTime) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.content = content;
		this.sex = sex;
		this.createTime = createTime;
	}
	
}

package com.ship.web.article;
//22
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ship.web.person.Person;
import com.ship.web.proxy.Proxy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Component
@Entity
@NoArgsConstructor(access=AccessLevel.PUBLIC)
@Getter
@Setter(AccessLevel.PROTECTED)
@Table(name="ARTICLE")
public class Article extends Proxy implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ARTICLEID") @NotNull
		private Long articleid;
	@Column(name="TITLE") @NotNull
		private String title;
	@Column(name="CONTENT") @NotNull
		private String content;
	@Column(name="USERID") @NotNull
		private String userid;
	@Column(name="COMMENTS") 
		private String comments;
	@Column(name="IMG") 
		private String img;
	@Column(name="FILE") 
		private String file;
	@Temporal(TemporalType.DATE)
	@Column(name="REGDATE") @NotNull
		private Date regdate;
	
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "personid")
	  private Person personid;
	
	@Builder
	private Article(String title, String content,
			String userid, String comments, String img
			,String file, Date regdate) {
		Assert.hasText(title, "title must not be empty");
		Assert.hasText(content, "content must not be empty");
		Assert.hasText(userid, "userid must not be empty");
		Assert.hasText(comments, "comments must not be empty");
		Assert.hasText(img, "img must not be empty");
		Assert.hasText(file, "file must not be empty");
		Assert.hasText(string(regdate), "regdate must not be empty");
		
		this.title = title;
		this.content = content;
		this.userid = userid;
		this.comments = comments;
		this.img = img;
		this.file = file;
		this.regdate = regdate;
	}
}

package com.ship.web.reservation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ship.web.article.Article;
import com.ship.web.proxy.Proxy;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Component
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
@Table(name="RESERVATION", 
	uniqueConstraints = 
	{@UniqueConstraint
			(columnNames = {"RESNUM"})})
public class Reservation extends Proxy implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="RESSEQ") @NotNull
		private Long resseq;
	@Column(name="RESNUM", length= 64 ) @NotNull
		private int resnum;
	@Column(name="USERID") @NotNull
		private String userid;
	@Column(name="STADIUMID") @NotNull
		private String stadiumid;
	@Temporal(TemporalType.DATE)
	@Column(name="RESDATE") @NotNull
		private Date resdate;
	
//	@OneToMany(mappedBy = "personseq",
//			cascade = CascadeType.ALL,
//			orphanRemoval = true)
//    private List<Article> articles = new ArrayList<>();

	@Builder
	private Reservation (Long resseq, int resnum, String userid, String stadiumid, Date resdate) {
			this.resseq = resseq;
			this.resnum = resnum;
			this.userid = userid;
			this.stadiumid = stadiumid;
			this.resdate = resdate;
	}
}

package com.ship.web.reservation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ship.web.article.Article;
import com.ship.web.futsal.FutsalMatch;
import com.ship.web.person.Person;
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
@Table(name = "RESERVATION")
public class Reservation extends Proxy implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RESSEQ")
	private Long resseq;
	@Column(name = "RESDATE")
	@NotNull
	private Long resdate;
	@Column(name = "WIN")
	private int win;
	@Column(name = "POINT")
	private int point;
	@Column(name = "KM")
	private int km;
	@Column(name = "SCORE")
	private int score;
	
	@ManyToOne(fetch = FetchType.LAZY)
	
	@JoinColumn(name = "personseq") private Person personseq;
	
	@ManyToOne(fetch = FetchType.LAZY)
	
	@JoinColumn(name = "futsalmatchseq") private FutsalMatch futsalmatchseq;
	

	public Reservation(@NotNull Long resseq, @NotNull Long resdate, int win, int point, int km,
			int score) {
		this.resseq = resseq;
		this.resdate = resdate;
		this.win = win;
		this.point = point;
		this.km = km;
		this.score = score;
	}

}

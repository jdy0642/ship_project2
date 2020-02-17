package com.ship.web.person;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ship.web.proxy.Proxy;
import com.ship.web.reservation.Reservation;
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
@Setter(AccessLevel.PUBLIC)
@ToString
@Table(name="PERSON", 
	uniqueConstraints = 
	{@UniqueConstraint
			(columnNames = {"USERID"})})
public class Person extends Proxy implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PERSONSEQ")
	private Long personseq;
	@Column(name="USERID", length= 32 ) @NotNull
		private String userid;
	@Column(name="PASSWD", length= 32 ) @NotNull
		private String passwd;
	@Column(name="NAME", length= 16 ) @NotNull
		private String name;
	@Column(name="TEL", length= 16 ) @NotNull
		private String tel;
	@Column(name="POINT", length= 16 ) 
		private String point;
	@Column(name="AGE", length= 16 )@NotNull
		private int age;
	@Column(name="MALE", length= 64 )@NotNull
		private boolean male;
	@Column(name="SCORE", length= 16 ) 
		private int score;
	@Column(name="MVP", length= 16 ) 
		private int mvp;
	@Column(name="WIN", length= 16 ) 
		private int win;
	@Column(name="KM", length= 32 ) 
		private int km;
	@Column(name="BOOKMARK") 
		private boolean bookmark;
	@Column(name="INTEREST", length= 128 ) @NotNull
		private String interest;
	@Column(name="LOLBLACK", length= 64 ) 
		private boolean lolblack;
	@Column(name="FUTBLACK", length= 64 ) 
		private boolean futblack;
	@Column(name="BLACKTIME", length= 64 ) 
	private String blacktime;
	@Column(name="BLACKREASON", length= 64 ) 
	private String blackreason;
	@Column(name="JOB", length= 64 )@NotNull
		private String job;
	@Column(name="EMAIL", length= 128 )@NotNull
		private String email;
	@Column(name="SUMMONERNAME", length= 64 )
		private String summonername;
	enum Level{HIGH, MID, LOW}

	/*
	 * @OneToMany(mappedBy = "PERSONSEQ", cascade = CascadeType.ALL, orphanRemoval =
	 * true) private List<Article> articles = new ArrayList<>();
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "personseq", cascade = CascadeType.ALL, orphanRemoval=true)
	private List<Reservation> reservations = new ArrayList<>();
	 

	@Builder
	private Person(String userid, String name, String passwd, String tel,
			 String point, int age, boolean male, int score, int mvp,
			 int win, int km, boolean bookmark, String interest,String blacktime, String blackreason,
			 boolean lolblack, boolean futblack, String job, String email, String summonername) {
			
		this.userid = userid;
		this.name = name;
		this.passwd = passwd;
		this.tel = tel;
		this.point = point;
		this.age = age;
		this.male = male;
		this.score = score;
		this.mvp = mvp;
		this.win = win;
		this.km = km;
		this.bookmark = bookmark;
		this.interest = interest;
		this.lolblack = lolblack;
		this.futblack = futblack;
		this.blacktime = blacktime;
		this.blackreason = blackreason;
		this.job = job;
		this.email = email;
		this.summonername = summonername;
	}
}
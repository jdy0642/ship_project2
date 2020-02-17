package com.ship.web.reservation;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ship.web.futsal.Futsal;
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
	@Column(name = "WIN", length= 16 )
	private String win;
	@Column(name = "KM", length= 16 )
	private int km;
	@Column(name = "SCORE", length= 16 )
	private int score;
	

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "personseq")
	private Person personseq;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "futsalseq")
	private Futsal futsal;
	
	@Builder
	private Reservation(@NotNull Long resseq, @NotNull Long resdate, String win, int km,
			int score) {
		this.resseq = resseq;
		this.resdate = resdate;
		this.win = win;
		this.km = km;
		this.score = score;

	}

}

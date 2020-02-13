package com.ship.web.futsal;

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
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Setter(value = AccessLevel.PUBLIC)
@ToString
@Table(name = "FUTSAL")
public class Futsal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FUTSALSEQ")
	private Long futsalseq;
	@Column(name = "TIME")
	@NotNull
	private Long time;
	@Column(name = "STADIUMNAME")
	@NotNull
	private String stadiumname;
	@Column(name = "STADIUMADDR" , length= 128 )
	@NotNull
	private String stadiumaddr;
	@Column(name = "STADIUMTEL" , length= 64 )
	@NotNull
	private String stadiumtel;
	@Column(name = "NUM")
	@NotNull
	private int num;
	@Column(name = "GENDER" , length= 16 )
	@NotNull
	private String gender;
	@Column(name = "DIFFICULTY" , length= 16 )
	@NotNull
	private int difficulty;
	@Column(name = "SHOES" , length= 32 )
	@NotNull
	private String shoes;
	@Column(name = "STADIUMFACILITY" , length= 64 )
	@NotNull
	private String stadiumfacility;
	@Column(name = "STADIUMING")
	@NotNull
	private String stadiumimg;
	@Column(name = "REMAIN" , length= 16 )
	@NotNull
	private int remain;
	@Column(name = "ADMINNAME" , length= 16 )
	@NotNull
	private String adminname;

	@JsonIgnore
	@OneToMany(mappedBy = "futsal", cascade = CascadeType.ALL, orphanRemoval =true)
	private List<Reservation> reservations = new ArrayList<>();
	
	@Builder
	private Futsal(@NotNull Long futsalseq, @NotNull Long time, @NotNull String stadiumname,
			@NotNull String stadiumaddr, @NotNull String stadiumtel, @NotNull int num, @NotNull String gender,
			@NotNull int difficulty, @NotNull String shoes, @NotNull String stadiumfacility, @NotNull String stadiumimg,
			@NotNull int remain, @NotNull String adminname) {
		this.futsalseq = futsalseq;
		this.time = time;
		this.stadiumname = stadiumname;
		this.stadiumaddr = stadiumaddr;
		this.stadiumtel = stadiumtel;
		this.num = num;
		this.gender = gender;
		this.difficulty = difficulty;
		this.shoes = shoes;
		this.stadiumfacility = stadiumfacility;
		this.stadiumimg = stadiumimg;
		this.remain = remain;
		this.adminname = adminname;
	}
}

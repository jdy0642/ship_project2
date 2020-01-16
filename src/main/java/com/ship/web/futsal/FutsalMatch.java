package com.ship.web.futsal;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

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
@Setter(value = AccessLevel.PROTECTED)
@ToString
@Table(name="FUTSALMATCH")
public class FutsalMatch implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="FUTSALMATCHSEQ") @NotNull
	private Long futsalmatchseq;
	@Column(name="TIME") @NotNull
	private Long time;
	@Column(name="STADIUMNAME") @NotNull
	private String stadiumName;
	@Column(name="STADIUMADDR") @NotNull
	private String stadiumaddr;
	@Column(name="STADIUMTEL") @NotNull
	private String stadiumtel;
	@Column(name="NUM") @NotNull
	private int num;
	@Column(name="GENDER") @NotNull
	private String gender;
	@Column(name="DIFFICULTY") @NotNull
	private int difficulty;
	@Column(name="SHOES") @NotNull
	private String shoes;
	@Column(name="STADIUMFACILITY") @NotNull
	private String stadiumfacility;
	@Column(name="STADIUMING") @NotNull
	private String stadiumimg;
	@Column(name="REMAIN") @NotNull
	private int remain;
	@Column(name="ADMINNAME") @NotNull
	private String adminName;
	
	@Builder
	private FutsalMatch(@NotNull Long futsalmatchseq, @NotNull Long time, @NotNull String stadiumName,
			@NotNull String stadiumaddr, @NotNull String stadiumtel, @NotNull int num, @NotNull String gender, @NotNull int difficulty,
			@NotNull String shoes, @NotNull String stadiumfacility, @NotNull String stadiumimg, @NotNull int remain,
			@NotNull String adminName) {
		this.futsalmatchseq = futsalmatchseq;
		this.time = time;
		this.stadiumName = stadiumName;
		this.stadiumaddr = stadiumaddr;
		this.stadiumtel = stadiumtel;
		this.num = num;
		this.gender = gender;
		this.difficulty = difficulty;
		this.shoes = shoes;
		this.stadiumfacility = stadiumfacility;
		this.stadiumimg = stadiumimg;
		this.remain = remain;
		this.adminName = adminName;
	}
}

package com.ship.web.lol;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.ship.web.proxy.Proxy;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Component
@Entity
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PROTECTED)
@ToString
@Table(name="LOL",
   uniqueConstraints = 
   {@UniqueConstraint
         (columnNames = {"CARDSEQ"})})

public class Lol extends Proxy implements Serializable{
   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="CARDSEQ") 
   private Long cardseq;
   @Column(name="RHOST") @NotNull
   private String rhost;
   @Column(name="RGUEST")
   private String rguest;
   @Column(name="LOLBLACK")
   private String lolblack;
   @Column(name="TITLE") @NotNull
   private String title;
   @Column(name="CONTENTS")
   private String contents;
   @Column(name="TIER")
   private String tier;
   @Column(name="IMGURL") @NotNull
   private String imgurl;
   @Column(name="IMG")
   private String img;
   @Column(name="CRAWLTIER") 
   private String crawltier;
   @Column(name="CRAWLRATE") 
   private String crawlrate;
   @Column(name="WTIME") 
   private Date wtime;
   @Column(name="POSITION") 
   private String position;
   
   
   @Builder
   private Lol(String rhost,String rguest, String lolblack,
         String title, String contents, String tier,
         String imgurl, String img, Date wtime, String position,
         String crawltier, String crawlrate) {
      this.rhost = rhost;
      this.rguest = rguest;
      this.lolblack = lolblack;
      this.title = title;
      this.contents = contents;
      this.tier = tier;
      this.imgurl = imgurl;
      this.img = img;
      this.crawltier = crawltier;
      this.crawlrate = crawlrate;
      this.wtime = wtime;
      this.position = position;
   }
}
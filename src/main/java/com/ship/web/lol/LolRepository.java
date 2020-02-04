package com.ship.web.lol;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LolRepository extends CrudRepository<Lol, Long>{
//	public Lol insertRoom(String title, String contents, String tier);
	public Lol findByCardseq(Long cardseq);
}

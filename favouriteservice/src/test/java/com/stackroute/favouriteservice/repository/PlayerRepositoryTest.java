package com.stackroute.favouriteservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.domain.Player;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class PlayerRepositoryTest {

	@Autowired
	private transient PlayerRepository playerRepository;

	public void setRepo(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
//	public void deleteData(){
//		final Player playerDel = playerRepository.findByName("wall");
//		playerRepository.delete(playerDel);
//		assertEquals(Optional.empty(), playerRepository.findByName("wall"));
//	}

	@Test
	public void testSavePlayer() throws Exception {
		playerRepository.save(new Player(18, "wall", "rahul", 2,"31"));
		final Player player = playerRepository.findByName("wall");
		System.out.println(player);
		assertThat(player.getName()).isEqualTo("wall");
		playerRepository.delete(player);
		assertEquals(null, playerRepository.findByName("wall"));	
		}

	@Test
	public void testDeletePlayer() throws Exception {
		playerRepository.save(new Player(28, "wall", "rahul", 1,"31"));
		final Player player = playerRepository.findByName("wall");
		playerRepository.delete(player);
		assertEquals(null, playerRepository.findByName("wall"));	
	}

	@Test
	public void testGetMyPlayers() throws Exception {
		Player player1=playerRepository.save(new Player(1, "dada", "sourav", 1,"3"));;
		final List<Player> players = playerRepository.findByUserId("3");
		assertEquals("dada", players.get(0).getName());
		playerRepository.delete(player1);
		assertEquals(null, playerRepository.findByName("dada"));
		
	}

}

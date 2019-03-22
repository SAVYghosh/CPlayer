package com.stackroute.favouriteservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.repository.PlayerRepository;

@Service
public class PlayerServiceImpl implements PlayerService {
	
	private final transient PlayerRepository playerRepo;
	
	@Autowired
	public PlayerServiceImpl(PlayerRepository playerRepo) {
		super();
		this.playerRepo = playerRepo;
	}
@Override
public boolean savePlayer(Player player) throws PlayerAlreadyExistsException{
	final Optional<Player> object = playerRepo.findByNameAndUserId(player.getName(),player.getUserId());
	if(object.isPresent()) {
		throw new PlayerAlreadyExistsException("Could not save player, Player already exists");
	}
	playerRepo.save(player);
	return true;
	
}
@Override
public boolean  deletePlayerById(int id)  throws PlayerNotFoundException {
	final Player player = playerRepo.findById(id);
	if(player ==  null) {
		throw new PlayerNotFoundException("Could not delete. Player not found ");
	}
	playerRepo.delete(player);
	return true;
}
@Override
public Player getPlayerById(int id) throws PlayerNotFoundException{
	final Player player =  playerRepo.findById(id);
	if (player == null) {
		throw new PlayerNotFoundException("player not found");
	}
	return player;	
}

@Override
public List<Player> getMyPlayers(String userId) {
	return playerRepo.findByUserId(userId);
}


}

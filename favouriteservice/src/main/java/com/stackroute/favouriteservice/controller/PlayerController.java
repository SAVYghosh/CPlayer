package com.stackroute.favouriteservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.domain.Player;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.service.PlayerService;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/api/v1/playerservice")
@CrossOrigin
public class PlayerController {
	private PlayerService playerService;

	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	@PostMapping("/player")
	public ResponseEntity<?> saveNewPlayer(@RequestBody final Player player, final HttpServletRequest request,
			final HttpServletResponse response) {
		ResponseEntity<?> responseEntity;
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		try {
			player.setUserId(userId);
			playerService.savePlayer(player);
			responseEntity = new ResponseEntity<Player>(player, HttpStatus.CREATED);
		} catch (PlayerAlreadyExistsException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}
		return responseEntity;
	}
	
	@DeleteMapping(path = "/player/{id}")
	public ResponseEntity<?> deletePlayerById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		try {
			playerService.deletePlayerById(id);
			responseEntity = new ResponseEntity<String>( "{ \"message\": \"" + "Player deleted successfully"+ "\"}", HttpStatus.OK);
		} catch (PlayerNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@GetMapping(path = "/player/{id}")
	public ResponseEntity<?> getPlayerById(@PathVariable("id") final int id) {
		ResponseEntity<?> responseEntity;
		try {
			Player player = playerService.getPlayerById(id);
			responseEntity = new ResponseEntity<Player>(player, HttpStatus.OK);
		} catch (PlayerNotFoundException e) {
			responseEntity = new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	@GetMapping("/player")
	public ResponseEntity<?> getMyPlayer(final HttpServletRequest request, final HttpServletResponse response) {
		final String authHeader = request.getHeader("authorization");
		final String token = authHeader.substring(7);
		String userId = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody().getSubject();
		List<Player> player = playerService.getMyPlayers(userId);
		ResponseEntity<?> responseEntity = new ResponseEntity<List<Player>>(player, HttpStatus.OK);
		return responseEntity;
	}
}

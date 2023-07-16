package com.example.workshop26prac.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshop26prac.Model.Game;
import com.example.workshop26prac.Repository.GameRepo;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@RestController
@RequestMapping
public class Controller {

    @Autowired
    GameRepo gameRepo;

    @GetMapping(path = "/games", produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<String> getGame(@RequestParam(defaultValue = "25") Integer  limit, 
    @RequestParam(defaultValue = "0") Integer offset){

        List<Game> listOfGames = gameRepo.getAllGames(limit, offset);
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for(Game game : listOfGames){

            JsonObject gameObj = Json.createObjectBuilder()
                .add("name", game.getName())
                .add("id", game.getGid())
                .build();

            arrayBuilder.add(gameObj);
            
        }

        JsonObject jsonObj = Json.createObjectBuilder()
                .add("Game", arrayBuilder.build())
                .add("Offset", offset)
                .add("limit", limit)
                .add("total", listOfGames.size())
                .add("timestamp", LocalDateTime.now().toString())
                .build();

        return ResponseEntity.ok(jsonObj.toString());

    }

    @GetMapping(path = "/games/ranking", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllGameRankAsc(@RequestParam(defaultValue = "25")Integer limit, 
    @RequestParam(defaultValue = "0") Integer offset){


        List<Game> listOfGames = gameRepo.getAllGameRankAsc(limit, offset);
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for(Game game : listOfGames){

            JsonObject gameObj = Json.createObjectBuilder()
                    .add("name", game.getName())
                    .add("id", game.getGid())
                    .add("ranking", game.getRanking())
                    .build();

            arrayBuilder.add(gameObj);
        }

        JsonObject jsonObj = Json.createObjectBuilder()
                .add("Game", arrayBuilder.build())
                .add("offset", offset)
                .add("limit", limit)
                .add("total", listOfGames.size())
                .add("timestamp", LocalDateTime.now().toString())
                .build();

        return ResponseEntity.ok(jsonObj.toString());

    }

    @GetMapping(path="/game/{gid}")
    public ResponseEntity<String> getGameByGid(@PathVariable Integer gid){

        Game game = gameRepo.getGameByGid(gid);


        JsonObject GameObj = Json.createObjectBuilder()
                .add("id", game.getGid())
                .add("name", game.getName())
                .add("year", game.getYear())
                .add("ranking", game.getRanking())
                .add("User Rating", game.getUsers_rated())
                .add("Url", game.getUrl())
                .add("Image", game.getImage())
                .build();

        return ResponseEntity.ok(GameObj.toString());
        // return ResponseEntity.ok(game.toString());

    }



    
}

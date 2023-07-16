package com.example.workshop26prac.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshop26prac.Model.Game;
import com.example.workshop26prac.Repository.GameRepo;

@Service
public class GameService {

    @Autowired
    GameRepo gameRepo;


    public List<Game> getAllGames(Integer limit, Integer offset){

        return gameRepo.getAllGames(limit, offset);

    }

    public List<Game> getAllGameRankAsc(Integer limit, Integer offset){

        return gameRepo.getAllGameRankAsc(limit, offset);
    }

    public Game getGameByGid(Integer gid){

        return gameRepo.getGameByGid(gid);

    }


}

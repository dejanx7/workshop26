package com.example.workshop26prac.Repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.example.workshop26prac.Model.Game;

@Repository
public class GameRepo {

    @Autowired
    MongoTemplate template;

    public List<Game> getAllGames(Integer limit, Integer offset){

        // db.game.find({})
        // .limit(<limit>)
        // .skip(<offset>)
        // .sort({"gid": 1});


        Criteria criteria = new Criteria("name").exists(true);
        Query query = new Query(criteria)
                    .limit(limit)
                    .skip(offset)
                    .with(Sort.by("gid"));

        List<Document> listOfDoc = template.find(query, Document.class, "game");

        List<Game> listOfGames = new ArrayList<>();

        for(Document doc : listOfDoc){

            Game game = new Game();
            game.setGid(doc.getInteger("gid"));
            game.setName(doc.getString("name"));
            listOfGames.add(game);
        }

        System.out.println(listOfDoc);
        System.out.println(listOfGames);

        return listOfGames;
        
    }

    public List<Game> getAllGameRankAsc(Integer limit, Integer offset){

        // db.game.find({})
        // .sort({ranking : 1});

        Criteria criteria = Criteria.where("name").exists(true);
        Query query = Query.query(criteria)
            .limit(limit)
            .skip(offset)
            .with(Sort.by( "ranking"));

        return template.find(query, Game.class, "game");



    }

    public Game getGameByGid(Integer gid){

        Criteria criteria = Criteria.where("gid").is(gid);
        Query query = Query.query(criteria);

        List<Game> gameList = template.find(query, Game.class, "game");
        Game game = gameList.get(0);
        return game;


    }
    
}

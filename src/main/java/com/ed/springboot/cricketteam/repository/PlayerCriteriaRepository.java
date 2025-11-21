package com.ed.springboot.cricketteam.repository;

import com.ed.springboot.cricketteam.entity.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PlayerCriteriaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Player> findPlayersByRole(String role){
        CriteriaBuilder cb= entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> query=cb.createQuery(Player.class);
        Root<Player> player=query.from(Player.class);
        Predicate condition=cb.equal(player.get("role"),role);
        query.where(condition);
        return entityManager.createQuery(query).getResultList();
    }
}

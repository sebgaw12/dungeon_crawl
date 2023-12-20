package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.actors.Actor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MonsterModel implements BaseModel {

    private int health;
    private int defense;
    private int attack;
    private int fieldOfView;
    private int positionX;
    private int positionY;
    private String name;

    public MonsterModel(Actor actor) {
        this.health = actor.getHealth();
        this.defense = actor.getDefense();
        this.attack = actor.getAttack();
        this.fieldOfView = 5;
        this.positionX = actor.getPosition().x();
        this.positionY = actor.getPosition().y();
        this.name = actor.getClass().getSimpleName().toUpperCase();
    }

    @Override
    public Position getPosition() {
        return Position.of(positionX, positionY);
    }
}

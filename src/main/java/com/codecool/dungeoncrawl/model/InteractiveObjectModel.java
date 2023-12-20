package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.interactiveobjects.InteractiveObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class InteractiveObjectModel implements BaseModel {

    private int positionX;
    private int positionY;
    private boolean isInteracted;
    private String name;

    public InteractiveObjectModel(InteractiveObject interactiveObject) {
        this.positionX = interactiveObject.getPosition().x();
        this.positionY = interactiveObject.getPosition().y();
        this.name = interactiveObject.getClass().getSimpleName().toUpperCase();
        this.isInteracted = interactiveObject.isWalkable();
    }

    @Override
    public Position getPosition() {
        return Position.of(positionX, positionY);
    }
}

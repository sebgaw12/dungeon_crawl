package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.engine.utils.Position;
import com.codecool.dungeoncrawl.logic.gameobjects.items.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemModel implements BaseModel {

    private int positionX;
    private int positionY;
    private String name;

    public ItemModel(Item item) {
        this.positionX = item.getPosition().x();
        this.positionY = item.getPosition().y();
        this.name = item.getClass().getSimpleName().toUpperCase();
    }

    @Override
    public Position getPosition() {
        return Position.of(positionX, positionY);
    }
}

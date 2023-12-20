package com.codecool.dungeoncrawl.fileloader;

import com.codecool.dungeoncrawl.logic.engine.Cell;

import java.util.function.Consumer;

public enum CharOnMap {
    PLAYER('@', ActorConsumer::player),
    EMPTY(' ', EnvironmentalObjectConsumer::empty),
    WALL('#', EnvironmentalObjectConsumer::wall),
    FLOOR('.', EnvironmentalObjectConsumer::floor),
    SKELETON('s', ActorConsumer::skeleton),
    FOOD('a', ItemConsumer::food),
    KEY('k', ItemConsumer::key),
    SWORD('m', ItemConsumer::sword),
    ARMOR('b', ItemConsumer::armor),
    DOOR('Y', InteractiveObjectConsumer::door),
    OGRE('O', ActorConsumer::ogre),
    CHEST('c', InteractiveObjectConsumer::chest),
    TORCH('T', ItemConsumer::torch),
    WATER('W', EnvironmentalObjectConsumer::water),
    BOAT('B', InteractiveObjectConsumer::boat),
    GRASS('G', EnvironmentalObjectConsumer::grass),
    LETTER_D('d', EnvironmentalObjectConsumer::letterD),
    LETTER_O('o', EnvironmentalObjectConsumer::letterO),
    LETTER_N('n', EnvironmentalObjectConsumer::letterN),
    LETTER_T('t', EnvironmentalObjectConsumer::letterT),
    LETTER_G('g', EnvironmentalObjectConsumer::letterG),
    LETTER_H('h', EnvironmentalObjectConsumer::letterH),
    LETTER_E('e', EnvironmentalObjectConsumer::letterE),
    LETTER_R('r', EnvironmentalObjectConsumer::letterR),
    MAGE('M', ActorConsumer::mage),
    ARCHER('A', ActorConsumer::archer),
    ARCHMAGE('Q', ActorConsumer::archMage),
    PALADIN('P', ActorConsumer::paladin),
    WARRIOR('w', ActorConsumer::warrior);

    private final char charOnMapTxt;
    private final Consumer<Cell> strategy;

    CharOnMap(char charOnMapTxt, Consumer<Cell> strategy) {
        this.charOnMapTxt = charOnMapTxt;
        this.strategy = strategy;
    }

    public void apply(Cell cell) {
        strategy.accept(cell);
    }

    public static CharOnMap fromChar(char charOnMap) {
        for (CharOnMap type : values()) {
            if (type.charOnMapTxt == charOnMap) {
                return type;
            }
        }
        throw new RuntimeException("Unrecognized character: '" + charOnMap + "'");
    }
}

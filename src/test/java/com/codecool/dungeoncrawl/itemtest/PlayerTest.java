package com.codecool.dungeoncrawl.itemtest;

import com.codecool.dungeoncrawl.logic.gameobjects.actors.actorplayer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlayerTest {

    @BeforeEach
    public void setup() {
        Player player = new Player();
    }

    @Test
    public void testAddToInventoryShouldReturnIllegalArgumentExceptionWhenPassingWrongObject() {
        // arrange
        // act & assert
    }
}

package com.stqa.astar.core;

import com.stqa.astar.cell.Cell;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AStarCoreTest {

    @Test
    void initialCheckingOfStartAStar() {
        AStarCore s=new AStarCore();
        assertFalse(s.startAStar());
    }

    @Test
    void initialCheckingOfOpenSet() {
        assertNotNull(AStarCore.openSet);
    }

    @Test
    void initialCheckingOfClosedSet() {
        assertNotNull(AStarCore.closedSet);
    }


    @Test
    void heuristicChecking55and44() {

        AStarCore s=new AStarCore();
        assertEquals(s.heuristic(new Cell(5,5),new Cell(4,4)),0.0);
    }

    @Test
    void heuristicChecking1010and22() {

        AStarCore s=new AStarCore();
        assertEquals(s.heuristic(new Cell(10,10),new Cell(2,2)),0.0);
    }

    @Test
    void heuristicChecking56and78() {
        AStarCore s=new AStarCore();
        assertEquals(s.heuristic(new Cell(5,6),new Cell(7,8)),0.0);
    }
}

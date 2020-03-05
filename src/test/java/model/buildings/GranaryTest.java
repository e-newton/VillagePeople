package model.buildings;

import model.exceptions.NoTypeAvailabeException;
import model.exceptions.NotEnoughException;
import model.resources.Food;
import model.resources.FoodTypes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GranaryTest {
    Granary g;
    @Before
    public void beforeEach(){
        g = new Granary();
    }

    @Test
    public void addFood() {
        assertTrue(g.getInventory().isEmpty());
        Food f = new Food(FoodTypes.APPLE, 5);
        g.addFood(f);
        assertFalse(g.getInventory().isEmpty());
        assertTrue(g.getInventory().containsKey(FoodTypes.APPLE));
        assertEquals((int)g.getInventory().get(FoodTypes.APPLE), 5);
    }

    @Test
    public void removeFood() {
        Food f = new Food(FoodTypes.APPLE, 5);
        g.addFood(f);
        try {
            g.removeFood(FoodTypes.APPLE, 3);
        } catch (NotEnoughException e) {
            fail();
        } catch (NoTypeAvailabeException e) {
            fail();
        }
        assertFalse(g.getInventory().isEmpty());
        assertTrue(g.getInventory().containsKey(FoodTypes.APPLE));
        assertEquals((int)g.getInventory().get(FoodTypes.APPLE), 2);

    }
    @Test
    public void removeFoodNotEnoughExceptionTest() {
        Food f = new Food(FoodTypes.APPLE, 5);
        g.addFood(f);
        try {
            g.removeFood(FoodTypes.APPLE, 555555555);
            fail();
        } catch (NotEnoughException e) {
            System.out.println("NotEnoughException Caught");
        } catch (NoTypeAvailabeException e) {
            fail();
        }
    }

    @Test
    public void removeFoodNoTypeAvailableExceptionTest() {
        Food f = new Food(FoodTypes.APPLE, 5);
        g.addFood(f);
        try {
            g.removeFood(FoodTypes.CHEESE, 2);
            fail();
        } catch (NotEnoughException e) {
            fail();
        } catch (NoTypeAvailabeException e) {
            System.out.println("NoTypeAvailableException Caught");
        }
    }

    @Test
    public void removeFoodEmptyTest() {
        removeFood();
        try {
            g.removeFood(FoodTypes.APPLE, 2);
        } catch (NotEnoughException e) {
            fail();
        } catch (NoTypeAvailabeException e) {
            fail();
        }
        assertTrue(g.getInventory().isEmpty());
        assertFalse(g.getInventory().containsKey(FoodTypes.APPLE));

        addFood();
    }
}
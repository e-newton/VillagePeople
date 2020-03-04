package math;



import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VectorTest {
    Vector v1;
    Vector v2;
    @Before
    public void beforeEach(){
        v1 = new Vector(1.0f, 0.0f);
        v2 = new Vector(0.0f, 1.0f);
    }

    @Test
    public void orthTest(){
        Vector v3 = new Vector(1.0f,1.0f);

        assertTrue(v1.isOrthogonal(v2));
        assertFalse(v1.isOrthogonal(v3));
        assertFalse(v2.isOrthogonal(v3));
    }
    @Test
    public void addTest(){
        assertEquals(Vector.add(v1,v2), new Vector(1.0f,1.0f));
    }

}

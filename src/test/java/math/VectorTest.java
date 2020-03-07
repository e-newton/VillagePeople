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
    @Test
    public void magnitudeTest() {
        assertEquals(v1.magnitude(),1,Vector.DELTA);
        assertEquals(Vector.add(v1,v2).magnitude(), Math.sqrt(2), Vector.DELTA);
        v1.add(v2);
        v1.normalize();
        assertEquals(v1.magnitude(),1.0f,Vector.DELTA);
    }

    @Test
    public void rotateAroundPointOriginTest(){
        v1.rotateAroundPoint(new Vector(0,0), 90);
        assertEquals(new Vector(0,1), v1);

    }
    @Test
    public void rotateAroundPointTest(){
        Vector vp = new Vector(1,1);
        Vector v = new Vector(2,1);
        v.rotateAroundPoint(vp,90);
        assertEquals(v, new Vector(1,2));
    }
    @Test
    public void rotateAroundPointTest2(){
        Vector vp = new Vector(100,100);
        Vector v = new Vector(200,100);
        v.rotateAroundPoint(vp,90);
        assertEquals(v, new Vector(100,200));
    }

}

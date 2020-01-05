

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import algorithms.*;
import dataStructure.*;
import utils.*;


/**
 * EX2 Structure test:
 * 1. make sure your code compile with this dummy test (DO NOT Change a thing in this test).
 * 2. the only change require is to run your GUI window (see line 64).
 * 3. EX2 auto-test will be based on such file structure.
 * 4. Do include this test-case in you submitted repository, in folder Tests (under src).
 * 5. Do note that all the required packages are imported - do NOT use other
 *
 * @author boaz.benmoshe
 *
 */
class Ex2Test {
    private static graph _graph;
    private static graph_algorithms _alg;
    public static final double EPS = 0.001;
    private static Point3D min = new Point3D(0,0,0);
    private static Point3D max = new Point3D(100,100,0);
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        _graph = tinyGraph();
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testConnectivity() {
        _alg = new Graph_Algo(_graph);
        boolean con = _alg.isConnected();
        if(!con) {
            fail("shoulbe be connected");
        }
    }
    @Test
    void testgraph() {
        boolean ans = drawGraph(_graph);
        assertTrue(ans);
    }

    private static graph tinyGraph() {
        graph ans = new DGraph();
        return ans;
    }
    boolean drawGraph(graph g) {
        Graph_Algo GA = new Graph_Algo();
         g = new DGraph();
        GA.init(g);

        g.addNode(new Node(1,new Point3D(1, 2),3,null,0));
        g.addNode(new Node(2,new Point3D(3.5, 4),1,null,4));
        g.addNode(new Node(3,new Point3D(7, -2),3,null,1));
        g.addNode(new Node(4,new Point3D(0, 0),9,null,2));
        g.addNode(new Node(5,new Point3D(2, -3),8,null,3));
        g.connect(2,5,1);
        g.connect(4,5,12);
        g.connect(1,2,2);
        g.connect(2,3,4);
        GA.drawGraph();
        g.connect(3,5,11);
        GA.drawGraph();
        try {

            Thread.sleep(15000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

}
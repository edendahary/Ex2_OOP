package algorithms;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.node_data;
import utils.Point3D;
import utils.StdDraw;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Graph_AlgoTest {
    @org.junit.jupiter.api.Test
    void TSP() {
        DGraph g = new DGraph();
        g.addNode(new Node(1,null,3,null,0));
        g.addNode(new Node(2,null,1,null,4));
        g.addNode(new Node(3,null,3,null,1));
        g.addNode(new Node(4,null,3,null,2));
        g.addNode(new Node(5,null,3,null,2));
        g.addNode(new Node(6,null,3,null,2));
        g.addNode(new Node(7,null,3,null,2));
        g.addNode(new Node(8,null,3,null,2));
        g.connect(1,7,2);
        g.connect(7,2,2);
        g.connect(2,8,4);
        g.connect(8,7,4);
        g.connect(7,3,4);
        g.connect(3,6,7);
        g.connect(6,5,2);
        g.connect(5,4,2);
        Graph_Algo GA = new Graph_Algo();
        GA.init(g);
        List<Integer> n = new ArrayList<>();
        n.add(1);
        n.add(2);
        n.add(3);
        n.add(4);
        List<node_data> k = new ArrayList<>();
        k=GA.TSP(n);
        if(k!=null) {
            for (int i = 0; i < k.size(); i++) {
                System.out.println(k.get(i).getKey());
            }
        }

    }

    @org.junit.jupiter.api.Test
    void shortestPathDist() {
        DGraph g = new DGraph();
        g.addNode(new Node(1,null,3,null,0));
        g.addNode(new Node(2,null,1,null,4));
        g.addNode(new Node(3,null,3,null,1));
        g.addNode(new Node(4,null,3,null,2));
        g.connect(1,2,2);
        g.connect(2,3,4);
        g.connect(3,4,7);
        g.connect(2,1,2);
        g.connect(4,3,2);
        g.connect(3,2,2);

        Graph_Algo GA = new Graph_Algo();
        GA.init(g);
        double x;
        x=GA.shortestPathDist(1,4);
        System.out.println(x);

    }

    @org.junit.jupiter.api.Test
    void initSaveText() {
        // create Grah_Algo and graph
        Graph_Algo GASave = new Graph_Algo();
        DGraph g = new DGraph();
        GASave.init(g);
        g.addNode(new Node(1,new Point3D(1.0, 2.0, 3.0),1,null,0));
        g.addNode(new Node(2,new Point3D(4.0, 5.0, 6.0), 2, null,0));
        g.addNode(new Node(3,new Point3D(6.0, 7.0, 8.0),3,null,1));
        g.connect(1,2,2);
        g.connect(2,3,4);

        String filename = "temp_graph_save.bin";

        // save to file
        GASave.save(filename);
        Graph_Algo GAInit = new Graph_Algo();
        GAInit.init(filename);

        assertEquals(3, GAInit.getGraph().getV().size());
        assertEquals(1, GAInit.getGraph().getE(1).size());
        assertEquals(GAInit.getGraph().getEdge(2, 3), GASave.getGraph().getEdge(2, 3));
        assertEquals(GAInit.getGraph().getNode(1), GASave.getGraph().getNode(1));
    }

    @org.junit.jupiter.api.Test
    void isConnectedTest() {
        Graph_Algo GA = new Graph_Algo();
        DGraph g = new DGraph();
        GA.init(g);

        g.addNode(new Node(1,null,3,null,0));
        g.addNode(new Node(2,null,1,null,4));
        g.addNode(new Node(3,null,3,null,1));
        g.connect(1,2,2);
        g.connect(2,3,4);

        assertFalse(GA.isConnected());

        g.connect(2,1,4);
        assertFalse(GA.isConnected());

        g.connect(3, 1, 2);
        assertTrue(GA.isConnected());

        g.removeEdge(2, 1);
        assertTrue(GA.isConnected());

        g.removeNode(2);
        assertFalse(GA.isConnected());
    }
    @org.junit.jupiter.api.Test
    void drawGraph() {
        Graph_Algo GA = new Graph_Algo();
        DGraph g = new DGraph();
        GA.init(g);

        g.addNode(new Node(1,new Point3D(50, 20),3,null,0));
        g.addNode(new Node(2,new Point3D(3.5, 4),1,null,4));
        g.addNode(new Node(3,new Point3D(7, -2),3,null,1));
        g.addNode(new Node(4,new Point3D(0, 0),9,null,2));
        g.addNode(new Node(5,new Point3D(2, -3),8,null,3));
        g.connect(1,4,1);
        g.connect(4,5,12);
        g.connect(1,2,2);
        g.connect(2,3,4);
        g.connect(3,5,11);
        g.connect(1,4,7.6);
        GA.drawGraph();

    }
}
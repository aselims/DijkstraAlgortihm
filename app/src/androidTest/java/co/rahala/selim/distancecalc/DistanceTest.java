package co.rahala.selim.distancecalc;

import android.test.InstrumentationTestCase;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import co.rahala.selim.distancecalc.models.Edge;
import co.rahala.selim.distancecalc.models.Graph;
import co.rahala.selim.distancecalc.models.Vertex;

/**
 * Created by aselims on 30/06/15.
 */
public class DistanceTest extends InstrumentationTestCase {

    private static final String TAG = DistanceTest.class.getSimpleName();
    private List<Vertex> Vertexs;
    private List<Edge> edges;
    Graph graph;
    DijkstraAlgorithm dijkstra;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        Vertexs = new ArrayList<>();
        edges = new ArrayList<>();

        addVEs();

        graph = new Graph(Vertexs, edges);
        dijkstra = new DijkstraAlgorithm(graph);


    }


    private void addVEs() {
        //AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
        addVE("E1", new Vertex("A", "A"), new Vertex("B", "B"), 5);
        addVE("E2", new Vertex("B", "B"), new Vertex("C", "C"), 4);
        addVE("E3", new Vertex("C", "C"), new Vertex("D", "D"), 8);
        addVE("E4", new Vertex("D", "D"), new Vertex("C", "C"), 8);
        addVE("E5", new Vertex("D", "D"), new Vertex("E", "E"), 6);
        addVE("E5", new Vertex("A", "A"), new Vertex("D", "D"), 5);
        addVE("E5", new Vertex("C", "C"), new Vertex("E", "E"), 2);
        addVE("E5", new Vertex("E", "E"), new Vertex("B", "B"), 3);
        addVE("E5", new Vertex("A", "A"), new Vertex("E", "E"), 7);


    }

    private void addVE(String laneId, Vertex src, Vertex dest,
                       int duration) {


        Vertexs.add(src);
        Vertexs.add(dest);

        Edge lane = new Edge(laneId, src, dest, duration);
        edges.add(lane);
    }


    public void testExcuteAD() {
        Vertex rSrc = null;
        Vertex rDest = null;
        for (Vertex vertex : Vertexs) {
            if (vertex.getName().equals("A")) {
                rSrc = vertex;
            }
            if (vertex.getName().equals("D")) {
                rDest = vertex;
            }
        }
        dijkstra.execute(rSrc);
        LinkedList<Vertex> path = dijkstra.getPath(rDest);
        if (path == null) {
            Log.d(TAG, "null path");
        }

        if (path.size() < 0) {
            Log.d(TAG, "path cannot be negative");
        }

        double min = 0;
        for (Vertex vertex1 : path) {
            System.out.println(vertex1 + " - " + vertex1.getMinDistance());
            min =vertex1.getMinDistance();
        }
        assertEquals(5.0, min);
    }

    public void  testDistanceABC(){
        String[] vs = {"A", "B", "C"};
        int[] dis = new int[vs.length];
        Vertex[] vertexes = new Vertex[vs.length];
        Vertex start = new Vertex(vs[0], vs[0]);
        Vertex previous = null;
        int i = 0;
        while (i <= vs.length-1){
            for(Edge edge : edges){
                if(edge.getSource().getName().equals(vs[i])){
                    previous = edge.getSource();
                    if(edge.getDestination().getName().equals(vs[i+1])){
                        dis[i] = edge.getWeight();
                        i++;
                    }
                }

            }

        }
        int sum =0;
        for (int j = 0; j <dis.length-1 ; j++) {
            sum += dis[j];
            Log.d(TAG, "dis[j]" + String.valueOf(dis[j])   );

        }
        Log.d(TAG, "Sum = " + String.valueOf(sum));


    }

    /*
    * int i =0;
        for (Vertex vertex :Vertexs){
            if(vertex.getName().equals(vs[i]))
            vertexes[i] = vertex;
            i++;
        }
        dijkstra.execute(vertexes[0]);
        LinkedList<Vertex> path = dijkstra.getPath(vertexes[vertexes.length-1]);

        //path.get()

        for(Vertex vertex : vertexes){

        }
*/

    public void testExcuteAC() {
        Vertex rSrc = null;
        Vertex rDest = null;
        for (Vertex vertex : Vertexs) {
            if (vertex.getName().equals("A")) {
                rSrc = vertex;
            }
            if (vertex.getName().equals("C")) {
                rDest = vertex;
            }
        }
        dijkstra.execute(rSrc);
        LinkedList<Vertex> path = dijkstra.getPath(rDest);
        if (path == null) {
            Log.d(TAG, "null path");
        }

        if (path.size() < 0) {
            Log.d(TAG, "path cannot be negative");
        }

        double min = 0;
        for (Vertex vertex1 : path) {
            System.out.println(vertex1 + " - " + vertex1.getMinDistance());
            min =vertex1.getMinDistance();
        }
        assertEquals(9.0, min);
    }


    
    /*
    Vertex rSrc = src;
        Vertex rDest = dest;
    for(Vertex vertex : Vertexs){
            if(src.getName().equals(vertex.getName())){
                rSrc = vertex;
            }
            if(dest.getName().equals(vertex.getName())){
                rDest = vertex;
            }
        }*/

}

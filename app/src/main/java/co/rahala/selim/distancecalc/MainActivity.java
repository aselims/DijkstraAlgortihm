package co.rahala.selim.distancecalc;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import co.rahala.selim.distancecalc.models.Edge;
import co.rahala.selim.distancecalc.models.Graph;
import co.rahala.selim.distancecalc.models.Vertex;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "xxxx" ;
    private List<Vertex> Vertexs;
    private List<Edge> edges;
    Graph graph;
    DijkstraAlgorithm dijkstra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Vertexs = new ArrayList<>();
        edges = new ArrayList<>();

        addVEs();

        graph = new Graph(Vertexs, edges);
        dijkstra = new DijkstraAlgorithm(graph);
        testExcute();

        /*The distance of the route A-D-C.
The distance of the route A-E-B-C-D.
The distance of the route A-E-D.*/

        String[] vs1 = {"A", "B", "C"};
        String[] vs2 = {"A", "D"};
        String[] vs3 = {"A", "D", "C"};
        String[] vs4 = {"A", "E", "B", "C", "D"};
        String[] vs5 = {"A", "E", "D"};

        int[] dis = new int[5];
        String vs;

           // vs= "vs".concat(String.valueOf(i));
            dis[0]  = getDistance(vs1);
        dis[1]  = getDistance(vs2);
        dis[2]  = getDistance(vs3);
        dis[3]  = getDistance(vs4);
        dis[4]  = getDistance(vs5);


        Log.d(TAG, "dis0 = " + dis[0]);
        Log.d(TAG, "dis1 = " + dis[1]);
        Log.d(TAG, "dis2 = " + dis[2]);
        Log.d(TAG, "dis3 = " + dis[3]);
        Log.d(TAG, "dis4 = " + dis[4]);







    }

    private void addVEs() {
        //AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
        addVE("E1", new Vertex("A","A"), new Vertex("B","B"), 5);
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

        Edge lane = new Edge(laneId,src, dest, duration);
        edges.add(lane);
    }





    public void testExcute(){


        // Lets check from location Loc_1 to Loc_10

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


        for (Vertex vertex1 : path) {
            System.out.println(vertex1 + " - " + vertex1.getMinDistance());
        }

        // assertEquals(5,);

    }


    public int  getDistance(String[] vs){
        final int len = vs.length-1;

        int[] ints = new int[len];

        //Vertex previous = null;
        int i = 0;
        boolean stop = false;
        while (i < len || !stop){
            for(Edge edge : edges){
                if(edge.getSource().getName().equals(vs[i])){
                   // previous = edge.getSource();
                    if(edge.getDestination().getName().equals(vs[i+1])){
                        ints[i] = edge.getWeight();
                        i++;
                        break;
                    }
                    else{
                        Log.d(TAG, "There is no such a route!");
                        stop = true;

                        break;
                    }
                }

            }

        }
        int sum =0;
        for (int j = 0; j <= ints.length-1 ; j++) {
            sum += ints[j];
            Log.d(TAG, "dis[" + j + "] = " + String.valueOf(ints[j])   );

        }
        Log.d(TAG, "Sum = " + String.valueOf(sum));

        return sum;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

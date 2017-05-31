

import java.io.*;
import java.util.*;
public class GraphColouring
{
    private static int[] colour;
    private static boolean printhappened;
    public static void main (String[] args)
    {
        printhappened = false;
        BufferedReader stdin;
        try {
            stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        stdin = null;
        ArrayList<String> inputList = null;
        try {
            inputList = (ArrayList<String>) readInput(stdin);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        int v = Integer.parseInt(inputList.get(0));
        int e = Integer.parseInt(inputList.get(1));
        int m = Integer.parseInt(inputList.get(2));

        v = 2;
        e = 1;
        m = 2;
        colour = new int[m];

        for(int i = 0; i < colour.length; i++) {
            colour[i] = -27;
        }

        GraphColouring gc = new GraphColouring();

        int[][] graph = new int[v][v];
        for (int i = 0; i < v; i++)
            for (int j = 0; j < v; j++) {
                    if(isInside(inputList,(i+1),(j+1))) {
                        graph[i][j] = 1;
                    }
                    else
                        graph[i][j] = 0;
            }

        printGraph(graph);
       // gc.graphColor(graph, c);
        colourIt(graph,m,0);

    }

    public static void colourIt(int[][] graph,int m,int giveZero) {

        if(giveZero == m) {
            if(!printhappened)
            {
                printTime(graph.length);
                printhappened = true;
            }
            return;
        }

        for(int cindex = 0; cindex < m; cindex++) {
                if(isAppropiate(graph,cindex,giveZero)) {
                        colour[giveZero] = cindex;
                        colourIt(graph,m,giveZero + 1);
                        colour[giveZero] = -27;
                }

        }

    }

    private static boolean isAppropiate(int[][] graph, int cindex, int giveZero) {

            for(int r = 0; r < graph.length; r++) {
                if(cindex == colour[r]) {
                    if(graph[giveZero][r] == 1)
                        return false;
                }
            }
            return true;

    }

    private static void printTime(int v) {
        for (int i = 0; i < v; i++)
            System.out.print((colour[i]+1) +" ");
        System.out.println();
    }

    public static void printGraph(int[][] graph) {
        for(int i = 0; i < graph.length; i++) {
            for(int j = 0; j < graph[i].length; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static ArrayList<String> readInput(BufferedReader input) throws IOException {
        ArrayList<String> list = new ArrayList<String>();

     /*   String s = input.readLine();
        list.add(s);
        String h = input.readLine();
        list.add(h);
        String g = input.readLine();
        list.add(g);

        for (int b = 0; b < Integer.parseInt(h); b++) {
            list.add(input.readLine());
        }
*/
       list.add(2 + "");
        list.add(1 + "");
        list.add(2 + "");
        list.add(1 + " " + 2);
        return list;
    }

    public static boolean isInside(ArrayList<String> str, int i, int j) {
        for(int r = 0; r < str.size();r++) {
            if(("" + i + " " + j).equals(str.get(r)) ||
                    ("" + j + " " + i).equals(str.get(r))) {
                return true;
            }
        }
        return false;
    }
}
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    final static int NO_CONNECTION = Integer.MAX_VALUE/2;// divide by 2 to avoid integer overflow
    final static int DEFAULT_CONNECTION= -1;
    static int p[][];//position array
    static int w[][];//graph connections distance
    static int d[][];//changed connections distance
    static int nSize=5;//size of the array
    public static void main(String[] args) {
	// write your code here
        w= new int[][]{
                {0, 1, NO_CONNECTION, 1, 5},
                {9, 0, 3, 2, 14},
                {NO_CONNECTION, NO_CONNECTION, 0, 4, NO_CONNECTION},
                {NO_CONNECTION, NO_CONNECTION, 2, 0, 3},
                {3, 4, NO_CONNECTION, 4, 0}
        };
        for(int i=0; i<w.length;i++){
            System.out.println(Arrays.toString(w[i]));
        }
        floyd();
        System.out.println("ran floyds w array");
        for(int i=0; i<w.length;i++){
            System.out.println(Arrays.toString(w[i]));
        }
        System.out.println("d array");
        for(int i=0; i<d.length;i++){
            System.out.println(Arrays.toString(d[i]));
        }
        System.out.println("p array");
        for(int i=0; i<d.length;i++){
            System.out.println(Arrays.toString(p[i]));
        }

        System.out.println("what vertices would you like to find path of, -1 to quit");
        Scanner kboard= new Scanner(System.in);
        int input= 0;
        while (input!=-1){
            input=kboard.nextInt();

            int to=kboard.nextInt();

            int totalWeight=findPath(input,to,0);
            System.out.println("the total weight is = "+totalWeight);
        }


    }

    public static void floyd(){

        p= new int[w.length][w.length];
        //adding connection algorithm
        for(int i=0;i<nSize;i++){
            for(int j=0;j<nSize;j++){
                p[i][j]=DEFAULT_CONNECTION;
            }
        }

        //copying over the w array to see changes
        d= new int[w.length][w.length];
        for (int i=0; i < nSize; i++){
            System.arraycopy(w[i],0,d[i],0,nSize);
        }


        for (int k=0; k < nSize; k++){
            for (int i=0; i < nSize; i++){
                for (int j=0; j < nSize; j++){
                    if ((d[i][k] + d[k][j]) < d[i][j]){
                        p[i][j] = k;
                        d[i][j] = d[i][k] + d[k][j];
                    }
                }
            }
        }
    }

    public static int findPath(int start, int finish,int weight){




        if(p[start][finish]!=-1){
            weight=findPath(start, p[start][finish],weight);
            System.out.println("vertex " + p[start][finish]+" is the intermediary vertex");
            weight=findPath(p[start][finish],finish,weight);
        }
        else{

            System.out.println(start+ " to "+ finish);
            weight+=w[start][finish];
            //System.out.println("the weight is "+weight);
        }
        return weight;

    }
}

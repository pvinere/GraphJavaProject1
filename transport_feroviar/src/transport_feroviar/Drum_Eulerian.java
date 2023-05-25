package transport_feroviar;

import java.util.*;

public class Drum_Eulerian {

    private static List<Integer> drum;
    private static int[][] matrice_adiacenta;
    
    //primeste o matrice g si returneaza o lista care reprezinta drumul eulerian
    public static List<Integer> gasesteEulerian(int[][] g) {
        
    	//initalizare
        matrice_adiacenta = g;
        drum = new ArrayList<Integer>();
        int start = gaseste_nod_start();
        dfs(start);
        return drum;
    }
    //parcurge toate nodurile, iar pentru fiecare nod se calculeaza gradul sa se verifice daca au grad par
    private static int gaseste_nod_start() {
        int start = 0;
        for (int i = 0; i < matrice_adiacenta.length; i++) {
        	//daca nu se gaseste nod de start cu grad impar, primul nod va fii considerat nod de start
            int grad = 0;
            //calcul grad noduri
            for (int j = 0; j < matrice_adiacenta.length; j++) {
                grad += matrice_adiacenta[i][j];
            }
            //verificare grad impar, daca da el este nodul de start
            if (grad % 2 == 1) {
                start = i;
                break;
            }
        }
        return start;
    }
    //parcurge recursiv toate nodurile pana cand nu mai existe nicio muchie nevizitata
    //se elimina nodurile parcurse si se adauga nodurile in lista 'drum'
    private static void dfs(int u) {
        for (int v = 0; v < matrice_adiacenta.length; v++) {
            if (matrice_adiacenta[u][v] > 0) {
                matrice_adiacenta[u][v]--;
                matrice_adiacenta[v][u]--;
                dfs(v);
            }
        }
        
        drum.add(u);
    }

}


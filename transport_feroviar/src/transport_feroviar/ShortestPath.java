package transport_feroviar;

public class ShortestPath {
	
	public static int[] dijkstra(int[][] matrice_adiacenta, int start) {
	    int nr_orase = matrice_adiacenta.length;
	    
	    //vector care stocheaza distanta minima de la nodul de start la fiecare nod in graf
	    int[] statii_de_tren = new int[nr_orase];
	    //vector care mentine o lista de noduri vizitate
	    boolean[] vizitate = new boolean[nr_orase];
	    
	    //toate elementele din statii de tren sunt initializate cu o valoare maxima pentru a
	    //se asigura ca orice valoare este mai mica decat maximul
	    for (int i = 0; i < nr_orase; i++) {
	        statii_de_tren[i] = Integer.MAX_VALUE;
	    }

	    //startul este setat la 0
	    statii_de_tren[start] = 0;
	    
	    //fiecare nod este vizitat o singura data fara nodul de start
	    for (int i = 0; i < nr_orase - 1; i++) {
	        int statia = -1;
	        //cauta cea mai mica distanta fata de nodul de start care nu a fost vizitat inca 
	        for (int j = 0; j < nr_orase; j++) {
	            if (!vizitate[j] && (statia == -1 || statii_de_tren[j] < statii_de_tren[statia])) {
	                statia = j;
	            }
	           
	        }
	        //seteaza toate orasele vizitate-TRUE
	        vizitate[statia] = true;
	        
	        //parcurge toti vecinii nodului vizitat
	        for (int j = 0; j < nr_orase; j++) {
	            int distanta = matrice_adiacenta[statia][j];
	            
	            //daca aceasta distanta este mai mica decat distanta minima care este curent stocata in statii_de_tren
	            //aceasta este actualizata cu cu noua distanta minima
	            if (distanta > 0 && statii_de_tren[statia] + distanta < statii_de_tren[j]) {
	            	
	            	//aduna distanta de la nodul vizitat la vecinul curent 
	                statii_de_tren[j] = statii_de_tren[statia] + distanta;
	            }
	            
	        }
	    }

	    return statii_de_tren;
	}

}

package transport_feroviar;

public class verificareConectivitate {
	
	
	public static void orase_conectate(int[][] matrice_adiacenta) {
		System.out.println("Orase care se gasesc in reteaua principala: ");
		
		int n = matrice_adiacenta.length; // numarul de orase
	    boolean[] vizitat = new boolean[n];
	    //nod de pornire pentru functia dfs
	    int nod_start = 0;
	    
	    dfs(matrice_adiacenta, nod_start, vizitat);
	    // daca toate nodurile sunt vizitate, afiseaza orasele care sunt in graful conex
	    for (int i = 0; i < n; i++) {
	        if (vizitat[i]) {
	           System.out.print(Main.getKey(Main.orase,i)+ " ");
	        }
	    }

	}
	
	public static void orase_neconectate(int[][] matrice_adiacenta) {
		System.out.println("Orase care nu sunt conectate la reteaua principala feroviara: ");
		
		int n = matrice_adiacenta.length; // numarul de orase
	    boolean[] vizitat = new boolean[n];
	  //nod de pornire pentru functia dfs
	    int nod_start = 0;
	  
	    dfs(matrice_adiacenta, nod_start, vizitat);
	    // daca nodurile nu sunt vizitate, inseamna ca nu apartin de graful conex 
	    for (int i = 0; i < n; i++) {
	        if (!vizitat[i]) {
	        	System.out.print(Main.getKey(Main.orase,i)+ " ");
	        }
	    }

	}
	
	//Depth-First Search
	public static void dfs(int[][] matrice_adiacenta, int nod_curent, boolean[] vizitat) {
	    //incepe cu un nod curent si este marcat ca true pentru nu a fii vizitat din nou
		vizitat[nod_curent] = true;
	    
	    
	    //verifica daca este 1 intre nodul curent si nodul respectiv(i) si daca nu a fost inca vizitat
	    for (int i = 0; i < matrice_adiacenta.length; i++) {
	        if (matrice_adiacenta[nod_curent][i] == 1 && !vizitat[i]) {
	        	//apel recursiv pentru a parcure intregul graf prin vizitarea nodurilor adiacente
	            dfs(matrice_adiacenta, i, vizitat);
	        }
	    }

	}
	

}

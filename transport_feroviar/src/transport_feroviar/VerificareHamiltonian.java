package transport_feroviar;

import java.util.List;

public class VerificareHamiltonian {
	public static boolean esteHamiltonian(int[][] matrice_adiacenta, List<Integer> noduri_vizitate, int nod_curent, int nod_start) {
	    // Daca am vizitat toate nodurile si exista o muchie intre ultimul nod vizitat si nodul initial, atunci graful este hamiltonian
	    if (noduri_vizitate.size() == matrice_adiacenta.length && matrice_adiacenta[nod_curent][nod_start] == 1) {
	        return true;
	    }
	    
	    // Parcurgem vecinii nodului curent
	    for (int i = 0; i < matrice_adiacenta.length; i++) {
	        if (matrice_adiacenta[nod_curent][i] == 1 && !noduri_vizitate.contains(i)) {
	            noduri_vizitate.add(i);
	            
	            // Apelam recursiv functia pentru vecinul curent
	            if (esteHamiltonian(matrice_adiacenta, noduri_vizitate, i, nod_start)) {
	                return true;
	            }
	            
	            // Stergem nodul vizitat pentru a putea explora alte posibilitati
	            noduri_vizitate.remove(noduri_vizitate.size() - 1);
	        }
	    }
	    
	    // Daca am ajuns aici, inseamna ca nu am gasit un circuit hamiltonian
	    return false;
	}

}

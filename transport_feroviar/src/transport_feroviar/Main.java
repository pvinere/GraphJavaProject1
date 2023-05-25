package transport_feroviar;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class Main {

	public static HashMap<String,Integer> orase = new HashMap<String,Integer>();
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		int numar_oras=0;
		
		//preluare date din fisier
		
		//File text = new File("src/transport_feroviar/destinatii.txt");
		File text = new File("src/transport_feroviar/destinatii_conex.txt");
		
		
		Scanner fisier = new Scanner(text);
		ArrayList<String> input = new ArrayList<String>();
		
		//citeste din fisier
		while(fisier.hasNext()){
		    String linie = fisier.nextLine();
		    //adauga in ArrayList input orasul
		    input.add(linie);
		    //adaugam orasele intr-un array de stringuri fara "-"
		    String[] split = linie.split("-");
		    //parcurge doar primele doua cuvinte(al 3-lea este numarul de km dintre orase)
		    for(int i = 0 ; i < 2; i++){
		    	//verifica daca orasul a fost introdus in HashMap
		        if(!orase.containsKey(split[i])){
		        	//daca nu se gaseste in HashMap atuci se adauga cu un numar oras care se incrementeaza de la 0
		            orase.put(split[i], numar_oras);
		            numar_oras++;
		        }
		    }
		}
		

		fisier.close();
		
		//Matrice adiacenta
		int[][] matrice_adiacenta = new int [numar_oras][numar_oras] ;
		int[][] matrice_adiacenta_km = new int [numar_oras][numar_oras];
		
		
		for(String a: input)
		{
			String[] split = a.split("-");
			//la intersectia dintre randul si coloana corespunzatoare se seteaza 1 pentru a indica ca exista o muchie
			matrice_adiacenta[orase.get(split[0])][orase.get(split[1])] = 1;
			matrice_adiacenta[orase.get(split[1])][orase.get(split[0])] = 1;
		}
		
		for(String a: input)
		{
			//la fel ca sus, doar ca se folosete split[2] care este nr de km pe muchie si se adauga la intersectia coloanei si randului
			String[] split = a.split("-");
			matrice_adiacenta_km[orase.get(split[0])][orase.get(split[1])] = Integer.parseInt(split[2]);
			matrice_adiacenta_km[orase.get(split[1])][orase.get(split[0])] = Integer.parseInt(split[2]);
		}
		
		//meniu functii
		System.out.println("MENIU");
		System.out.println("1. Afiseaza toate orasele care au statie de tren.");
		System.out.println("2. Linile directe dintre orase(Lista muchiilor)");
		System.out.println("3. Tabel legaturi orase.(Matricea de adiacenta.)");
		System.out.println("4. Orasele si legaturi directe.(Lista de adiacenta)");
		System.out.println("5. Cele mai scurte legaturi dintre orase (Shortest Path)");
		System.out.println("6. Verificare daca exista drum intre toate statiile din tara(Verificare Conectivitate)");
		System.out.println("7. Traseu pentru trenuri de marfuri(Hamiltonian)");
		System.out.println("8. Traseu turistic,mocanita (Eulerian)");
		System.out.println("Inchide");
		
		System.out.print("Selecteaza varianta:");
		Scanner varianta = new Scanner(System.in);
		int chose = varianta.nextInt();
		
		
		switch(chose){
		
		//afiseaza toate orasele
		case 1:
			System.out.println();
			//parcurge HashMap-ul
			for(Map.Entry i:orase.entrySet())
			{
				//Afiseaza cu ajutorul functie getKey() orasele
				System.out.print(i.getKey()+ " ");
			}
			break;
			
		case 2:
			System.out.println();
			//afiseaza lista muchilor (i-a din fisier pana la ultimul "-" pentru a nu lua si numarul de km
			for(String a : input)
			{
				System.out.println(a.substring(0,a.lastIndexOf("-")));
			}
			
			break;
			
		case 3:
			//afisare matrice de adiacenta
			System.out.println();
			
			for(int i = 0;i<numar_oras;i++)
			{
				System.out.print(getKey(orase,i) + "\s");
			}
			
			System.out.println();
			System.out.println();
			
			for(int i = 0;i<numar_oras;i++)
			{
				System.out.print(getKey(orase,i) + "\t\t");
				
				for(int j=0;j<numar_oras;j++)
				{
					
					System.out.print(matrice_adiacenta[i][j]+"");
				}
					
					System.out.println();
			}
			
			
			break;
			
			//lista de adiacenta
		case 4:
			//parcurge toate orasele
			for(int i = 0; i < numar_oras; i++){
				//afisare orasul principal
			    System.out.print(getKey(orase,i) + ": ");
			    //parcurge orasele care au legatura cu orasul principal (contine 1 in matricea de adiacenta)
			    for(int j = 0; j < numar_oras; j++){
			        if(matrice_adiacenta[j][i] == 1){
			        	
			        	//afisare legaturi cu orasul principal
			            System.out.print(getKey(orase,j) + ", ");
			        }    
			    }
			    System.out.println();
			}
			break;
			
		case 5:
			System.out.println("");
			System.out.println("Ce oras: ");
			
			String varianta_oras = varianta.nextLine();
			//pentru a nu citit tasta enter
			varianta_oras = varianta.nextLine();
			
			
			int[] distante = ShortestPath.dijkstra(matrice_adiacenta, orase.get(varianta_oras));
			int[] distante_km = ShortestPath.dijkstra(matrice_adiacenta_km, orase.get(varianta_oras));
			    System.out.println("Prin cate orase trebuie sa treci ca sa ajungi de la orasul " + varianta_oras + " la: ");
		
			 
			    for (int i = 0; i < numar_oras; i++) {
			    	if(!varianta_oras.equals(getKey(orase,i)))
			    	{
			    		System.out.println(getKey(orase, i) + ": " + distante[i] + " orase" + " si " + distante_km[i] + " km");
			    	}
			    }
			break;
			
		case 6:
			System.out.println(" ");
			verificareConectivitate.orase_conectate(matrice_adiacenta);
			System.out.println(" ");
			System.out.println(" ");
			verificareConectivitate.orase_neconectate(matrice_adiacenta);
			
			break;
			
		case 7:
			
			List<Integer> noduri_vizitate = new ArrayList<Integer>();
			noduri_vizitate.add(0); // Adaugam nodul initial
			if (VerificareHamiltonian.esteHamiltonian(matrice_adiacenta, noduri_vizitate, 0, 0)) {
			    System.out.println("Traseul este optim pentru trenuri de marfuri(este hamiltonian).");
			} else {
			    System.out.println("Traseul nu este optim pentru trenuri de marfuri(nu este hamiltonian).");
			}
			
			
			break;
			
			
		case 8:
			//lista de noduri din drumul eulerian gasite de functia gasesteEulerian()
			List<Integer> drum_eulerian = Drum_Eulerian.gasesteEulerian(matrice_adiacenta);
			//verfica daca numarul de noduri din drumul eulerian este egal cu numarul de noduri din graf + 1
			//daca nu, nu exista drum eulerian
		    if (drum_eulerian.size() != input.size() + 1) {
		        System.out.println("Graficul nu este eulerian");
		    } else {
		        System.out.println("Drumul eulerian este:");
		        
		        //afisare nodurile din drumul eulerian
		        for (int i = drum_eulerian.size() - 1; i >= 0; i--) {
		            System.out.print(getKey(orase, drum_eulerian.get(i)));
		            //cat timp nu este ultimul din lista, ca sa nu afiseze la final "->"
		            if (i > 0) {
		                System.out.print(" -> ");
		            }
		        }
		        System.out.println();
		   
		    }
		
			break;
			
		default:
			System.out.println("Ai introdus o varianta gresita");
		
		}
		
		varianta.close();
		
	}
	
	//metoda care face o cautare in hashmap in functie de valoare si nu dupa cheie
	public static String getKey(HashMap<String, Integer> map, Integer value){
	    for(Map.Entry<String , Integer> entry : map.entrySet()){
	        if(entry.getValue() == value){
	            return entry.getKey();
	        }
	        
	    }
	    return null;
	}
	
	

}

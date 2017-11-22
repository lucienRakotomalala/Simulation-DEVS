import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gen myGen = new Gen("GEN");
		Buf myBuf = new Buf("BUF");
		Proc myProc = new Proc("PROC");
		
		
		ArrayList<AtomicBehaviour> atomicArray = new ArrayList<AtomicBehaviour>(); 
		double currentTime = 0;
		double finalTime = 5;
		atomicArray.add(myBuf);
		atomicArray.add(myProc);
		atomicArray.add(myGen);
		/*  Jusqu'a la fin de l'éxécution  */
		while(currentTime < finalTime) {
			ArrayList<AtomicBehaviour> imminentComponent = new ArrayList<AtomicBehaviour>();
			ArrayList<String> outputs = new ArrayList<String>();
			double tmin = Double.POSITIVE_INFINITY;		// tmin a l'infini
			
			System.out.println(atomicArray.size());
			
			/*  Parcours de Tous Les eléments*/
			for(AtomicBehaviour elem : atomicArray) {	// Pour tous les éléments dela simulation
				System.out.println(elem.getName());
				if(elem.getTa()<tmin) 
				{												// Si le Ta de l'élément est inférieur a tmin,
						tmin = elem.getTa()	;							// Alors je met a jour tmin, et je regarde 
						imminentComponent.clear();												// si des composants imminents ont le meme tmin
						imminentComponent.add(elem)	;																	// Alors j'ajoute l'élément aux composant iminents
						
							
				}
				else if(elem.getTa()==tmin) {
					imminentComponent.add(elem);
				}
				
			}
			
			/* Exécution sortie des éléments imminents  */
			for(AtomicBehaviour i : imminentComponent) {
				outputs = i.lambda();
			}
			/* Boucle pour delta --> TRANSITIONS */
			for(AtomicBehaviour b : atomicArray) 		
			{
				ArrayList<String> c = new ArrayList<String>(b.getInputs()); // copies des entrées de b dans c
				c.retainAll(outputs); // garde dans c que les elements présent dans outputs
				if(imminentComponent.contains(b))/*imminent*/
				{
					if( c.isEmpty()) /*si vide : pas d'entrées*/  /*pas d'entrée*/
					{
						b.delta_int();	
					}
					else /* entrée*/
					{
						b.delta_con(outputs);
					}
				}
				else if(!c.isEmpty() )/* pas imminent && entrée*/ 
				{
					b.delta_ext(outputs);	
				}
				
			}
			
			
			System.out.println("tmin = "+ tmin);
			System.out.println(imminentComponent);
			currentTime += tmin;
		}
	}

}

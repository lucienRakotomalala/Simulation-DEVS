import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		Gen myGen = new Gen("GEN");
		Buf myBuf = new Buf("BUF");
		Proc myProc = new Proc("PROC");
		
		
		ArrayList<AtomicBehaviour> atomicArray = new ArrayList<AtomicBehaviour>(); 
		double currentTime = 0;
		double finalTime = 5;
		atomicArray.add(myBuf);
		atomicArray.add(myProc);
		atomicArray.add(myGen);
		/* Init tr all components   */
		for(AtomicBehaviour e : atomicArray)
		{
			e.setTr(e.getTa());
		}
		/*  Jusqu'a la fin de l'éxécution  */
		while(currentTime < finalTime) {
			System.out.println("Current time = "+ currentTime);
			ArrayList<AtomicBehaviour> imminentComponent = new ArrayList<AtomicBehaviour>();
			ArrayList<String> outputs = new ArrayList<String>();
			double tmin = Double.POSITIVE_INFINITY;		// tmin a l'infini
			
			
			
			/*  Parcours de Tous Les eléments*/
			for(AtomicBehaviour elem : atomicArray) {	// Pour tous les éléments dela simulation
				
				if(elem.getTr()<tmin) 
				{												// Si le Ta de l'élément est inférieur a tmin,
						tmin = elem.getTa()	;							// Alors je met a jour tmin, et je regarde 
						imminentComponent.clear();												// si des composants imminents ont le meme tmin
						imminentComponent.add(elem)	;																	// Alors j'ajoute l'élément aux composant iminents
						
							
				}
				else if(elem.getTa()==tmin) {
					imminentComponent.add(elem);
				}
				
			}
			System.out.println("Liste des éléments imminents : ");
			/* Exécution sortie des éléments imminents  */
			for(AtomicBehaviour i : imminentComponent) 
			{
				System.out.println(i.getName() + " avec lambda(S) = "+ i.lambda());
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
				// mise a jour du tr de tout les élements 
				b.setTr(currentTime);
				
			}
			
			currentTime += tmin;
		}
	}

}

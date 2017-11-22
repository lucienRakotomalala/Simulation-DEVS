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
		/*  Jusqu'a la fin de l'�x�cution  */
		while(currentTime < finalTime) {
			System.out.println("Current time = "+ currentTime);
			ArrayList<AtomicBehaviour> imminentComponent = new ArrayList<AtomicBehaviour>();
			ArrayList<String> outputs = new ArrayList<String>();
			double tmin = Double.POSITIVE_INFINITY;		// tmin a l'infini
			
			
			
			/*  Parcours de Tous Les el�ments*/
			for(AtomicBehaviour elem : atomicArray) {	// Pour tous les �l�ments dela simulation
				
				if(elem.getTr()<tmin) 
				{												// Si le Ta de l'�l�ment est inf�rieur a tmin,
						tmin = elem.getTa()	;							// Alors je met a jour tmin, et je regarde 
						imminentComponent.clear();												// si des composants imminents ont le meme tmin
						imminentComponent.add(elem)	;																	// Alors j'ajoute l'�l�ment aux composant iminents
						
							
				}
				else if(elem.getTa()==tmin) {
					imminentComponent.add(elem);
				}
				
			}
			System.out.println("Liste des �l�ments imminents : ");
			/* Ex�cution sortie des �l�ments imminents  */
			for(AtomicBehaviour i : imminentComponent) 
			{
				System.out.println(i.getName() + " avec lambda(S) = "+ i.lambda());
				outputs = i.lambda();
			}
			
			/* Boucle pour delta --> TRANSITIONS */
			for(AtomicBehaviour b : atomicArray) 		
			{
				ArrayList<String> c = new ArrayList<String>(b.getInputs()); // copies des entr�es de b dans c
				c.retainAll(outputs); // garde dans c que les elements pr�sent dans outputs
				if(imminentComponent.contains(b))/*imminent*/
				{
					if( c.isEmpty()) /*si vide : pas d'entr�es*/  /*pas d'entr�e*/
					{
						b.delta_int();	
					}
					else /* entr�e*/
					{
						b.delta_con(outputs);
					}
				}
				else if(!c.isEmpty() )/* pas imminent && entr�e*/ 
				{
					b.delta_ext(outputs);	
				}
				// mise a jour du tr de tout les �lements 
				b.setTr(currentTime);
				
			}
			
			currentTime += tmin;
		}
	}

}

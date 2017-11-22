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
		/*  Jusqu'a la fin de l'�x�cution  */
		while(currentTime < finalTime) {
			ArrayList<AtomicBehaviour> imminentComponent = new ArrayList<AtomicBehaviour>();
			ArrayList<String> outputs = new ArrayList<String>();
			double tmin = Double.POSITIVE_INFINITY;		// tmin a l'infini
			
			System.out.println(atomicArray.size());
			
			/*  Parcours de Tous Les el�ments*/
			for(AtomicBehaviour elem : atomicArray) {	// Pour tous les �l�ments dela simulation
				System.out.println(elem.getName());
				if(elem.getTa()<tmin) 
				{												// Si le Ta de l'�l�ment est inf�rieur a tmin,
						tmin = elem.getTa()	;							// Alors je met a jour tmin, et je regarde 
						imminentComponent.clear();												// si des composants imminents ont le meme tmin
						imminentComponent.add(elem)	;																	// Alors j'ajoute l'�l�ment aux composant iminents
						
							
				}
				else if(elem.getTa()==tmin) {
					imminentComponent.add(elem);
				}
				
			}
			
			/* Ex�cution sortie des �l�ments imminents  */
			for(AtomicBehaviour i : imminentComponent) {
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
				
			}
			
			
			System.out.println("tmin = "+ tmin);
			System.out.println(imminentComponent);
			currentTime += tmin;
		}
	}

}

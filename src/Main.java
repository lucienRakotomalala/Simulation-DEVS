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
		while(currentTime < finalTime) {
			ArrayList<AtomicBehaviour> imminentComponent = new ArrayList<AtomicBehaviour>();
			double tmin = Double.POSITIVE_INFINITY;		// tmin a l'infini
			System.out.println(atomicArray.size());
			for(AtomicBehaviour elem : atomicArray) {	// Pour tous les éléments dela simulation
				System.out.println(elem.getName());
				if(elem.getTa()<tmin) {					// Si le Ta de l'élément est inférieur a tmin,
						tmin = elem.getTa()	;							// Alors je met a jour tmin, et je regarde 
						imminentComponent.clear();												// si des composants imminents ont le meme tmin
						imminentComponent.add(elem)	;																	// Alors j'ajoute l'élément aux composant iminents
																								// Sinon je clen la liste et ajoute l'élément	
				}
				
			}
			System.out.println("tmin = "+ tmin);
			System.out.println(imminentComponent);
			currentTime += tmin;
		}
	}

}

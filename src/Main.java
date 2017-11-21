import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gen myGen = new Gen();
		Buf myBuf = new Buf();
		Proc myProc = new Proc();
		
		
		ArrayList<AtomicBehaviour> atomicArray = new ArrayList<AtomicBehaviour>(); 
		double currentTime = 0;
		atomicArray.add(1,myBuf);
		atomicArray.add(2,myProc);
		atomicArray.add(3,myGen);
		while(currentTime < atomicArray.get(1).getTa()) {
			
		}
	}

}

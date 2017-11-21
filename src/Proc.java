import java.util.ArrayList;
import java.util.HashMap;

public class Proc implements AtomicBehaviour{
	
	int current_state;
	int next_state;
	double e;
	String name;
	
	public Proc (String n) {
		this.name = n;
	}
	
	public String getName() {return this.name;}
	
	public void init() {
		current_state = 0;		
	}

	
	public void delta_int(double t){
		if(current_state == 1)
			next_state = 0;
		
	}

	
	public void delta_ext(double t, ArrayList<String> inputs){
		if(current_state == 0 && inputs.contains("req"))
			next_state = 1;
	}

	public ArrayList<String> lambda(){
		ArrayList<String> outputs = new ArrayList<>();
		HashMap<String,Boolean> boolean_outputs = new HashMap<String,Boolean>();
		if(current_state == 1){
			boolean_outputs.put("done",true);
			outputs.add("done");
		}
		return outputs;
	}

	public double getTa(){

		if(current_state == 0){
			return Double.POSITIVE_INFINITY;
		}
		else if(current_state == 1){
			return 2.0;
		}
		return -1;
	}
}
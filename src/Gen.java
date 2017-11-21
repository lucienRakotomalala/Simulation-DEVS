import java.util.ArrayList;
import java.util.HashMap;

public class Gen implements AtomicBehaviour{

	int current_state;
	int next_state;
	double e;
	String name;
	
	public Gen(String n) {
		this.name = n;
	}
	
	public String getName() {return this.name;}
	
	public void init() {
		current_state = 0;
	}

	
	public void delta_int(double t){
		if(current_state == 0 )
			next_state = 0;
	}

	
	public void delta_ext(double t, ArrayList<String> inputs){
	
	}
	
	public ArrayList<String> lambda(){
		ArrayList<String> outputs = new ArrayList<>();
		HashMap<String,Boolean> boolean_outputs = new HashMap<String,Boolean>();
		if(current_state == 0){
			boolean_outputs.put("job",true);
			outputs.add("job");
		}
		return outputs;
	}

	public double getTa(){

		if(current_state == 0){
			return 1.0;
		}
		return -1;
	}

}
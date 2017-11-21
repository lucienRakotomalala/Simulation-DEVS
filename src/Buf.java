import java.util.ArrayList;
import java.util.HashMap;

public class Buf implements AtomicBehaviour{
	
	int current_state;
	int next_state;
	double e;
	int q = 0;
	String name;
	
	public Buf(String n) {
		this.name = n;
	}
	
	public String getName() {return this.name;}
	
	public void init() {
		current_state = 0;
	}

	public void delta_int(double t){
		if(current_state == 1){
			q--;
			next_state = 2;
		}
	}

	
	public void delta_ext(double t, ArrayList<String> inputs){
		if(current_state == 0 && inputs.contains("job")){
			q++;
			next_state = 1;
		}
		else if(current_state == 1 && inputs.contains("job")){
			q++;
			next_state = 1;
		}
		else if(current_state == 2 && inputs.contains("job")){
			q++;
			next_state = 2;
		}
		else if(current_state == 2 && inputs.contains("done")){
			if(q>0)
				next_state = 1;
			if(q==0)
				next_state = 0;
		}
	}

	public ArrayList<String> lambda(){
		ArrayList<String> outputs = new ArrayList<>();
		HashMap<String,Boolean> boolean_outputs = new HashMap<String,Boolean>();
		if(current_state == 1){
			boolean_outputs.put("req",true);
			outputs.add("req");
		}
		return outputs;
	}

	public double getTa(){

		if(current_state == 0){
			return Double.POSITIVE_INFINITY;
		}
		else if(current_state == 1){
			return 0.0;
		}
		else if(current_state == 2){
			return Double.POSITIVE_INFINITY;
		}
		
		return 0;
	}
}
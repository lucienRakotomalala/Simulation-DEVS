import java.util.ArrayList;
import java.util.HashMap;

public class Buf implements AtomicBehaviour{
	
	int current_state;
	int next_state;
	double tr;
	int q = 0;
	String name;
	ArrayList<String> outputs;
	ArrayList<String> inputs;
	
	public Buf(String name){
		this.name = name;
		outputs = new ArrayList<>();
		outputs.add("req");

		inputs = new ArrayList<>();
		inputs.add("done");
		inputs.add("job");
	}
	
	public ArrayList<String> getOutputs() {
		return outputs;
	}

	public ArrayList<String> getInputs() {
		return inputs;
	}

	
	public String getName() {
		return name;
	}

	public void init() {
		current_state = 0;
	}

	public void delta_int(){
		if(current_state == 1){
			q--;
			next_state = 2;
		}
		current_state = next_state;

	}

	
	public void delta_ext(ArrayList<String> inputs){
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
		current_state = next_state;
	}
	
	public void delta_con(ArrayList<String> inputs){
		current_state = next_state;
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
	
	public double getTr(){
		return tr;
	}
	
	public void setTr(double tr){
		this.tr = tr;
	}
}
import java.util.ArrayList;
import java.util.HashMap;

public class Gen implements AtomicBehaviour{

	@Override
	public String toString() {
		return "Gen\t s(" + current_state+") tr("+tr+")";
	}

	int current_state;
	int next_state;
	double tr;
	String name;
	ArrayList<String> outputs;
	ArrayList<String> inputs;
	
	public Gen(String name){
		this.name = name;
		outputs = new ArrayList<>();
		outputs.add("job");
		
		inputs = new ArrayList<>();
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
		if(current_state == 0 )
			next_state = 0;
		current_state = next_state;

	}
	
	public void delta_ext(ArrayList<String> inputs){
		current_state = next_state;
	}
	
	public void delta_con(ArrayList<String> inputs){
		current_state = next_state;
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
	
	public double getTr(){
		return tr;
	}
	
	public void setTr(double tr){
		this.tr = tr;
	}

}
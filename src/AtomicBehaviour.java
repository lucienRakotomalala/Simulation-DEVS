import java.util.ArrayList;

public interface AtomicBehaviour {
	
	public void init();
	public void delta_int(double t);
	public void delta_ext(double t, ArrayList<String> inputs);
	public ArrayList<String> lambda();
	public double getTa();

}

package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		Tuple tuple;
		if ((tuple = child.next()) != null){
			for (int i=0; i<tuple.attributeList.size();i++){
				if ((tuple.getAttributeName(i)).equals(attributePredicate)){
					newAttributeList = new ArrayList<Attribute>();
					Attribute a = tuple.attributeList.get(i);
					newAttributeList.add(a);
					tuple = new Tuple(newAttributeList);
					return tuple;
					}
				}
			}
		return null;
	}
		

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}
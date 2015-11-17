package simpledatabase;
import java.util.ArrayList;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		ArrayList<Tuple> tuplesTemp = new ArrayList<Tuple>();
		while (true) {
			Tuple tuple = child.next();
			newAttributeList = new ArrayList<Attribute>();
			if (tuple == null) break;
			else {
				for (Attribute tupleAttribute:tuple.attributeList) {
					Attribute temp = new Attribute();
					temp.attributeName = tupleAttribute.attributeName;
					temp.attributeType  = tupleAttribute.attributeType;
					temp.attributeValue = tupleAttribute.attributeValue;
					newAttributeList.add(temp);
				}
				tuplesResult.add(new Tuple(newAttributeList));
			}
		}
		int position = 0, tuplePosition=0;
		while (tuplesResult.size() > 0){
			tuplesTemp = new ArrayList<Tuple>();
			Tuple min = new Tuple(tuplesResult.get(0).attributeList);
			for (Tuple cur:tuplesResult){
				for (Attribute a:cur.attributeList){
					if (a.attributeName.equals(orderPredicate))
						position = cur.attributeList.indexOf(a);
				}
				if ((cur.getAttributeValue(position).toString()).compareTo(min.getAttributeValue(position).toString()) <= 0){
					min = new Tuple(cur.attributeList);
				}
			}
			for (int i=0; i<tuplesResult.size();i++){
				if (tuplesResult.get(i).getAttributeValue(0).equals(min.getAttributeValue(0)))
					tuplePosition = i;
			}
			tuplesResult.remove(tuplePosition);
			tuplesTemp.add(min);
			return tuplesTemp.get(0);
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
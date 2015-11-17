package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Tuple rightTuple;
		while (true) {
			Tuple leftTuple = leftChild.next();
			ArrayList<Attribute> newAttributeList2 = new ArrayList<Attribute>();
			if (leftTuple == null) break;
			else {
				for (Attribute leftTupleAttribute:leftTuple.attributeList) {
					Attribute temp = new Attribute();
					temp.attributeName = leftTupleAttribute.attributeName;
					temp.attributeType  = leftTupleAttribute.attributeType;
					temp.attributeValue = leftTupleAttribute.attributeValue;
					newAttributeList2.add(temp);
				}
				tuples1.add(new Tuple(newAttributeList2));
			}
		}
		while ((rightTuple = rightChild.next()) != null) {
			for (Attribute rightTupleAttribute:rightTuple.attributeList){
				for (Tuple temp:tuples1){
					for (Attribute tupleTempAttribute:temp.attributeList){
						if (rightTupleAttribute.getAttributeValue().equals(tupleTempAttribute.attributeValue)){
							newAttributeList = new ArrayList<Attribute>();
							for (Attribute tuples1Attribute:temp.attributeList) newAttributeList.add(tuples1Attribute);
							for (Attribute rightTupleAttribute2:rightTuple.attributeList){
								if (rightTupleAttribute2.getAttributeValue().equals(tupleTempAttribute.attributeValue));
								else newAttributeList.add(rightTupleAttribute2);
							}
							return new Tuple(newAttributeList);
							}}}}}
		return null;
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}
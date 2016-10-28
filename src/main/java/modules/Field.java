package modules;

public class Field<T>{
	
	public enum BooleanOperator {
		AND, OR
	}
	
	public enum  Operator {
		EQ, GE, LE
	}
	
	private Operator operator ;
	private T valueTarget ;
	private String fieldTarget ;
	
	
	public Field(){}
	
	public Field(Operator operator, T valueTarget, String fieldTarget) {
		this.operator = operator;
		this.valueTarget = valueTarget;
		this.fieldTarget = fieldTarget;
	}

	public Field<T> setOperator(Operator operator) {
		this.operator = operator;
		return this ;
	}


	public  Field<T> setValueTarget(T valueTarget) {
		this.valueTarget = valueTarget;
		return this ;
	}


	public  Field<T> setFieldTarget(String fieldTarget) {
		this.fieldTarget = fieldTarget;
		return this ;
	}

	public T getValue(){
		return valueTarget ;
	}
	
	public String getFieldTarget(){
		return fieldTarget ;
	}
	
	public Operator getOperator(){
		return operator ;
	}
}



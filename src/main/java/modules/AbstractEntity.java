package modules;

/**
 * Created by adric on 20/10/2016.
 */
public abstract class AbstractEntity {
	
	public abstract Object getId(); //string ou int
	
	@Override
	public boolean equals(Object object){
		if(!(object instanceof AbstractEntity))
			return false;
		return this.getId().equals(((AbstractEntity)object).getId()) ;
	}
	
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
	
	
}

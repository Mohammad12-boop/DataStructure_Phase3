package hashing;

public class HNode <T extends Comparable<T>> {
	
	private T data;
	private String flag;
	
	public HNode(T data) {
		
		this.data=data;
		flag="E";
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return  data.toString()+", "+flag ;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}

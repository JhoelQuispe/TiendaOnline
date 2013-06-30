package pe.edu.ucsp.oms.domain;

public class Notification implements BaseEntity<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Integer id;
	String detail;
	
	@Override
	public Integer getId() {
		return id;
	}
	@Override
	public void setId(Integer id) {
		this.id=id;
		
	}
}

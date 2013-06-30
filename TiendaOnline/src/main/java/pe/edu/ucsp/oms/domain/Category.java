package pe.edu.ucsp.oms.domain;

public class Category implements BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static String TABLE_NAME = "Categories";
	
	private Long id;

	private String name;
	
	private Category parent;

	private Long idParent;
	
	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Long getIdParent(){
		return idParent;
	}
	
	public void setIdParent(Long idParent){
		this.idParent = idParent;
	}
	
	public void setParentName(String parentName){
		this.parent.name = parentName; 
	}
	
	public String getParentName(){
		return this.parent.name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}

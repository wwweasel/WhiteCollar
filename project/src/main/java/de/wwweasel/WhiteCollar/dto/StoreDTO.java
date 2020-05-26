package de.wwweasel.WhiteCollar.dto;

public class StoreDTO {
	
	private Integer id=null;
	private String name;
	private int capacity;
	
	public StoreDTO() {}
	
	public StoreDTO( String name, int capacity ) {
		this.name = name;
		this.capacity = capacity;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}

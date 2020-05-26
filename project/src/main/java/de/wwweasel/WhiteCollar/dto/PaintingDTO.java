package de.wwweasel.WhiteCollar.dto;

import java.time.LocalDateTime;


public class PaintingDTO {
	
	private Integer id=null;
	
	private String authorName = "Anonymous";
	
	private String name;
	
	private int price;
	
	private LocalDateTime creationDateTime;
		
	private Integer shopId;
	
	public PaintingDTO() {}
	
	public PaintingDTO( String authorName, String name, int price, Integer shopId) {
		this.authorName = authorName;
		this.name = name;
		this.price = price;
		this.shopId = shopId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer storeId) {
		this.shopId = storeId;
	}
	
	
}

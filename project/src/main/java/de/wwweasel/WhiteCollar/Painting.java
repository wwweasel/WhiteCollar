package de.wwweasel.WhiteCollar;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Painting{
	
// fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String authorName;
	private String name;
	private int price;
	
	@Column(name = "creationDateTime", columnDefinition = "TIMESTAMP")
	private LocalDateTime creationDateTime;
		
	@JsonIgnore
	@ManyToOne
	private Store store;
	
// Constructors
	public Painting() {}
	
	public Painting(String name) {
		
	}
	
// Getters and Setters
	public Integer getId() {
		return id;
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

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(LocalDateTime creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	@Override
	public String toString() {
		return "Painting [id="+getId() + " authorName=" + authorName + ", name=" + name + ", price=" + price + ", creationDateTime="
				+ creationDateTime  + "]";//+ ", store=" + store
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Painting other = (Painting) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}

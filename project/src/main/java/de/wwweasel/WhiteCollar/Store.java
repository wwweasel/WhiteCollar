package de.wwweasel.WhiteCollar;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Store{
	
// fields
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private int capacity;
	
	@JsonIgnore
	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
	private final List<Painting> paintings = new ArrayList<Painting>();
	
	
// Constructors
	public Store() {}
	
	public Store( String name, int capacity ) {
		this.name = name;
		this.capacity = capacity;
	}
	
// Getters and Setters
	public Integer getId() {
		return id;
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

	public List<Painting> getPaintings() {
		return paintings;
	}

	@Override
	public String toString() {
		return "Store [id="+getId() + " name=" + name + ", capacity=" + capacity + "]";
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
		Store other = (Store) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}

package de.wwweasel.WhiteCollar;

import java.util.ArrayList;
import java.util.List;

public class StorePaintingsDTO {
	
	private Store store;
	private List<Painting> paintings = new ArrayList<Painting>();
	
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public List<Painting> getPaintings() {
		return paintings;
	}
	public void setPaintings(List<Painting> paintings) {
		this.paintings = paintings;
	}
	
	public void addPainting(Painting painting) {
		this.paintings.add(painting);
	}
	
	
}

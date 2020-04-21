package de.wwweasel.WhiteCollar.dto;

import de.wwweasel.WhiteCollar.entities.Painting;
import de.wwweasel.WhiteCollar.entities.Store;

public class StorePaintingDTO {
	
	private Store store;
	private Painting painting;
	
	
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public Painting getPainting() {
		return painting;
	}
	public void setPainting(Painting painting) {
		this.painting = painting;
	}
	
	
}

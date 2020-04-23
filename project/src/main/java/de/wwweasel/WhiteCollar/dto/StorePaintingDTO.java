package de.wwweasel.WhiteCollar.dto;


import javax.validation.Valid;

import de.wwweasel.WhiteCollar.entities.Painting;
import de.wwweasel.WhiteCollar.entities.Store;


public class StorePaintingDTO {
	
	@Valid
	private Store store;
	
	@Valid
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

package de.wwweasel.WhiteCollar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.wwweasel.WhiteCollar.entities.Painting;
import de.wwweasel.WhiteCollar.entities.Store;
import de.wwweasel.WhiteCollar.services.PaintingService;
import de.wwweasel.WhiteCollar.services.StoreService;

//@RestController
@RequestMapping("/stores")
public class StoreRestController {
	
	@Autowired
	StoreService storeService;
	@Autowired
	PaintingService paintingService;
	
	@RequestMapping(method=RequestMethod.GET,value={"/{id}/paintings"})
	@CrossOrigin("*")
	public List<Painting> index(@PathVariable Optional<Integer> id) {
		int idd = id.get();
		Store store = storeService.findById(idd);
		List<Painting> paintings = store.getPaintings();
		return paintings;
	}
	
	@RequestMapping(method=RequestMethod.GET,value={"","/"})
	@CrossOrigin("*")
	public List<Store> index() {
		List<Store> stores = storeService.findAll();
		return stores;
	}
	
}

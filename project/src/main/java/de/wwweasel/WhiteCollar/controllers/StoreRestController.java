package de.wwweasel.WhiteCollar.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.wwweasel.WhiteCollar.dto.PaintingDTO;
import de.wwweasel.WhiteCollar.dto.StoreDTO;
import de.wwweasel.WhiteCollar.entities.Painting;
import de.wwweasel.WhiteCollar.entities.Store;
import de.wwweasel.WhiteCollar.repos.StoreRepo;
import de.wwweasel.WhiteCollar.services.PaintingService;
import de.wwweasel.WhiteCollar.services.StoreService;

//@CrossOrigin(origins = {"http://localhost:8080","*"})
@RestController
//@RequestMapping("/stores")
public class StoreRestController {
	
	@Autowired
	StoreService storeService;
	@Autowired
	PaintingService paintingService;
	
	
	// GET 
	@RequestMapping(method=RequestMethod.GET,value={"/stores/"})
	public List<StoreDTO> index() {
		return storeService.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET,value={"/stores/{id}/paintings"})
	public List<PaintingDTO> getShopPaintings(@PathVariable Integer id) {
		return paintingService.paintingsByStoreId(id);
	}
	
	@RequestMapping(method=RequestMethod.GET,value={"/paintings"})
	public List<PaintingDTO> getPaintings() {
		return paintingService.findAll();
	}

	
	// POST
	@RequestMapping(method=RequestMethod.POST,value="/stores/")
	public StoreDTO create(@RequestBody StoreDTO dto) {
		return storeService.save( dto );
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/stores/{id}/paintings")
	public PaintingDTO create(@RequestBody PaintingDTO dto) {
		return paintingService.save( dto );
	}
	

	@RequestMapping(method=RequestMethod.DELETE,value="/stores/{id}")
	public ResponseEntity<String> deleteStore(@PathVariable Integer id) {
		storeService.delete(id);
		return new ResponseEntity<String>("Successfully deleted Store with ID: " + id, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/stores/{id}/paintings/{paintingId}")
	public ResponseEntity<String> deletePainting(@PathVariable Integer id, @PathVariable Integer paintingId) {
		paintingService.delete(paintingId);
		return new ResponseEntity<String>("Successfully deleted Painting with ID: " + paintingId, HttpStatus.OK);
	}
	
	
	
}

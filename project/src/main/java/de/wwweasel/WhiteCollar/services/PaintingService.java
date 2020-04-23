package de.wwweasel.WhiteCollar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.wwweasel.WhiteCollar.entities.Painting;
import de.wwweasel.WhiteCollar.repos.PaintingRepo;
 
@Service
public class PaintingService {
	@Autowired
	PaintingRepo repo;
	
	public void save(Painting painting){
		repo.save(painting);
	}
	
	public void delete(Integer id) {
		Optional<Painting> paintingOpt = repo.findById(id);
		if(paintingOpt.isPresent()) {
			Painting painting = paintingOpt.get();
			repo.delete(painting);
		}else {
			System.out.println("Given id: " + id + " does not exist in DataBase");
		}
	}
	
	public Painting findById(Integer id) {
		Optional<Painting> paintingOpt = repo.findById(id);
		Painting painting = null;
		if(paintingOpt.isPresent()) {
			painting = paintingOpt.get();
		}
		return painting;
	}
	
	public List<Painting> findAll(){
		return repo.findAll();
	}
	
	public List<Painting> paintingsByStoreId(Integer storeId) {
		return repo.paintingsByStoreId(storeId);
	}
	
}

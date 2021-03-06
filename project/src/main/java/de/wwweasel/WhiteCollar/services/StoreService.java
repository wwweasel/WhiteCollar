package de.wwweasel.WhiteCollar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.wwweasel.WhiteCollar.entities.Painting;
import de.wwweasel.WhiteCollar.entities.Store;
import de.wwweasel.WhiteCollar.repos.StoreRepo;

@Service
public class StoreService {
	@Autowired
	StoreRepo repo;
	
	public Store save(Store store) {
		return repo.save(store);
	}
	
	public void delete(Integer id) {
		Optional<Store> storeOpt = repo.findById(id);
		if(storeOpt.isPresent()) {
			Store store = storeOpt.get();
			repo.delete(store);
		}
	}
	
	public Store findById(Integer id) {
		Optional<Store> storeOpt = repo.findById(id);
		Store store = null;
		if(storeOpt.isPresent()) {
			store = storeOpt.get();
		}
		return store;
	}
	
	public List<Store> findAll(){
		return repo.findAll();
	}
	
	public List<Painting> listPaintingsFromStoreId(Integer storeId){
		return repo.listPaintingsFromStoreId(storeId);
	}
}

package de.wwweasel.WhiteCollar.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.wwweasel.WhiteCollar.Exceptions.ApiDeleteException;
import de.wwweasel.WhiteCollar.dto.PaintingDTO;
import de.wwweasel.WhiteCollar.dto.StoreDTO;
import de.wwweasel.WhiteCollar.entities.Painting;
import de.wwweasel.WhiteCollar.entities.Store;
import de.wwweasel.WhiteCollar.repos.StoreRepo;

@Service
public class StoreService {
	@Autowired
	StoreRepo repo;
	
	public StoreDTO save(StoreDTO dto) {
		return convertToDTO( repo.save( convertToStore(dto) ) );
	}
	
	public void delete(Integer id) throws ApiDeleteException{
		Optional<Store> storeOpt = repo.findById(id);
		if(storeOpt.isPresent()) {
			Store store = storeOpt.get();
			repo.delete(store);
		}else {
			throw new ApiDeleteException("Could not delete Store with ID: " + id);
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
	
	public List<StoreDTO> findAll(){
		return repo.findAll()
				.stream()
				.map(store -> convertToDTO(store) )
				.collect(Collectors.toList());
	}
	
	public List<Painting> listPaintingsFromStoreId(Integer storeId){
		return repo.listPaintingsFromStoreId(storeId);
	}
	
	public StoreDTO convertToDTO(Store store) {
		StoreDTO dto = new StoreDTO();
		dto.setId(store.getId());
		dto.setName(store.getName());
		dto.setCapacity(store.getCapacity());
		return dto;
	}
	
	public Store convertToStore(StoreDTO dto) {
		Store store;
		if(dto.getId()!=null) {
			store = repo.findById(dto.getId()).get();
		}else {
			store = new Store();
		}
		store.setName(dto.getName());
		store.setCapacity(dto.getCapacity());
		return store;
	}
}

package de.wwweasel.WhiteCollar.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.wwweasel.WhiteCollar.Exceptions.ApiDeleteException;
import de.wwweasel.WhiteCollar.Exceptions.ApiRequestException;
import de.wwweasel.WhiteCollar.dto.PaintingDTO;
import de.wwweasel.WhiteCollar.entities.Painting;
import de.wwweasel.WhiteCollar.entities.Store;
import de.wwweasel.WhiteCollar.repos.PaintingRepo;
import de.wwweasel.WhiteCollar.repos.StoreRepo;
 
@Service
public class PaintingService {
	@Autowired
	PaintingRepo repo;
	
	@Autowired
	StoreRepo storeRepo;
	
	public PaintingDTO save(PaintingDTO dto) throws ApiRequestException{
		Painting painting = convertToPainting(dto);
		Store store = storeRepo.findById( painting.getStore().getId() ).get();
		if( store.getPaintings().size() >= store.getCapacity() ) {
			throw new ApiRequestException("StoreCapacity full!");
		}else {
			return convertToDTO( repo.save(painting) );		
		}
	}
	
	public void delete(Integer id) throws ApiDeleteException{
		Optional<Painting> paintingOpt = repo.findById(id);
		if(paintingOpt.isPresent()) {
			Painting painting = paintingOpt.get();
			repo.delete(painting);
		}else {
			throw new ApiDeleteException("Could not delete Painting with ID: " + id);
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
	
	public List<PaintingDTO> findAll(){
		return repo.findAll()
				.stream()
				.map(painting -> convertToDTO(painting) )
				.collect(Collectors.toList());
	}
	
	public List<PaintingDTO> paintingsByStoreId(Integer storeId) {
		return repo.paintingsByStoreId(storeId)
				.stream()
				.map(painting -> convertToDTO(painting)) // Convert the paintings to paintingDTOs
				.collect(Collectors.toList());
	}
	
	public PaintingDTO convertToDTO(Painting painting) {
		PaintingDTO dto = new PaintingDTO();
		dto.setId(painting.getId());
		dto.setAuthorName(painting.getAuthorName());
		dto.setName(painting.getName());
		dto.setPrice(painting.getPrice());
		dto.setShopId( painting.getStore().getId() );
		return dto;
	}
	
	public Painting convertToPainting(PaintingDTO dto) {
		Painting painting;
		if(dto.getId()!=null) {
			painting = repo.findById(dto.getId()).get();
		}else {
			painting = new Painting();
			painting.setStore( storeRepo.findById( dto.getShopId() ).get() );
		}
		painting.setAuthorName(dto.getAuthorName());
		painting.setName(dto.getName());
		painting.setPrice(dto.getPrice());
		// No need to set CreationDate
		return painting;
	}
	
}

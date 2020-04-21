package de.wwweasel.WhiteCollar.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.wwweasel.WhiteCollar.entities.Painting;

@Repository
public interface PaintingRepo extends JpaRepository<Painting, Integer>{
	
	// Here you actually perform a JOIN in the WHERE clasuse and then find all paintings with the given storeId 
	//@Query("SELECT p FROM Painting p, Store d WHERE p.store = d AND d.id = :storeId")// Or: "SELECT p FROM Painting p, Store d WHERE p.store.id = d.id AND d.id = :storeId"
	//List<Painting> paintingsByStoreId(Integer storeId);
	
	// Alternative Join
	@Query("SELECT p FROM Painting p WHERE p.store.id = :storeId")// Or: "SELECT p FROM Painting p, Store d WHERE p.store.id = d.id AND d.id = :storeId"
	List<Painting> paintingsByStoreId(Integer storeId);
	
	//@Query("SELECT p FROM Painting p JOIN p.store s WHERE s.id = :storeId")
	//List<Painting> paintingsByStoreId(Integer storeId);
	
}

package de.wwweasel.WhiteCollar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepo extends JpaRepository<Store, Integer>{
	
	// Find all the paintings from a given storeId
	// this automatically creates an 'implicit inner Join'	
	@Query("SELECT s.paintings FROM Store s WHERE s.id = :storeId")
	List<Painting> listPaintingsFromStoreId(Integer storeId);
	

}

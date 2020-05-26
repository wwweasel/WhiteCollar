package de.wwweasel.WhiteCollar.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.wwweasel.WhiteCollar.entities.Painting;
import de.wwweasel.WhiteCollar.entities.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Integer>{
	
	// Find all the paintings from a given storeId
	// this automatically creates an 'implicit inner Join'
	@Query("SELECT s.paintings FROM Store s WHERE s.id = :storeId")
	List<Painting> listPaintingsFromStoreId(Integer storeId);
	
	@Query("SELECT COUNT(p) FROM Store s, Painting p WHERE s.id = p.store.id AND s.id = :storeId")
	Integer paintingsCount(Integer storeId);
	
	
}

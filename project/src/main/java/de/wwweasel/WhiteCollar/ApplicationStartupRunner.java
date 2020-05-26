package de.wwweasel.WhiteCollar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import de.wwweasel.WhiteCollar.dto.PaintingDTO;
import de.wwweasel.WhiteCollar.dto.StoreDTO;
import de.wwweasel.WhiteCollar.services.PaintingService;
import de.wwweasel.WhiteCollar.services.StoreService;

@Component
public class ApplicationStartupRunner implements CommandLineRunner{
	
	@Autowired
	private StoreService storeService;
	@Autowired
	private PaintingService paintingService;
	
	@Override
	public void run(String... args) throws Exception {
		
		storeService.save( new StoreDTO("StoreOne",3) );		
		storeService.save( new StoreDTO("StoreTwo",4) );
		storeService.save( new StoreDTO("StoreThree",6) );
		
		paintingService.save( new PaintingDTO("Famous Author", "Great Book", 70, 1) );
		paintingService.save( new PaintingDTO("Famous Author", "Another Great Book", 40, 1) );
		paintingService.save( new PaintingDTO("Somebody", "Title", 15, 1) );
		
		paintingService.save( new PaintingDTO("Somebody", "Title", 15, 2) );
		paintingService.save( new PaintingDTO("Max Mustermann", "Geiles Buch", 15, 2) );
		
	}
	
}

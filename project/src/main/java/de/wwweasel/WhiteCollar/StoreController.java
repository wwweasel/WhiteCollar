package de.wwweasel.WhiteCollar;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/stores")
public class StoreController {
	
	@Autowired
	StoreService storeService;
	@Autowired
	PaintingService paintingService;
	
	@RequestMapping("*")
	public String dedirect() {
		return "redirect:/stores/";
	}
	
	@RequestMapping(method=RequestMethod.GET,value={"","/","/{id}/paintings"})
	public String index(@PathVariable Optional<Integer> id, Model model, @ModelAttribute("capacityError") String capacityError) {
		
		if(id.isPresent()) {
			int idd = id.get();
			Store store = storeService.findById(idd);
			List<Painting> paintings = store.getPaintings();// You could also use a JPQL Query here to retrieve the Paintings
			StorePaintingsDTO dto = new StorePaintingsDTO();
			dto.setStore( store );
			dto.setPaintings(paintings);
			model.addAttribute("storePaintingsDTO", dto);
			if(capacityError!=null){model.addAttribute("capacityError", capacityError);}
			return "store";
		}else {
			List<Store> stores = storeService.findAll();
			model.addAttribute("stores",stores);
			return "index";
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/create")
	public String create(Model model) {
		model.addAttribute("store",new Store());
		return "create";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/")
	public String create(@ModelAttribute Store store) {
		storeService.save(store);
		return "redirect:/stores/";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/delete")
	public String delete(@RequestParam Integer[] storeIds) {
		for (int i = 0; i < storeIds.length; i++) {
			storeService.delete(storeIds[i]);
		}
		return "redirect:/stores/";
	}
	
	// PAINTINGS
	
	@RequestMapping(method=RequestMethod.GET,value="/{id}/paintings/create")
	public ModelAndView createPainting(@PathVariable Integer id) {
		ModelAndView mv = new ModelAndView();
		Store store = storeService.findById(id);
		
		if(store.getPaintings().size()==store.getCapacity()) {
			
			mv.addObject("capacityError","capacityError");
			mv.setViewName("redirect:/stores/"+ id +"/paintings");
			return mv;
		}else {
			StorePaintingDTO dto = new StorePaintingDTO();
			dto.setStore(store);
			dto.setPainting(new Painting());
			
			mv.addObject("storePaintingDTO", dto);
			mv.setViewName("createPainting");
			return mv;
		}
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/{id}/paintings")
	public String createPainting(@ModelAttribute StorePaintingDTO dto, @PathVariable Integer id) {
		Store store = dto.getStore();
		Painting painting = dto.getPainting();
		painting.setStore(store);
		paintingService.save(painting);
		return "redirect:/stores/"+ id +"/paintings";
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/{id}/paintings/delete")
	public String deletePainting( @PathVariable Integer id, @RequestParam(required=false) Integer[] paintingIds){
		if(paintingIds!=null) {
			for (int i = 0; i < paintingIds.length; i++) {
				paintingService.delete( paintingIds[i] );
			}
			return "redirect:/stores/"+ id +"/paintings";
		}else {
			for (Painting painting : paintingService.findAll()) {
				paintingService.delete( painting.getId() );
			}
			return "redirect:/stores/"+ id +"/paintings";
		}
	}
	
	@RequestMapping(method=RequestMethod.GET,value="/paintingsByStoreId")
	@ResponseBody
	public List<Painting> paintingsByStoreId(@RequestParam Integer storeId) {
		//return paintingService.paintingsByStoreId(storeId);
		return storeService.listPaintingsFromStoreId(storeId);
	}
	
	
}

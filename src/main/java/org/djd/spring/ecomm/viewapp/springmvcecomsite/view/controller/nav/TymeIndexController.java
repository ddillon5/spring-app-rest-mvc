package org.djd.spring.ecomm.viewapp.springmvcecomsite.view.controller.nav;

import javax.validation.Valid;

import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.Chair;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.repos.ChairRepository;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.utils.EnumsClasses.ColorEnum;
import org.djd.spring.ecomm.viewapp.springmvcecomsite.model.utils.EnumsClasses.SizeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("ecomm-app")
public class TymeIndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(TymeIndexController.class);	
	
	private final ChairRepository chairRepository;
	
	TymeIndexController(ChairRepository chairRepository){
		this.chairRepository = chairRepository;
	}
	
	@RequestMapping("/")
    String tymeIndex(){
       return "thyme-index";
    }
	
	//done djd
	@RequestMapping("/chair/new")
	public String newChair(Model model) {
		model.addAttribute("chair", new Chair());
		model.addAttribute("sizes",  SizeEnum.values());
		model.addAttribute("colors", ColorEnum.values());
		
		
		return "new-chair";
	}
	@RequestMapping("/chair/edit/{id}")
	public String editChair(Model model, @PathVariable Long id) {
		
		model.addAttribute("sizes",  SizeEnum.values());
		model.addAttribute("colors", ColorEnum.values());
		model.addAttribute("chair", chairRepository.findById(id));
		logger.info("id from path : " + id);
		logger.info("beforeEdit chairId =: " + chairRepository.findById(id).get().getId());
		return "new-chair";
	}
	
	
	@RequestMapping(value = "chair", method = RequestMethod.POST)
	public String saveChair(@Valid Chair chair, Model model, BindingResult result){
		logger.error("got here.  chairId := " + chair.getId());
		if (result.hasErrors()) {
			logger.info("it has errors.  in method.");
			 return "new-chair";
		    // form validation error
		 
		} else {
		 
			chairRepository.save(chair);
		}
		
		
		logger.error("redirect:/ecomm-app/chair/" + chair.getId());
		
        return "redirect:/ecomm-app/chair/" + chair.getId();
    }
	
    @RequestMapping("chair/{id}")
    public String showProduct(@PathVariable Long id, Model model){
        chairRepository.findById(id).ifPresent(o -> model.addAttribute("chair", o));
        logger.info(chairRepository.findById(id).toString());
        return "chair";
    }
	
//    @RequestMapping(value = "/chairs", method = RequestMethod.GET)
//    public String list(Model model){
//        model.addAttribute("products",  chairRepository.findAll());
//        return "all-chair";
//    }
//    @RequestMapping("chair/edit/{id}")
//    public String edit(@PathVariable Long id, Model model){
//        model.addAttribute("product", chairRepository.findById(id));
//        return "chair";
//    }
    
    @RequestMapping("chair/delete/{id}")
    public String delete(@PathVariable Long id){
        chairRepository.deleteById(id);
        
        return "redirect:/all-chairs";
    }
	@RequestMapping("/chair/all")
	public String allchairs(Model model) {
		model.addAttribute("chairs", chairRepository.findAll());
		return "all-chairs";
	}
	
//	@RequestMapping("/get-all-chairs")
//	public String getAllChairs() {
//				
//		return "get-all-chairs";
//	}
}

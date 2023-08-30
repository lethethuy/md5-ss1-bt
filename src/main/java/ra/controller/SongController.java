package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ra.model.DTO.SongDTOForm;
import ra.model.service.ISongService;
import ra.model.service.SongService;

@Controller
public class SongController {
    @Autowired
    private SongService songService;
    // findAll
    @GetMapping
    public String home(Model model){
        model.addAttribute("list",songService.findAll());
        return "home";
    }
    // add
    @GetMapping("/add")
    public ModelAndView add(){
        return new ModelAndView("add","song", new SongDTOForm());
    }@PostMapping("/add")
    public String doAdd(@ModelAttribute SongDTOForm song){
        songService.save(song);
        return "redirect:/";
    }

    // edit
    @GetMapping("/edit/{id}")
    public ModelAndView findById(@PathVariable Long id){
        return new ModelAndView("edit","song", songService.findById(id));
    }
    // update
    @PostMapping("/update")
    public String doUpdate(@ModelAttribute SongDTOForm song){
        songService.save(song);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        songService.delete(id);
        return "redirect:/";
    }

}

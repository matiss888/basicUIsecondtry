package lv.bootcamp.shelter.controller;

import lombok.RequiredArgsConstructor;
import lv.bootcamp.shelter.dto.AnimalResponse;
import lv.bootcamp.shelter.form.AnimalForm;
import lv.bootcamp.shelter.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AnimalPageController {

    private final AnimalService animalService;

    @GetMapping("/")
    public String index() { return "index"; }

    @GetMapping("/animals")
    public String listOfAllAnimals(Model model) {
        List<AnimalResponse> allAnimals = animalService.findAll();
        model.addAttribute("animals", allAnimals);
        return "animals";
    }

    @GetMapping("/animals/new")
    public String newAnimalForm(Model model) {
        model.addAttribute("form", new AnimalForm(null,null,null,null,null,null));
        return "animals-new";
    }

    @PostMapping("/animals")
    public String submitNewAnimal(@ModelAttribute AnimalForm animalForm) {
        animalService.createFromForm(animalForm);
        return "redirect:/animals";
    }

}

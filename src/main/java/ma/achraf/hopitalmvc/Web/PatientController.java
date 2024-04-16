package ma.achraf.hopitalmvc.Web;

import lombok.AllArgsConstructor;
import ma.achraf.hopitalmvc.Repository.PatientRepository;
import ma.achraf.hopitalmvc.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor // injct via consructeur
public class PatientController {

    private PatientRepository patientRepository;
@GetMapping("/index") // il retourne une vue qui s appelt patient HTML


    public String index(Model model,

                        @RequestParam (name = "page",  defaultValue ="0") int p,
                        @RequestParam ( name= "size", defaultValue = "6") int s,
                        @RequestParam  (name = "keyword",  defaultValue ="")  String kw

){ // methode qui retourne string apres je stocke dans le model avec une type model
Page<Patient> pagePatient=patientRepository.findByNomContains(kw,PageRequest.of(p,s));// declare une liste de patient mais kankhtaro chhal dyal les patient bghina ntl3o
    // c est a dire donne moi 10 patient a partire de la page 0
    // http://localhost:8084/index?page=0&size=5
    model.addAttribute("listPatients",pagePatient);
    model.addAttribute("pages", new  int[pagePatient.getTotalPages()]); // attributs qui sappel pages avec dans la dimension c est le nombre de page
    model.addAttribute("currentPage",p);
    model.addAttribute("keyword",kw);
        return "patient";
    }
@GetMapping("/delete") //quand je vois /delete  cette methode qui va exucite
    public String delete (Long id,String keyword,int page){ //recupere  id
    patientRepository.deleteById(id);// supprimer et apres on va revenir pour afficher  en fait une redirection
    return "redirect:/index?page="+page+"&keyword="+keyword;
    }


}

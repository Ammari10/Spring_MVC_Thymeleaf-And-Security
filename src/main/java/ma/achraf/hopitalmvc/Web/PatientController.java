package ma.achraf.hopitalmvc.Web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.achraf.hopitalmvc.Repository.PatientRepository;
import ma.achraf.hopitalmvc.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor // injct via consructeur
public class PatientController {

    private PatientRepository patientRepository;
@GetMapping("/index") // il retourne une vue qui s appelt patient HTML


    public String index(Model model,

                        @RequestParam (name = "page",  defaultValue ="0") int p,
                        @RequestParam ( name= "size", defaultValue = "8") int s,
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
@GetMapping ("/")
    public  String home () {
    return  "redirect :/index";
}
@GetMapping ("Patient")
@ResponseBody
public  List<Patient> listPatients (){
    return  patientRepository.findAll();
    }

    @GetMapping("/formPatients")
    public String formPatient(Model model ){
    model.addAttribute("patient",new Patient());
         return  "formPatients";
    }
@PostMapping (path = "/save")
public String save(Model model, @Valid Patient patient, BindingResult bindingResult, // pour faire la validation tu doit 3 chose a faire la premier c est les inotation de Validation ajoute LE (size emty  DECIMAL MIN... et pour la troisieme c est au niveau de controlleur il faut utilise la notation Valide et BindingResult
                   @RequestParam(defaultValue = "0")  int page,
                   @RequestParam(defaultValue = "") String keyword){
    if (bindingResult.hasErrors()) return "formPatients";
    patientRepository.save(patient);
    return "redirect:/index?page="+page+"&keyword="+keyword;
}
    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id, String keyword, int page){
        Patient patient=patientRepository.findById(id).orElse(null);
        if (patient==null) throw new RuntimeException("patient introuvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";

    }


    }


package ma.achraf.hopitalmvc.Web;

import lombok.AllArgsConstructor;
import ma.achraf.hopitalmvc.Repository.PatientRepository;
import ma.achraf.hopitalmvc.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor // injct via consructeur
public class PatientController {

    private PatientRepository patientRepository;
@GetMapping("/index") // il retourne une vue qui s appelt patient HTML
    public String index(Model model){ // methode qui retourne string apres je stocke dans le model avec une type model
    List<Patient> patientList=patientRepository.findAll(); // declare une liste de patient
    model.addAttribute("listPatients",patientList);
        return "patient";
    }


}

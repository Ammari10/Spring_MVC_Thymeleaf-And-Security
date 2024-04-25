package ma.achraf.hopitalmvc;

import ma.achraf.hopitalmvc.Repository.PatientRepository;
import ma.achraf.hopitalmvc.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class HopitlMvcApplication  implements CommandLineRunner {
@Autowired // l injct de dpndc


    private PatientRepository patientRepository ;

    public static void main(String[] args) {
        SpringApplication.run(HopitlMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // patientRepository.save(new Patient(null,"Shryyfa",new Date(),false,23));
        // patientRepository.save(new Patient(null,"Ayoub",new Date(),false,223));
        //patientRepository.save(new Patient(null,"Mohammed",new Date(),false,233));
        // patientRepository.save(new Patient(null,"Ali",new Date(),false,233));
        //patientRepository.save(new Patient(null,"Youness",new Date(),false,233));
        //patientRepository.save(new Patient(null,"Yassine",new Date(),false,233));
        //patientRepository.save(new Patient(null,"Safae",new Date(),false,233));
        //patientRepository.save(new Patient(null,"Oumaima",new Date(),false,233));
        //cree patient premier solution sans parametre

        //Patient patient = new Patient();
      //  patient.setId(null);
      //  patient.setNom("Ashraf");
      //  patient.setDateNaissance(new Date());
        // patient.setScore(23);

        // deuxieme solution Avec des parametre
        //Patient patient2 = new Patient (null,"shryyfa",new Date(),false,123);

        // troisieme solution avec Builder
        //Patient patient3 =Patient.builder()
               // .nom("mikasa")
               // .dateNaissance(new Date())
              //  .score( 23)
             ////   .Malade ( true)
            //    .build();



    }
    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
}

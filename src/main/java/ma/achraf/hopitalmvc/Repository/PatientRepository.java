package ma.achraf.hopitalmvc.Repository;

import ma.achraf.hopitalmvc.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    //1er methode chercher et pageable pour la panigagtion et la sortable pour le trie
    Page<Patient> findByNomContains(String keyword, Pageable pageable);

    //2eme method chercher
    @Query("select p from Patient p where p.nom like :x")
    Page<Patient> chercher(@Param("x") String keyword, Pageable pageable);
}

package app.model.clinic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clinics")
public class ClinicController {
    private ClinicService clinicService;

    @GetMapping
    public List<Clinic> getAllDoctors(){
        return clinicService.getAllClinics();
    }

    @GetMapping("/{cid}")
    public ResponseEntity<Clinic> getDoctorById(@PathVariable("cid") Long cid){
        Optional<Clinic> clinic = clinicService.getClinicById(cid);
        return clinic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}

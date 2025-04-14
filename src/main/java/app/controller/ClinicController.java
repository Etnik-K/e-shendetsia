package app.controller;

import app.model.Clinic;
import app.service.ClinicService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // e gjeneron konstruktori vet me lombok
@Getter
@RestController
@RequestMapping("/api/clinics")
public class ClinicController extends BaseController {
    private final ClinicService clinicService; // qikjo o constructor injection

    @GetMapping
    public List<Clinic> getAllDoctors(){
        return clinicService.getAllClinics();
    }

    @GetMapping("/{clinic_id}")
    public ResponseEntity<Clinic> getDoctorById(@PathVariable("clinic_id") Long cid){
        // auth ni her, tani kallzo
        Optional<Clinic> clinic = clinicService.getClinicById(cid);
        return clinic.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/test")
    public String getDoctorByIdTest(){
        return "ckm :}";
    }
}

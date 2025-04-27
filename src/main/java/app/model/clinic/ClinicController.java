package app.model.clinic;

import app.util.ApiResponse;
import app.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clinics")
public class ClinicController extends BaseController {
    private ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping
    public List<Clinic> getAllDoctors(){
        return clinicService.getAllClinics();
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<ApiResponse<Clinic>> getDoctorById(@PathVariable("clinicId") Long clinicId){
        Optional<Clinic> clinic = clinicService.getClinicById(clinicId);
        return clinic.map(this::ok).orElseGet(() -> this.notFound(STR."Klinika me ID \{clinicId} nuk eksiston"));
    }

}

package millaku.altin.eshendetsia.controller.concrete;


import millaku.altin.eshendetsia.model.entity.Doctor;
import millaku.altin.eshendetsia.service.interfaces.DoctorService;
import millaku.altin.eshendetsia.http.response.ApiResponse;
import millaku.altin.eshendetsia.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Kontrolleri per menaxhimin e doktoreve ne sistem.
 * Mundeson marrjen e te dhenave per te gjithe doktoret dhe kerkimin e doktoreve me ID.
 */
@RestController
@RequestMapping("/doctors")
public class DoctorController extends BaseController {

    private final DoctorService doctorService;

    /**
     * Konstruktori i kontrollerit.
     * Perdor dependency injection per te marre sherbimet e doktoreve.
     *
     * @param doctorService sherbimi i doktoreve qe do te perdoret
     */
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * Merr listen e te gjithe doktoreve ne sistem.
     *
     * @return lista e te gjithe doktoreve
     */
    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    /**
     * Merr doktorin sipas ID-se.
     *
     * @param doctorId ID e doktorit qe do te kerkohet
     * @return doktori i gjetur ose error nese nuk ekziston
     */
    @GetMapping("/{doctorId}")
    public ResponseEntity<ApiResponse<Doctor>> getDoctorById(@PathVariable("doctorId") Long doctorId){
        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
        return doctor.map(this::ok).orElseGet(() -> this.error(STR."Doktorri me id \{doctorId} nuk ekziston", HttpStatus.NOT_FOUND));
    }

}

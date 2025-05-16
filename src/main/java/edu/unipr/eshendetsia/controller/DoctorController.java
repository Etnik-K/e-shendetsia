package edu.unipr.eshendetsia.controller;


import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.service.interfaces.DoctorService;
import edu.unipr.eshendetsia.http.response.ApiResponse;
import edu.unipr.eshendetsia.util.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController extends BaseController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<ApiResponse<Doctor>> getDoctorById(@PathVariable("doctorId") Long doctorId){
        Optional<Doctor> doctor = doctorService.getDoctorById(doctorId);
        return doctor.map(this::ok).orElseGet(() -> this.error(STR."Doktorri me id \{doctorId} nuk ekziston", HttpStatus.NOT_FOUND));
    }

}

package edu.unipr.eshendetsia.controller.concrete;


import edu.unipr.eshendetsia.model.entity.Doctor;
import edu.unipr.eshendetsia.service.interfaces.DoctorService;
import edu.unipr.eshendetsia.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        return this.ok(doctorService.getAllDoctors());
    }

    /**
     * Merr doktorin sipas ID-se.
     *
     * @param doctorId ID e doktorit qe do te kerkohet
     * @return doktori i gjetur ose error nese nuk ekziston
     */
    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctorId") Long doctorId, @RequestHeader("Authorization") String authHeader){
        return this.ok(doctorService.getDoctorById(doctorId, authHeader));
    }

}

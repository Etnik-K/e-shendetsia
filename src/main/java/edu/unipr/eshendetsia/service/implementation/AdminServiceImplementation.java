package edu.unipr.eshendetsia.service.implementation;

import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.model.entity.Admin;
import edu.unipr.eshendetsia.repository.AdminRepository;
import edu.unipr.eshendetsia.service.interfaces.AdminService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImplementation implements AdminService {

    private final AdminRepository adminRepository;

    public AdminServiceImplementation(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findById(Long id) throws NotFoundException {
        Optional<Admin> admin = adminRepository.findById(id);

        if (admin.isEmpty())
            throw new NotFoundException("Admini nuk u gjet");

        return admin.get();
    }
}

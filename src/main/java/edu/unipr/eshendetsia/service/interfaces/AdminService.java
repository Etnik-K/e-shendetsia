package edu.unipr.eshendetsia.service.interfaces;

import edu.unipr.eshendetsia.exception.concrete.NotFoundException;
import edu.unipr.eshendetsia.model.entity.Admin;

public interface AdminService {
    /**
     * tregon se a eksiston nje admin me Id perkatese
     * @param id - id e adminit
     * @return Adminin - nese eksiston
     */
    Admin findById(Long id) throws NotFoundException;
}

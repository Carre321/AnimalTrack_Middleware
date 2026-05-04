package com.tonin.animaltrack.service.impl;

import java.util.List;

import com.tonin.animaltrack.dao.VeterinarioGranjaDAO;
import com.tonin.animaltrack.model.VeterinarioGranja;
import com.tonin.animaltrack.service.VeterinarioGranjaService;

public class VeterinarioGranjaServiceImpl implements VeterinarioGranjaService {

    private VeterinarioGranjaDAO veterinarioGranjaDAO = null;

    public VeterinarioGranjaServiceImpl() {
        this.veterinarioGranjaDAO = new VeterinarioGranjaDAO();
    }

    @Override
    public List<VeterinarioGranja> findByVeterinarioId(Long veterinarioId) {
        return veterinarioGranjaDAO.findByVeterinarioId(veterinarioId);
    }

    @Override
    public List<VeterinarioGranja> findByGranjaId(Long granjaId) {
        return veterinarioGranjaDAO.findByGranjaId(granjaId);
    }

    @Override
    public VeterinarioGranja create(VeterinarioGranja veterinarioGranja) {
        if (veterinarioGranja == null || veterinarioGranja.getVeterinarioId() == null || veterinarioGranja.getGranjaId() == null) {
            return null;
        }
        veterinarioGranjaDAO.create(veterinarioGranja);
        return veterinarioGranja;
    }

    @Override
    public void update(Long oldVeterinarioId, Long oldGranjaId, VeterinarioGranja veterinarioGranja) {
        if (oldVeterinarioId != null && oldGranjaId != null && veterinarioGranja != null
                && veterinarioGranja.getVeterinarioId() != null && veterinarioGranja.getGranjaId() != null) {
            veterinarioGranjaDAO.update(oldVeterinarioId, oldGranjaId, veterinarioGranja);
        }
    }

    @Override
    public void delete(Long veterinarioId, Long granjaId) {
        if (veterinarioId != null && granjaId != null) {
            veterinarioGranjaDAO.delete(veterinarioId, granjaId);
        }
    }
}

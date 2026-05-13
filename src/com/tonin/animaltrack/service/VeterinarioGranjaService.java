package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.VeterinarioGranja;

public interface VeterinarioGranjaService {

    public List<VeterinarioGranja> findByVeterinarioId(Long veterinarioId) throws Exception;

    public List<VeterinarioGranja> findByGranjaId(Long granjaId) throws Exception;

    public VeterinarioGranja create(VeterinarioGranja veterinarioGranja) throws Exception;

    public void update(Long oldVeterinarioId, Long oldGranjaId, VeterinarioGranja veterinarioGranja) throws Exception;

    public void delete(Long veterinarioId, Long granjaId) throws Exception;
}

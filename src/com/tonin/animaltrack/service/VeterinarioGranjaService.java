package com.tonin.animaltrack.service;

import java.util.List;

import com.tonin.animaltrack.model.VeterinarioGranja;

public interface VeterinarioGranjaService {

    public List<VeterinarioGranja> findByVeterinarioId(Long veterinarioId);

    public List<VeterinarioGranja> findByGranjaId(Long granjaId);

    public VeterinarioGranja create(VeterinarioGranja veterinarioGranja);

    public void update(Long oldVeterinarioId, Long oldGranjaId, VeterinarioGranja veterinarioGranja);

    public void delete(Long veterinarioId, Long granjaId);
}

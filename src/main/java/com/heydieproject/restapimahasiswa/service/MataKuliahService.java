package com.heydieproject.restapimahasiswa.service;

import com.heydieproject.restapimahasiswa.entity.MataKuliah;

public interface MataKuliahService {
    public Iterable<MataKuliah> findAll();
    public MataKuliah createNewMataKuliah(MataKuliah mataKuliah);
    public MataKuliah findById(Long id);
    public void deleteMataKuliah(Long id);
}

package com.heydieproject.restapimahasiswa.service;

import com.heydieproject.restapimahasiswa.entity.Jurusan;

public interface JurusanService {
    public Iterable<Jurusan> findAll();
    public Jurusan createNewJurusan(Jurusan jurusan);
    public Jurusan findById(Long id);
    public void deleteJurusan(Long id);
}

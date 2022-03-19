package com.heydieproject.restapimahasiswa.service;

import com.heydieproject.restapimahasiswa.entity.Fakultas;

public interface FakultasService {
    public Iterable<Fakultas> findAll();
    public Fakultas createNewFakultas(Fakultas fakultas);
    public Fakultas findById(Long id);
        public void deleteFakultas(Long id);
}

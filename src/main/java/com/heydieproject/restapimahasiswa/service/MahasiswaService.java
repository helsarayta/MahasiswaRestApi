package com.heydieproject.restapimahasiswa.service;

import com.heydieproject.restapimahasiswa.entity.Mahasiswa;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MahasiswaService {
    public Iterable<Mahasiswa> findAll();
    public Mahasiswa saveMahasiswa(Mahasiswa mahasiswa);
    public Mahasiswa findById(Long id);
    public void deleteMahasiswa(Long id);
    public Mahasiswa searchByName(String name);
    public List<Mahasiswa> searchByNameLike(String name);
    public List<Mahasiswa> searchMahasiswaByJurusan(Long jurusanId);
    public List<Mahasiswa> searchMahasiswaByMataKuliah(Long mataKuliahId);
    public Iterable<Mahasiswa> findAllPage(Pageable pageable);
    public Iterable<Mahasiswa> findByNameContains(String name, Pageable pageable);

}

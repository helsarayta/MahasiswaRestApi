package com.heydieproject.restapimahasiswa.service.serviceImpl;

import com.heydieproject.restapimahasiswa.entity.Mahasiswa;
import com.heydieproject.restapimahasiswa.entity.MataKuliah;
import com.heydieproject.restapimahasiswa.repository.MahasiswaRepository;
import com.heydieproject.restapimahasiswa.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MahasiswaServiceImpl implements MahasiswaService {
    @Autowired
    private MahasiswaRepository mahasiswaRepository;
    @Autowired
    private MataKuliahServiceImpl mataKuliahService;

    @Override
    public Iterable<Mahasiswa> findAll() {
        return mahasiswaRepository.findAll();
    }

    @Override
    public Mahasiswa saveMahasiswa(Mahasiswa mahasiswa) {
        return mahasiswaRepository.save(mahasiswa);
    }

    @Override
    public Mahasiswa findById(Long id) {
        Optional<Mahasiswa> mahasiswa = mahasiswaRepository.findById(id);
        if (!mahasiswa.isPresent()) {
            return null;
        }
        return mahasiswa.get();
    }

    @Override
    public void deleteMahasiswa(Long id) {
        mahasiswaRepository.deleteById(id);
    }

    @Override
    public Mahasiswa searchByName(String name) {
        return mahasiswaRepository.searchByName(name);
    }

    @Override
    public List<Mahasiswa> searchByNameLike(String name) {
        return mahasiswaRepository.searchByNameLike(name);
    }

    @Override
    public List<Mahasiswa> searchMahasiswaByJurusan(Long jurusanId) {
        return mahasiswaRepository.searchMahasiswaByJurusan(jurusanId);
    }

    @Override
    public List<Mahasiswa> searchMahasiswaByMataKuliah(Long mataKuliahId) {
        MataKuliah mataKuliahServiceById = mataKuliahService.findById(mataKuliahId);
        if(mataKuliahServiceById == null) {
            return new ArrayList<>();
        }
        return mahasiswaRepository.searchMahasiswaByMataKuliah(mataKuliahServiceById);
    }

    @Override
    public Iterable<Mahasiswa> findAllPage(Pageable pageable) {
        return mahasiswaRepository.findAll(pageable);
    }

    @Override
    public Iterable<Mahasiswa> findByNameContains(String name, Pageable pageable) {
        return mahasiswaRepository.findByNamaContains(name, pageable);
    }
}

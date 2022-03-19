package com.heydieproject.restapimahasiswa.service.serviceImpl;


import com.heydieproject.restapimahasiswa.entity.MataKuliah;
import com.heydieproject.restapimahasiswa.repository.MataKuliahRepository;
import com.heydieproject.restapimahasiswa.service.MataKuliahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MataKuliahServiceImpl implements MataKuliahService {
    @Autowired
    private MataKuliahRepository mataKuliahRepository;

    @Override
    public Iterable<MataKuliah> findAll() {
        return mataKuliahRepository.findAll();
    }

    @Override
    public MataKuliah createNewMataKuliah(MataKuliah mataKuliah) {
        return mataKuliahRepository.save(mataKuliah);
    }

    @Override
    public MataKuliah findById(Long id) {
        Optional<MataKuliah> mataKuliah = mataKuliahRepository.findById(id);
        if(!mataKuliah.isPresent()){
            return null;
        }
        return mataKuliah.get();
    }

    @Override
    public void deleteMataKuliah(Long id) {
        mataKuliahRepository.deleteById(id);
    }
}

package com.heydieproject.restapimahasiswa.service.serviceImpl;

import com.heydieproject.restapimahasiswa.entity.Fakultas;
import com.heydieproject.restapimahasiswa.repository.FakultasRepository;
import com.heydieproject.restapimahasiswa.service.FakultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class FakultasServiceImpl implements FakultasService {
    @Autowired
    private FakultasRepository fakultasRepository;

    @Override
    public Iterable<Fakultas> findAll() {
        return fakultasRepository.findAll();
    }

    @Override
    public Fakultas createNewFakultas(Fakultas fakultas) {
        return fakultasRepository.save(fakultas);
    }

    @Override
    public Fakultas findById(Long id) {
        Optional<Fakultas> fakultas = fakultasRepository.findById(id);
        if(!fakultas.isPresent()) {
            return null;
        }
        return fakultas.get();
    }



    @Override
    public void deleteFakultas(Long id) {
        fakultasRepository.deleteById(id);
    }
}

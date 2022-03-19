package com.heydieproject.restapimahasiswa.service.serviceImpl;

import com.heydieproject.restapimahasiswa.entity.Jurusan;
import com.heydieproject.restapimahasiswa.repository.JurusanRepository;
import com.heydieproject.restapimahasiswa.service.JurusanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class JurusanServiceImpl implements JurusanService {
    @Autowired
    private JurusanRepository jurusanRepository;

    @Override
    public Iterable<Jurusan> findAll() {
        return jurusanRepository.findAll();
    }

    @Override
    public Jurusan createNewJurusan(Jurusan jurusan) {
        return jurusanRepository.save(jurusan);
    }

    @Override
    public Jurusan findById(Long id) {
        Optional<Jurusan> jurusan = jurusanRepository.findById(id);
        if (!jurusan.isPresent()) {
            return null;
        }
        return jurusan.get();
    }

    @Override
    public void deleteJurusan(Long id) {
        jurusanRepository.deleteById(id);
    }
}

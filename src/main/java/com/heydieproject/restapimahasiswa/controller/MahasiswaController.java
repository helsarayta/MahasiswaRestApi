package com.heydieproject.restapimahasiswa.controller;

import com.heydieproject.restapimahasiswa.dto.ResponseDto;
import com.heydieproject.restapimahasiswa.dto.SearchDto;
import com.heydieproject.restapimahasiswa.entity.Mahasiswa;
import com.heydieproject.restapimahasiswa.service.serviceImpl.MahasiswaServiceImpl;
import com.heydieproject.restapimahasiswa.service.serviceImpl.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mahasiswa")
public class MahasiswaController {
    @Autowired
    private MahasiswaServiceImpl mahasiswaService;
    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<ResponseDto<Mahasiswa>> createMahasiswa(@Valid @RequestBody Mahasiswa mahasiswa, Errors errors) {
        ResponseDto<Mahasiswa> responseDto = new ResponseDto<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseDto.getMessage().add(error.getDefaultMessage());
            }
            responseDto.setPayload(null);
            responseDto.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
        responseDto.setStatus(true);
        responseDto.setPayload(mahasiswaService.saveMahasiswa(mahasiswa));
        responseDto.getMessage().add("Berhasil menambahkan mahasiswa baru");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public Iterable<Mahasiswa> findAll() {
        return mahasiswaService.findAll();
    }

    @GetMapping("/{id}")
    public Mahasiswa findById(@PathVariable Long id) {
        return mahasiswaService.findById(id);
    }

    @PutMapping()
    public ResponseEntity<ResponseDto<Mahasiswa>> updateMahasiswa(@Valid @RequestBody Mahasiswa mahasiswa,
                                                                  Errors errors) {
        ResponseDto<Mahasiswa> responseDto = new ResponseDto<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseDto.getMessage().add(error.getDefaultMessage());
            }
            responseDto.setPayload(null);
            responseDto.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }

        responseDto.setStatus(true);
        responseDto.setPayload(mahasiswaService.saveMahasiswa(mahasiswa));
        responseDto.getMessage().add("Berhasil update mahasiswa");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMahasiswa(@PathVariable Long id) {
        mahasiswaService.deleteMahasiswa(id);
    }

    @PostMapping("/search/name")
    public Mahasiswa searchByName(@RequestBody SearchDto search) {
        return mahasiswaService.searchByName(search.getSearchKey());
    }

    @PostMapping("/search/namelike")
    public List<Mahasiswa> searchByNameLike(@RequestBody SearchDto search) {
        return mahasiswaService.searchByNameLike(search.getSearchKey());
    }

    @GetMapping("/search/jurusan/{jurusanId}")
    public List<Mahasiswa> searchMahasiswaByJurusan(@PathVariable("jurusanId") Long jurusanId) {
        return mahasiswaService.searchMahasiswaByJurusan(jurusanId);
    }

    @GetMapping("/search/matakuliah/{mataKuliahId}")
    public List<Mahasiswa> searchMahasiswaByMatakuliah(@PathVariable Long mataKuliahId) {
        return mahasiswaService.searchMahasiswaByMataKuliah(mataKuliahId);
    }

    @PostMapping("/{size}/{page}")
    public Iterable<Mahasiswa> findAllPaging(@PathVariable int size, @PathVariable int page) {
        PageRequest request = PageRequest.of(page, size);
        return mahasiswaService.findAllPage(request);
    }

    @PostMapping("/search/{size}/{page}/{sort}")
    public Iterable<Mahasiswa> findNamePagingSorting(@PathVariable int size,
                                                     @PathVariable int page,
                                                     @PathVariable String sort,
                                                     @RequestBody SearchDto search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        if (sort.equalsIgnoreCase("desc")) {
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
        }

        return mahasiswaService.findByNameContains(search.getSearchKey(), pageable);
    }
    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReport(format);
    }


}

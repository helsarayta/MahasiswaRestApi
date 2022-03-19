package com.heydieproject.restapimahasiswa.controller;

import com.heydieproject.restapimahasiswa.dto.ResponseDto;
import com.heydieproject.restapimahasiswa.entity.MataKuliah;
import com.heydieproject.restapimahasiswa.service.serviceImpl.MataKuliahServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/matakuliah")
public class MataKuliahController {
    @Autowired
    private MataKuliahServiceImpl mataKuliahService;

    @PostMapping
    public ResponseEntity<ResponseDto<MataKuliah>> createMataKuliah(@Valid @RequestBody MataKuliah mataKuliah,
                                                                    Errors errors) {
        ResponseDto<MataKuliah> responseDto = new ResponseDto<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseDto.getMessage().add(error.getDefaultMessage());
            }
            responseDto.setPayload(null);
            responseDto.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
        responseDto.setStatus(true);
        responseDto.setPayload(mataKuliahService.createNewMataKuliah(mataKuliah));
        responseDto.getMessage().add("Mata kuliah berhasil ditambahkan");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public Iterable<MataKuliah> findAll() {
        return mataKuliahService.findAll();
    }

    @GetMapping("/{id}")
    public MataKuliah findById(@PathVariable Long id) {
        return mataKuliahService.findById(id);
    }

    @PutMapping()
    public ResponseEntity<ResponseDto<MataKuliah>> updateMataKuliah(@Valid @RequestBody MataKuliah mataKuliah,
                                                                    Errors errors) {
        ResponseDto<MataKuliah> responseDto = new ResponseDto<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseDto.getMessage().add(error.getDefaultMessage());
            }
            responseDto.setPayload(null);
            responseDto.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
        responseDto.setStatus(true);
        responseDto.setPayload(mataKuliahService.createNewMataKuliah(mataKuliah));
        responseDto.getMessage().add("Mata kuliah berhasil diupdate");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMataKuliah(@PathVariable Long id) {
        mataKuliahService.deleteMataKuliah(id);
    }


}

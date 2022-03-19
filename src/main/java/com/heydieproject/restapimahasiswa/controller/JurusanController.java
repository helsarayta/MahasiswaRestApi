package com.heydieproject.restapimahasiswa.controller;

import com.heydieproject.restapimahasiswa.dto.ResponseDto;
import com.heydieproject.restapimahasiswa.entity.Jurusan;
import com.heydieproject.restapimahasiswa.service.serviceImpl.JurusanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/jurusan")
public class JurusanController {
    @Autowired
    private JurusanServiceImpl jurusanService;

    @PostMapping
    public ResponseEntity<ResponseDto<Jurusan>> createNew(@Valid @RequestBody Jurusan jurusan, Errors errors) {
        ResponseDto<Jurusan> responseDto = new ResponseDto<>();
        if(errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()) {
                responseDto.getMessage().add(error.getDefaultMessage());
            }
            responseDto.setPayload(null);
            responseDto.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
        responseDto.setStatus(true);
        responseDto.setPayload(jurusanService.createNewJurusan(jurusan));
        responseDto.getMessage().add("Jurusan berhasil ditambahkan");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public Iterable<Jurusan> findAll() {
        return jurusanService.findAll();
    }

    @GetMapping("/{id}")
    public Jurusan findById(@PathVariable Long id) {
        return jurusanService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<Jurusan>> updateJurusan(@Valid @RequestBody Jurusan jurusan,
                                                              Errors errors
                                                              ) {
        ResponseDto<Jurusan> responseDto = new ResponseDto<>();

        if(errors.hasErrors()) {
            for(ObjectError error : errors.getAllErrors()) {
                responseDto.getMessage().add(error.getDefaultMessage());
            }
            responseDto.setPayload(null);
            responseDto.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }

        responseDto.setStatus(true);
        responseDto.setPayload(jurusanService.createNewJurusan(jurusan));
        responseDto.getMessage().add("Jurusan berhasil diUpdate");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public void deleteJurusan(@PathVariable Long id) {
        jurusanService.deleteJurusan(id);
    }


















}

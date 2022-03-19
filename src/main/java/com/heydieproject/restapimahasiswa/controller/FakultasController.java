package com.heydieproject.restapimahasiswa.controller;

import com.heydieproject.restapimahasiswa.dto.ResponseDto;
import com.heydieproject.restapimahasiswa.entity.Fakultas;
import com.heydieproject.restapimahasiswa.service.serviceImpl.FakultasServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fakultas")
public class FakultasController {

    @Autowired
    private FakultasServiceImpl fakultasService;

    @PostMapping
    public ResponseEntity<ResponseDto<Fakultas>> createFakultas(@Valid @RequestBody Fakultas fakultas, Errors errors) {
        ResponseDto<Fakultas> responseDto = new ResponseDto<>();
        if (errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()) {
                responseDto.getMessage().add(error.getDefaultMessage());
            }
            responseDto.setPayload(null);
            responseDto.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
        responseDto.setStatus(true);
        responseDto.setPayload(fakultasService.createNewFakultas(fakultas));
        responseDto.getMessage().add("Fakultas berhasil ditambahkan");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public Iterable<Fakultas> findAll() {
        return fakultasService.findAll();
    }

    @GetMapping("/{id}")
    public Fakultas findById(@PathVariable Long id) {
        return fakultasService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<Fakultas>> updateFakultas(@Valid @RequestBody Fakultas fakultas,
                                                                Errors errors
                                                                ) {
        ResponseDto<Fakultas> responseDto = new ResponseDto<>();

        if (errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()) {
                responseDto.getMessage().add(error.getDefaultMessage());
            }
            responseDto.setPayload(null);
            responseDto.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }

        responseDto.setStatus(true);
        responseDto.setPayload(fakultasService.createNewFakultas(fakultas));
        responseDto.getMessage().add("Fakultas berhasil diUpdate");
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public void deleteFakultas(@PathVariable Long id) {
        fakultasService.deleteFakultas(id);
    }




}

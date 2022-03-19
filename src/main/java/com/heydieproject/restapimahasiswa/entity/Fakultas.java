package com.heydieproject.restapimahasiswa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tbl_fakultas")
public class Fakultas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nama_fakultas", nullable = false, unique = true)
    @NotEmpty(message = "Nama fakultas harus diisi!!")
    private String namaFakultas;
    @OneToMany
    private Set<Mahasiswa> mahasiswaList;

    public Fakultas() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaFakultas() {
        return namaFakultas;
    }

    public void setNamaFakultas(String namaFakultas) {
        this.namaFakultas = namaFakultas;
    }

    public Set<Mahasiswa> getMahasiswaList() {
        return mahasiswaList;
    }

    public void setMahasiswaList(Set<Mahasiswa> mahasiswaList) {
        this.mahasiswaList = mahasiswaList;
    }
}

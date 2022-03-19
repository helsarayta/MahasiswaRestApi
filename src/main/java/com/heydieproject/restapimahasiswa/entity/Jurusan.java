package com.heydieproject.restapimahasiswa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tbl_jurusan")
public class Jurusan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nama_jurusan", nullable = false, unique = true)
    @NotEmpty(message = "Nama jurusan harus diisi!!")
    private String namaJurusan;
    @OneToMany(mappedBy = "jurusan")
    private Set<Mahasiswa> mahasiswaList;

    public Jurusan() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaJurusan() {
        return namaJurusan;
    }

    public void setNamaJurusan(String namaJurusan) {
        this.namaJurusan = namaJurusan;
    }

    public Set<Mahasiswa> getMahasiswaList() {
        return mahasiswaList;
    }

    public void setMahasiswaList(Set<Mahasiswa> mahasiswaList) {
        this.mahasiswaList = mahasiswaList;
    }
}

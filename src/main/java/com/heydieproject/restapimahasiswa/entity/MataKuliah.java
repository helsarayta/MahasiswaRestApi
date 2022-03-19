package com.heydieproject.restapimahasiswa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tbl_mata_kuliah")
@JsonIdentityInfo(
        scope = MataKuliah.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class MataKuliah implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nama_mata_kuliah", nullable = false, unique = true)
    @NotEmpty(message = "Nama mata kuliah harus diisi!!")
    private String namaMataKuliah;
    @ManyToMany(mappedBy = "mataKuliahList")
    private Set<Mahasiswa> mahasiswaList;

    public MataKuliah() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaMataKuliah() {
        return namaMataKuliah;
    }

    public void setNamaMataKuliah(String namaMataKuliah) {
        this.namaMataKuliah = namaMataKuliah;
    }

    public Set<Mahasiswa> getMahasiswaList() {
        return mahasiswaList;
    }

    public void setMahasiswaList(Set<Mahasiswa> mahasiswaList) {
        this.mahasiswaList = mahasiswaList;
    }
}

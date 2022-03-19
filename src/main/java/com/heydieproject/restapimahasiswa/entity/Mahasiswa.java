package com.heydieproject.restapimahasiswa.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "tbl_mahasiswa")
@JsonIdentityInfo(
        scope = Mahasiswa.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Mahasiswa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nama_mahasiswa", nullable = false)
    @NotEmpty(message = "Nama mahasiswa harus diisi!!")
    private String nama;
    @Column(name = "nomor_mahasiswa", nullable = false, unique = true)
    @NotEmpty(message = "Nomor mahasiswa harus diisi!!")
    private String noMahasiswa;
    @Column(name = "umur_mahasiswa")
    private Integer umur;
    @ManyToMany
    @JoinTable(
            name = "tbl_mahasiswa_matakuliah",
            joinColumns = @JoinColumn(name = "mahasiswa_id"),
            inverseJoinColumns = @JoinColumn(name = "matakuliah_id"))

    private Set<MataKuliah> mataKuliahList;

    @ManyToOne
    @JoinColumn(name = "id_jurusan")
    private Jurusan jurusan;

    @ManyToOne
    @JoinColumn(name = "id_fakultas")
    private Fakultas fakultas;

    public Mahasiswa() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNoMahasiswa() {
        return noMahasiswa;
    }

    public void setNoMahasiswa(String noMahasiswa) {
        this.noMahasiswa = noMahasiswa;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public Set<MataKuliah> getMataKuliahList() {
        return mataKuliahList;
    }

    public void setMataKuliahList(Set<MataKuliah> mataKuliahList) {
        this.mataKuliahList = mataKuliahList;
    }

    public Jurusan getJurusan() {
        return jurusan;
    }

    public void setJurusan(Jurusan jurusan) {
        this.jurusan = jurusan;
    }

    public Fakultas getFakultas() {
        return fakultas;
    }

    public void setFakultas(Fakultas fakultas) {
        this.fakultas = fakultas;
    }
}

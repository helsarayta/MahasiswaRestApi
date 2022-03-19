package com.heydieproject.restapimahasiswa.repository;

import com.heydieproject.restapimahasiswa.entity.Mahasiswa;
import com.heydieproject.restapimahasiswa.entity.MataKuliah;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.websocket.server.PathParam;
import java.util.List;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {

    @Query("SELECT x FROM Mahasiswa x WHERE x.nama = :nama")
    public Mahasiswa searchByName(@PathParam("nama") String nama);

    @Query("SELECT x FROM Mahasiswa x WHERE x.nama LIKE :nama")
    public List<Mahasiswa> searchByNameLike(@PathParam("nama") String nama);

    @Query("SELECT x FROM Mahasiswa x WHERE x.jurusan.id = :jurusanId")
    public List<Mahasiswa> searchMahasiswaByJurusan(@PathParam("jurusanId") Long jurusanId);

    @Query("SELECT x FROM Mahasiswa x WHERE :mataKuliah MEMBER OF x.mataKuliahList")
    public List<Mahasiswa> searchMahasiswaByMataKuliah(@PathParam("mataKuliah")MataKuliah mataKuliah);

    Page<Mahasiswa> findAll(Pageable pageable);
    Page<Mahasiswa> findByNamaContains(String nama, Pageable pageable);
}

package com.heydieproject.restapimahasiswa.service.serviceImpl;

import com.heydieproject.restapimahasiswa.entity.Mahasiswa;
import com.heydieproject.restapimahasiswa.repository.MahasiswaRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    public String exportReport(String formatReport) throws FileNotFoundException, JRException {
        String urlPath = "C:\\Users\\Heydie\\Documents";

        List<Mahasiswa> allMahasiswa = mahasiswaRepository.findAll();

        File file = ResourceUtils.getFile("classpath:mahasiswa.jrxml");

        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(allMahasiswa);

        Map parameters = new HashMap<>();
        parameters.put("ReportBy", "Heydie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        if(formatReport.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint,urlPath + "\\mahasiswa.html");
        }
        if(formatReport.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint,urlPath + "\\mahasiswa.pdf");
        }

        return "report generate at : "+ urlPath;
    }

}

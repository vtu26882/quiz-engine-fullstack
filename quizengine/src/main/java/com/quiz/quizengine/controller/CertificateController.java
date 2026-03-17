package com.quiz.quizengine.controller;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.quiz.quizengine.service.CertificateService;

@RestController
@RequestMapping("/certificate")
@CrossOrigin(origins = "http://localhost:3000")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadCertificate(
            @RequestParam String username,
            @RequestParam String quizTitle,
            @RequestParam int score,
            @RequestParam int total
    ) throws Exception {

        File file = certificateService.generateCertificate(username, quizTitle, score, total);

        FileInputStream fis = new FileInputStream(file);
        byte[] data = fis.readAllBytes();
        fis.close();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF)
                .body(data);
    }
}
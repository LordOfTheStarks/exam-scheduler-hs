package com.hochschule.exam_scheduler.exam.pdfparser;

import com.hochschule.exam_scheduler.exam.model.Exam;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class ExamPdfParser {
    public List<Exam> parseTimeTablePDF(File file) throws IOException {
        List<Exam> exams = new ArrayList<>();

        try(PDDocument document = new PDDocument()) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

        }
        return exams;
    }
}

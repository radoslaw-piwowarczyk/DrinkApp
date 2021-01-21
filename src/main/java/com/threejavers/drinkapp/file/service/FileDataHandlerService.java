package com.threejavers.drinkapp.file.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileDataHandlerService {

    private final FileParserService fileParserService;
    private final FileUploadService fileUploadService;

    public void dataUploadHandler(Part partFile) throws IOException {
        fileParserService.parseDataToDatabase(fileUploadService.uploadFile(partFile));
    }
}

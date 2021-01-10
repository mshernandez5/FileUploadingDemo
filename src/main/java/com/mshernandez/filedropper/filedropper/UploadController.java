package com.mshernandez.filedropper.filedropper;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
public class UploadController
{
    @Value("${upload.directory}")
    private String uploadDirectory;

    /**
     * Allows a client to upload a file to the
     * default directory.
     * 
     * @param uploadedFile An uploaded file.
     */
    @RequestMapping("api/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile uploadedFile)
    {
        // Check In Case No File Provided In Request
        if (uploadedFile == null)
        {
            return;
        }
        // Where To Write The Received File
        File file = new File(uploadDirectory + File.separator + uploadedFile.getOriginalFilename());
        // Try To Write File (Overwriting Any With The Same Name)
        try (FileOutputStream fileOut = new FileOutputStream(file))
        {
            fileOut.write(uploadedFile.getBytes());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
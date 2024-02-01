package br.com.souza.files3upload.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class S3Service {

    private final AmazonS3 s3client;
    private static final String BUCKET_NAME = System.getenv("BUCKET_NAME");

    public S3Service(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    public void uploadFileIntoS3(MultipartFile file, String fileName) throws Exception{
        if(file != null && !file.isEmpty()){
            File newFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            try (OutputStream os = new FileOutputStream(newFile)) {
                os.write(file.getBytes());
            }

            try{
                s3client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, newFile));
            }catch (Exception e){
                log.error("Error to upload file into S3 Bucket.", e);
                throw e;
            }
            newFile.delete();
        }
    }

    public byte[] downloadFileFromS3(String fileName) throws Exception{
        try{
            S3Object s3Object = s3client.getObject(BUCKET_NAME, fileName);
            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
            return IOUtils.toByteArray(s3ObjectInputStream);
        }catch (Exception e){
            log.error("Error to download file from S3 Bucket.", e);
            throw e;
        }
    }
}

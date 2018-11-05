package br.com.trustsystems.elfinder;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class S3Utils {

    private static String ak ="";
    private static String sk="";
    public static void putObject(String keyName,InputStream inputStream ,String contentType) {
        AWSCredentials credentials = new BasicAWSCredentials(ak, sk);
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).withRegion("cn-north-1").build();
//        File file = new File("D:\\Downloads\\13.png");
//        FileInputStream inputStream=null;
//        try {
//            inputStream = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        if(keyName.startsWith("/")){
            keyName = keyName.replaceFirst("/","");
        }
        try {
            s3.putObject("taotest", keyName, inputStream, metadata);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            throw new RuntimeException(e);
        }
    }

    public static void deleteObject(String keyName) {
        AWSCredentials credentials = new BasicAWSCredentials(ak, sk);
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(credentials);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).withRegion("cn-north-1").build();

        if(keyName.startsWith("/")){
            keyName = keyName.replaceFirst("/","");
        }
        try {
            s3.deleteObject("taotest",keyName);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            throw new RuntimeException(e);
        }
    }
}

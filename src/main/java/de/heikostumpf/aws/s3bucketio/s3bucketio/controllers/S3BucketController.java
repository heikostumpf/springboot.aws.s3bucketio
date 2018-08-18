package de.heikostumpf.aws.s3bucketio.s3bucketio.controllers;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import de.heikostumpf.aws.s3bucketio.s3bucketio.models.BucketObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping(value = "/s3")
@RestController
public class S3BucketController {

    @RequestMapping(value = "/buckets", method = RequestMethod.GET, produces = "application/json")
    public List<Bucket> getBuckets() {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        List<Bucket> buckets = s3.listBuckets();
        return buckets;
    }

    @RequestMapping(value = "/object/{bucketName}")
    public List<S3ObjectSummary> getBucketObjects(@PathVariable String bucketName) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        ObjectListing ol = s3.listObjects(bucketName);
        List<S3ObjectSummary> objects = ol.getObjectSummaries();
        return objects;
    }

    @RequestMapping(value = "object", method = RequestMethod.PUT, produces = "application/json")
    public BucketObject addBucketObject(@RequestBody BucketObject bucketObject) {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        try {
            s3.putObject(bucketObject.getBucketName(), bucketObject.getFileName(), bucketObject.getFileContent());
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }

        return bucketObject;
    }

}

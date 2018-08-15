package de.heikostumpf.aws.s3bucketio.s3bucketio.controllers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping(value = "/s3")
@RestController
public class S3BucketController {

    @RequestMapping(value = "/buckets", method = RequestMethod.GET, produces = "application/json")
    public List<Bucket> getS3Buckets() {
        final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        List<Bucket> buckets = s3.listBuckets();
        return buckets;
    }

}

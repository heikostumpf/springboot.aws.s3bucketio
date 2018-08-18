package de.heikostumpf.aws.s3bucketio.s3bucketio.controllers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import de.heikostumpf.aws.s3bucketio.s3bucketio.models.BucketObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "object", method = RequestMethod.PUT, produces = "application/json")
    public BucketObject addBucketObject(@RequestBody BucketObject bucketObject) {
        return bucketObject;
    }

}

package com.san.dataflow;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.PipelineResult;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadFromS3 {

    private static final Logger log = LoggerFactory.getLogger(ReadFromS3.class);

    public static void main(String[] args) {
        Options options = PipelineOptionsFactory.fromArgs(args).withValidation().as(Options.class);
        Pipeline pipeline = Pipeline.create(options);

        AWSCredentials awsCredentials = new BasicAWSCredentials(options.getAWSAccessKey().get(),
                options.getAWSSecretKey().get());
        options.setAwsCredentialsProvider(new AWSStaticCredentialsProvider(awsCredentials));

        PCollection<String> fileLines =
                pipeline.apply("ReadFromFile", TextIO.read().from("s3://test.csv")); //Replace the file path

        fileLines.apply("PrintLines", MapElements.via(new SimpleFunction<String, Void>() {
            @Override
            public Void apply(String lines) {
                System.out.println(lines);
                return null;
            }
        }));

        PipelineResult result = pipeline.run();
        try {
            result.getState(); // To skip the error while creating the template
            result.waitUntilFinish();
        } catch (UnsupportedOperationException e) {
            log.error("UnsupportedOperationException :" + e.getMessage());
        } catch (Exception e) {
            log.error("Exception :" + e.getMessage(), e);
        }
    }
}

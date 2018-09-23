package com.san.dataflow;

import org.apache.beam.sdk.io.aws.options.S3Options;
import org.apache.beam.sdk.options.*;

public interface Options extends PipelineOptions, S3Options {
    @Description("AWS Access Key")
    @Validation.Required
    ValueProvider<String> getAWSAccessKey();
    void setAWSAccessKey(ValueProvider<String> value);

    @Description("AWS secret key")
    @Validation.Required
    ValueProvider<String> getAWSSecretKey();
    void setAWSSecretKey(ValueProvider<String> value);
}

# dataflow-input-from-s3
Read files from s3 and create pcollection from it.


1. Build the runnable jar

    mvn package
    
2. Run the code locally

  java -jar target/dataflow-input-from-s3-bundled-0.1.jar --tempLocation=gs://<PATH> --project=<GCP_PROJECT_ID> --AWSAccessKey="<AWS_ACCESS_KEY>" --AWSSecretKey="<AWS_SECRET_KEY>" --awsRegion="<AWS_REGION>"
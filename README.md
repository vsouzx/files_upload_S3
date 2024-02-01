# How to upload/download files in Bucket S3
<h3>In this example, I used the following technologies:</h3>
<p>-Java</p>
<p>-Spring Boot</p>
<p>-MongoDB</p>
<p>-Bucket S3</p>
<p>-AWS IAM</p>
<p>-AWS SDK (Software Development Kit)</p>

# What are the advantages of storing files in S3 instead of a database?
<p>-Performance: Storing images in database can increase the size of your database significantly, which might impact database performance, especially for read/write operations and backups.</p>
<p>-Accessibility and Scalability: S3 is designed for high availability and scalability. It is easier to serve images directly from S3, especially if your application has a high traffic.</p>

# What do you need to run the project in your machine?
<p>1. Have an account is AWS</p>
<p>2. Create a bucket S3</p>
<p>3. If the bucket is not public, you will need to create an user and a group that has permission to use S3</p>
<p>4. Then you need to generate the access key and the secret access key to be able to use the SDK in Java</p>

# Run the project step by step
<p>1. Clone the repository</p>
<p>2. Before running the project, you must pass some Enviroment Variables:</p>
<p>(of course, passing your respective values)</p>
<img src="https://github.com/vsouzx/FacialRecognitionApi/assets/88911545/ed70e057-fbdf-4ea1-aeec-3e613ae772cf"></img>

<br><br/>
<p>3. Also before running the project, you need to run the docker-compose.yml file to start the mongodb.</p>
<p>You can do this, running this command in your terminal: <code>docker compose up -d</code> </p>
<p>Then you´re ready to run the project.</p>

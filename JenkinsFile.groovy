pipeline {
    agent any
    stages {
        stage('Create Zip File') {
            steps {
                script{
                    fileOperations([fileDeleteOperation(excludes: '', includes: '/deploymentFile.zip')])
                } 
                
                script{
                    zip dir: '', exclude: '', glob: '', zipFile: 'deploymentFile.zip'
                } 
            }
        }
        stage('Deploy Lambda Function') {
            steps {
                deployLambda([alias: '', artifactLocation: 'deploymentFile.zip', awsAccessKeyId: 'AKIAW27ZI5T5JZ63WEJR', awsRegion: 'us-east-1', awsSecretKey: '{AQAAABAAAAAwb8aHtRM0Uc1O4YrCnqqVyRBOKbvmCKB4nzTSEA6qJoA4Y/IZN8Rd5cnM1g7A/w5iTrHYTgnpsn9D+XUXBZYDrg==}', deadLetterQueueArn: '', description: 'Managed by Jenkins Pipeline', environmentConfiguration: [kmsArn: ''], functionName: 'HelloWorldFunction', handler: 'lambda_function.lambda_handler', memorySize: '1024', role: 'arn:aws:iam::470284823802:role/lambdaDeploymentRole', runtime: 'python3.9', securityGroups: '', subnets: '', timeout: '300', updateMode: 'full'])
            }
        }
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }
    }
}
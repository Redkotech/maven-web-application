pipeline {
    agent any
    tools {
        maven 'my-maven'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn clean package'
                
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
        stage('CodeQuality') {
            steps {
                echo 'checking code quality....'
                //sh ' mvn sonar:sonar'
            }
            post {
                success {
                    echo 'Now archiving the artifacts'
                    archiveArtifacts artifacts: '**/*.war'
                }
            }
        }
        stage('Deploying') {
            steps {
                echo 'deploying app to tomcat'
                sh 'pwd'
                sh 'ls -la'
                sh "docker build -t dox2410/webapp:${env.BUILD_ID} ."
                sh "docker push dox2410/webapp:${env.BUILD_ID}"
                sh "docker run -it -d -name web-app -p 8081:8080 dox2410/webapp:${env.BUILD_ID}"
                 //build job: 'deploy-copy-war'
            }
        }
    }
}

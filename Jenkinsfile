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
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
            post {
                success {
                    echo 'Now archiving the artifacts'
                    archiveArtifacts artifacts: '**/*.war'
                }
            }
        }
        stage('staging') {
            steps {
                 build job: 'deploy-copy-war'
            }
        }
    }
}

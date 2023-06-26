mavenJob('mvn-DSLProject') {
  logRotator(-1, 10)
  displayName('maven-DSL-project')
  description('Builds and deploys a Maven project')
  mavenInstallation('Maven 3.9.2')

  scm {
    git('https://github.com/Redkotech/maven-web-application.git', 'master')
  }

  triggers {
    //scm('* * * * *')
     githubPush()
  }
  goals('clean package')
  
  publishers {
    archiveArtifacts('**/*.war')
    archiveJunit('**/target/surefire-reports/TEST-*.xml')
  }

/*  postBuild {
    always {
      emailext {
        recipientProviders {
          developers()
        }
        subject('Build Notification - ${PROJECT_NAME} - Build #${BUILD_NUMBER} - ${BUILD_STATUS}')
        body('''
          <p>Build Information:</p>
          <ul>
            <li>Project: ${PROJECT_NAME}</li>
            <li>Build Number: ${BUILD_NUMBER}</li>
            <li>Status: ${BUILD_STATUS}</li>
          </ul>
        ''')
      }
    }
  }*/
}
  

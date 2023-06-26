job('mvn-project') {
  displayName('maven-DSL-project')
  description('Builds and deploys a Maven project')

  scm {
    git('https://github.com/Redkotech/maven-web-application.git', 'master')
  }

  triggers {
    scm('* * * * *')
  }

  steps {
    maven('clean package', 'maven-standalone-application/pom.xml')
  }
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

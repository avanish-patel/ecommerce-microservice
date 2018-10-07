pipeline {
    agent any
    // { label 'master' }
    stages {
       stage('Build') {
          steps {
             sh './gradlew clean build -x test'
          }
       }
       stage('Deploy'){
                  steps{
                      sh 'cf push account-address-service -p ./build/libs/account-address-service-0.0.1-SNAPSHOT.jar'
                  }
              }
    }
}

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
               sh 'cf push shipment-service -p ./build/libs/shipment-service-0.0.1-SNAPSHOT.jar'
           }
       }
    }
}
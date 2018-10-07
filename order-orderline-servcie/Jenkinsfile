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
               sh 'cf push order-orderline-service -p ./build/libs/order-orderline-service-0.0.1-SNAPSHOT.jar'
           }
       }
    }
}
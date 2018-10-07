pipeline {
    agent any


    stages {
       stage('Build') {
          steps {
             sh './gradlew clean build -x test'
          }
       }
       stage('Deploy'){
                  steps{
                      sh 'cf push product-service -p ./build/libs/product-service-0.0.1-SNAPSHOT.jar'
                  }
       }
    }
}
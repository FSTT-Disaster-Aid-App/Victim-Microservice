
pipeline {
   tools {
     maven 'maven'
   }

  environment {
    dockerimagename = "ayga/gateway-app"
    dockerImage = ""
  }

  agent any

  stages {

    stage('Checkout Source') {
      steps {
      checkout scmGit(branches: [[name: 'main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/FSTT-Disaster-Aid-App/Victim-Microservice.git']])
				sh 'mvn clean package'
      }
    }

    stage('Build image') {
      steps{
        script {
          dockerImage = docker.build dockerimagename
        }
      }
    }

    stage('Pushing Image') {
      environment {
               registryCredential = 'dockerhublogin'
           }
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
            dockerImage.push("latest")
          }
        }
      }
    }

    stage('Deploying App to Kubernetes') {
      steps {
        script {
        kubernetesDeploy(configs: "victim-service.yaml", kubeconfigId: "kubernetes")
        }
      }
    }

  }

}

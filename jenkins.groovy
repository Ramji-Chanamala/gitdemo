pipeline {

    agent any
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven 'Maven 3.3.9' 
        jdk 'jdk8' 
    }
    stages{
        stage('checkout') {
            steps{
                //checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Ramji-Chanamala/DevOpsClassCodes.git']]])
                git 'https://github.com/Ramji-Chanamala/DevOpsClassCodes.git'
            }
        }
        stage('compile'){
            steps {
                sh 'mvn compile'
            } 
        }
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('cobertura'){
            steps {
                sh 'mvn clean cobertura:cobertura -Dcobertura.report.format=xml'
            }
        }   
        stage('Package'){
            steps {
                sh 'mvn clean install'
            }
        }   
    }

    post {
        success {
            archiveArtifacts 'target/*.war'
            junit 'target/surefire-reports/*.xml'
        }
    }   
}

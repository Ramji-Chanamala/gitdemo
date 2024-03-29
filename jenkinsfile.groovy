pipeline {

    agent any
    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "OnlineMaven"
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
}

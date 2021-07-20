  
pipeline {
    agent master
    stages {
		env.JAVA_HOME = tool name: 'myJava', type: 'jdk'
		def mvnHome = tool name: 'MyMaven', type: 'maven'
		def mvnCMD = "$mvnHome/bin/mvn"
        stage('checkout') {
            git 'https://github.com/rakeshdevops92/devops-1.git'
        }
		stage('compile') {
            echo "compiling your code"
			sh "${mvnCMD} compile"
        }
		stage('package') {
			echo "Packaging your code"
			sh "${mvnCMD} package"
		    }
    }
}

pipeline {
    agent any
    stages {
        stage('Package') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [],
                userRemoteConfigs: [[url: 'https://github.com/traccytian/Teedy_2024.git']])
                sh 'mvn -B -DskipTests clean package'
            }
        }
        
        // Building Docker images
        stage('Building image') {
            steps {
                sh 'docker build -t teedy_manual .'
            }
        }
        
        // Uploading Docker images into Docker Hub
        stage('Upload image') {
            steps {
                sh '''
                docker login
                docker tag teedy_manual transymbol/teedy_local:v1.0
                docker push transymbol/teedy_local:v1.0
                '''
            }
        }
        
        // Running Docker container
        stage('Run containers') {
            steps {
                sh '''
                docker run -d -p 8083:8080 --name teedy_jk01 teedy_manual
                docker run -d -p 8084:8080 --name teedy_jk02 teedy_manual
                docker run -d -p 8085:8080 --name teedy_jk03 teedy_manual
                '''
            }
        }
    }
}
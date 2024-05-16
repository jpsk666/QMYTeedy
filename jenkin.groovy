pipeline {
    agent any
    stages {
        stage('Package') {
            steps {
                checkout scm
                git(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/jpsk666/QMYTeedy.git']])
                sh 'mvn -B -DskipTests clean package'
            }
        }
        // Building Docker images
        stage('Building image') {
            steps {
                // 在这里添加构建 Docker 镜像的命令
            }
        }
        // Uploading Docker images into Docker Hub
        stage('Upload image') {
            steps {
                // 在这里添加上传 Docker 镜像到 Docker Hub 的命令
            }
        }
        // Running Docker container
        stage('Run containers') {
            steps {
                // 在这里添加运行 Docker 容器的命令
            }
        }
    }
}


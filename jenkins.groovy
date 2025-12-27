pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
    steps {
        sh 'sudo docker build -t java-devops-app .'
    }
}

stage('Deploy') {
    steps {
        sh '''
        sudo docker stop app || true
        sudo docker rm app || true
        sudo docker run -d -p 8080:8080 --name app java-devops-app
        '''
    }
}

    }
}

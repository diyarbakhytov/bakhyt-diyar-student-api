pipeline {
    agent any

    tools {
        jdk 'jdk21'
    }

    environment {
        STUDENT_NAME = 'Diyar Bakhyt'
        STUDENT_ID   = '38310'
        IMAGE_NAME   = 'bakhyt-diyar-student-api'
        IMAGE_TAG    = 'v1.1-bakhyt-diyar-38310'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                sh '''
                    echo "Student: $STUDENT_NAME (ID $STUDENT_ID)"
                    git log -1 --oneline
                    java -version
                '''
            }
        }
        stage('Build') {
            steps {
                sh '''
                    echo "Building Student API by $STUDENT_NAME"
                    chmod +x gradlew
                    ./gradlew clean bootJar -x test --no-daemon
                    ls -l build/libs
                '''
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test --no-daemon'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'build/test-results/test/*.xml'
                }
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME:$IMAGE_TAG .'
            }
        }
        stage('Verify') {
            steps {
                sh '''
                    docker image inspect $IMAGE_NAME:$IMAGE_TAG --format 'Image: {{.RepoTags}} Size: {{.Size}} Created: {{.Created}}'
                    docker images $IMAGE_NAME
                    echo "Verification passed for $STUDENT_NAME: image $IMAGE_NAME:$IMAGE_TAG exists"
                '''
            }
        }
    }

    post {
        success { echo "SUCCESS: pipeline completed for ${STUDENT_NAME}" }
        failure { echo "FAILURE: pipeline failed for ${STUDENT_NAME}" }
    }
}

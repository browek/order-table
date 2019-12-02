pipeline {
    agent any

    stages {
        stage('Build') {
            stages {
                stage('Build backend') {
                    steps {
                        dir('backend') {
                            sh 'echo "RUN BACKEND BUILD"'
                            sh './gradlew build -x test'
                        }
                    }
                }

                stage('Build and test frontend') {
                    agent {
                        docker {
                            image 'node:alpine'
                        }
                    }
                    stages {
                        stage('Build and test frontend') {
                            steps {
                                dir('frontend') {
                                    sh 'echo "RUN FRONTEND BUILD"'
                                    sh 'yarn install'
                                    sh 'yarn build'
                                }
                            }
                        }
                    }
                }
            }
        }

        stage('Unit tests') {
            steps {
                dir('backend/build/test-results/test') {
                    sh 'echo "REMOVE OLD TESTS RESULTS"'
                    deleteDir()
                }
                dir('backend') {
                    sh 'echo "RUN BACKEND UNIT TESTS"'
                    sh './gradlew test'
                }
            }
        }

        stage('Publish test results') {
            steps {
                sh 'echo "PUBLISH TESTS RESULTS"'
                junit '**/test-results/test/*.xml'
            }
        }
    }
    post { 
        always { 
            sh 'docker-compose up'
        }
    }
}
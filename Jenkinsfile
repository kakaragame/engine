pipeline {
    agent any
    tools {
        jdk 'Java 11'
    }
    stages {
        stage ('Build') {
            steps {
              sh 'git checkout -b ${GIT_BRANCH} origin/${GIT_BRANCH}'
              sh 'sh build.sh'
            }
            post {
                success {
                    archiveArtifacts artifacts: 'archives/*.jar, files.txt, build/version.properties', followSymlinks: false
                    javadoc javadocDir: 'build/docs/javadoc', keepAll: true
                }
            }
        }
    }
}
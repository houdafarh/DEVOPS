pipeline {
    agent any
stages {
stage("Cloning Project from Git") {
steps { 
git branch: 'Mayssa', credentialsId: 'GitCredentials', url: 'https://github.com/houdafarh/DEVOPS.git'
}
}
stage("Build") {
steps {
dir("TimesheetProject"){
bat "mvn compile"}
}}
stage("Unit tests") {
steps {
dir("TimesheetProject"){
bat "mvn test"}
}}

stage("Code Quality") {
steps {
dir("TimesheetProject"){
bat "mvn sonar:sonar"}
}}

stage("Clean- Package"){
steps {
dir("TimesheetProject"){
bat "mvn clean package"}
}}

stage("DEPLOY with Nexus") {
steps {
dir("TimesheetProject"){
bat "mvn deploy:deploy-file -DgroupId=tn.esprit.spring -DartifactId=Timesheet-spring-boot-core-data-jpa-mvc-REST-1 -Dversion=2.0 -DgeneratePom=true -Dpackaging=war -DrepositoryId=deploymentRepo -Durl=http://localhost:8081/repository/maven-releases/ -Dfile=target/Timesheet-spring-boot-core-data-jpa-mvc-REST-1-2.0.war -DskipTests"
}}
}

    stage("Email Notification"){
        steps {
            dir("TimesheetProject"){
    emailext attachLog: true, body: '''End of Pipeline
Finished: SUCCESS''', subject: '#Success', to: 'abdellaoui.mayssa1@esprit.tn'} } }
}
}

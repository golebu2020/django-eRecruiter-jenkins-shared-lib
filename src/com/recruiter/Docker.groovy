#!/usr/bin/env groovy

package com.recruiter

class Docker implements Serializable {

    def script

    Docker(script){
        this.script = script
    }

    //golebu2020/maven-repo:tagname

    def dockerBuildImage(String imageName){
        script.echo "building and pushing..."
        script.sh "docker build --tag golebu2020/maven-repo:${imageName} ."
//        script.sh "docker tag ${imageName} golebu2020/maven-repo:${imageName}"
    }

    def pushDockerImage(String imageName){
        script.echo "Pushing..."
        script.withCredentials([script.usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'USR', passwordVariable: 'PASS')]){
            script.sh "echo ${script.PASS} | docker login -u ${script.USR} --password-stdin"
            script.sh "docker push golebu2020/maven-repo:${imageName}"
        }
    }
}

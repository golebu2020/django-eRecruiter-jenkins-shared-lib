#!/usr/bin/env groovy

package com.recruiter

class Docker implements Serializable {

    def script

    Docker(script){
        this.script = script
    }

    def dockerBuildImage(String imageName){
        script.echo "building and pushing..."
        script.sh "docker build --tag ${imageName} ."
        script.sh "docker tag ${imageName} 46.101.168.73:8082/${imageName}"
    }

    def pushDockerImage(String imageName){
        script.withCredentials([script.usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'USR', passwordVariable: 'PASS')]){
            script.sh "echo ${script.PASS} | docker login -u ${script.USR} --password-stdin 46.101.168.73:8082"
            script.sh "docker push 46.101.168.73:8082/${imageName}"
        }
    }
}

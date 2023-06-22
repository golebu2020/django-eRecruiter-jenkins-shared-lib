#!/usr/bin/env groovy
import com.recruiter.Docker

def call(String imageName){
    return new Docker(this).dockerBuildImage(imageName)
}
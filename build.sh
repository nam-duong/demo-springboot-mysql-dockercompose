#!/usr/bin/env bash

mvn clean package

docker build -t demo-spring-boot .

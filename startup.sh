#!/usr/bin/env bash
gradle build
gradle bootRun -p eureka-server &
gradle bootRun -p users-service &
gradle bootRun -p api-service &
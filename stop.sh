#!/usr/bin/env bash
kill $(cat users-service/user-micro-service.pid)
kill $(cat eureka-server/eureka-server.pid)
kill $(cat api-service/application.pid)
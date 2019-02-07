#!/usr/bin/env bash
mvn clean package
docker build -t djorquab/relational .
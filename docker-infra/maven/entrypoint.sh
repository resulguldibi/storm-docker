#!/bin/bash

mvn -f /source-code/storm-topologies/pom.xml clean package
cp -rp /source-code/storm-topologies/target/*.jar /output

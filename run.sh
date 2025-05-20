#!/bin/bash
mvn exec:java -Dexec.mainClass="com.example.App" -Dexec.args="$@"
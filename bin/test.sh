#!/bin/bash

echo "auditing NonViolator class:"
java -classpath brainmethodcheck-1.0.jar:checkstyle-8.42-all.jar com.puppycrawl.tools.checkstyle.Main -c mychecks.xml NonViolator.java
echo "======================================================================================"
echo "auditing violator class:" 
java -classpath brainmethodcheck-1.0.jar:checkstyle-8.42-all.jar com.puppycrawl.tools.checkstyle.Main -c mychecks.xml Violator.java
echo "======================================================================================"
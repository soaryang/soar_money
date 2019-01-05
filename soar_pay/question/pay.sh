#!/bin/bash

echo "Hello,Danmu System Server have four projects...."
echo "1.soar_pay"
echo "If you want to deploy the project.please input code:"

read char

echo 'Input a number between 1 to 3'
echo "1:start,2:stop,3:restart"
echo 'Your number is:\c'

read aNum


#array=(element1 element2 element3 .... elementN)  

arrayname=(soar_pay)

array=(1)

FileName=""

basePath=/home/workspace

for data in ${array[@]}
   do
   if [ $data -eq $char ];then 
        FileName=${arrayname[$data-1]}
	break  
   fi   
done

echo $FileName" is ready to deploy......."

if [ "$FileName" = "" ]; then  
  echo "code is wrong"
  echo "bye bye!"
  exit 0  
fi


DeployFile_PATH=$basePath/deployworkspace/soar_money/$FileName

SOURCE_PATH=$basePath/sourcecode/soar_money
echo "$SOURCE_PATH"
#pidFileName
pidFileName=$FileName".pid"

#jar name
javaFileName=$FileName"-1.0-SNAPSHOT.jar"
echo "jarName:"$javaFileName

propertiseFile=$basePath/config/java/$FileName".properties"

function restart(){
   doMvn;
   stop;
   start;
}

function doMvn(){
	cd $SOURCE_PATH
	git pull origin master
	mvn clean install  -Dmaven.test.skip
	cd $SOURCE_PATH/$FileName/target
	cp -r $javaFileName $DeployFile_PATH/$javaFileName
	echo "cp -r $javaFileName $DeployFile_PATH/$javaFileName"
}

function stop(){
	echo "pidpath:$DeployFile_PATH/$pidFileName"
    PID=$(cat $DeployFile_PATH/$pidFileName)
    kill -9 $PID
}

function start(){
	
    nohup java  -Xms256m -Xmx256m -jar $DeployFile_PATH/$javaFileName  --spring.config.location=$propertiseFile > /dev/null 2>&1 &
    echo "java  -Xms256m -Xmx256m -jar $DeployFile_PATH/$javaFileName  --spring.config.location=$propertiseFile"
    cd $DeployFile_PATH
	
    echo $! > $pidFileName

    echo $FileName"deploy success"
}



case $aNum in
    1)  start;
    ;;
    2)  stop;
    ;;
    3)  restart;
    ;;
    *)  exit 0;
    ;;
esac


exit 0


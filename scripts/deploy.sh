
REPOSITORY=/home/ubuntu/one-bucket
cd $REPOSITORY

APP_NAME=one_bucket
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep 'SNAPSHOT.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(pgrep -f $APP_NAME)
if [ -z $CURRENT_PID ];
then
  echo "> 종료할것 없음."

else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 10
  if ps -p $CURRENT_PID > /dev/null; then
     echo "> 프로세스가 종료되지 않았으므로 kill -9로 강제 종료합니다."
     kill -9 $CURRENT_PID
  fi
fi
sleep 5

# echo "> $JAR_PATH 배포"
# java -jar $JAR_PATH 

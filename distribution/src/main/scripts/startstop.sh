#!/bin/sh
USER=magneto
WORK_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
PIDFILE=$WORK_DIR/magneto.pid
JAR=$WORK_DIR/magneto.jar

isProcessRunning() {
  [ "$(ps -p $1 | wc -l)" -gt 1 ]
}

waitForStop() {
  PID=`cat $PIDFILE`
  echo "Sending server the stop signal"
  kill $PID
  sleep 3;
  if isProcessRunning $PID ; then
    echo "Process still running";
    sleep 10;
    if  isProcessRunning $PID ; then
      echo "Process hasent shut down. Killing it.";
      kill -9 $PID
      rm $PIDFILE;
      sleep 3;
    else
      echo "Shutdown complete"
    fi
  else
    echo "Shutdown complete"
  fi
}

startMagneto() {
    if [ -f $PIDFILE ] ; then
		PID=`cat $PIDFILE`;
                if isProcessRunning $PID ; then
                        echo "Process already running with pid $PID"
                        exit 1
                else
                        chown $USER $PIDFILE
                fi
        else
                touch $PIDFILE
                chown $USER $PIDFILE
        fi
    echo "Starting Magneto"
    cd $WORK_DIR

	if [ ! -d logs ]; then
		sudo -u $USER mkdir logs
	fi

    sudo -u $USER java -jar  $JAR 1> /dev/null 2> logs/error.log &
    echo $! > $PIDFILE
}

case "$1" in
  start)
    startMagneto;
    ;;
  stop)
    waitForStop;
    ;;
  restart)
    echo "Restarting Magneto"
    waitForStop;
    startMagneto;
    ;;
    status)
    if [ ! -f $PIDFILE ] ; then
	echo "Not running. No PID found"
	exit 1;
    else
	PID=`cat $PIDFILE`;
	if isProcessRunning $PID ; then
		echo "Running with PID $PID"
		exit 0
	else
		echo "Not running. No process with PID $PID found"
		exit 1
	fi
     fi
     ;;
  *)
    echo "Use start, stop or restart for this script"
    ;;
esac
exit 0

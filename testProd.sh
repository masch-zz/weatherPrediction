#!/bin/bash

if [ -z "$1" ]
  then
    echo "No day supplied. Usage: testProd.sh [Number of day]"
    exit 1
fi

DAY=$1


curl https://planet-weather-prediction.herokuapp.com/clima?dia="$DAY"

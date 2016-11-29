#!/bin/bash

while :
do

    for i in `seq 1 20`
    do
        sleep .01
        curl -o /dev/null -s -w "$i %{time_total}\n" localhost:8080/dynamic/$i
    done

done
#!/bin/bash

while :
do

    for i in `seq 1 10`
    do
        sleep .1
        curl -o /dev/null -s -w "$i %{time_total}\n" localhost:8080/static/$i
    done

done
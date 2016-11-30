#!/bin/bash

while :
do

        sleep .2
        curl -o /dev/null -s -w "$i %{time_total}\n" localhost:8080/countTo/1 &

done
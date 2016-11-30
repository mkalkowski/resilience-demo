#!/bin/bash

while :
do

        sleep .01
        curl -o /dev/null -s -w "$i %{time_total}\n" localhost:8080/print/1

done
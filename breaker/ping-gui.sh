#!/bin/bash

while :
do
    sleep .2
    curl -s localhost:8080/top/counterparty &
done

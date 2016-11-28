#!/bin/bash

while :
do
    sleep .3
    curl -s localhost:8080/top/counterparty
done

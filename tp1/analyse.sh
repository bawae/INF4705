#!/bin/sh

FILE_NAME="analyse.csv"
FILE_LOCATION="logs"
TEST_FILES="./tp1-H10-donnees/*.txt"
SORT_ARRAY=( bubble counting quick quickMed quickSeuil quickMedSeuil )
SORT_ARRAY_ELEMENTS=${#SORT_ARRAY[@]}
TEST_FILES_ELEMENTS=${#TEST_FILES[@]}

# Déclarer les colonnes du tableau
echo "Nom du fichier,bubble,counting,quick,quickMed,quickSeuil,quickMedSeuil \n" > $FILE_LOCATION/$FILE_NAME

for ((i=0;i<TEST_FILES_ELEMENTS;i++));
do
	echo "$TEST_FILES[i]," > $FILE_LOCATION/$FILE_NAME
	for ((i=0;i<SORT_ARRAY_ELEMENTS;i++));
	do
		java -jar $SORT_ARRAY[i] "-f $TEST_FILES[i]" > $FILE_LOCATION/$FILE_NAME
	done
	echo "\n" > $FILE_LOCATION/$FILE_NAME
done
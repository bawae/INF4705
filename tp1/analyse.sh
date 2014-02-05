#!/bin/sh

FILE_NAME="analyse.csv"
FILE_LOCATION="logs"
TEST_FILES_LOCATION='tp1-H10-donnees'
SORT_ARRAY=( bubble counting quick quickMed quickSeuil quickMedSeuil )
SORT_ARRAY_ELEMENTS=${#SORT_ARRAY[@]}

echo -e "Nom du fichier,bubble,counting,quick,quickMed,quickSeuil,quickMedSeuil" > $FILE_LOCATION/$FILE_NAME

for f in $TEST_FILES_LOCATION/*
do
	echo $f"," >> $FILE_LOCATION/$FILE_NAME

	for ((j=0;j<SORT_ARRAY_ELEMENTS;j++))
	do
		java -jar "${SORT_ARRAY[j]}".jar -f $f >> $FILE_LOCATION/$FILE_NAME
	done

	echo -e "" >> $FILE_LOCATION/$FILE_NAME
done

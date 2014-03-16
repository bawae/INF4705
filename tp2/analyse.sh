#!/bin/sh

FILE_NAME="analyse.csv"
FILE_LOCATION="logs"
TEST_FILES_LOCATION='Restos'
SORT_ARRAY=( vorace dynamique local )
SORT_ARRAY_ELEMENTS=${#SORT_ARRAY[@]}

echo -e "Nom du fichier,vorace,dynamique,local" > $FILE_LOCATION/$FILE_NAME

for f in $TEST_FILES_LOCATION/*
do
	echo -n $f >> $FILE_LOCATION/$FILE_NAME

	for ((j=0;j<SORT_ARRAY_ELEMENTS;j++))
	do
		echo -n "," >> $FILE_LOCATION/$FILE_NAME
		java -jar ${SORT_ARRAY[j]}.jar -f $f >> $FILE_LOCATION/$FILE_NAME
	done

	echo -e "" >> $FILE_LOCATION/$FILE_NAME
done

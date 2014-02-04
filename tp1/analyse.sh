#!/bin/sh

FILE_NAME="analyse.csv"
FILE_LOCATION="logs"
TEST_FILES="./tp1-H10-donnees/*.txt"
SORT_ARRAY=('bubble' 'counting' 'quick' 'quickMed' 'quickSeuil' 'quickMedSeuil')
SORT_ARGS="-f $FILE"

# Déclarer les colonnes du tableau
echo "Nom du fichier,bubble,counting,quick,quickMed,quickSeuil,quickMedSeuil \n" > $FILE_LOCATION/$FILE_NAME

for f in $TEST_FILES
do
	echo "$f,"
	for a in $SORT_ARRAY
	do
		java -jar $a "$SORT_ARGS," > $FILE_LOCATION/$FILE_NAME
	done
	echo "\n"
done
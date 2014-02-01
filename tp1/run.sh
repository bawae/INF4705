#!/bin/sh
FILE_NAME="seuil.csv"
FILE_LOCATION="logs"
TEST_FILES="./tp1-H10-donnees/*.txt"
SORT_ARRAY=('quicksortmedseuil', 'quicksortseuil')
SORT_ARGS="-f $FILE -p"

# Déclarer les colonnes du tableau
echo -e "Nom du fichier,Algorithme,Seuil,Temps d'execution" > $FILE_LOCATION/$FILE_NAME

for f in $TEST_FILES
do
	for i=2 to 100
	do
		for f in $SORT_ARRAY
		do
			java -jar $f $SORT_ARGS > $FILE_LOCATION/$FILE_NAME
		done
	done
done

#!/bin/bash
FILE_NAME='seuil.csv'
FILE_LOCATION='logs'
TEST_FILES_LOCATION='tp1-H10-donnees'
TEST_FILE_ARRAY=(1000 5000 10000 50000 100000)
SORT_ARRAY=(quickSeuil quickMedSeuil)

# Déclarer les colonnes du tableau
echo -e "Nom du fichier,Algorithme,Seuil,Temps d'execution" > $FILE_LOCATION/$FILE_NAME

for f in ${TEST_FILE_ARRAY[@]}
do
	for((j=0; j < 30; j++))
	do
		TEST_FILE_NAME="testset_"$f"_"$j".txt"

		for((i=5; i <= 50; i++))
		do
	#		SORT_ARGS="-f ""$TEST_FILES_LOCATION""/""$TEST_FILE_NAME"" -s ""$i

			for sort in ${SORT_ARRAY[@]}
			do
				echo -n $TEST_FILE_NAME,$sort,$i, >> $FILE_LOCATION/$FILE_NAME
	#			echo -e $sort.jar $SORT_ARGS
				java -jar $sort.jar -f $TEST_FILES_LOCATION/$TEST_FILE_NAME -s $i >> $FILE_LOCATION/$FILE_NAME
			done
		done
	done
done

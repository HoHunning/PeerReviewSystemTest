# Old version of testing
# var
MainClass="Main"
NumTestCase=39

# compile
javac *.java

# run test cases (39+1)
# TC0 is sampleinput
> diff.out
> diff_clear.out
for((i=0;i<=$NumTestCase;i++)) do
	echo "Case$i:"
	echo "Case$i:" >> diff.out
	echo "Case$i:" >> diff_clear.out
	
	# diff origin
	java $MainClass TC$i.in > c$i.out
	diff TC$i.out c$i.out >> diff.out

	# diff remove space
	java OutputParser TC$i.out answerTestCase$i.out
	java OutputParser c$i.out studentTestCase$i.out
	diff answerTestCase$i.out studentTestCase$i.out >> diff_clear.out

	if [[ $i != $NumTestCase ]]; then
		echo "-------------------------------------" >> diff.out 
		echo "-------------------------------------" >> diff_clear.out
	fi
done

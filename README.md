# PeerReviewSystemTest

## Goal
- This project tries to manipulate **junit** to judge the inclass test - Peer Review System in our course, Software Design.

- There are two stages for our judge. One is create junit test cases, and another is test the result by **gradlew**. 

## Structure
```
src
|_ main
   |_ java/com/example/demo
|  |  |_ PeerReviewSystem/{answer submit by students}
|  |  |_ DemoApplication(where we create junit test cases)
|  |_ resources/templates/PeerReviewSystem/PeerReviewSystemTest.java.ftl (the junit test cases template)
|
|_ test/java/com/example/demo
|  |_ TestCase/{place we put origin test cases and other inputs}
```
## Steps
- put answers, testcases, inputs to coresponding directory.
- create test cases
```
./gradlew build
java -jar build/libs/PeerReviewSystemTest-0.0.1-SNAPSHOT.jar ${number of test cases}
```
- test
```
./gradlew test
```
- after test
```
./gradlew clean
```
- We will write the steps as a script in the future.
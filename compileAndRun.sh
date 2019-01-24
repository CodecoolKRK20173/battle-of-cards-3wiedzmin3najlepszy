#!/bin/bash
rm -f test.db && mvn install && java -cp target/BattleOfCards-1.0.jar:target/sqlite-jdbc-3.25.2.jar com.codecool.battleofcards.main.Main

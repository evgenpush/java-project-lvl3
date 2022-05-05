install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew checkstyleMain checkstyleTest

test:
	./gradlew test

.PHONY: build

build:
	./gradlew clean install checkstyleMain test

report:
	./gradlew jacocoTestReport

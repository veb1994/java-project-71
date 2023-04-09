compare:
	@./build/install/app/bin/app $(FILE1) $(FILE2)

run-dist:
	@./build/install/app/bin/app
	
report:
	@./gradlew jacocoTestReport
	
build:
	@./gradlew clean build

clean:
	@./gradlew clean

install:
	@./gradlew installDist
	
.PHONY: build

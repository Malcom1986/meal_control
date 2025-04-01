start:
	docker compose up
test:
	./gradlew test
lint:
	./gradlew checkstyleMain checkstyleTest
start-dev:
	export SPRING_PROFILES_ACTIVE=dev && ./gradlew bootRun
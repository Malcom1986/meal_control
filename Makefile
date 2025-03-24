start:
	docker compose up
test:
	./gradlew test
lint:
	./gradlew checkstyleMain checkstyleTest
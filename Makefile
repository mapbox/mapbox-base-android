.PHONY: check
check:
	./gradlew ktlint;
	./gradlew lint;
	sh scripts/kdoc-validate.sh;
	python scripts/license-validate.py
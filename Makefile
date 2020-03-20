.PHONY: check
check:
	./gradlew ktlint;
	sh scripts/kdoc-validate.sh;
	python scripts/license-validate.py

.PHONY: buildDebug
buildDebug:
	./gradlew annotations:assemble && \
    ./gradlew annotations-processor:assemble && \
    ./gradlew common:assembleDebug && \
    ./gradlew liblogger:assembleDebug

.PHONY: buildRelease
buildRelease:
	./gradlew annotations:assemble && \
    ./gradlew annotations-processor:assemble && \
    ./gradlew common:assembleRelease && \
    ./gradlew liblogger:assembleRelease

.PHONY: bintrayPublish
bintrayPublish:
	./gradlew :annotations:bintrayUpload ; \
	./gradlew :annotations-processor:bintrayUpload ; \
	./gradlew :common:bintrayUpload ; \
	./gradlew :liblogger:bintrayUpload ; \

.PHONY: artifactoryPublish
artifactoryPublish:
	./gradlew :annotations:artifactoryPublish ; \
	./gradlew :annotations-processor:artifactoryPublish ; \
	./gradlew :common:artifactoryPublish ; \
	./gradlew :liblogger:artifactoryPublish ; \

.PHONY: runUnitTests
runUnitTests:
	./gradlew test
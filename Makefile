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
    ./gradlew liblogger:assembleDebug && \
    ./gradlew libloader:assembleDebug

.PHONY: buildRelease
buildRelease:
	./gradlew annotations:assemble && \
    ./gradlew annotations-processor:assemble && \
    ./gradlew common:assembleRelease && \
    ./gradlew liblogger:assembleRelease && \
    ./gradlew libloader:assembleRelease

.PHONY: bintrayPublish
bintrayPublish:
	./gradlew :annotations:bintrayUpload ; \
	./gradlew :annotations-processor:bintrayUpload ; \
	./gradlew :common:bintrayUpload ; \
	./gradlew :liblogger:bintrayUpload ; \
	./gradlew :libloader:bintrayUpload ; \

.PHONY: artifactoryPublish
artifactoryPublish:
	./gradlew :annotations:artifactoryPublish ; \
	./gradlew :annotations-processor:artifactoryPublish ; \
	./gradlew :common:artifactoryPublish ; \
	./gradlew :liblogger:artifactoryPublish ; \
	./gradlew :libloader:artifactoryPublish ; \

.PHONY: runUnitTests
runUnitTests:
	./gradlew test
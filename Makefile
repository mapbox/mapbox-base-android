.PHONY: check
check:
	./gradlew ktlint;
	./gradlew lint;
	sh scripts/kdoc-validate.sh;
	python scripts/license-validate.py

.PHONY: buildDebug
buildDebug:
	./gradlew annotations:assemble && \
    ./gradlew annotations-processor:assemble && \
    ./gradlew base:assembleDebug

.PHONY: buildRelease
buildRelease:
	./gradlew annotations:assemble && \
    ./gradlew annotations-processor:assemble && \
    ./gradlew base:assembleRelease

.PHONY: bintrayPublish
bintrayPublish:
	./gradlew :annotations:bintrayUpload ; \
	./gradlew :annotations-processor:bintrayUpload ; \
	./gradlew :base:bintrayUpload ; \

.PHONY: artifactoryPublish
artifactoryPublish:
	./gradlew :annotations:artifactoryPublish ; \
	./gradlew :annotations-processor:artifactoryPublish ; \
	./gradlew :base:artifactoryPublish ; \
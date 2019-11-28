.PHONY: check
check:
	./gradlew ktlint;
	sh scripts/kdoc-validate.sh;
	python scripts/license-validate.py

.PHONY: buildDebug
buildDebug:
	./gradlew annotations:assemble && \
    ./gradlew annotations-processor:assemble && \
    ./gradlew common:assemble

.PHONY: buildRelease
buildRelease:
	./gradlew annotations:assemble && \
    ./gradlew annotations-processor:assemble && \
    ./gradlew common:assemble

.PHONY: bintrayPublish
bintrayPublish:
	./gradlew :annotations:bintrayUpload ; \
	./gradlew :annotations-processor:bintrayUpload ; \
	./gradlew :common:bintrayUpload ; \

.PHONY: artifactoryPublish
artifactoryPublish:
	./gradlew :annotations:artifactoryPublish ; \
	./gradlew :annotations-processor:artifactoryPublish ; \
	./gradlew :common:artifactoryPublish ; \
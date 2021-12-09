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

.PHONY: sdkRegistryPublish
sdkRegistryPublish:
	./gradlew :annotations:mapboxSDKRegistryUpload ; \
	./gradlew :annotations-processor:mapboxSDKRegistryUpload ; \
	./gradlew :common:mapboxSDKRegistryUpload ; \
	./gradlew :liblogger:mapboxSDKRegistryUpload ; \
	./gradlew :libloader:mapboxSDKRegistryUpload ; \

.PHONY: runUnitTests
runUnitTests:
	./gradlew test

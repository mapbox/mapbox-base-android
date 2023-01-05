#!/usr/bin/python
import json
import os
import datetime

# run gradle license generation
os.system("./gradlew common:licenseReleaseReport")

# convert output to LICENSE.md file
path = os.getcwd()
try:
    now = datetime.datetime.now()
    with open(path + "/LICENSE.md", 'w+') as licenseFile:
        licenseFile.write("<!-- This file was generated. Use `python scripts/license-generate.py`to update. -->  \n")
        licenseFile.write("### License\n")
        licenseFile.write("\n")
        licenseFile.write("Mapbox Base SDK for Android\n")
        licenseFile.write("\n")
        licenseFile.write("Copyright &copy; 2021 - {} Mapbox, Inc. All rights reserved.\n".format(now.year))
        licenseFile.write("\n")
        licenseFile.write("The software and files in this repository (collectively, “Software”) are licensed under the Mapbox TOS for use only with the relevant Mapbox product(s) listed at www.mapbox.com/pricing. This license allows developers with a current active Mapbox account to use and modify the authorized portions of the Software as needed for use only with the relevant Mapbox product(s) through their Mapbox account in accordance with the Mapbox TOS.  This license terminates automatically if a developer no longer has a Mapbox account in good standing or breaches the Mapbox TOS. For the license terms, please see the Mapbox TOS at https://www.mapbox.com/legal/tos/ which incorporates the Mapbox Product Terms at www.mapbox.com/legal/service-terms.  If this Software is a SDK, modifications that change or interfere with marked portions of the code related to billing, accounting, or data collection are not authorized and the SDK sends limited de-identified location and usage data which is used in accordance with the Mapbox TOS. [Updated {}-{:02d}]\n".format(now.year, now.month))
        licenseFile.write("\n")
        licenseFile.write("## Additional Mapbox Base Android licenses\n")
        with open(path + "/common/build/reports/licenses/licenseReport.json", 'r') as dataFile:
            data = json.load(dataFile)

            gradleLicensePlugin ="""
            {
                "project": "Gradle License Plugin",
                "url": "https://github.com/jaredsburrows/gradle-license-plugin",
                "licenses": [
                    {
                        "license": "The Apache Software License, Version 2.0",
                        "license_url": "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                 ]
            }
            """
            data.append(json.loads(gradleLicensePlugin))

            for entry in data:
                projectName = entry["project"]
                projectUrl = entry["url"]
                for license in entry["licenses"]:
                    licenseName = license["license"]
                    licenseUrl = license["license_url"]

                licenseFile.write("Mapbox Base Android uses portions of the %s.  \n" % projectName +
                                  ("URL: [%s](%s)  \n" % (projectUrl, projectUrl) if projectUrl is not None else "") +
                                  "License: [%s](%s)" % (licenseName, licenseUrl) +
                                  "\n\n===========================================================================\n\n")
    licenseFile.close()
except IOError as err:
    print("I/O error({0}): {1}".format(err.errno, err.strerror))

# remove obsolete open_source_licenses.html
# os.system("rm -f common/src/main/assets/open_source_licenses.html")



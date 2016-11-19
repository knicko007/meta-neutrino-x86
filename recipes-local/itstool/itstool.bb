DESCRIPTION = "itstool"
HOMEPAGE = ""
SECTION = "console/utils"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=59c57b95fd7d0e9e238ebbc7ad47c5a5"

DEPENDS += "libxml2-native"

SRCREV ?= "${AUTOREV}"
PV = "${SRCPV}"
PR = "1"

SRC_URI = "git://github.com/itstool/itstool.git;protocol=http \
"

S = "${WORKDIR}/git"


inherit autotools

BBCLASSEXTEND = "native nativesdk"

FILES_${PN}_append += "/usr/share"

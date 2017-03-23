DESCRIPTION = "PyXDG is a python library to access freedesktop.org standards"
SECTION = "devel/python"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=f30a9716ef3762e3467a2f62bf790f0a"
PR = "r2"

SRC_URI = "http://www.freedesktop.org/~lanius/pyxdg-${PV}.tar.gz"
SRC_URI += "file://desktopentry-cmp.patch"

S = "${WORKDIR}/pyxdg-${PV}"

inherit distutils

SRC_URI[md5sum] = "86a5441285fc908145414b63348d11a3"
SRC_URI[sha256sum] = "c2adf20955cb62af5c94622d0dd47fb82ee63da33c03d5a1f82337ba83044bb7"

DESCRIPTION = "meld - compare and merge files"
HOMEPAGE = "https://github.com/GNOME/meld.git"
SECTION = "console/utils"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

DEPENDS = "python itstool-native"
RDEPENDS_${PN} = "python-core gtk+ python-pygobject python-pygtk python-pycairo gtksourceview python-numbers"

SRCREV ?= "${AUTOREV}"
PV = "${SRCPV}"
PR = "1"

SRC_URI = "git://github.com/GNOME/meld.git;branch=meld-1-8;protocol=http \
	   file://change_prefix.patch \
	   file://0001-fix-build-if-using-python3.patch \
	   file://scrollkeeper.patch \
	   file://0001-change-colors-to-make-meld-usable-with-dark-themes.patch \
"

S = "${WORKDIR}/git"


inherit autotools-brokensep gobject-introspection gsettings

FILES_${PN} += "/usr \
		/usr/bin \
		/usr/lib \
		/usr/share \		
"

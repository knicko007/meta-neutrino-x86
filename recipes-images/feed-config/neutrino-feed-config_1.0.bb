DESCRIPTION = "Neutrino-HD image feed configuration"
# derived from poky-feed-config
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r1"
PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_DEFAULT_DEPS = "1"

do_compile() {
	mkdir -p ${S}/${sysconfdir}/opkg/

	basefeedconf=${S}/${sysconfdir}/opkg/base-feeds.conf
	rm -f $basefeedconf
	touch $basefeedconf
	if [ -n "${IPK_FEED_SERVER}" ]; then
		echo "# URI prefix '${IPK_FEED_SERVER}'" >> $basefeedconf
		echo "# is set by the IPK_FEED_SERVER variable." >> $basefeedconf
		echo "# Architectures which had no packages available" >> $basefeedconf
		echo "# at image creation time are commented out." >> $basefeedconf
	else
		echo "# set the IPK_FEED_SERVER variable during build to" >> $basefeedconf
		echo "# configure real feeds here." >> $basefeedconf
	fi
	echo "#" >> $basefeedconf

	ipkgarchs="all ${PACKAGE_ARCH} core2-64"
	for arch in $ipkgarchs; do
		FNAME="$arch"
		if [ -n "${IPK_FEED_SERVER}" ]; then
			URI="${IPK_FEED_SERVER}/$arch"
			printf "src/gz\t$FNAME\t$URI\n" >> $basefeedconf
		fi
	done
	# TODO: handle IPK_FEED_URIS?
}

do_install () {
	install -d ${D}${sysconfdir}/opkg
	install -m 0644  ${S}/${sysconfdir}/opkg/* ${D}${sysconfdir}/opkg/
}

FILES_${PN} = "${sysconfdir}/opkg/ "

CONFFILES_${PN} += "${sysconfdir}/opkg/base-feeds.conf"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS_append += "rtmpdump openssl virtual/libiconv"

SRC_URI_append += " \
		   file://0001-Revert-lavc-Switch-bitrate-to-64bit-unless-compatibi.patch;apply=no \
		   file://0002-add-HDS-ro_new.patch \
		   file://capture.sh \
"

PACKAGECONFIG ??= "avdevice avfilter avcodec avformat swscale swresample ffplay postproc \
                   bzlib gpl lzma librtmp libvorbis openssl theora x264 libroxml \
		   ffplay ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'x11 xv', '', d)} \
"
PACKAGECONFIG[libroxml] = "--enable-libroxml,--disable-libroxml,libroxml"
PACKAGECONFIG[librtmp] = "--enable-librtmp,--disable-librtmp,rtmpdump"


do_configure() {
    # We don't have TARGET_PREFIX-pkgconfig
    sed -i '/pkg_config_default="${cross_prefix}${pkg_config_default}"/d' ${S}/configure
    mkdir -p ${B}
    cd ${B}
    ${S}/configure ${EXTRA_OECONF}
    sed -i -e s:Os:O4:g ${B}/config.h
}

do_install_append() {
    install -m 0644 ${S}/libavfilter/*.h ${D}${includedir}/libavfilter/
    install -m 0755 ${WORKDIR}/capture.sh ${D}${bindir}
}

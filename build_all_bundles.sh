#!/bin/bash

function main {
	./build_bundle.sh releases.liferay.com/commerce/2.0.5/Liferay%20Commerce%202.0.5.lpkg releases.liferay.com/portal/7.1.3-ga4/liferay-ce-portal-tomcat-7.1.3-ga4-20190508171117552.7z
	./build_bundle.sh files.liferay.com/private/ee/commerce/2.0.5/Liferay%20Commerce%20Enterprise%202.0.5.lpkg files.liferay.com/private/ee/portal/7.1.10.1/liferay-dxp-tomcat-7.1.10.1-sp1-20190110085705206.zip files.liferay.com/private/ee/fix-packs/7.1.10/dxp/liferay-fix-pack-dxp-12-7110.zip
}

main
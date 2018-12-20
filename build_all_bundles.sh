#!/bin/bash

function main {
	./build_bundle.sh releases.liferay.com/commerce/1.0.2/Liferay%20Commerce%201.0.2.lpkg releases.liferay.com/portal/7.1.1-ga2/liferay-ce-portal-tomcat-7.1.1-ga2-20181112144637000.7z
	./build_bundle.sh files.liferay.com/private/ee/commerce/1.0.2/Liferay%20Commerce%20Enterprise%201.0.2.lpkg files.liferay.com/private/ee/portal/7.1.10/liferay-dxp-tomcat-7.1.10-ga1-20180703090613030.zip files.liferay.com/private/ee/fix-packs/7.1.10/dxp/liferay-fix-pack-dxp-4-7110.zip
}

main
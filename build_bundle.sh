#!/bin/bash

function date {
	if [ -z ${1+x} ] || [ -z ${2+x} ]
	then
		if [ "$(uname)" == "Darwin" ]
		then
			echo $(/bin/date)
		else
			echo $(/usr/bin/date)
		fi
	else
		if [ "$(uname)" == "Darwin" ]
		then
			echo $(/bin/date -jf "%a %b %e %H:%M:%S %Z %Y" "${1}" "${2}")
		else
			echo $(/usr/bin/date -d "${1}" "${2}")
		fi
	fi
}

function main {

	#
	# Make temporary directory.
	#

	local current_date=$(date)

	local timestamp=$(date "${current_date}" "+%Y%m%d%H%M")

	mkdir -p ${timestamp}

	#
	# Download and extract Portal.
	#

	local portal_bundle_url=http://mirrors.lax.liferay.com/${1}

	local portal_bundle_name=${portal_bundle_url##*/}

	curl -o ${timestamp}/${portal_bundle_name} ${portal_bundle_url}

	7z x -O${timestamp} ${timestamp}/${portal_bundle_name}

	rm ${timestamp}/${portal_bundle_name}

	if [[ ${portal_bundle_name} == *-dxp-* ]]
	then
		echo "Download fixpack."
	fi

	#
	# Rename Portal to Commerce directory.
	#

	local commerce_lpkg_url=http://mirrors.lax.liferay.com/${2}

	local commerce_version=${commerce_lpkg_url%/*}

	commerce_version=${commerce_version##*/}

	local commerce_bundle_name

	if [[ ${portal_bundle_name} == *-dxp-* ]]
	then
		commerce_bundle_name=liferay-commerce-enterprise-${commerce_version}
	else
		commerce_bundle_name=liferay-commerce-${commerce_version}
	fi

	mv ${timestamp}/liferay-* ${timestamp}/${commerce_bundle_name}

	#
	# Download Commerce.
	#

	local commerce_lpkg_url=http://mirrors.lax.liferay.com/${2}

	curl -o "${timestamp}/${commerce_bundle_name}/osgi/marketplace/Liferay Commerce.lpkg" ${commerce_lpkg_url}

	#
	# Start Tomcat.
	#

	start_tomcat ${timestamp}

	#
	# Build bundle.
	#

	cd ${timestamp}

	7z a -md1024m ${commerce_bundle_name}-${timestamp}.7z ${commerce_bundle_name}

	cd ..
}

function start_tomcat {
	local timestamp=${1}

	cp ${timestamp}/liferay-commerce-*/tomcat-*/bin/setenv.sh setenv.sh.bak

	printf "\nexport LIFERAY_CLEAN_OSGI_STATE=true" >> ${timestamp}/liferay-commerce-*/tomcat-*/bin/setenv.sh

	./${timestamp}/liferay-commerce-*/tomcat-*/bin/catalina.sh start

	until $(curl --head --fail --output /dev/null --silent http://localhost:8080)
	do
		sleep 3
	done

	./${timestamp}/liferay-commerce-*/tomcat-*/bin/catalina.sh stop

	sleep 20

	rm -fr ${timestamp}/liferay-commerce-*/logs/*
	rm -fr ${timestamp}/liferay-commerce-*/tomcat-*/logs/*

	mv setenv.sh.bak ${timestamp}/liferay-commerce-*/tomcat-*/bin/setenv.sh
}

main ${1} ${2}
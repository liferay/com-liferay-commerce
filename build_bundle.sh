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

function get_commerce_bundle_name {
	local commerce_version=${1%/*}

	commerce_version=${commerce_version##*/}

	local current_date=$(date)

	local timestamp=$(date "${current_date}" "+%Y%m%d%H%M")

	local commerce_bundle_name=liferay-commerce-${commerce_version}-${timestamp}.7z

	echo ${commerce_bundle_name}
}

function main {
	rm -fr dist

	mkdir -p dist

	local portal_bundle_url=https://releases.liferay.com/portal/7.1.1-ga2/liferay-ce-portal-tomcat-7.1.1-ga2-20181112144637000.7z

	local portal_bundle_name=${portal_bundle_url##*/}

	curl -o dist/${portal_bundle_name} ${portal_bundle_url}

	7z x -Odist dist/${portal_bundle_name}

	local commerce_lpkg_url=https://releases.liferay.com/commerce/1.0.2/Liferay%20Commerce%201.0.2.lpkg

	curl -o "dist/liferay-ce-portal-7.1.1-ga2/osgi/marketplace/Liferay Commerce.lpkg" ${commerce_lpkg_url}

	start_tomcat

	7z a -md1024m dist/$(get_commerce_bundle_name ${commerce_lpkg_url}) dist/liferay-ce-portal-7.1.1-ga2
}

function start_tomcat {
	./dist/liferay-*/tomcat-*/bin/catalina.sh start

	until $(curl --head --fail --output /dev/null --silent http://localhost:8080)
	do
		sleep 3
	done

	./dist/liferay-*/tomcat-*/bin/catalina.sh stop

	sleep 10

	rm -fr dist/liferay-*/tomcat-*/logs/*
}

main
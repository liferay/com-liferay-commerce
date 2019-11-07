#!/bin/bash

# Requires 7z and python to run.
# Run `fakelpkg` in your com-liferay-commerce repo, and it'll produce an LPKG based off your current codebase.

function fakelpkg() {
    name="Liferay Commerce.lpkg"

    api_artifacts=""
    impl_artifacts=""

    private_repo=../com-liferay-commerce-private

    if [ ! -e $private_repo ]
    then
        read -p 'Please enter the path of the private repo: ' private_repo
    fi

    # prebuild all necessary jars, and only the necessary jars
    gw -Dbuild.profile=portal clean jar

    cd $private_repo
    gw -Dbuild.profile=portal-private clean jar
    cd -

    # create list of artifacts for all modules flagged with marker file, in both public and private repos
    for lfrbuild_file in $(find . $private_repo -type f -name ".lfrbuild-portal*")
    do
        module="$(dirname $lfrbuild_file)"

        # test utils should be skipped, jars are in {module}/build/libs/, wars are in {module}/dist/
        if [[ "$module" =~ "-test-" ]]
        then
            artifact=""
        elif [ -e $module/build/libs ]
        then
            artifact="$module/build/libs/*.jar"
        else
            # assemble may fail for some jars, and produces extra jars: only run for wars
            cd $module
            gw assemble
            cd -

            artifact="$module/dist/*.war"

            # append version number if not already present or deployment will fail
            for war in $artifact
            do
                version=$(pyjson 'response["version"]' < $module/package.json)
                version=${version//\"/}

                if [[ ! "$war" =~ "$version" ]]
                then
                    mv $war ${war/.war/}-$version.war
                fi
            done
        fi

        # if bnd.bnd contains "Export-Package", put in API lpkg, otherwise in Impl lpkg
        if [ -e $module/bnd.bnd ] && grep -q "Export-Package" $module/bnd.bnd
        then
            api_artifacts="$api_artifacts $artifact"
        else
            impl_artifacts="$impl_artifacts $artifact"
        fi
    done

    api_lpkg_name="${name/.lpkg/ - API.lpkg}"
    impl_lpkg_name="${name/.lpkg/ - Impl.lpkg}"

    # create liferay-marketplace.properties for LPKG or deployment fails
    echo "category=Utility" > liferay-marketplace.properties
    echo "remote-app-id=110900894" >> liferay-marketplace.properties
    echo "context-names=commerce-speedwell-theme,minium-theme" >> liferay-marketplace.properties
    echo "version=2.0.6.custom" >> liferay-marketplace.properties
    echo "icon-url=https://web.liferay.com/web/guest/marketplace/-/mp/asset/icon/110900894/2.0.6" >> liferay-marketplace.properties
    echo "required=false" >> liferay-marketplace.properties
    echo "title=Liferay Commerce" >> liferay-marketplace.properties
    echo "restart-required=true" >> liferay-marketplace.properties

    # assemble
    7z a -tzip "$api_lpkg_name" liferay-marketplace.properties $api_artifacts
    7z a -tzip "$impl_lpkg_name" liferay-marketplace.properties $impl_artifacts
    7z a -tzip "$name" "$api_lpkg_name" "$impl_lpkg_name"

    # clean up
    rm "$api_lpkg_name" "$impl_lpkg_name" liferay-marketplace.properties
}

function findup() {
    target_file="$1"

    cur="$(pwd)"
    dir="$(pwd)/$2"

    attempts="$(echo "$dir" | grep -o / | wc -l)"

    while [ $attempts -gt 0 ] && [ ! -e "$dir/$target_file" ]
    do
        attempts=$(( $attempts - 1 ))
        dir="$(dirname $dir)"
    done

    if [ $attempts -eq 0 ]
    then
        echo "No $target_file file exists here or above."
    else
        echo "$dir/$target_file"
    fi
}

function gw() {
    gradlew="$(findup "gradlew")"

    "$gradlew" "$@"
}

function pyjson() {
    python -c "import sys,json; response=json.load(sys.stdin); print(json.dumps(${1:-response}, indent=4, sort_keys=True))";
}


fakelpkg

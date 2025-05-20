#!/bin/bash
echo "[DEBUG] current directory: $(pwd)"

ROOT_POM="pom.xml"
INTERNAL_MODULES=("shared-events")

# extract current version
CURRENT_VERSION=$(sed -n '0,/<version>/{s:.*<version>\(.*\)</version>.*:\1:p}' "$ROOT_POM")

if [[ -z "$CURRENT_VERSION" ]]; then
    echo "no version found in pom.xml"
    exit 1
fi

echo "[DEBUG] version found = $CURRENT_VERSION"

# get new version
if [[ "$CURRENT_VERSION" == *-SNAPSHOT ]]; then
    NEW_VERSION="${CURRENT_VERSION%-SNAPSHOT}"
    echo "[INFO] remove SNAPSHOT → $NEW_VERSION"
else
    BASE_VERSION=$(echo "$CURRENT_VERSION" | awk -F. '{print $1"."$2}')
    PATCH=$(echo "$CURRENT_VERSION" | awk -F. '{print $3}')
    PATCH=$((PATCH + 1))
    NEW_VERSION="$BASE_VERSION.$PATCH-SNAPSHOT"
    echo "[INFO] update SNAPSHOT → $NEW_VERSION"
fi

echo "[INFO] update portfolio-api-root pom.xml..."
sed -i "0,/<version>$CURRENT_VERSION<\/version>/s//<version>$NEW_VERSION<\/version>/" "$ROOT_POM"

# apply new version to all pom
for pom in $(find . -name "pom.xml" ! -path "./target/*" ! -path "./.*/pom.xml"); do
    echo "[INFO] update module of $pom"
    sed -i "0,/<version>$CURRENT_VERSION<\/version>/s//<version>$NEW_VERSION<\/version>/" "$pom"

    if [[ "$pom" != "./pom.xml" ]]; then
        
        # upate parent
        if grep -q "<artifactId>portfolio-api-root</artifactId>" "$pom"; then
            echo "[INFO] update parent $pom"
            sed -i "/<artifactId>portfolio-api-root<\/artifactId>/ {
                N; s/<version>$CURRENT_VERSION<\/version>/<version>$NEW_VERSION<\/version>/
            }" "$pom"
        fi

        # update internal module dependencies
        for module in "${INTERNAL_MODULES[@]}"; do
            if grep -q "<artifactId>shared-events</artifactId>" "$pom"; then
                echo "[INFO] update $module version in $pom"
                sed -i "/<artifactId>shared-events<\/artifactId>/ {
                    N; s/<version>$CURRENT_VERSION<\/version>/<version>$NEW_VERSION<\/version>/   
                }" "$pom"
            fi
        done
    fi
done

echo "[OK] updated all pom.xml from $CURRENT_VERSION to $NEW_VERSION"

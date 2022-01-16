#!/usr/bin/env bash
readonly SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
readonly UPDATE_DIR="$SCRIPT_DIR/../main/java/org/shirakumo/lichat/updates/"
readonly SPEC_DIR="$SCRIPT_DIR/spec/"
mvn -f "$SCRIPT_DIR/../../pom.xml" \
    compile exec:java -Dexec.mainClass="org.shirakumo.lichat.SpecGenerator" \
    -Dexec.args="$UPDATE_DIR $SPEC_DIR/lichat.sexpr $SPEC_DIR/shirakumo.sexpr"
